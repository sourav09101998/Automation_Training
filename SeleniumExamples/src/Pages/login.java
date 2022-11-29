package Pages;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class login {
	
	// Create a WebDriver reference variable. 
    WebDriver driver;

    By userName = By.name("uid");
    By password = By.name("password");
    By titleText =By.className("barone");
    By login = By.name("btnLogin");

 // Declare a constructor and pass WebDriver reference as an input parameter. 
    public login(WebDriver driver){

        this.driver = driver;

    }
    
    // Declare methods to perform operations on the elements. 
    //Set username in textbox
    public void setUserName(String strUserName){
        driver.findElement(userName).sendKeys(strUserName);
    }
    
    //Set password in password textbox
    public void setPassword(String strPassword){
         driver.findElement(password).sendKeys(strPassword);
    }
    
    //Click on login button
    public void clickLogin(){
            driver.findElement(login).click();
    }

    //Get the title of Login Page
    public String getLoginTitle(){
    	return driver.findElement(titleText).getText();
    }

   
    public void loginTo(String strUserName,String strPasword){

        //Fill user name
        this.setUserName(strUserName);

        //Fill password
        this.setPassword(strPasword);

        //Click Login button
        this.clickLogin();        
    }

}