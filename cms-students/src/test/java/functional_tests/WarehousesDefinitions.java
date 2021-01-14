package gradle.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.Given;
import cucumber.runtime.ScenarioImpl;
import env.DriverUtil;
import info.seleniumcucumber.methods.BaseTest;
import info.seleniumcucumber.methods.TestCaseFailed;

public class PredefinedStepDefinitions implements BaseTest {
	
    //Drivers
    protected WebDriver driver = DriverUtil.getDefaultDriver();
	

    //Create Warehouse

	//Step to navigate to specified URL
	@Given("^I navigate to \"([^\"]*)\"$")
	public void navigate_to(String link) throws Exception
	{
		navigationObj.navigateTo(link);
	}

	// click on web element
	@When("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}


	// click on web element
	@And("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}
	// click on web element
	@And("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}

	// click on web element
	@And("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}


	// click on web element
	@Then("^I click on element having (.+) \"(.*?)\"$")
	public void click(String type,String accessName) throws Exception
	{
		miscmethodObj.validateLocator(type);
		clickObj.click(type, accessName);
	}


	public final void tearDown() {
		DriverUtil.closeDriver();
	}
}
