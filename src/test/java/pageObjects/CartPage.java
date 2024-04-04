package pageObjects;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath=".//*[@data-name='Active Cart']/descendant::h1")
	public WebElement shoppingCartHeader;
	
	@FindBy(xpath=".//*[contains(@class, 'a-row sc-list-item')]")
	public List<WebElement> addedProductList; 
	
	@FindBy(xpath="//*[@id='sc-subtotal-amount-buybox']/span")
	public WebElement cartSubTotal;
	
	public String getCartSubTotal() {
		return cartSubTotal.getText();
	}
	
	public List<String> getAllProductsPriceOnCart() throws IOException{
		waitForElementToBeVisible(shoppingCartHeader);
		List<String> addedProductsPriceList = new ArrayList<String>();
		System.out.println("Total items added = "+addedProductList.size());
		for(WebElement product:addedProductList) {
			System.out.println("Added Product = "+product);
		}
		for(WebElement product:addedProductList) {
			String productCost = getItemCostUsingJavascript(product.findElement(By.xpath("//*[contains(@class, 'a-link-normal')] //*[contains(@class, 'a-truncate-cut')]")));
			addedProductsPriceList.add(productCost);
		}
		return addedProductsPriceList;
	}
	
	public void comparePriceOnCartWithPriceOnProductPage(List<String> priceOnProductPage) throws IOException {
		List<String> pricesOnCart = getAllProductsPriceOnCart();
		for(int i=0; i<pricesOnCart.size();i++) {
			Assert.assertTrue(pricesOnCart.get(i).equals(priceOnProductPage.get(i)), "Price on Cart is NOT same as Price on Product");
		}
		
	}
	
	public void verifyProductPriceWithCartSubTotal() throws IOException {
		List<String> pricesOnCart = getAllProductsPriceOnCart();
		DecimalFormat decFor = new DecimalFormat("0.00");
		Double sum =0.0;
		for(String price:pricesOnCart)
			sum+=Double.parseDouble(price.substring(1, price.length()));
		String cartSubTotal = getCartSubTotal();
		Assert.assertTrue(cartSubTotal.substring(1, cartSubTotal.length()).equals(decFor.format(sum)), "Sum of the item prices on cart is NOT equal to the cart subtotal");
		
		
	}
	
}
