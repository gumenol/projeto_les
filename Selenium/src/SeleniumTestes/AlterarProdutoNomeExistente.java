package SeleniumTestes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AlterarProdutoNomeExistente {

	public static void produtoAlterar (WebDriver driver) {
		
		WebElement search;
		Select dropdown;
try {
			search = driver.findElement(By.id("Alterar23"));
			search.click();
			Thread.sleep(5000);
			
			//tela alterar produto
			//campo nome do produto
			search = driver.findElement(By.name("nome-produto"));
			search.clear();
			search.sendKeys("Mate 20 Pro");
			Thread.sleep(2000);
			
			//campo marca produto
			dropdown = new Select(driver.findElement(By.name("marca-produto")));
			dropdown.selectByValue("3");
			Thread.sleep(1000);
			
			//campo valor do produto
			search = driver.findElement(By.name("valor-produto"));
			search.clear();
			search.sendKeys("4500");
			Thread.sleep(2000);
			
			//campo descricao do produto
			search = driver.findElement(By.name("descricao-produto"));
			search.clear();
			search.sendKeys("Mate 20 Pro super daora");
			Thread.sleep(2000);
			
			//botao cadastrar produto
			search = driver.findElement(By.name("operacao"));
			search.click();
			Thread.sleep(5000);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	

