package runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
@RunWith(Cucumber.class)
@CucumberOptions( 
				plugin ={
						"html:html_reports.html" ,
						"json:src/Reportss/json-reports.json" ,
						"junit:src/Reportss/cucumber-reports.xml",
						// "rerun:report_output/rerun.txt",
						// "pretty:pretty-report.log", 
						// "html:target/jsonReports/html-report.html",
						// "json:target/jsonReports/json-report.json" ,
						// "junit:target/jsonReports/junit-report.xml"
						},
				 features = "src/test/resources/Feature",
				 glue={"stepDefinition"}
 )
public class TestRunners {

		@AfterClass
		public static void after()  throws Exception{
			File reportOutputDirectory = new File("Reportss/");
        List<String> jsonFiles = new ArrayList<String>();
        jsonFiles.add("src/Reportss/json-reports.json");

        String buildNumber = "1";
        String projectName = "CucumberProject";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.addClassifications("Branch", "Master");
        configuration.setSortingMethod(SortingMethod.NATURAL);
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
        // configuration.setQualifier("sample", "Chrome 80, mobile");
        // points to the demo trends which is not used for other tests
        // configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
		}
}
