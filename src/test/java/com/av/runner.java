package com.av;

import org.testng.annotations.Test;

public class runner extends base {

    @Test
    public void runner () {
        launch("chrome");
        get("ibm.com").screenshot();
        System.out.println(jsonReader(isArray("xpath", "src/main/resources/locators.locators"), 1, "xpath"));
        findByXpath(jsonReader(isArray("xpath", "src/main/resources/locators.locators"), 1, "xpath")).click();
        screenshot();
        screenshot("ibm_screenshot_");
    }

}
