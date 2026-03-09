package com.av;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Base {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeClass
    public void playInit() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
    }

    @BeforeMethod
    public void launch() {
        browserContext = browser.newContext(new Browser.NewContextOptions().setBaseURL("https://www.google.com"));
        page = browserContext.newPage();
        page.navigate("");
    }

    @AfterMethod
    private void destroyContext() {
        if (browserContext != null) {
            browserContext.close();
        }
    }

    @AfterClass
    private void destroy() {
        if (playwright != null) {
            playwright.close();
        }
    }

    protected Utils utils () {
        return new Utils(page);
    }

}
