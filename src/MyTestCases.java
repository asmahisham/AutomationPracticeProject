import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		
		WebElement autoCompleteInput=driver.findElement(By.id("autocomplete"));
		
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
		
		int RandomIndex = rand.nextInt(1,4);
		
		selector.selectByIndex(RandomIndex);
		
	}
	
	@Test(priority = 4)
	public void CheckBox(){
		WebElement divForCheckBox = driver.findElement(By.id("checkbox-example"));
		List<WebElement> myCheckBox = divForCheckBox.findElements(By.xpath("//input[@type='checkbox']"));
		
		for(int i = 0; i<myCheckBox.size(); i++) {
			myCheckBox.get(i).click();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
