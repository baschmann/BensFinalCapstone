package hooks;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;


import java.time.Duration;


public class hooks {
    @Before("not @demo and not @api")
    public void beforeScenario(){
        System.out.println("We are running before each scenario");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }



    @After("not @demo and not @api")
    public void afterScenario(Scenario scenario){
        System.out.println("We are running after each scenario");

        if(scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot , "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }
//     @BeforeStep
//    public void beforeStep(){
//        System.out.println("This line will get printed before each step");
//    }
    //@AfterStep("not @demo and not @api")
    //public void afterStep() throws InterruptedException {
    //System.out.println("This line will get printed after each step");
    //Thread.sleep(2000);
    }





//THis is a note code for using order as parameter in the annotation
//    @Before(order=2)
//    public void beforeScenario(){
//        System.out.println("We are running before each scenario");
//        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        System.out.println("This is running as order 1 in before method");
//    }
//
//    @Before(order=1)
//    public void beforeScenario2(){
//        System.out.println("This is running as order 2 in before method");
//    }


// We can use tag inside the annotation parameter
//    @Before("@regression")
//    public void beforeScenario(){
//        System.out.println("We are running before each scenario");
//        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//    }




