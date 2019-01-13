package org.sec.application.crawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.lang3.StringUtils;
import org.sec.core.concurrent.SecExecutor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by secret on 14-6-30.
 */
public class SecHtmlUtil {

    public static final String getImg(String url) {
        SecExecutor executor = SecExecutor.init(50);
        try {
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.getOptions().setCssEnabled(false);
            client.getOptions().setThrowExceptionOnScriptError(false);
            HtmlPage page = client.getPage(url);
//            DomNodeList<DomElement> elements = page.getElementsByTagName("img");
//            elements.stream().forEach(e -> {
//                executor.execute(() -> {
//                    if (isImageSizeOK(e.getAttribute("width"), e.getAttribute("height")))
//                        new ImageDownloader("/opt/myImg").downloadImage(e.getAttribute("src"));
//                });
//            });
            String pageContent = page.asXml();
            Pattern pattern = Pattern.compile("http://[^\\s^\\{^\\}]+\\.(jpg|png)", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(pageContent);
            while (matcher.find()) {
                String src = pageContent.substring(matcher.start(), matcher.end());
                executor.execute(() -> {
                    new ImageDownloader("F:\\DownloadImages").downloadImage(src);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("html unit error");
//            try {
//                Source source = new Source(HttpUtil.request(url));
//                List<StartTag> tags = source.getAllStartTags();
//                tags.stream().forEach(tag -> {
//                    if (tag.getName().equalsIgnoreCase("img")) {
//                        executor.execute(() -> {
//                            Element element = tag.getElement();
//                            if (isImageSizeOK(element.getAttributeValue("width"), element.getAttributeValue("height")))
//                                new ImageDownloader("/opt/myImg").downloadImage(element.getAttributeValue("src"));
//                        });
//                    }
//                });
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
        }

        executor.destroy();
        return null;
    }

    @SuppressWarnings("unused")
	private static boolean isImageSizeOK(String widthStr, String heightStr) {
        try {
            if (StringUtils.isNotBlank(widthStr) && StringUtils.isNotBlank(heightStr)) {
                int widthIndex = widthStr.indexOf("px");
                int heightIndex = heightStr.indexOf("px");
                int width = (widthIndex == -1 ? Integer.parseInt(widthStr) : Integer.parseInt(widthStr.substring(0, widthIndex)));
                int height = (heightIndex == -1 ? Integer.parseInt(heightStr) : Integer.parseInt(heightStr.substring(0, heightIndex)));
                if (width >= 200 && height >= 200)
                    return true;
                else
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
