package skeleton;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import skeleton.Belly;
import cucumber.api.java.en.Then;

public class Stepdefs {
	
	Belly belly = new Belly(); 
	
    @Given("I have eaten (\\d+) cukes")
    public void I_have_eaten_cukes(int cukes) throws Throwable {
        System.out.println("I'm eating " + cukes + " cukes!");
    	belly.eat(cukes);
    }
    
    @When("I wait (\\d+) hour")
    public void i_wait_hour(int arg1) throws Throwable {
        belly.wait(arg1);
    }

    @Then("(?i)my\\s*belly\\s*should\\s*growl")
    public void my_belly_should_growl() throws Throwable {
    	assertTrue(belly.isGrowling());
    }
    
    @Then("(?i)my\\s*belly\\s*should\\s*not\\s*growl")
    public void my_belly_should_not_growl() throws Throwable {
    	assertTrue(!belly.isGrowling());
    }
    
}
