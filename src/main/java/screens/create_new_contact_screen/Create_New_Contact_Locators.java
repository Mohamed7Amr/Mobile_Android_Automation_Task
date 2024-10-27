package screens.create_new_contact_screen;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import screens.Screen_Base;

public class Create_New_Contact_Locators extends Screen_Base {

    /******************************************CONSTRUCTORS***************************************/
    public Create_New_Contact_Locators(AppiumDriver mobile_Driver) {
        super(mobile_Driver);
    }

    /******************************************LOCATORS*******************************************/
    /**
     * Names-Suffix Notes:
     * 1)Txt:TextBox; 2)Link:WebLink; 3)Btn:Button; 4)Li:ListItem;
     * 5)Msg:Message; 6)DDL:DropDownList; 7)Opt:Option; 8)Chbox:CheckBox;
     * 9)Rdo:RadioButton; 10)TxtArea:TextArea;
     */

    By first_Name_Txt = AppiumBy.xpath("//android.widget.EditText[@text='First name']");
    By last_Name_Txt = AppiumBy.xpath("//android.widget.EditText[@text='Last name']");
    By phone_Txt = AppiumBy.xpath("//android.widget.EditText[@text='Phone'][@hint='Phone']");
    By number_Type_DDL = AppiumBy.xpath("//android.widget.TextView[@text='Mobile']");
    By work_Number_Type_Opt = AppiumBy.xpath("//android.widget.CheckedTextView[@index='1'][@text='Work']");
    By home_Number_Type_Opt = AppiumBy.xpath("//android.widget.CheckedTextView[@index='2'][@text='Home']");
    By email_Txt = AppiumBy.xpath("//android.widget.EditText[@text='Email']");
    By custom_Label_DDL = AppiumBy.xpath("//android.widget.TextView[@text='Home']");
    By custom_Opt = AppiumBy.xpath("//android.widget.CheckedTextView[@index='3'][@text='Custom']");
    By label_Name_Txt = AppiumBy.xpath("//android.widget.EditText[@index='0'][@text='Label name']");
    By ok_Btn = AppiumBy.id("android:id/button1");
    By save_Btn = AppiumBy.id("com.android.contacts:id/editor_menu_save_button");
    By contact_Name_Label = AppiumBy.id("com.android.contacts:id/large_title");

}
