import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://codenboxautomationlab.com/practice/";

	Random rand = new Random();

	@BeforeTest
	public void Setup() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1)
	public void RadioButtons() {

		WebElement divForRadio = driver.findElement(By.id("radio-btn-example"));
		divForRadio.findElements(By.tagName("input"));

		int RandomIndex = rand.nextInt(divForRadio.findElements(By.tagName("input")).size());

		WebElement selectedInput = divForRadio.findElements(By.tagName("input")).get(RandomIndex);

		selectedInput.click();

		boolean ActualResult = selectedInput.isSelected();
		boolean ExpectedResult = true;
		Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 2)
	public void DynamicDropDown() throws InterruptedException {
		String[] myRandomTwoCharacter = { "AB", "EA", "GH", "IJ", "KL", "MO", "OP" };

		int RandomIndex = rand.nextInt(myRandomTwoCharacter.length);
		String myInputData = myRandomTwoCharacter[RandomIndex];

		WebElement autoCompleteInput = driver.findElement(By.id("autocomplete"));

		autoCompleteInput.sendKeys(myInputData);
		Thread.sleep(1000);
		autoCompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String dataInsideMyInput = (String) js.executeScript("return arguments[0].value", autoCompleteInput);

		String updatedData = dataInsideMyInput.toLowerCase();
		boolean ActualResult = updatedData.contains(myInputData.toLowerCase());

		Assert.assertEquals(ActualResult, true);

	}

	@Test(priority = 3)
	public void SelectTag() {
		WebElement mySelectElement = driver.findElement(By.id("dropdown-class-example"));

		Select selector = new Select(mySelectElement);

		int RandomIndex = rand.nextInt(1, 4);

		selector.selectByIndex(RandomIndex);

	}

	@Test(priority = 4)
	public void CheckBox() {
		WebElement divForCheckBox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheckBox = divForCheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {
			myCheckBox.get(i).click();
		}

	}

	@Test(priority = 5)
	public void SwitchToWindow() throws InterruptedException {
		WebElement openWindowElement = driver.findElement(By.id("openwindow"));
		openWindowElement.click();

		Thread.sleep(1000);

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		// Tried two examples of finding the element, once by the css selector, second
		// by the correct form/way to write the xpath
		// driver.findElement(By.cssSelector(".menu-item.menu-item-type-custom.menu-item-object-custom.menu-item-9675")).click();
		driver.findElement(By.xpath("//a[@href='https://codenbox.com/technologies/']")).click();

		Thread.sleep(3000);

		driver.close();

		driver.switchTo().window(windowHandles.get(0));

		Thread.sleep(1000);

		WebElement divForCheckBox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheckBox = divForCheckBox.findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < myCheckBox.size(); i++) {
			myCheckBox.get(i).click();
		}

	}

	@Test(priority = 6)
	public void SwitchToTab() throws InterruptedException {
		WebElement openTabElement = driver.findElement(By.id("opentab"));
		openTabElement.click();

		Thread.sleep(1000);

		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		WebElement playButtonToPause = driver.findElement(By.cssSelector(".ytp-play-button.ytp-button"));
		playButtonToPause.click();

		Thread.sleep(3000);
		driver.close();

		driver.switchTo().window(windowHandles.get(0));

	}

	@Test(priority = 7)
	public void Alert() throws InterruptedException {
		WebElement inputField = driver.findElement(By.id("name"));
		inputField.sendKeys("asmaa");

		WebElement confirmAlertButton = driver.findElement(By.id("confirmbtn"));
		confirmAlertButton.click();

		Thread.sleep(2000);

		boolean ActualResult = driver.switchTo().alert().getText().contains("asmaa");
		boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);
		driver.switchTo().alert().accept();
	}

	@Test(priority = 8)
	public void TableExample() {
		WebElement theTable = driver.findElement(By.id("product"));
		List<WebElement> allCoursesList = theTable.findElements(By.tagName("td"));

		for (int i = 1; i < allCoursesList.size(); i += 3) {
			System.out.println(allCoursesList.get(i).getText());
		}

	}

	@Test(priority = 9)
	public void ElementDisplayed() throws InterruptedException {
		WebElement hideButton = driver.findElement(By.id("hide-textbox"));
		hideButton.click();

		WebElement elementWeWantToHide = driver.findElement(By.id("displayed-text"));

		boolean ActualResultHide = elementWeWantToHide.isDisplayed();
		boolean ExpectedResultHide = false;

		Assert.assertEquals(ActualResultHide, ExpectedResultHide);

		Thread.sleep(2000);

		WebElement showButton = driver.findElement(By.id("show-textbox"));
		showButton.click();

		boolean ActualResultShow = elementWeWantToHide.isDisplayed();
		boolean ExpectedResultShow = true;

		Assert.assertEquals(ActualResultShow, ExpectedResultShow);

	}

	@Test(priority = 10)
	public void EnabledDisabled() throws InterruptedException {
		WebElement disableButton = driver.findElement(By.id("disabled-button"));
		disableButton.click();

		WebElement elementWeWantToDisable = driver.findElement(By.id("enabled-example-input"));

		boolean ActualResultDisable = elementWeWantToDisable.isEnabled();
		boolean ExpectedResultDisable = false;
		Assert.assertEquals(ActualResultDisable, ExpectedResultDisable);

		Thread.sleep(2000);

		WebElement enableButton = driver.findElement(By.id("enabled-button"));
		enableButton.click();

		boolean ActualResultEnable = elementWeWantToDisable.isEnabled();
		boolean ExpectedResultEnable = true;
		Assert.assertEquals(ActualResultEnable, ExpectedResultEnable);
	}

	@Test(priority = 11)
	public void MouseHover() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1750)");

		Actions action = new Actions(driver);
		Thread.sleep(3000);

		WebElement target = driver.findElement(By.id("mousehover"));
		action.moveToElement(target).perform();

		Thread.sleep(3000);
		driver.findElement(By.linkText("Reload")).click();
	}

	@Test(priority = 12)
	public void Calendar() throws InterruptedException {
		driver.findElement(By.linkText("Booking Calendar")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);
		for (int i = 1; i < 22; i++) {
			List<WebElement> AvailableDate = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));

			AvailableDate.get(i).click();
			Thread.sleep(1000);

		}
		driver.close();
		driver.switchTo().window(windowHandles.get(0));
	}

	@Test(priority = 13)
	public void iFrame() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,2400)");

		WebElement myFrame = driver.findElement(By.id("courses-iframe"));

		Thread.sleep(3000);
		driver.switchTo().frame(myFrame);
		js.executeScript("window.scrollTo(0,9300)");

		String myText = driver.findElement(By.xpath("//*[@id=\"ct_heading-1b594e8\"]/div/h3/span")).getText();

		System.out.println(myText);

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"ct_button-20c391b5\"]/a")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"ct-pagetitle\"]/div/ul/li[1]/a")).click();

	}

}
