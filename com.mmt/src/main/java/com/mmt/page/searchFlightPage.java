package com.mmt.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class searchFlightPage {
    private final WebDriver driver;
    public By closedHomePageModal = By.xpath("//*[contains(@class, 'Modal__close')]");
    public By modalText = By.xpath("//*[contains(@placeholder, 'mobile number')]");
    public By fromCity = By.id("fromCity");
    public By fromCityDrpDownvalue = By.xpath("//div[contains(@class, 'suggestions-container')]/div/ul/li/div/div/p[1]");
    public By toCity = By.id("toCity");
    public By toCityDrpDownvalue = By.xpath("//div[contains(@class, 'react-autosuggest')]/div/ul/li/div/div/p[1]");
    public By calendarModal = By.xpath("//div[@class='RangeExample oneWay']");
    public By calendarTitle = By.xpath("//div[@class='RangeExample oneWay']/div/div/div[3]/p/span");
    public By selectNextDate = By.xpath("//div[@class='DayPicker-Month'][1]/div[3]/div/div[contains(@aria-disabled,'false') and not (contains(@aria-disabled,'true'))]/div/p");
    public By searchButton = By.xpath("//*[contains(@class, 'widgetSearchBtn') and text()='Search']");
    public By cheapestFilter = By.xpath("//div[contains(@class, 'clusterTabsHead')]/div[1]/div/p[1]");
    public By listingModalText = By.xpath("//div[contains(@class,'commonOverlay')]/div/div/p[contains(text(), 'Now Lock Prices & Pay Later!')]");
    public By closeListingModal = By.xpath("//div[contains(@class, 'commonOverlay')]/span");

    public By searchListElements = By.xpath("//*[contains(@class, 'airlineName')]");
    public By searchFlightsPrice = By.xpath("//*[contains(@class, 'clusterViewPrice')]");




    public searchFlightPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getClosedModal(){
        return driver.findElement(closedHomePageModal);
    }

    public WebElement getModalText(){
        return driver.findElement(modalText);
    }

    public WebElement getFromCity(){
        return driver.findElement(fromCity);
    }

    public List<WebElement> getFromCityDrpDownvalue(){
        return driver.findElements(fromCityDrpDownvalue);
    }

    public WebElement getToCity(){
        return driver.findElement(toCity);
    }

    public List<WebElement> getToCityDrpDownvalue(){
        return driver.findElements(toCityDrpDownvalue);
    }
    public WebElement getCalendarModal(){
        return driver.findElement(calendarModal);
    }

    public WebElement getCalendarTitle(){
        return driver.findElement(calendarTitle);
    }

    public List <WebElement> getNextDate(){
        return driver.findElements(selectNextDate);
    }

    public WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }
    public List <WebElement> getSearchListElements(){
        return driver.findElements(searchListElements);
    }
    public WebElement getCheapestFilter(){
        return driver.findElement(cheapestFilter);
    }
    public WebElement getCloseListingModal(){
        return driver.findElement(closeListingModal);
    }

    public WebElement getListingModalText(){
        return driver.findElement(listingModalText);
    }

    public List<WebElement> getSearchFlightsPrice(){
        return driver.findElements(searchFlightsPrice);
    }















}


