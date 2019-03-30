package SeleniumTestes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InativarProduto {
public static void produtoInativo (WebDriver driver) {
		
		WebElement search;
		
	try {
			
			//botao inativar produto com id 1
			search = driver.findElement(By.id("Excluir1"));
			search.click();
			Thread.sleep(5500);
			
			//botao ativar produto com id 1
			search = driver.findElement(By.id("Ativar1"));
			search.click();
			Thread.sleep(5500);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}