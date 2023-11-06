package com.epam.training.olha_haichenkova.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SpotifyMainPage extends AbstractPage {

    private static final String BASE_URL = "https://open.spotify.com/";

    @FindBy(xpath = "//button[@data-testid='login-button']")
    private WebElement loginButton;

    public SpotifyMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SpotifyMainPage openPage() {
        driver.get(BASE_URL);
        waitToBeClickable(loginButton);
        return this;
    }

    public SpotifyLoginPage openLoginForm(){
        loginButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(BASE_URL)));
        return new SpotifyLoginPage(driver);
    }

}
