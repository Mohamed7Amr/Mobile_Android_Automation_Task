package screens.contacts_screen;

import io.appium.java_client.AppiumDriver;

public class Contacts_Actions extends Contacts_Locators {

    /********************************************CONSTRUCTORS*************************************************/
    public Contacts_Actions(AppiumDriver mobile_Driver) {
        super(mobile_Driver);
    }

    /********************************************ACTIONS******************************************************/
    public void click_Create_New_Contact()
    {
        clickWebElement(find_Element(create_New_Contact_Btn));
    }
}
