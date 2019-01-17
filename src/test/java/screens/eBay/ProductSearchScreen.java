package screens.eBay;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ScreenBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductSearchScreen extends ScreenBase {

	//Identify all the elements related to Product search screen
	
	@AndroidFindBy(id = "com.ebay.mobile:id/pill_item")
	public List<WebElement> brandNames;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/recycler")
	public WebElement productSearchResut;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/button_bin")
	public WebElement buyNowButton;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/textview_item_name")
	public WebElement itemName;
	
	@AndroidFindBy(id = "com.ebay.mobile:id/textview_item_price")
	public WebElement itemPrice;
	

	public ProductSearchScreen(AndroidDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);

	}

	//Method to choose Brand and navigate to Product search result screen
	
	public void chooseBrand(String requiredBrand) throws Exception {
		try {
			
			if (!(brandNames.isEmpty()) && brandNames != null) {
			
				for (int i = 0; i < brandNames.size(); i++) {
					String name = brandNames.get(i).findElements(By.id(("com.ebay.mobile:id/textview_item_title")))
							.get(i).getText();
					if (!(name.isEmpty())) {
						if (requiredBrand.equalsIgnoreCase(name)) {
							brandNames.get(i).click();
							break;
						}
					} else {
						throw new Exception("Brand Name is empty");
					}
				}
				
			} else {
				throw new Exception("Brand Names list is empty");
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
	

	// Method to choose the Product of choice for Purchasing
	
	
	public void selectTheProduct() throws Exception {
		
		if(ScreenBase.swipeVertically(productSearchResut)){
			List<WebElement> productsList=productSearchResut.findElements(By.id("com.ebay.mobile:id/cell_collection_item"));
			if(!(productsList.isEmpty()) && productsList!=null) {
			productsList.get(2).click();
			if(!(itemName.getText().isEmpty()) && !((itemPrice.getText().isEmpty()))){
				
				ScreenBase.setProperty("ItemName", itemName.getText().toString());
				System.out.println(Double.parseDouble((itemPrice.getText().toString()).replaceAll("[^\\d.]", "")));
				ScreenBase.setProperty("ItemPrice",Double.parseDouble((itemPrice.getText().toString()).replaceAll("[^\\d.]", "")));
			}
			
			}else {
				throw new Exception ("No Products are displayed");
			}
		}else {
			throw new Exception("Swipe failed");
		}
		
		
	}
	
	//Method to buy the Product and navigate to product review screen
	
	public void buyTheProduct() throws Exception {
		
		if(buyNowButton.isDisplayed()) {
			buyNowButton.click();
		}else {
			Assert.fail("Buy now button is not displayed");
		}
	}
	
	
	
}
