package org.sec.application.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by zhou_wei on 2014/12/25.
 */
public class SearchTest {

    private static Directory directory = null;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        index("D:\\workspace\\v1.0\\code\\backend_system\\css-manage\\src");
        System.out.println("Create or update indexes finished in " + (System.currentTimeMillis() - t1) + "ms");
        try (Scanner in = new Scanner(System.in)) {
            String line = null;
            while (true) {
                if (line == null)
                    System.out.println("Key word for search:");
                line = in.nextLine();
                if (line == null)
                    continue;
                line = line.trim();
                if (line.length() == 0)
                    continue;
                else if (line.equalsIgnoreCase("exit"))
                    return;
                search(line);
                line = null;
            }
        }
    }

    private static void search(String key) {
        if (directory == null) return;
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(indexReader);
            Analyzer analyzer = new SmartChineseAnalyzer();
            QueryParser parser = new QueryParser("content", analyzer);
            Query query = parser.parse(key);
            TopDocs topDocs = searcher.search(query, 5);
            System.out.println("Total found: " + topDocs.totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document document = searcher.doc(scoreDoc.doc);
                System.out.println(document.get("path"));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void index(String dir) {
        Analyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        directory = new RAMDirectory();
        try {
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            indexDocs(indexWriter, dir);
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void indexDocs(IndexWriter indexWriter, String dir) {
        if (dir == null) throw new IllegalArgumentException("dir can not be null");
        File file = new File(dir);
        if (file.exists()) {
            if (file.isDirectory()) {
                String[] files = file.list();
                assert files != null;
                Arrays.stream(files).forEach(f -> indexDocs(indexWriter, dir + File.separator + f));
            } else {
                Document doc = new Document();
                doc.add(new StringField("path", dir, Field.Store.YES));
                doc.add(new LongPoint("lastModified", file.lastModified()));
                try {
                    doc.add(new TextField("content", new BufferedReader(new InputStreamReader(new FileInputStream(dir), StandardCharsets.UTF_8))));
                    if (indexWriter.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                        indexWriter.addDocument(doc);
                    } else {
                        indexWriter.updateDocument(new Term("path", dir), doc);
                    }
                } catch (IOException ignore) {
                }
            }
        } else {
            throw new IllegalArgumentException("dir[" + dir + "] does not exists");
        }
    }
}
