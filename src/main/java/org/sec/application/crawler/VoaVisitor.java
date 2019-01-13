package org.sec.application.crawler;

import net.vidageek.crawler.Page;
import net.vidageek.crawler.PageVisitor;
import net.vidageek.crawler.Status;
import net.vidageek.crawler.Url;
import org.sec.utils.HttpClient4Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouwei
 * @since 2017/7/21
 */
public class VoaVisitor implements PageVisitor {

    private static Logger logger = LoggerFactory.getLogger(VoaVisitor.class);

    private static String domain = "http://www.51voa.com";
    static String beginUrl = domain + "/VOA_Special_English/";

    VoaVisitor() {
    }

    @Override
    public boolean followUrl(Url url) {
        String urlStr = url.link();
        logger.info("followUrl {}", urlStr);
        if (urlStr.matches(".*/VOA_Special_English/.*html$")) {
            return true;
        } else if (urlStr.endsWith(".mp3")) {
            downloadMp3(url.link());
            return false;
        }
        return false;
    }

    @Override
    public void visit(Page page) {
        // do nothing
    }

    @Override
    public void onError(Url errorUrl, Status statusError) {
        logger.error("get url error [url={}, status={}]", errorUrl, statusError.name());
    }

    private void downloadMp3(String url) {
        try {
            HttpClient4Util.download(url, "D:/voa");
            logger.info("download {} finished", url);
        } catch (Exception e) {
            logger.error("download {} error", url, e);
        }
    }
}
