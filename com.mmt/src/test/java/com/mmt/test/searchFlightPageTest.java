package com.mmt.test;

import com.mmt.page.Base;
import com.mmt.page.searchFlightPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class searchFlightPageTest extends Base {
    private static final Logger logger = LogManager.getLogger(searchFlightPageTest.class);
    public WebDriver driver = initializeDriver();
    WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(20));
    searchFlightPage searchFlight = new searchFlightPage(driver);
    String expectedMmtUrl = "https://www.makemytrip.com/";

    @Test(priority = 1)
    public void redirectToHomePage() throws IOException {
        try {
            driver.get(expectedMmtUrl);
            String actualMmtUrl = driver.getCurrentUrl();

            if (actualMmtUrl.contains(expectedMmtUrl)) {
                System.out.println("Successfully Landed on the Home Page");
                Assert.assertTrue(true, "Landed on the Home Page successfully.");
            } else {
                Assert.fail("Failed to land on the Home Page.");
            }
        } catch (Exception mainException) {
            Base.TakeScreenshot("redirectToHomePage");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 2)
    public void closeModal() throws IOException {
        boolean visiblityOfMoal = false;
        try {
            WebElement closeModal = searchFlight.getClosedModal();
            wt.until(ExpectedConditions.visibilityOf(closeModal));
            if (closeModal.isDisplayed()) {
                visiblityOfMoal = true;
            }
            if (visiblityOfMoal) {
                WebElement getModalText = searchFlight.getModalText();
                wt.until(ExpectedConditions.visibilityOfElementLocated(searchFlight.modalText));
                closeModal.click();
                System.out.println("Successfully Closed the Modal:-" + getModalText);
            }

        } catch (Exception mainException) {
            Base.TakeScreenshot("closeModal");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception" + mainException.getMessage());
        }
    }

    @Test(priority = 3)
    public void verifyFromCity() throws IOException {
        try {
            WebElement fromCity = searchFlight.getFromCity();
            wt.until(ExpectedConditions.elementToBeClickable(searchFlight.fromCity));
            fromCity.click();
            List<WebElement> fromCityDrpDownValue = searchFlight.getFromCityDrpDownvalue();
            wt.until(ExpectedConditions.elementToBeClickable(searchFlight.fromCityDrpDownvalue));
            int totalSizeList = fromCityDrpDownValue.size();
            System.out.println("Total Elements Presented In, From City Dropdown: " + totalSizeList);

            for (int i = 0; i < totalSizeList; i++) {
                if (fromCityDrpDownValue.get(i).getText().contains("New Delhi")) {
                    System.out.println("Selected, From City: " + fromCityDrpDownValue.get(i).getText());
                    fromCityDrpDownValue.get(i).click();
                    break;
                }
            }
        } catch (Exception mainException) {
            Base.TakeScreenshot("verifyFromCity");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifyToCity() throws IOException {
        try {
            WebElement toCity = searchFlight.getToCity();
            wt.until(ExpectedConditions.elementToBeClickable(searchFlight.toCity));
            toCity.click();
            List<WebElement> toCityDrpDownValue = searchFlight.getToCityDrpDownvalue();
            wt.until(ExpectedConditions.elementToBeClickable(searchFlight.toCityDrpDownvalue));
            int totalSizeList = toCityDrpDownValue.size();
            System.out.println("Total Elements Presented, In To City Dropdown: " + totalSizeList);

            for (int i = 0; i < totalSizeList; i++) {
                if (toCityDrpDownValue.get(i).getText().contains("Mumbai")) {
                    System.out.println("Selected, To City: " + toCityDrpDownValue.get(i).getText());
                    toCityDrpDownValue.get(i).click();
                    break;
                }
            }

            try {
                WebElement calendarModal = searchFlight.getCalendarModal();
                wt.until(ExpectedConditions.visibilityOf(calendarModal));
                if (calendarModal.isDisplayed()) {
                    logger.info("Calendar Modal Displayed");
                    WebElement calendarBody = searchFlight.getCalendarTitle();
                    wt.until(ExpectedConditions.visibilityOfElementLocated(searchFlight.calendarTitle));
                    String expectedTitle = calendarBody.getText();
                    String actualTitle = "Book round trip for great savings";
                    if (actualTitle.contains(expectedTitle)) {
                        System.out.println("Modal Title: " + expectedTitle);
                        LocalDate currentDate = LocalDate.now();
                        System.out.println("currentDate:-" + currentDate);
                        LocalDate nextDate = currentDate.plusDays(1);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String formattedNextDate = nextDate.format(formatter);
                        System.out.println("formattedNextDate:-" + formattedNextDate);
                        String splitFormattedNextDate = formattedNextDate.substring(0, 2);
                        System.out.println("Split DD-MM-YYYY and Pick only Day:- " + splitFormattedNextDate);

                        List<WebElement> selectNextDate = searchFlight.getNextDate();
                        wt.until(ExpectedConditions.elementToBeClickable(searchFlight.selectNextDate));
                        int sizeOfDaysElement = selectNextDate.size();
                        System.out.println("Total Number of Days Elements:- " + sizeOfDaysElement);

                        for (int i = 0; i < sizeOfDaysElement; i++) {

                            if (selectNextDate.get(i).getText().contains(splitFormattedNextDate)) {
                                System.out.println("Next Date Selected:- " + selectNextDate.get(i).getText());
                                selectNextDate.get(i).click();
                                break;
                            }

                        }

                    }
                }

            } catch (Exception childException) {
                Base.TakeScreenshot("calendarModal");
                logger.error("Failed due to Exception: " + childException.getMessage());
                Assert.fail("Failed due to Exception: " + childException.getMessage());

            }
        } catch (Exception mainException) {
            Base.TakeScreenshot("verifyToCity");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }


    @Test(priority = 5)
    public void verifySearchButton() throws IOException {
        try {
            WebElement searchButton = searchFlight.getSearchButton();
            wt.until(ExpectedConditions.elementToBeClickable(searchFlight.searchButton));
            if (searchButton.isDisplayed()) {
                System.out.println("Search Button is displayed and clicked.");
                searchButton.click();
            }
        } catch (Exception mainException) {
            Base.TakeScreenshot("verifySearchButton");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }

    @Test(priority = 6)
    public void verifySearchResultList() throws IOException {
        String expectedResult = "Cheapest";
        boolean visibilityOfModal = false;

        wt.until(ExpectedConditions.presenceOfElementLocated(searchFlight.closeListingModal));
        try {
            driver.switchTo().parentFrame();
            WebElement listingModal = searchFlight.getCloseListingModal();
            wt.until(ExpectedConditions.presenceOfElementLocated(searchFlight.closeListingModal));
            String modalText = listingModal.getText();
            System.out.println("Listing Modal Text: " + modalText);

            if (listingModal.isDisplayed()) {
                visibilityOfModal = true;
            }

            if (visibilityOfModal) {
                WebElement listingModalText = searchFlight.getListingModalText();
                wt.until(ExpectedConditions.visibilityOfElementLocated(searchFlight.listingModalText));
                listingModal.click();
                System.out.println("Successfully Closed the Modal.");
            }

            try {
                WebElement searchListFilter = searchFlight.getCheapestFilter();
                wt.until(ExpectedConditions.visibilityOfElementLocated(searchFlight.cheapestFilter));
                String actualResult = searchListFilter.getText();

                if (actualResult.contains(expectedResult)) {
                    System.out.println("Successfully Reached the flight Listing Page.");
                }

                try {
                    List<WebElement> flightElements = searchFlight.getSearchListElements();
                    int totalFlightList = flightElements.size();
                    wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchFlight.searchListElements));

                    List<WebElement> searchFlightsPrice = searchFlight.getSearchFlightsPrice();
                    int totalFlightPriceList = searchFlightsPrice.size();

                    List<Integer> prices = new ArrayList<>();

                    for (int i = 0; i < totalFlightPriceList; i++) {
                        String priceText = searchFlightsPrice.get(i).getText();
                        System.out.println("Total Flight Prices: " + priceText);
                        int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
                        prices.add(price);
                    }

                    Collections.sort(prices);
                    int secondLowestPrice = prices.get(1);

                    for (int i = 0; i < totalFlightList; i++) {
                        int price = Integer.parseInt(searchFlightsPrice.get(i).getText().replaceAll("[^0-9]", ""));

                        if (price == secondLowestPrice) {
                            String airlineName = flightElements.get(i).getText();
                            System.out.println("####--Printing The Airline name and Price Having Second Lowest Price--###");
                            System.out.println("Airline Name and Price: " + airlineName + " : " + price);
                            break; // Exit the loop once the 2nd lowest price is found
                        }
                    }
                } catch (Exception childException) {
                    Base.TakeScreenshot("searchList");
                    logger.error("Failed due to Exception: " + childException.getMessage());
                    Assert.fail("Failed due to Exception: " + childException.getMessage());
                }
            } catch (Exception childException) {
                Base.TakeScreenshot("searchListFilter");
                logger.error("Failed due to Exception: " + childException.getMessage());
                Assert.fail("Failed due to Exception: " + childException.getMessage());
            }
        } catch (Exception mainException) {
            Base.TakeScreenshot("verifySearchResultList");
            logger.error("Failed due to Exception: " + mainException.getMessage());
            Assert.fail("Failed due to Exception: " + mainException.getMessage());
        }
    }


}


