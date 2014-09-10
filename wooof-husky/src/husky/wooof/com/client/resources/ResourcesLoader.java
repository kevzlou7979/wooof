package husky.wooof.com.client.resources;

import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.CssResource;

public class ResourcesLoader {
    private static final int MOBILE_MAX_WIDTH = 600;
    private static final String MOBILE_MEDIA = "@media only screen and (max-width: " + MOBILE_MAX_WIDTH + "px), " +
            "only screen and (max-device-width: " + MOBILE_MAX_WIDTH + "px) {";
    private static final String CLOSING_BRACKET = "}";

    public ResourcesLoader(HuskyResources resources) {
        resources.huskycss().ensureInjected();
        injectMediaCss(resources);
    }
 
    private void injectMediaCss(HuskyResources resources) {
        injectMobileStylesheet(resources.huskymobilecss());
    }
 
    private void injectMobileStylesheet(CssResource cssResource) {
        String mobileCss = wrapMobileStylesheet(cssResource.getText());
        StyleInjector.injectAtEnd(mobileCss);
    }
 
    private String wrapMobileStylesheet(String content) {
        return MOBILE_MEDIA + content + CLOSING_BRACKET;
    }
}
