package screens;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class Screen_Base {

    /**************************************************OBJECTS_DECLARATIONS***********************************************/
    protected AppiumDriver mobile_Driver;
    private Actions action;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    /****************************************************CONSTRUCTORS***************************************************/
    public Screen_Base(AppiumDriver mobile_Driver)
    {
        this.mobile_Driver = mobile_Driver;
    }

    /************************************************USER_ACTIONS*******************************************************/
    /**
     * 1) This method keeps checking for the element visibility for 5 seconds.
     * 2) If visible, the webpage will be scrolled to the target webElement, and will have background-color as green &
     * borders as black.
     * 3) try code; click on the element exactly as shown on the UI (if there's overlay, won't be clicked).
     * 4) catch code; Click on the element from the DOM, thus even if the element is covered by an overlay but already
     * exist in the DOM, then it will be clicked.
     * @param ele the target WebElement
     */
    protected void clickWebElement(WebElement ele)
    {
        try {
            ele.click();
        }
        catch (Exception e) {
            tap(ele);
        }
    }

    /**
     * 1) This method keeps checking for the element visibility for 5 seconds.
     * 2) If visible, the webpage will be scrolled to the target webElement, and will have background-color as yellow &
     * borders as black.
     * 3) Send text to the Target WebElement.
     * @param ele the target WebElement
     * @param text the text that shall be written in the target webElement
     */
    protected void writeText(WebElement ele, String text)
    {

//        scrollTo_Highlight(ele,"yellow");
        ele.sendKeys(text);
    }
    protected String getText(WebElement ele)
    {
        wait_Element_Visibility(10, ele);
        return ele.getText();
    }


    /**********************************************LOCATING_ELEMENTS*********************************************/
    /**
     * It's better using locators as parameters with ExpectedCondition class to wait upon,
     * they are more stable than web-elements, and in this method you can send locators extracted
     * by different selectors to this method
     * @param locator it must have "By" data-type because it carries value of AppiumBy.Selector(xpath,accessibilityId,etc..)
     * where AppiumBy class extends By class.z
     * @return web-element object of data-type "WebElement" to interact with.
     */
    protected WebElement find_Element(By locator)
    {
        return mobile_Driver.findElement(wait_Locator_Visibility(locator));
    }

    /*******************************************MOBILE_GESTURES***********************************************/
    protected void long_Click(WebElement ele)
    {
        js = (JavascriptExecutor)mobile_Driver;
        try {
            js.executeScript("mobile:longClickGesture",
                    ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),"duration",3000));
        }
        catch (Exception e)
        {
            Point coordinates = ele.getLocation();
            int x = coordinates.getX();
            int y = coordinates.getY();
            System.out.println("this is dims:" + coordinates);
            mobile_Driver.executeScript("mobile:longClickGesture",
                    ImmutableMap.of("x",x,"y",y, "duration",3000));
        }
    }
    protected void double_Click(WebElement ele)
    {
        js = (JavascriptExecutor)mobile_Driver;
        try{
            js.executeScript("mobile:doubleClickGesture",
                    ImmutableMap.of("elementId",((RemoteWebElement)ele).getId()));
        }
        catch (Exception e)
        {
            Point coordinates = ele.getLocation();
            int x = coordinates.getX();
            int y = coordinates.getY();
            System.out.println("this is dims:" + coordinates);
            mobile_Driver.executeScript("mobile:doubleClickGesture", ImmutableMap.of("x",x,"y",y));
        }
    }
    protected void tap(WebElement ele)
    {
        js = (JavascriptExecutor)mobile_Driver;
        try{
            js.executeScript("mobile:clickGesture",
                    ImmutableMap.of("elementId",((RemoteWebElement)ele).getId()));
        }
        catch (Exception e)
        {
            Point coordinates = ele.getLocation();
            int x = coordinates.getX();
            int y = coordinates.getY();
            System.out.println("this is dims:" + coordinates);
            js.executeScript("mobile:clickGesture", ImmutableMap.of("x",x,"y",y));
        }
    }


    /**************ASSISTIVE_METHODS***************/
    protected String reverse_String(String input_To_Be_Reversed) {
        StringBuilder sb = new StringBuilder(input_To_Be_Reversed);
        String input_Reversed = (sb.reverse()).toString();
        return input_Reversed;
    }

    /***************ACTIONS_CLASS_METHODS************/
    protected void hover_WebElement(WebElement ele)
    {
        action = new Actions(mobile_Driver);
        action.moveToElement(ele).perform();
    }

    /***************JS_INJECTION****************/

    //    protected void js_Scroll_View_Element_ExactLocation(WebElement ele)
    //    {
    //        js = (JavascriptExecutor) mobile_Driver;
