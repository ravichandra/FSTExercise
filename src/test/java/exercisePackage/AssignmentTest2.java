package exercisePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentTest2 {
	@Test
	public void computerDatabase() throws Exception {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://computer-database.gatling.io/computers");

		Thread.sleep(3000);

		// 1. Verify the title as Computers database
		String title = driver.getTitle();
		System.out.println("Title is - " + title);
		Assert.assertEquals(title, "Computers database");

		// 2. Verify the page header is the same as the page title
		String pageHeader = driver.findElement(By.xpath("//a[text() = 'Computer database']")).getText();
		System.out.println("Page Header is - " + pageHeader);
		Assert.assertEquals(pageHeader, "Computer database");

		// 3. User must see the filter by computer name text box
		boolean blnFilterFound = driver.findElement(By.xpath("//input[@id = 'searchbox']")).isDisplayed();
		System.out.println("Search filter displayed - " + blnFilterFound);
		Assert.assertEquals(blnFilterFound, true);

		// 4. User able to see add a new computer button
		boolean blnAddFound = driver.findElement(By.xpath("//a[@id = 'add']")).isDisplayed();
		System.out.println("Add Computer button is displayed- " + blnAddFound);
		Assert.assertEquals(blnAddFound, true);

		// 5. User able to see the filter by name button
		boolean blnFilterByNameFound = driver.findElement(By.xpath("//input[@id = 'searchsubmit']")).isDisplayed();
		System.out.println("Filter by name button displayed - " + blnFilterByNameFound);
		Assert.assertEquals(blnFilterByNameFound, true);

		// 6. User able to see the table and the headers as follows
		// Computer name
		String strComp = driver.findElement(By.xpath("(//th//a)[1]")).getText();
		System.out.println("Column-1 is - " + strComp);
		Assert.assertEquals(strComp, "Computer name");
		
		// Introduced
		String strIntro = driver.findElement(By.xpath("(//th//a)[2]")).getText();
		System.out.println("Column-2 is - " + strIntro);
		Assert.assertEquals(strIntro, "Introduced");
		
		// Discontinued
		String strDisc = driver.findElement(By.xpath("(//th//a)[3]")).getText();
		System.out.println("Column-3 is - " + strDisc);
		Assert.assertEquals(strDisc, "Discontinued");
		
		// Company
		String strCompany = driver.findElement(By.xpath("(//th//a)[4]")).getText();
		System.out.println("Column-4 is - " + strCompany);
		Assert.assertEquals(strCompany, "Company");
		

		// 7. The user should see the pagination
		boolean blnPagination = driver.findElement(By.xpath("//a[text() = 'Displaying 1 to 10 of 574']")).isDisplayed();
		System.out.println("Pagination Exists - " + blnPagination);
		Assert.assertEquals(blnPagination, true);
		
		//Add a new computer
		//1. Click on create this computer
		driver.findElement(By.xpath("//a[@id = 'add']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@class = 'btn primary']")).click();
		Thread.sleep(3000);
		
		//2. User should see the red background on the computer name field
		//Failed to refine type : Predicate isEmpty() did not fail.
		boolean blnErrorFound = driver.findElement(By.xpath("//span[text() = 'Failed to refine type : Predicate isEmpty() did not fail.']")).isDisplayed();
		System.out.println("Error message:'Failed to refine type : Predicate isEmpty() did not fail.'  Exists - " + blnErrorFound);
		Assert.assertEquals(blnErrorFound, true);
		
		//3. Enter the computer name
		driver.findElement(By.xpath("//input[@id = 'name']")).sendKeys("MyCompany");
		
		//4. Select the company as Nokia
		WebElement dropDown = driver.findElement(By.xpath("//select[@id = 'company']"));
		Select s = new Select(dropDown);
		s.selectByVisibleText("Nokia");
		Thread.sleep(2000);
		//5. Submit the form
		driver.findElement(By.xpath("//input[@class = 'btn primary']")).click();
		Thread.sleep(3000);
		
		//6. Successful message should be displayed
		String successMsg = driver.findElement(By.xpath("//div[@class = 'alert-message warning']")).getText();
		System.out.println("Success message is - "+successMsg);
		Assert.assertEquals(successMsg, "Done ! Computer MyCompany has been created");
		Thread.sleep(2000);
		
		//Search the created data
		//1. result should be visible (defect)
		driver.findElement(By.xpath("//input[@id = 'searchbox']")).sendKeys("MyCompany");
		driver.findElement(By.xpath("//input[@id = 'searchsubmit']")).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath("//em[text() = 'Nothing to display']")).isDisplayed()) {
			System.out.println("No results displayed - It is a defect");
		}else {
			System.out.println("Result displayed as expected");
		}
		driver.close();
	}
}
