package com.epam.training.olha_haichenkova;

import com.epam.training.olha_haichenkova.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver driver;

    @BeforeEach
    public void setUpDriver(){
        driver = DriverSingleton.getDriver();
    }

    @AfterEach
    public void tearDownDriver(){
        DriverSingleton.closeDriver();
    }

}
