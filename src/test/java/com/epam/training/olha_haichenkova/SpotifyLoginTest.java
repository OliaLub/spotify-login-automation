package com.epam.training.olha_haichenkova;

import com.epam.training.olha_haichenkova.page.SpotifyLoginPage;
import com.epam.training.olha_haichenkova.page.SpotifyMainPage;
import com.epam.training.olha_haichenkova.page.SpotifyUserPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SpotifyLoginTest extends BaseTest{

    private static final String EMPTY_LOGIN_MESSAGE = "Укажіть ім’я користувача Spotify або адресу електронної пошти.";
    private static final String EMPTY_PASSWORD_MESSAGE = "Введіть пароль.";

    @DisplayName("UC -3 - Test Login form with correct credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/validCredentials.csv")
    public void enterCorrectLoginAndPassword(String login, String password, String name){
        SpotifyUserPage spotifyUserPage = new SpotifyMainPage(driver)
                .openPage()
                .openLoginForm()
                .logIn(login, password);
        String loggedInUserName = spotifyUserPage.receiveUserName();
        spotifyUserPage.logOut();
        Assertions.assertEquals(name, loggedInUserName,
                String.format("The user name was expected as: %s, but actual is: %s!", name,loggedInUserName));
    }

    @DisplayName("UC-2 - Test Login form with incorrect credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/invalidCredentials.csv")
    public void enterOneInvalidCredential(String login, String password, String errorMessage){
        SpotifyLoginPage spotifyLoginPage = new SpotifyMainPage(driver)
                .openPage()
                .openLoginForm()
                .inputLogin(login)
                .inputPassword(password)
                .clickLoginButton();

        String message = spotifyLoginPage.getIncorrectCredentialsBanner();
        Assertions.assertEquals(errorMessage, message,
                String.format("The error message was expected as: %s, but actual is: %s!", errorMessage,message));
    }

    @DisplayName("UC -1 - Test Login form with empty login credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/validCredentials.csv")
    public void enterEmptyLoginAndValidPassword(String login, String password){
        SpotifyLoginPage spotifyLoginPage = new SpotifyMainPage(driver)
                .openPage()
                .openLoginForm()
                .inputLogin(login)
                .clearLoginField()
                .inputPassword(password)
                .clickLoginButton();
        String message = spotifyLoginPage.loginInvalidMessage();
        Assertions.assertEquals(EMPTY_LOGIN_MESSAGE, message,
                String.format("The error message was expected as: %s, but actual is: %s!", EMPTY_LOGIN_MESSAGE,message));
    }

    @DisplayName("UC -1 - Test Login form with empty password credentials")
    @ParameterizedTest
    @CsvFileSource(resources = "/validCredentials.csv")
    public void enterValidLoginAndEmptyPassword(String login, String password){
        SpotifyLoginPage spotifyLoginPage = new SpotifyMainPage(driver)
                .openPage()
                .openLoginForm()
                .inputLogin(login)
                .inputPassword(password)
                .clearPasswordField()
                .clickLoginButton();
        String message = spotifyLoginPage.passwordInvalidMessage();
        Assertions.assertEquals(EMPTY_PASSWORD_MESSAGE, message,
                String.format("The error message was expected as: %s, but actual is: %s!", EMPTY_LOGIN_MESSAGE,message));
    }

}
