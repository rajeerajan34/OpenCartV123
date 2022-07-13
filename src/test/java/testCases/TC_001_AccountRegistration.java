//Added comment on 11th July 2022
package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{	
	@Test(groups={"regression","master"})
	public void test_account_registration() throws IOException
	{   
		logger.info("Starting TC_001_AccountRegistration");
		try
		{
			
		//driver.get("http://localhost/opencart/upload/"); 
			
		//reading from config file
		driver.get(rb.getString("appURL"));
		logger.info("Home Page Displayed ");
		
		driver.manage().window().maximize();
		
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked My Account");
		
		hp.clickRegister();
		logger.info("Clicked Register ");
		
		logger.info("Providing Customer Details");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName("John");
		logger.info("Provided Customer first name");
		
		regpage.setLastName("Canedy");
		logger.info("Provided Customer last name");
		
		regpage.setEmail(randomestring()+"@gmail.com");
		//regpage.setEmail("abc123gmail.com");
		logger.info("Provided Customer Email");
		
		regpage.setTelephone("65656565");
		logger.info("Provided Customer telephone no");
		
		regpage.setPassword("abcxyz");
		regpage.setConfirmPassword("abcxyz");
		logger.info("Provided password & confirmation password");
		
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		logger.info("Clicked on continue button");
		
		String confmsg=regpage.getConfirmationMsg();
		logger.info("Validation started");
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Registration test passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Registration test failed");
			captureScreen(driver, "test_account_Registration"); //Capturing screenshot
			Assert.assertTrue(false);
		}
	  }
	  catch(Exception e)
		{
		  logger.fatal("Registration test failed");
		  captureScreen(driver, "test_account_Registration"); //Capturing screenshot
		  Assert.fail();
		}
		
		logger.info(" Finished TC_001_AccountRegistration ");
	}
}