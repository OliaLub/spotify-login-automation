package com.epam.training.olha_haichenkova.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SpotifyLoginPage extends AbstractPage {

    private static final String LOGIN_URL = "https://accounts.spotify.com/";
    private static final String LOGIN_INVALID_MESSAGE_XPATH = "//div[@data-testid='username-error']";
    private static final String INCORRECT_CREDENTIALS_BANNER = "//div[@data-encore-id='banner']";

    @FindBy(xpath = "//input[@data-testid='login-username']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@data-testid='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = LOGIN_INVALID_MESSAGE_XPATH)
    private WebElement invalidLoginMessage;

    @FindBy(xpath = "//div[@data-testid='password-error']")
    private WebElement invalidPasswordMessage;

    @FindBy(xpath = INCORRECT_CREDENTIALS_BANNER)
    private WebElement incorrectNameOrPasswordBanner;

    @FindBy(xpath = "//button[@data-testid='login-button']")
    private WebElement loginButton;

    public SpotifyLoginPage(WebDriver driver) {
        super(driver);
    }

    public SpotifyLoginPage inputLogin(String login){
        waitToBeClickable(loginField);
        loginField.clear();
        loginField.sendKeys(login);
        return this;
    }

    public SpotifyLoginPage clearLoginField(){
        loginField.sendKeys(Keys.CONTROL + "a");
        loginField.sendKeys(Keys.DELETE);
        return this;
    }

    public SpotifyLoginPage inputPassword(String password){
        waitToBeClickable(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }
    public SpotifyLoginPage clearPasswordField(){
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);
        return this;
    }

    public SpotifyLoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public String loginInvalidMessage(){
        waitToBePresent(By.xpath(LOGIN_INVALID_MESSAGE_XPATH));
        return invalidLoginMessage.getText();
    }

    public String passwordInvalidMessage(){
        return invalidPasswordMessage.getText();
    }

    public SpotifyUserPage logIn(String login, String password){
        inputLogin(login);
        inputPassword(password);
        clickLoginButton();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(LOGIN_URL)));
        return new SpotifyUserPage(driver);
    }

    public String getIncorrectCredentialsBanner(){
        waitToBePresent(By.xpath(INCORRECT_CREDENTIALS_BANNER));
        return incorrectNameOrPasswordBanner.getText();
    }

    @Override
    public SpotifyLoginPage openPage() {
        driver.get(LOGIN_URL);
        waitToBeClickable(loginButton);
        return this;
    }

}
