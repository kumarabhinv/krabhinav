package com.av;

import org.testng.annotations.Test;

public class runner extends base {

    @Test
    public void runner () {
        launch("chrome");
        get("ibm.com").screenshot();
    }

}
