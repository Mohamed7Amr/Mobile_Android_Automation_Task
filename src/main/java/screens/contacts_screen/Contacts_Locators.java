package screens.contacts_screen;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import screens.Screen_Base;

public class Contacts_Locators extends Screen_Base {

    /********************************************CONSTRUCTORS*************************************************/
    public Contacts_Locators(AppiumDriver mobile_Driver) {
        super(mobile_Driver);
    }

    /*******************************************MOBILE_LOCATORS***********************************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea;
     */
    By create_New_Contact_Btn = AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Create new contact']");

}
