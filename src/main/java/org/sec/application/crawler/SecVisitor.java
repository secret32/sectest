package org.sec.application.crawler;

import net.vidageek.crawler.Page;
import net.vidageek.crawler.PageVisitor;
import net.vidageek.crawler.Status;
import net.vidageek.crawler.Url;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2014/6/9.
 */
public class SecVisitor implements PageVisitor {

    private Logger logger = Logger.getLogger(SecVisitor.class);

    private boolean followUrl = false;

    public SecVisitor(boolean followUrl) {
        this.followUrl = followUrl;
    }

    @Override
    public void visit(Page page) {
        SecHtmlUtil.getImg(page.getUrl());
    }

    @Override
    public void onError(Url errorUrl, Status statusError) {
        logger.error("onError:" + errorUrl.link() + " statusError=" + statusError);
    }

    @Override
    public boolean followUrl(Url url) {
        return followUrl;
    }
}
