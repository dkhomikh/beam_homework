package com.beamtest.apitest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class WeatherApiTest {
	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		  	driver = new SafariDriver();
		    baseUrl = "http://openweathermap.org/current";
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  }
	
	@After
	public void tearDown() throws Exception {
		 driver.close();
		 driver.quit();
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		driver.get(baseUrl);
		driver.navigate().to("http://api.openweathermap.org/data/2.5/weather?q=chicago,us&appid=b6b831f8b8bae6ade07a224d2953746a");
		//appid=key, was generated after i signed in, it is not not static!
		WebElement webElement=driver.findElement(By.tagName("pre"));
		WeatherApiResponse weatherApiResponse=new WeatherApiResponse();
		String ExpectedString=weatherApiResponse.GetResponse();
		System.out.println("Result from get: " + webElement.getText());
		Assert.assertTrue(webElement.getText(), equals(ExpectedString));
	}

}


