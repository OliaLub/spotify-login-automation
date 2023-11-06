package com.epam.training.olha_haichenkova.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpotifyUserPage extends AbstractPage {

    private static final String USER_NAME_ATTRIBUTE = "aria-label";

    @FindBy(xpath = "//button[@data-testid='user-widget-link']")
    private WebElement userWidgetLink;

    @FindBy(xpath = "//button[@data-testid='user-widget-dropdown-logout']")
    private WebElement logOutButton;

    public SpotifyUserPage(WebDriver driver) {
        super(driver);
    }

    public SpotifyMainPage logOut(){
        userWidgetLink.click();
        waitToBeClickable(logOutButton);
        logOutButton.click();
        return new SpotifyMainPage(driver);
    }

    public String receiveUserName(){
        waitToBeClickable(userWidgetLink);
        return userWidgetLink.getAttribute(USER_NAME_ATTRIBUTE);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
