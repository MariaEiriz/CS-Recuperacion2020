package com.example.tetris.steps;

import com.example.tetris.utils.App;
import com.example.tetris.utils.SmokeTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class HUDef {
    private App app;
    @SmokeTest
    @Given("^We open the app$")
    public void weOpenTheApp() {
        this.app = new App();
    }

    @SmokeTest
    @When("^We start the game$")
    public void weStartTheGame() {
        app.start();
    }

    @SmokeTest
    @Then("^We see an activity Screen$")
    public void weSeeAnActivityScreen() {
        assertTrue(app.isStarted());
    }

}
