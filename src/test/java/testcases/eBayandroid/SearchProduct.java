package testcases.eBayandroid;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import screens.eBay.CheckOutScreen;
import screens.eBay.HomeScreen;
import screens.eBay.ProductReviewScreen;
import screens.eBay.ProductSearchScreen;
import screens.eBay.SignInScreen;
import utility.TestUtil;

public class SearchProduct extends TestBase {

	HomeScreen home;
	SignInScreen signIn;
	ProductSearchScreen productSearch;
	ProductReviewScreen productReview;
	CheckOutScreen checkOut;

	@BeforeTest
	public void init() {

		home = new HomeScreen(driver);
		signIn = new SignInScreen(driver);
		productSearch = new ProductSearchScreen(driver);
		productReview = new ProductReviewScreen(driver);
		checkOut = new CheckOutScreen(driver);
	}

	@Test(priority = 1, dataProvider = "getData")
	public void searchProduct(String userName, String password, String searchKey, String brand) {
		try {
			home.setCounty();
			home.tapOnSignIn();
			signIn.enterUserName(userName);
			signIn.enterPassword(password);
			signIn.tapOnLogin();
			home.validateSignIn();
			home.searchForProduct(searchKey);
			productSearch.chooseBrand(brand);
			productSearch.selectTheProduct();
			productSearch.buyTheProduct();
			productReview.tapOnReview();
			checkOut.validateItemDetails();

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("SearchProduct");

	}

}
