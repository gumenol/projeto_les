package SeleniumTestes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CadastroProdutoMesmoNome {

public static void produtoMesmoNomeCadastro (WebDriver driver) {
	
	WebElement search;
	Select dropdown;
	
try {
	
		//tela inicial
		search = driver.findElement(By.id("produtos"));
		search.click();
		Thread.sleep(500);
		search = driver.findElement(By.id("visualizar"));
		search.click();
		Thread.sleep(500);
		
		//tela todos os produtos
		search = driver.findElement(By.id("cadastrar"));
		search.click();
		Thread.sleep(2000);
		
		//tela de cadastro de produto
		//campo nome do produto
		search = driver.findElement(By.name("nome-produto"));
		search.clear();
		search.sendKeys("Mi Mix 10");
		Thread.sleep(1000);
		
		//campo categoria produto
		dropdown = new Select(driver.findElement(By.name("categoria-produto")));
		dropdown.selectByValue("1");
		Thread.sleep(1000);
		
		//campo marca produto
		dropdown = new Select(driver.findElement(By.name("marca-produto")));
		dropdown.selectByValue("1");
		Thread.sleep(1000);
		
		//campo valor do produto
		search = driver.findElement(By.name("valor-produto"));
		search.clear();
		search.sendKeys("4500");
		Thread.sleep(1000);
		
		//campo descricao do produto
		search = driver.findElement(By.name("descricao-produto"));
		search.clear();
		search.sendKeys("Mi Mix 10 Super top");
		Thread.sleep(2000);
		
		//botao cadastrar produto
		search = driver.findElement(By.name("operacao"));
		search.click();
		Thread.sleep(2000);
		
		
	} catch(Exception e) {
		e.printStackTrace();
	}
}
	
}