package runners;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import reportgenerate.GenerateNewReports;
@RunWith(Cucumber.class)
@CucumberOptions( 
				plugin ={
						"html:html_reports.html" ,
						"json:src/Reportss/json-reports.json" ,
						"junit:src/Reportss/cucumber-reports.xml",
						// "rerun:report_output/rerun.txt",
						},
				 features = "src/test/resources/Feature",
				 glue={"stepDefinition"}
 )
public class TestRunners {
		@AfterClass
		public static void after()  throws Exception{
			GenerateNewReports.generateNewReport().generateReports();
		}
}
