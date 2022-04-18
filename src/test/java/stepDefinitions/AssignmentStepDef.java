package stepDefinitions;



import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentStepDef {
	
	WebDriver driver;
	
	@Given("user is on saucedemo homepage")
	public void user_is_on_saucedemo_homepage() {
		
		WebDriverManager.chromedriver().setup();
		
	     driver = new ChromeDriver();
	     driver.get("https://www.saucedemo.com/");    
	     driver.manage().window().maximize();
	}
	
	@Given("user logged in using correct credential")
	public void user_logged_in_using_correct_credential(DataTable dataTable) {
	    
		List<Map<String, String>> login = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> loginDetails : login) {
			driver.findElement(By.id("user-name")).sendKeys(loginDetails.get("username"));
			driver.findElement(By.id("password")).sendKeys(loginDetails.get("password"));
			driver.findElement(By.id("login-button")).click();
			
		}
	}

	@Given("user adds required item to cart")
	public void user_adds_required_item_to_cart() {
	    
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();
	}

	@Given("user proceeds to checkout")
	public void user_proceeds_to_checkout() {
	   driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	   driver.findElement(By.id("checkout")).click();
	}

	@Given("user enters the following details for checkout")
	public void user_enters_the_following_details_for_checkout(DataTable dataTable) {
		List<Map<String, String>> checkout = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> checkoutDetails : checkout) {
			
			driver.findElement(By.id("first-name")).sendKeys(checkoutDetails.get("FirstName"));
			driver.findElement(By.id("last-name")).sendKeys(checkoutDetails.get("LastName"));
			driver.findElement(By.id("postal-code")).sendKeys(checkoutDetails.get("PostalCode"));			
			
		}
	    
	}

	@When("user confirm checkout")
	public void user_confirm_checkout() {
		 driver.findElement(By.id("continue")).click();
		 driver.findElement(By.id("finish")).click();
	}

	@Then("user verify final confirmation message")
	public void user_verify_final_confirmation_message() {
		
		String expectedConfirmation = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		
		String confirmationMessage = driver.findElement(By.xpath("//*[contains(text(), 'Your order has been dispatched, and will arrive just as fast as the pony can get there!')]")).getText();
		
		 assertEquals(expectedConfirmation, confirmationMessage);
		 driver.quit();
	    
	}
	
	@Given("user adds one item and then remove that item to go back")
	public void user_adds_one_item_and_then_remove_that_item_to_go_back() {
		
		driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
		driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
	   
		
	}
	
	@Given("user sorts item low to high")
	public void user_sorts_item_low_to_high() {
		
		Select select = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
		select.selectByVisibleText("Price (low to high)");
	   
		
	}



}
