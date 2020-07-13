package com.example.tetris.steps;

import com.example.tetris.utils.App;
import com.example.tetris.utils.SmokeTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;
public class HUDef {
    private App app;

    @SmokeTest
    @Given("^We open the app$")
    public void weOpenTheApp() {
        this.app = new App();
    }

    @SmokeTest
    @When("^We start the app")
    public void weStartTheGame() {
        app.start();
    }
    @SmokeTest
    @Then("^We see an initial Screen$")
    public void weSeeAnInitialScreen() {
        assertTrue(app.isStarted());
    }

    @SmokeTest
    @When("^We start modo clasico$")
    public void weStartModoClasico() {
        app.startModoClasico();
    }
    @SmokeTest
    @When("^We start muerte subita$")
    public void weStartMuerteSubita() {
        app.startMuerteSubita();
    }
    @SmokeTest
    @Then("^We are playing modo clasico$")
    public void weArePlayingModoClasico() {
        assertTrue(app.isStartModoClasico());
    }
    @SmokeTest
    @Then("^We are playing muerte subita$")
    public void weArePlayingMuerteSubita() {
        assertTrue(app.isStartMuerteSubita());
    }
    @SmokeTest
    @When("^We put the last piece$")
    public void wePutTheLastPiece() {
        app.colocarUltimaPieza();
    }

    @SmokeTest
    @Then("^We are game over$")
    public void weAreGameOver() {
        assertTrue(app.isGameOver());
    }
    @SmokeTest
    @Given("^We Open Settings$")
    public void weOpenSettings() {
        this.app.startSettings();
    }
    @SmokeTest
    @When("^We change any color piece$")
    public void weChangeColors() {
        app.setColors("Red", "Red",null, "Pink",null,null, null);
    }

    @SmokeTest
    @Then("^We change settings$")
    public void weChangeSettings() {
        assertTrue(app.isSettingsChanged());
    }
}
