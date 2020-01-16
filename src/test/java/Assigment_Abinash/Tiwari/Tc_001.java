package Assigment_Abinash.Tiwari;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
   
//LOGIN OF APP    
public class Tc_001 extends BaseMobileClass {   
	@Test  
	public static void testcase001() throws Exception {
		 launchAppilication(); // STEP 1                         
		 loginApp();           // STEP 2  
		 expWait(getPropertyValue("closeBtn")); //WAIT
		 searchItem();
		 itemDetails();
		 scrollUptoIDClick(getPropertyValue("addToChart"));//SCROLL AND CLICK  
		 checkIncart();
		 assertEquals(priceIncart, priceWhileBuying); 
		//  ArrayList<MobileElement> ListOfOptions = (ArrayList<MobileElement>) driver.findElementsById("com.amazon.mShop.android.shopping:id/item_title");
		//  System.out.println(ListOfOptions.size());
		  
		  
		 
		}

		
  }     

                          