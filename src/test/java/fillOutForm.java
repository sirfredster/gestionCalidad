import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * @author Alfredo Colque Callata
 */

public class fillOutForm {

    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://courses.ultimateqa.com/users/sign_in/");
    }

/*    Se hace el test para el logeo con datos correctos de inicio de sesion.
      El resultado debe ser el correcto inicio de sesion, indicado por el nombre de usuario "Automation D "*/
    @Test
    public void SignIn1Positive(){
        WebElement emailTextBox = driver.findElement(By.id("user_email"));
        emailTextBox.sendKeys("automation.diplomado.dh@outlook.com");

        WebElement passwordTextBox = driver.findElement(By.id("user_password"));
        passwordTextBox.sendKeys("Password123");

        WebElement submitButton1 = driver.findElement(By.cssSelector(
                "#btn-signin"));
        submitButton1.click();
        //Verificacion
        WebElement confirmationMessage2 = driver.findElement(
                By.xpath("//*[@id=\"my_account\"]/span"));
        String actualMessage = confirmationMessage2.getAttribute("innerText");
        Assert.assertEquals("Automation D ",actualMessage,
                "The User and Password is correct");
    }

/*    Se hace el test para el logeo con datos incorrectos de inicio de sesion
      El resultado debe ser un aviso "invalid mail or password"*/
    @Test
    public void SignIn2Negative(){
        WebElement emailTextBox = driver.findElement(By.id("user_email"));
        emailTextBox.sendKeys("automation.diplomado.dh@houtlook.com");

        WebElement passwordTextBox = driver.findElement(By.id("user_password"));
        passwordTextBox.sendKeys("Password123");

        WebElement submitButton1 = driver.findElement(By.cssSelector(
                "#btn-signin"));
        submitButton1.click();
        //Verificacion
        WebElement confirmationMessage2 = driver.findElement(
                By.xpath("//*[@id=\"notifications-error\"]/ul/li"));
        String actualMessage = confirmationMessage2.getAttribute("innerText");
        Assert.assertEquals("Invalid email or password.",actualMessage,
                "The User or Password is incorrect");
    }
    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
