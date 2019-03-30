package SeleniumTestes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CadastroProduto {

	public static void produtoCadastro (WebDriver driver) {
		
		WebElement search;
		Select dropdown;
		
try {
			
			//tela de cadastro de produto
			//campo nome do produto
			search = driver.findElement(By.name("nome-produto"));
			search.clear();
			search.sendKeys("iPhone X");
			Thread.sleep(2000);
			
			//campo categoria produto
			dropdown = new Select(driver.findElement(By.name("categoria-produto")));
			dropdown.selectByValue("1");
			Thread.sleep(2000);
			
			//campo marca produto
			dropdown = new Select(driver.findElement(By.name("marca-produto")));
			dropdown.selectByValue("1");
			Thread.sleep(2000);
			
			//campo valor do produto
			search = driver.findElement(By.name("valor-produto"));
			search.clear();
			search.sendKeys("5000");
			Thread.sleep(2000);
			
			//campo descricao do produto
			search = driver.findElement(By.name("descricao-produto"));
			search.clear();
			search.sendKeys("iPhone X de 128 GB - Cor: Black - Tela de 6.1 Polegadas - AMOLED");
			Thread.sleep(2000);
			
			//botao cadastrar produto
			search = driver.findElement(By.name("operacao"));
			search.click();
			Thread.sleep(6000);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	}
	
