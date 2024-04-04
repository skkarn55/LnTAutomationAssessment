package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="nav-link-accountList-nav-line-1")
	public WebElement signInButton;
	
	@FindBy(css="input#twotabsearchtextbox")
	public List<WebElement> searchProductTextBox1;
	
	@FindBy(css="input#nav-bb-search")
	public WebElement searchProductTextBox2;
	
	@FindBy(css="input#nav-search-submit-button")
	public WebElement searchProductButton1;
	
	@FindBy(css="input.nav-bb-button")
	public WebElement searchProductButton2;
	
	@FindBy(xpath=".//h5[contains(text(), 'International Shopping Transition Alert')]")
	public WebElement internationalShoppingAlert;
	
	@FindBy(xpath=".//span[contains(text(), 'Change Address')]")
	public WebElement changeAddressButton;
	
	@FindBy(css="input#GLUXZipUpdateInput")
	public WebElement zipInputTextBox;
	
	@FindBy(css="span#GLUXZipUpdate-announce")
	public WebElement applyNewZipCodeButton;
	
	@FindBy(css="input#GLUXConfirmClose")
	public WebElement zipCodeUpdateConfirmationButton;
	
	@FindBy(xpath=".//*[text()='Results']")
	public WebElement resultText;
	
	
	@FindBy(css="a#nav-global-location-popover-link span#glow-ingress-line2")
	public WebElement deliveryLocation;
	
	@FindBy(xpath=".//*[contains(@class, 'sg-col-20-of-24 s-result-item')]")
	public List<WebElement> productList;
	
	@FindBy(xpath="//*[@class='a-price']")
	public WebElement productPrice;
	
	
	public void searchProduct(String productName) {
		if(searchProductTextBox1.size()>0) {
			searchProductTextBox1.get(0).sendKeys(productName);
			searchProductButton1.click();
		}
		else{
			searchProductTextBox2.sendKeys(productName);
			searchProductButton2.click();
		}
	}
	
	public String getProductPriceFromProductPage(int productNum) {
		waitForElementToBeVisible(deliveryLocation);
		if(!deliveryLocation.getText().contains("00501")) {
			waitForPageLoad();
			clickOnElementUsingAction(changeAddressButton);
			updateZipCode();	
		}
		waitForPageLoad();
		String actualItemPrice = getItemCostUsingJavascript(productList.get(productNum).findElement(By.xpath("//*[@class='a-price']/span[contains(@class, 'a-offscreen')]")));
		return actualItemPrice;
	}
	
	public SelectedProductPage selectProduct(int productNum){
		clickOnElementUsingJavascriptExecutor(productList.get(productNum).findElement(By.xpath("//h2/a[contains(@class,'a-link-normal')]")));
		return new SelectedProductPage(driver);
	}
	
	private void updateZipCode() {
		waitForElementToBeVisible(zipInputTextBox);
		zipInputTextBox.sendKeys("00501");
		clickOnElementUsingAction(applyNewZipCodeButton);
		try {
			zipCodeUpdateConfirmationButton.click();
		}
		catch(StaleElementReferenceException e) {
			clickOnElementUsingJavascriptExecutor(zipCodeUpdateConfirmationButton);
		}
	}

}
