package com.av;

import org.testng.annotations.Test;

public class TraversingGoogle extends Base {

    @Test
    private void traversingGoogle() {
        utils().findByRoleAsLink("AI Mode").click();
    }

}
