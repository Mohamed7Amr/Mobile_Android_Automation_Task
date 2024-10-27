package screens.create_new_contact_screen;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.Assert;

public class Create_New_Contact_Actions extends Create_New_Contact_Locators{

    /******************************************CONSTRUCTORS***************************************/
    public Create_New_Contact_Actions(AppiumDriver mobile_Driver) {
        super(mobile_Driver);
    }

    /******************************************ACTIONS*******************************************/
    @Step("enter first name")
    public void enter_First_Name(String first_Name)
    {
        writeText(find_Element(first_Name_Txt),first_Name);
    }
    @Step("enter last anem")
    public void enter_Last_Name(String last_Name)
    {
        writeText(find_Element(last_Name_Txt),last_Name);
    }
    @Step("enter phone number")
    public void enter_Phone_Number(String phone_Number)
    {
        writeText(find_Element(phone_Txt),phone_Number);
    }
    @Step("open mobile number dropdown list")
    public void open_Mobile_Number_Type_DDL()
    {
        clickWebElement(find_Element(number_Type_DDL));
    }
    @Step("choose mobile number type")
    public void choose_Mobile_Number_Type(String mobile_Number_Type)
    {
        switch(mobile_Number_Type){
            case "work" -> clickWebElement(find_Element(work_Number_Type_Opt));
            case "home" -> clickWebElement(find_Element(home_Number_Type_Opt));
            default -> throw new IllegalArgumentException("Invalid choice");
        }
    }
    @Step("insert email address")
    public void insert_Email_Address(String email_Address)
    {
        writeText(find_Element(email_Txt),email_Address);
    }
    @Step("open custom label DDL")
    public void open_Custom_Label_DDL()
    {
        tap(find_Element(custom_Label_DDL));
    }
    @Step("choose \"custom\" option")
    public void click_Custom_Option()
    {
        tap(find_Element(custom_Opt));
    }
    @Step("Create customized label name")
    public void customize_Label_Name(String label_Name)
    {
        writeText(find_Element(label_Name_Txt),label_Name);
    }
    @Step("Click \"ok\" button")
    public void click_Ok_Btn()
    {
        tap(find_Element(ok_Btn));
    }
    @Step("Click \"save\" button")
    public void click_Save_Btn()
    {
        clickWebElement(find_Element(save_Btn));
    }

    /********************************************VALIDATIONS******************************************/
    public void validate_Contact_Creation(String contact_Name)
    {
        String actual_Result = getText(find_Element(contact_Name_Label));
        Assert.assertEquals(actual_Result,contact_Name,"Contact name is correct Assertion");
    }
}
