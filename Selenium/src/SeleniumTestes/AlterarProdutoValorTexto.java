package SeleniumTestes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AlterarProdutoValorTexto {

	public static void AlterarValorTexto (WebDriver driver) {
		
		WebElement search;
		Select dropdown;
		
	try {
		
		//tela alterar produto
		
		//campo marca produto
		dropdown = new Select(driver.findElement(By.name("marca-produto")));
		dropdown.selectByValue("3");
		Thread.sleep(1000);
		
		//campo valor do produto
		search = driver.findElement(By.name("valor-produto"));
		search.clear();
		search.sendKeys("4500ffasfsd");
		Thread.sleep(2000);
		
		//campo descricao do produto
		search = driver.findElement(By.name("descricao-produto"));
		search.clear();
		search.sendKeys("iPhone X topzera muito bom 10 a zero");
		Thread.sleep(2000);
		
		//botao cadastrar produto
		search = driver.findElement(By.name("operacao"));
		search.click();
		Thread.sleep(5000);
	} catch(Exception e) {
		
		}
	
	}
}