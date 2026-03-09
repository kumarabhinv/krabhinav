package com.av;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class Utils {

    private Locator locator;
    private final Page page;

    public Utils (Page page) {
        this.page = page;
    }

    public Utils findByRoleAsLink(String linkName) {
        this.locator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(linkName));
        return this;
    }

    public void click () {
        locator.click();
    }

}
