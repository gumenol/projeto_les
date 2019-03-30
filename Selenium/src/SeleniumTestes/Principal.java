package SeleniumTestes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Principal {

	public static void main (String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gusmo\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		
		driver.get("http://localhost:8080/LES1_2019_web/dashboard-admin.jsp");
			
		
		  CadastroProdutoMesmoNome.produtoMesmoNomeCadastro(driver);
		  
		  driver.switchTo().alert().accept();
		  
		  CadastroProdutoValorComTexto.produtoValorTexto(driver);
		  
		  driver.switchTo().alert().accept();
		  
		  CadastroProdutoValorIncorreto.produtoValorZerado(driver);
		  
		  driver.switchTo().alert().accept();
		  
		  CadastroProduto.produtoCadastro(driver);
		 
			 
		  InativarProduto.produtoInativo(driver);
			 
		
		  AlterarProdutoNomeExistente.produtoAlterar(driver);
		  driver.switchTo().alert().accept();
		  
		  AlterarProdutoValorTexto.AlterarValorTexto(driver);
		  driver.switchTo().alert().accept();
		  
		  AlterarProdutoValorZerado.AlterarValorZerado(driver);
		  driver.switchTo().alert().accept();
		 
		  AlterarProduto.alterarProduto(driver);
		 
	}
	
}
