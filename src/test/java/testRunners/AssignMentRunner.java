package testRunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;





@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/features/Assignment.feature"},
		glue = {"stepDefinitions"},
		
				plugin = {"pretty" , "json:target/MyReports/reports.json",
						"junit:target/MyReports/report.xml"},
		monochrome = true //This is to remove special characters in console output
)		
public class AssignMentRunner {

}
