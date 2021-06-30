package stepdefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.runtime.Argument;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import tasks.Busqueda;
import userinterface.HomePage;

import java.util.concurrent.TimeUnit;

public class GlobalStepDef {
    @Managed (driver = "chrome")
    private WebDriver navegador;
    private Actor actor = Actor.named("Global");
    private HomePage homePage = new HomePage();
    By busquedaLocator = By.xpath("//*[local-name()='li']/a[@aria-label='Search Icon Link']");
    By busquedaLocato = By.xpath("//*[@class='elementor-section elementor-top-section elementor-element elementor-element-6c1b1ed elementor-section-boxed elementor-section-height-default elementor-section-height-default']");


    @Dado("^: que los usuarios utilicen la opcion de buscar de la pagina$")
    public void queLosUsuariosUtilicenLaOpcionDeBuscarDeLaPagina() {
        actor.can(BrowseTheWeb.with(navegador));
        actor.wasAbleTo(Open.browserOn(homePage));
        navegador.manage().window().maximize();


    }

    @Cuando("^: los usuarios realizan las consultas de la web$")
    public void losUsuariosRealizanLasConsultasDeLaWeb() throws InterruptedException {
    WebElement flags=navegador.findElement(busquedaLocato);
        JavascriptExecutor js = (JavascriptExecutor)navegador;

    js.executeScript("arguments[0].scrollIntoView();",flags);
        //navegador.findElement(busquedaLocato);
        Thread.sleep(5000);
        navegador.findElement(busquedaLocator).click();
        WebElement searchbox = navegador.findElement(By.name("s"));
        searchbox.clear();

        searchbox.sendKeys("pruebas");
        searchbox.submit();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        js.executeScript("window.scrollBy(0,1000)","");

    }

    @Entonces("^: los usuaruis visualizan las busquedas consultadas$")
    public void losUsuaruisVisualizanLasBusquedasConsultadas() throws InterruptedException {
        Thread.sleep(5000);
        navegador.quit();
    }

}
