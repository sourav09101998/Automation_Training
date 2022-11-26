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

Feature: Scenario for the GMO Application

		@GMOHome
    Scenario: Enter GMO online
        Given I am on gmo home page
        When I enter gmo online
        Then The application should display catalog page
		
		@catalog
    Scenario: Online catalog - product selection
        Given Online catalog page is displayed
        When I place order for 5 qty of 3 Person Dome Tent
        Then Place Order page is displayed with the selected items
		
		@catalog @Usingso		
		Scenario Outline: Online catalog - product selection -SO
				Given Online catalog page is displayed 
				When I select "<orderQty>" quantity of "<itemDesc>" 
				Then I should see items "<itemDesc>" selected with quantity "<orderQty>"
				Examples:
					| orderQty | itemDesc |
					|				2 |	External Frame Backpack |
					|				4 | Padded Socks |
					|				2 | Hiking Boots |

		  @catalog @Usingso
			Scenario: Online catalog - place order - SO 
					Given I have selected the items on catalog page
					When I place order for the items 
					Then Place Order page is displayed with the selected items
			
			@catalog @UsingDT
			Scenario: Online catalog - product selection - DT
					Given Online catalog page is displayed
					When I place order for below items
							| orderQty | itemDesc |
							|				10 |	Hiking Boots |
							|				4	 |3 Person Dome Tent | 
					Then Place Order page is displayed with the selected items
	
			@catalog @UsingEDS
			Scenario: Online catalog product selection - DT 
					Given Online catalog page is displayed
					When I place order for items from this file "src/test/resources/testData/data.csv"
					Then Place Order page is displayed with the selected items
	
			@PlaceOrder
	    Scenario: Place the Order
	        Given Place order page is displayed
	        When the I proceeed to place order with the selected item
	        Then I should be taken to billing info page to provice my address
	
			@Billing 
	    Scenario: Provide billing address and checkout
	        Given I am on Billing page
	        When I input correct billing address
	        And I checkout
	        Then I should be taken to receipt page
			
			@Billing @UsingDT
			Scenario: Provide billing address and checkout
					Given I am on Billing page
					When I input below billing address
					|billName|billAddress|billCity|billState|billZipCode|billPhone	|billEmail			|CardNumber			|CardDate|
					|Amdocs  | Pune			 | Pune		|Maha			|12345			|1234567890|amdocs@amdocs.com|123456789012345|12/25 |
					And I checkout
					Then I should be taken to receipt page
			
			@Receipt
	    Scenario: confirm receipt and return to GMO home page
	        Given I am in Receipt page
	        When I opt to return to home page
	        Then I should be navigated to GMO home page
	        
	        