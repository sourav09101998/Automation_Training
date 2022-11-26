#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Scenario for the OrangeHRM Application

  @OrangeLogin
  Scenario: Enter OrangeHRM Page
	    Given I am on OrangeHRM Login Page
	    When I enter <UserName> and <Password> for Login OrangeHRM
	    And I Login
	    Then The application should display Dashboard page

	@OrangeLogin @UsingDT	
	Scenario: Enter OrangeHRM Page - By Login - DT
			Given I am on OrangeHRM Login Page
			When I login for below credentials 
						|userName|password|
						|Admin	 |admin123|
			And I Login
			Then The application should display Dashboard Page
			
	@OrangeAdmin
	Scenarios: Go to Admin page
			Given I am on Dashboard Page
			When I click on Admin Button
			Then The application should display Admin Page
	
	@DisplayTableRecord
	Scenarios: Display all the record in console
			Given I am on Admin Page
			When I traversing each row and coloum of table
			Then 
	

	
	
