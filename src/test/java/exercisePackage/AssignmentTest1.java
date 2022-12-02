package exercisePackage;

import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssignmentTest1 {
	@Test
	public void sauceDemo() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");

		Thread.sleep(3000);

		// 1. Verify the title as Swag Labs
		String title = driver.getTitle();
		System.out.println("Title is - "+title);
		Assert.assertEquals("Swag Labs", title);

		// 2. Verify the login button text is capitalized
		WebElement btnLogin = driver.findElement(By.xpath("//input[@id = 'login-button']"));
		String loginText = btnLogin.getAttribute("value");
		System.out.println("login text is - "+loginText.trim().toUpperCase());
		Assert.assertEquals(loginText.trim().toUpperCase(), "LOGIN");

		// 3. Login with standard_user & secret_sauce
		driver.findElement(By.xpath("//input[@id = 'user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id = 'password']")).sendKeys("secret_sauce");
		btnLogin.click();
		Thread.sleep(5000);

		// 4. Verify default filter dropdown is A-Z
		String defaultFilter = driver.findElement(By.xpath("//span[@class = 'active_option']")).getText();
		System.out.println("default filter is - "+defaultFilter);
		Assert.assertEquals(defaultFilter, "NAME (A TO Z)");

		// 5. Add the first product to the cart
		driver.findElement(By.xpath("(//button[@class = 'btn btn_primary btn_small btn_inventory'])[1]")).click();
		Thread.sleep(2000);

		// 6. Verify the cart badge has 1 product
		String cartBadge = driver.findElement(By.xpath("//span[@class = 'shopping_cart_badge']")).getText();
		System.out.println("Cart badge value after adding first item is - "+cartBadge);
		Assert.assertEquals(cartBadge, "1");

		// 7. Add the last product to the cart
		int size = driver.findElements(By.xpath("//button[@class = 'btn btn_primary btn_small btn_inventory']")).size();
		driver.findElement(By.xpath("(//button[@class = 'btn btn_primary btn_small btn_inventory'])[" + size + "]")).click();
		Thread.sleep(2000);

		// 8. Verify the cart badge value is increased
		String cartBadge1 = driver.findElement(By.xpath("//span[@class = 'shopping_cart_badge']")).getText();
		System.out.println("Cart badge value after adding second item is - "+cartBadge1);
		Assert.assertEquals(cartBadge1, "2");

		// 9. Remove the first product from the cart
		driver.findElement(By.xpath("(//button[@class = 'btn btn_secondary btn_small btn_inventory'])[1]")).click();
		Thread.sleep(2000);

		// 10. Verify the cart badge has 1 product
		String cartBadge2 = driver.findElement(By.xpath("//span[@class = 'shopping_cart_badge']")).getText();
		System.out.println("Cart badge value after removing first item is - "+cartBadge1);
		Assert.assertEquals(cartBadge2, "1");
		
		//11. Click on the cart 🛒
		driver.findElement(By.xpath("//a[@class = 'shopping_cart_link']")).click();
		Thread.sleep(2000);
		
		//12. Verify the added product is available
		String prodName = driver.findElement(By.xpath("//div[@class = 'inventory_item_name']")).getText();
		Assert.assertEquals(prodName, "Test.allTheThings() T-Shirt (Red)");
		Thread.sleep(2000);
		
		//13. Click on the continue shopping
		driver.findElement(By.xpath("//button[@name = 'continue-shopping']")).click();
		Thread.sleep(2000);
		
		//14. Change the price filter from low to high
		WebElement dropDown = driver.findElement(By.xpath("//select[@class = 'product_sort_container']"));
		
		Select s = new Select(dropDown);
		s.selectByVisibleText("Price (low to high)");
		
		
		//15. Verify the price sorted properly
		double[] arr = new double[size];
		
		for(int i=0; i<size; i++) {
			arr[i] = Double.parseDouble(driver.findElement(By.xpath("(//div[@class = 'inventory_item_price'])["+(i+1)+"]")).getText().replace("$", ""));
		}
		System.out.println(Arrays.toString(arr));
		
		double[] arr2 = new double[size];
		for(int j=0; j<size; j++) {
			arr2[j] = arr[j];
		}
		Arrays.sort(arr);
		
		System.out.println(Arrays.toString(arr));
		
		if(Arrays.equals(arr, arr2)) {
			System.out.println("Sorted as expected from low to high price");
		}else {
			System.out.println("Sorting is not as expected");
		}
		
		driver.close();
	}
}
