package org.sec.application.crawler;

import net.vidageek.crawler.PageCrawler;
import net.vidageek.crawler.config.CrawlerConfiguration;
import net.vidageek.crawler.visitor.DoesNotFollowVisitedUrlVisitor;

/**
 * Created by Administrator on 2014/5/30.
 */
public class SecCrawler {

    public static void main(String[] args) {
        String url = "http://cn.bing.com/images?FORM=Z9LH";
        CrawlerConfiguration cfg = new CrawlerConfiguration(url);
        PageCrawler crawler = new PageCrawler(cfg);
        crawler.crawl(new DoesNotFollowVisitedUrlVisitor(url, new SecVisitor(true)));
    }

}
