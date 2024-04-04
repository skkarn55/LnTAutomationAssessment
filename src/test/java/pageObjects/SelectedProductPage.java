package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.AbstractComponents;

public class SelectedProductPage extends AbstractComponents{

	WebDriver driver;
	public SelectedProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath=".//*[contains(text(), 'Back to results')]")
	public WebElement backToResultsLink;
	
	@FindBy(css="input#add-to-cart-button")
	public WebElement addToCartButton;
	
	@FindBy(css="div#deliveryBlockMessage")
	public WebElement deliveryBlockMessageText; 
	
	@FindBy(css="div#contextualIngressPtLabel_deliveryShortLine")
	public WebElement changeLocationLink;
	
	
	
	@FindBy(css="span#attachSiNoCoverage-announce")
	public List<WebElement> noThanksButton;
	
	@FindBy(xpath=".//*[@id='sw-atc-confirmation']/descendant::h1")
	public WebElement addedToCartConfirmationText;
	
	@FindBy(id="nav-cart")
	public WebElement cartButton;
	
	public void addProductToCart() {
		waitForElementToBeVisible(backToResultsLink);
		waitForElementToBeClickable(addToCartButton);
		addToCartButton.click();
		waitForPageLoad();
		if(noThanksButton.size()>0) {
			clickOnElementUsingAction(noThanksButton.get(0));
		}
		waitForElementToBeVisible(addedToCartConfirmationText);
		Assert.assertTrue(addedToCartConfirmationText.getText().contains("Added to Cart"));
	}
	
	public CartPage openCart() {
		cartButton.click();
		return new CartPage(driver);
	}

}
