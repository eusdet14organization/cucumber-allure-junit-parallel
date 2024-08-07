package com.eurotech.actors;

import com.eurotech.context.TestContext;
import com.eurotech.pages.plain.PlainMainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageActor {
    PlainMainPage pmp;
    TestContext context;

    public MainPageActor(TestContext context){
        this.context = context;
        pmp = new PlainMainPage(context);
    }

    public MainPageActor assertFooterTextContains(String expectedText){
        assertTrue(pmp.footer.getText().contains(expectedText));
        return this;
    }

    public String getFooterText(){
        return pmp.getFooterText();
    }
}
