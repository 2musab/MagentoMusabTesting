package homePage;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCase {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https:magento.softwaretestingboard.com/";
	String logoutPage = "https:magento.softwaretestingboard.com/customer/account/logout/";
	Random rand = new Random();

	String pass = "iLoveMyMom!234k";

	String[] firstNames = { "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona", "George", "Hannah", "Ian",
			"Jasmine" };
	int firstNamesLength = firstNames.length;
	int randomFirstName = rand.nextInt(firstNamesLength);

	String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
			"Taylor" };

	int lastNamesLength = lastNames.length;
	int randomLastName = rand.nextInt(lastNamesLength);

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get("https:magento.softwaretestingboard.com/");
	}

	@Test(priority = 1, enabled = false)
	public void CreateAnAccount() {

		WebElement createAccount = driver
				.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));
		createAccount.click();
	}

	@Test(priority = 2, enabled = false)
	public void infoRegister() {

		String RandomName = firstNames[randomFirstName] + lastNames[randomLastName];

		WebElement FirstName = driver.findElement(By.id("firstname"));
		WebElement LastName = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email_address"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement confirmPass = driver.findElement(By.id("password-confirmation"));
		WebElement button = driver.findElement(By.cssSelector("button[title='Create an Account']"));

		FirstName.sendKeys(firstNames[randomFirstName]);
		LastName.sendKeys(lastNames[randomLastName]);
		email.sendKeys(RandomName + rand.nextInt(999) + "@gmail.com");
		password.sendKeys(pass);
		confirmPass.sendKeys(pass);
		button.click();

		String actualMessage = "Thank you for registering with Main Website Store.";
		String expectedMessage = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"))
				.getText();

		Assert.assertEquals(actualMessage, expectedMessage);

	}

	@Test(priority = 3, enabled = false)
	public void addMenItem() throws InterruptedException {

		WebElement menSection = driver.findElement(By.id("ui-id-5"));
		menSection.click();

		WebElement menContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> menContainerList = menContainer.findElements(By.tagName("li"));

		int menContainerListSize = menContainerList.size();

		System.out.println("men items container : " + menContainerListSize);
		int randomItemPicker = rand.nextInt(menContainerListSize);

		WebElement randomMenItem = menContainerList.get(randomItemPicker);
		randomMenItem.click();

		WebElement sizes = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizesList = sizes.findElements(By.tagName("div"));
		int sizesListNumber = sizesList.size();
		System.out.println("size number : " + sizesListNumber);
		int randomSize = rand.nextInt(sizesListNumber);
		WebElement randomSizeSelector = sizesList.get(randomSize);
		randomSizeSelector.click();

		WebElement colorPicker = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> colorPickerList = colorPicker.findElements(By.tagName("div"));
		int colorPickerListSize = colorPickerList.size();
		System.out.println("Color number : " + colorPickerListSize);
		int randomColorPicker = rand.nextInt(colorPickerListSize);
		WebElement randomColorPickerList = colorPickerList.get(randomColorPicker);
		randomColorPickerList.click();

		WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
		addToCartButton.click();

		Thread.sleep(5000);

		WebElement MessageAdded = driver.findElement(By.className("message-success"));

		System.out.println(MessageAdded.getText().contains("You added"));

		Assert.assertEquals(MessageAdded.getText().contains("You added"), true);

	}

	@Test(priority = 4, enabled = true)
	public void addWomen() throws InterruptedException {

		WebElement womenSection = driver.findElement(By.id("ui-id-4"));
		womenSection.click();

		WebElement womenItems = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> womenItemsList = womenItems.findElements(By.tagName("li"));
		int womenItemsListSize = womenItemsList.size();
		int RandomWomenItemPicker = rand.nextInt(womenItemsListSize);
		WebElement randomWomenItemSelector = womenItemsList.get(RandomWomenItemPicker);
		randomWomenItemSelector.click();

		WebElement sizeContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		List<WebElement> sizeContainerList = sizeContainer.findElements(By.tagName("div"));
		int sizeContainerListSize = sizeContainerList.size();
		int randomSizeContainerListSize = rand.nextInt(sizeContainerListSize);
		WebElement randomSizeSelector = sizeContainerList.get(randomSizeContainerListSize);
		randomSizeSelector.click();

		WebElement womenColors = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> womenColorsList = womenColors.findElements(By.tagName("div"));
		int womenColorsListSize = womenColorsList.size();
		int randomWomenColorsList = rand.nextInt(womenColorsListSize);
		WebElement womenRandomSelector = womenColorsList.get(randomWomenColorsList);
		womenRandomSelector.click();

		WebElement addToCart = driver.findElement(By.id("product-addtocart-button"));
		addToCart.click();

		Thread.sleep(5000);

		WebElement MessageAdded = driver.findElement(By.cssSelector(".message-success.success.message"));

		System.out.println(MessageAdded.getText().contains("You added"));

		Assert.assertEquals(MessageAdded.getText().contains("You added"), true);

		WebElement rateTab = driver.findElement(By.id("tab-label-reviews-title"));
		rateTab.click();

		WebElement rateLabel = driver.findElement(By.cssSelector(".control.review-control-vote"));
		int starsNumberSize = rateLabel.findElements(By.tagName("label")).size();

		String[] id = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };

		WebElement randomStar = driver.findElement(By.id(id[rand.nextInt(starsNumberSize)]));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", randomStar);

		WebElement nickName = driver.findElement(By.id("nickname_field"));
		nickName.sendKeys(firstNames[randomFirstName] + " " + lastNames[randomLastName]);

		WebElement summary = driver.findElement(By.id("summary_field"));
		summary.sendKeys("My rating");

		WebElement reviewField = driver.findElement(By.id("review_field"));
		reviewField.sendKeys("My review is automated");

		WebElement submit = driver.findElement(By.cssSelector("button[class='action submit primary']"));
		submit.click();

		String actualSubmit = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"))
				.getText();
		String expectedSubmit = "You submitted your review for moderation.";

		Assert.assertEquals(actualSubmit, expectedSubmit);

	}

}
