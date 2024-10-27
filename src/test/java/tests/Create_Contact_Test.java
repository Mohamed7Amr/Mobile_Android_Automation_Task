package tests;

import com.github.javafaker.Faker;
import listeners.ITestListener_Listener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import screens.contacts_screen.Contacts_Actions;
import screens.create_new_contact_screen.Create_New_Contact_Actions;
import test_bases.Test_Base;
import static data_reader.Load_Properties.*;

@Listeners(ITestListener_Listener.class)
public class Create_Contact_Test extends Test_Base {

    /***************************************OBJECTS_DECLARATIONS/INSTANTIATES******************************************/
    private Contacts_Actions ca;
    private Create_New_Contact_Actions cnca;
    private Faker fake_Data = new Faker();

    /**********************************************TEST_DATA**********************************************************/
    private final String first_Name = fake_Data.numerify("Mohamed##");
    private final String last_Name = contact_Data.getProperty("last_Name");
    private final String phone = contact_Data.getProperty("phone");
    private final String email = fake_Data.internet().emailAddress();
    private final String work_Number_Type = contact_Data.getProperty("work_Number_Type");
    private final String home_Number_Type = contact_Data.getProperty("home_Number_Type");
    private final String label_Name = contact_Data.getProperty("label_Name");

    /**********************************************TEST_CASES********************************************************/
    @Test
    public void create_Contact()
    {
        ITestListener_Listener.mobile_Driver = mobile_Driver;
        ca = new Contacts_Actions(mobile_Driver);
        cnca = new Create_New_Contact_Actions(mobile_Driver);

        ca.click_Create_New_Contact();
        cnca.enter_First_Name(first_Name);
        cnca.enter_Last_Name(last_Name);
        cnca.enter_Phone_Number(phone);
        cnca.open_Mobile_Number_Type_DDL();
        cnca.choose_Mobile_Number_Type(work_Number_Type);
        cnca.insert_Email_Address(email);
        cnca.open_Custom_Label_DDL();
        cnca.click_Custom_Option();
        cnca.customize_Label_Name(label_Name);
        cnca.click_Ok_Btn();
        cnca.click_Save_Btn();
        cnca.validate_Contact_Creation(first_Name + " " + last_Name);
    }

}
