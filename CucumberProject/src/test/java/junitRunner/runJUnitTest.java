package junitRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(               features="classpath:features/gmo.feature", // pick from src/test/resources
                glue= {"StepDef"}, //pick from src/test/java
                tags= "@GMOHome or (@catalog and @UsingDT) or @PlaceOrder or (@Billing and @UsingDT) or @Receipt",
                plugin = {"pretty",  // produce verbose report                                
                                "html:target/cucumber-report/report.html", //generate HTML here
                                "json:target/cucumber-report/cucumber.json", //to be post processed by user/other tools
                                "junit:target/cucumber-reprts/CucumberJunit.xml"}, //can be used to generate visual reports by user/other tools                                
                monochrome=true,  //readable output in console, default false
                dryRun=false
                )
public class runJUnitTest {}