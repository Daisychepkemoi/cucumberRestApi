package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( plugin ={
						"pretty:pretty-report.log", 
						"html:target/jsonReports/html-report.html",
						"json:target/jsonReports/json-report.json" ,
						"junit:target/jsonReports/junit-report.xml"
						},
				 features = "src/test/resources/Feature"
				 ,glue={"stepDefinition"}
 )

 
public class TestRunners {
	
}
