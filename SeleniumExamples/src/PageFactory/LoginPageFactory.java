package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {

	WebDriver driver;

//	 All WebElements are identified by @FindBy annotation

	@FindBy(name = "uid")WebElement userName;

	@FindBy(name = "password")WebElement password;

//	@FindBy(className = "barone")WebElement titleText;
	@FindBy(how = How.CLASS_NAME, using = "barone")WebElement titleText;
	
	@FindBy(name = "btnLogin")WebElement login;

	// Declare a constructor and pass WebDriver reference as an input parameter. 
	public LoginPageFactory(WebDriver driver) {

		this.driver = driver;
		
		/*
		 * The initElements() method of PageFactory class is used to initialize WebElements declared in the page class. 
		 * It is a static method. That’s why we call it using the class name. 
		 * It is called inside a constructor that will automatically initialize all the elements in the page class when it is instantiated.
		 */
		
		// Call initElements() method by using PageFactory reference and pass driver and this as parameters. 
		PageFactory.initElements(driver, this);

	}
	
	// Declare methods to perform operations on the elements.
	// Set user name in textbox
	public void setUserName(String strUserName) {
		userName.sendKeys(strUserName);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	// Click on login button
	public void clickLogin() {
		login.click();
	}

	// Get the title of Login Page
	public String getLoginTitle() {
		return titleText.getText();
	}

	public void loginTo(String strUserName, String strPasword) {

		// Fill user name
		this.setUserName(strUserName);

		// Fill password
		this.setPassword(strPasword);

		// Click Login button
		this.clickLogin();

	}

}
