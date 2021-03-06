package no.jenjon13.reeddit.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CounterPageObject {
    private final WebDriver driver;

    public CounterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void toIndexPage() {
        final String localIndexUrl = String.format("%s:%s%s%s", Config.HOST, Config.PORT, Config.CONTEXT, Config.INDEX);
        driver.get(localIndexUrl);
        waitForPageToLoad();
    }

    public boolean isAtIndexPage() {
        final String title = driver.getTitle();
        return title.contains("PG6100");
    }

    private Boolean waitForPageToLoad() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        return wait.until((ExpectedCondition<Boolean>) input -> {
            final String script = "return /loaded|complete/.test(document.readyState);";
            final String res = jsExecutor.executeScript(script).toString();
            return Boolean.parseBoolean(res);
        });
    }

}
