package com.hospital.management;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HospitalSeleniumTest {

    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void registerPatientTest() {

        driver.get("http://localhost:8081");

        driver.findElement(By.id("name"))
                .sendKeys("Rahul");

        driver.findElement(By.id("age"))
                .sendKeys("25");

        driver.findElement(By.id("disease"))
                .sendKeys("Fever");

        driver.findElement(By.id("registerButton"))
                .click();

        String message = driver.findElement(
                By.id("successMessage")
        ).getText();

        assertTrue(
                message.contains("Patient Registered Successfully!")
        );

        String patientName = driver.findElement(
                By.id("patientName")
        ).getText();

        assertEquals("Rahul", patientName);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
