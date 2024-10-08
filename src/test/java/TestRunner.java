import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/product.feature",
        glue = {"com.tdd.productservice.steps.ProductSteps", "com.tdd.productservice.steps"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
}