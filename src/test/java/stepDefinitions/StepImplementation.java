package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.SelectedProductPage;
import utils.BaseClass;

public class StepImplementation extends BaseClass{
	
	public LandingPage landingPageObj;
	public SelectedProductPage selectedProductPageObj;
	public CartPage cartPageObj;
	public List<String> itemPriceOnProductPage = new ArrayList<String>();;
	
	@Given("User landed on Amazon Home Page")
	public void User_landed_on_Amazon_Home_Page() throws FileNotFoundException, IOException {
		landingPageObj = launchApplication();
	}
	
	@Given("^User searched product (.+)$")
	public void User_searched_product(String productName) {
		landingPageObj.searchProduct(productName);
	}
	
	@When("^User selects product sequence (.+) from searched product list$")
	public void User_selects_product_sequence_from_searched_product_list(int productSequence) throws IOException {
		itemPriceOnProductPage.add(landingPageObj.getProductPriceFromProductPage(productSequence));
		selectedProductPageObj = landingPageObj.selectProduct(productSequence);
	}
	
	@And("User added the selected product to cart")
	public void User_added_the_selected_product_to_cart() {
		selectedProductPageObj.addProductToCart();
	}
	
	@Then("User verified the product price in the cart")
	public void User_verified_the_product_price_in_the_cart() throws IOException {
		cartPageObj = selectedProductPageObj.openCart();
		cartPageObj.comparePriceOnCartWithPriceOnProductPage(itemPriceOnProductPage);
		cartPageObj.verifyProductPriceWithCartSubTotal();
		closeBrowser();
	}
	

}
