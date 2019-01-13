package org.sec.application.crawler;

import net.vidageek.crawler.PageCrawler;
import net.vidageek.crawler.config.CrawlerConfiguration;

/**
 * @author zhouwei
 * @since 2017/7/21
 */
public class VoaCrawler {

    public static void main(String[] args) {
        CrawlerConfiguration cfg = new CrawlerConfiguration(VoaVisitor.beginUrl);
        PageCrawler crawler = new PageCrawler(cfg);
        crawler.crawl(new VoaVisitor());
    }

}
