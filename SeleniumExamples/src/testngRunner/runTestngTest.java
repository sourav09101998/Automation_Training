package testngRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions
(		features={"src/test/resources/features/dog.feature"}, // pick from src/test/resources
                glue= {"StepDef1"}, //pick from src/test/java
                tags= "",
                plugin = {"pretty",  // produce verbose report                                
                		 "html:target/cucumber-report/report.html", //generate HTML here
                         "json:target/cucumber-report/cucumber.json", //to be post processed by user/other tools
                         "junit:target/cucumber-report/CucumberJunit.xml",
//                         "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                         "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                         }, //can be used to generate visual reports by user/other tools                                   
                monochrome=true,  //readable output in console, default false
                dryRun=false
                )
public class runTestngTest extends AbstractTestNGCucumberTests{}