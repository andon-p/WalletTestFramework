package test.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/test/features", glue = "test.stepdefs.web", tags = "@Web",  plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class RunWebTests extends AbstractTestNGCucumberTests {
}