//            Point coordinates = ele.getLocation();
//            int x = coordinates.getX();
//            int y = coordinates.getY();
    //        js.executeScript("scrollBy(" + x + "," + y + ");");
    //    }
    protected void js_Scroll_View_Element_Center_With_Window(WebElement ele)
    {
        js = (JavascriptExecutor) mobile_Driver;

        Point coordinates = ele.getLocation();
        int x = coordinates.getX();
        int y = coordinates.getY();

        Dimension dims = mobile_Driver.manage().window().getSize();
        int windowWidth = dims.getWidth();
        int windowHeight = dims.getHeight();

        js.executeScript("window.scrollTo(" + (x - windowWidth/2) + "," + (y - windowHeight/2) + ");");
    }
    protected void js_Scroll_View_Element_TopWindow(WebElement ele)
    {
        js = (JavascriptExecutor) mobile_Driver;
        js.executeScript("arguments[0].scrollIntoView()", ele);
    }

    /***************EXPLICIT_WAIT****************/
    protected By wait_Locator_Visibility(By locator)
    {
        wait = new WebDriverWait(mobile_Driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return locator;
    }
    protected void wait_Elements_Visibility(int seconds, List<WebElement> eles)
    {
        wait = new org.openqa.selenium.support.ui.WebDriverWait( mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(eles));
    }
    protected void wait_Element_Visibility(int seconds, WebElement ele) {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }
    protected void wait_Invisible_Element(int seconds, WebElement ele)
    {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(ele));

    }
    protected void wait_Element_Clickable(int seconds, WebElement ele)
    {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(ele));

    }

    protected void wait_Text_In_Element(int seconds, WebElement ele, String text)
    {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
//            wait.until(ExpectedConditions.attributeContains());
        wait.until(ExpectedConditions.textToBePresentInElement(ele, text));
    }

    /**
     * This method checks the default value of the src attribute, then keeps checking (polling) if it has its value
     * changed after uploading a new image. Condition shall be fulfilled once the condition of having a default value
     * is inverted.
     * @param seconds is the time to make sure that the value of src attribute has changed from its default value
     * @param ele img-tag webElement that has src attribute with its default value used by the dev team
     * @param src_Attribute attribute that has its value changed after uploading a new image to its img-tag component
     * @param src_Attribute_Value default value that I do my validation upon, making sure it changed after uploading
     */
    protected void wait_Element_Src_Attribute_Value(int seconds, WebElement ele, String src_Attribute, String src_Attribute_Value)
    {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(ele, src_Attribute, src_Attribute_Value)));
    }

    /**
     * this method is to wait for a specific element to have "disabled" attribute which makes the element dimmed and
     * not intractable with, also its value is empty string in the DOM.
     */
    protected void wait_Disable_Element(int seconds, WebElement ele)
    {
        wait = new WebDriverWait(mobile_Driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.attributeToBe(ele,"disabled",""));
    }

    /***************SCREENSHOT_INTERFACE**************/
    @SneakyThrows
    public void take_ScreenShot(String pass_Fail_Dir, String ssName_Extension) {
        TakesScreenshot ss = (TakesScreenshot) mobile_Driver;
        File sourceFile = ss.getScreenshotAs(OutputType.FILE);
        File targetFile = new File("Screenshots/"+ pass_Fail_Dir + ssName_Extension);
        FileUtils.copyFile(sourceFile,targetFile);
    }
}