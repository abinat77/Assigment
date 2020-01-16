package Assigment_Abinash.Tiwari;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.MobileElement;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.apache.poi.ss.usermodel.Cell;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseMobileClass extends TestExcel {
	
	
	public static String udid = "";
	public static String deviceName = "";
	public static String platformName = "";
	public static String platformVersion = "";
	public static String app = "";
	public static String appPackage = "";
	public static String appActivity = "";
	public static String name  = "";
	public static String pwd = "";
	public static String stringSearch = "";
	public static  String desInCart = "";
	public static  String priceIncart ="";
	public static  String priceWhileBuying ="";
	public static AndroidDriver driver;
	
	
	
	public static String getAppPackage() {
		return appPackage;
	}

	public static void setAppPackage(String appPackage) {
		BaseMobileClass.appPackage = appPackage;
	}

	public static String getAppActivity() {
		return appActivity;
	}

	public static void setAppActivity(String appActivity) {
		BaseMobileClass.appActivity = appActivity;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		BaseMobileClass.name = name;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		BaseMobileClass.pwd = pwd;
	}

	public static String getStringSearch() {
		return stringSearch;
	}

	public static void setStringSearch(String stringSearch) {
		BaseMobileClass.stringSearch = stringSearch;
	}
	
	
	
	public static String getUdid() {
		return udid;
	}

	public static void setUdid(String udid) {
		BaseMobileClass.udid = udid;
	}

	public static String getDeviceName() {
		return deviceName;
	}

	public static void setDeviceName(String deviceName) {
		BaseMobileClass.deviceName = deviceName;
	}

	public static String getPlatformName() {
		return platformName;
	}

	public static void setPlatformName(String platformName) {
		BaseMobileClass.platformName = platformName;
	}

	public static String getPlatformVersion() {
		return platformVersion;
	}

	public static void setPlatformVersion(String platformVersion) {
		BaseMobileClass.platformVersion = platformVersion;
	}

	public static String getApp() {
		return app;
	}

	public static void setApp(String app) {
		BaseMobileClass.app = app;
	}

	@BeforeTest
	public static void startDriver() throws  IOException {
		// obtaining input bytes from a file
		
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook myExcelBook = new XSSFWorkbook(fis);
		XSSFSheet myExcelSheet = myExcelBook.getSheet("MobileDetails");
		XSSFRow row = myExcelSheet.getRow(0);
   
		for (int col = 1; col < row.getLastCellNum(); col++) {
			Row row1 = myExcelSheet.getRow(col); // returns the logical row
			Cell cell = row1.getCell(0);
			String value = cell.getStringCellValue(); // getting cell value
			if (value.equalsIgnoreCase("Y")) {
				System.out.println("The Value for the Yes col is " + col);
				getrowDetails(col);
				break;

			} else {
				continue;
			}

		}

	}
	
	public static void getrowDetails(int valrow) throws IOException {

		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook myExcelBook = new XSSFWorkbook(fis);
		XSSFSheet myExcelSheet = myExcelBook.getSheet("MobileDetails");
		XSSFRow row = myExcelSheet.getRow(1);
		System.out.println("The Length of the row is as " + row.getLastCellNum());
		for (int i = 0; i < row.getLastCellNum(); i++) {
			row = myExcelSheet.getRow(valrow); // returns the logical row
			Cell cell = row.getCell(i);
			
			setDeviceName(ReadCellData(valrow,1));
			setPlatformName(ReadCellData(valrow, 2));
			setUdid(ReadCellData(valrow,3));   
			setPlatformVersion(ReadCellData(valrow,4 ));
			setApp(ReadCellData(valrow,5));
			setAppPackage(ReadCellData(valrow,6));
			setAppActivity(ReadCellData(valrow,7));
			setName(ReadCellData(valrow, 8));
			setPwd(ReadCellData(valrow,9));
			setStringSearch(ReadCellData(valrow, 10));
				


	}

}
	
	public static String getPropertyValue(String Key) throws IOException
	{
		Properties OR;
		File f1;
		FileInputStream file;
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")
				+ "\\PropertiesFileAndXpaths\\Object.Properties");
				
		file = new FileInputStream(f1);
		OR.load(file);
		return OR.getProperty(Key);
    
	}

public static void launchAppilication() throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", getDeviceName());    
		capabilities.setCapability("platformName", getPlatformName()); 
		capabilities.setCapability("udid",getUdid());  
		capabilities.setCapability("platformVersion",getPlatformVersion());
		capabilities.setCapability("app",getApp()); 
		capabilities.setCapability("appPackage", getAppPackage());
		capabilities.setCapability("appActivity", getAppActivity());
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	}             


 public static void loginApp() throws Exception { 
	 driver.findElementById(getPropertyValue("signIn")).click();       
	 driver.findElementByXPath(getPropertyValue("userName")).sendKeys(getName());  
	 driver.findElementByXPath(getPropertyValue("continueBtn")).click();
	 driver.findElementByXPath(getPropertyValue("pwd")).sendKeys(getPwd());
	 driver.findElementByXPath(getPropertyValue("loginBtn")).click();
	 
 }
 public static void scrollUptoIDClick(String element) {
		String uiSelector = "new UiSelector().resourceId(\"" + element + "\")";
		String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
				+ ");";

		driver.findElementByAndroidUIAutomator(command).click(); // Perform the required action on the element

	}

	public static void checkIncart() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		 driver.findElementById(getPropertyValue("cartInTop")).click();
		 driver.findElementByXPath("//android.widget.Button[@text='Delete']").click();
		 Thread.sleep(4000);
		 desInCart =  driver.findElementByXPath("//android.view.View[@id='sc-item-C04056075-f748-4eae-8104-37cca95e84b5']/android.view.View[@index='1']/android.view.View[@index='2']/android.view.View[@index='0']").getText();
		 priceIncart = driver.findElementByXPath(getPropertyValue("priceOut")).getText();
		 System.out.println(priceIncart);
		 System.out.println(desInCart);        
		
	}

	public static void itemDetails() throws IOException {
		// TODO Auto-generated method stub
		 String description = driver.findElementByXPath("//android.view.View[@id='title_feature_div']/android.view.View[@index='0']").getText();
		 System.out.println(description);
		 String priceWhileBuying = driver.findElementByXPath(getPropertyValue("priceIn")).getText();
		 System.out.println(priceWhileBuying); 
		
	}

	public static void expWait(String propertyValue) throws IOException {
		// TODO Auto-generated method stub
		 MobileElement e1 = (MobileElement) driver.findElementById(getPropertyValue("searchBox")); 
		 WebDriverWait wait = new WebDriverWait(driver, 15);
		 WebElement mobileElement = driver.findElementByXPath(getPropertyValue("closeBtn"));
		 wait.until(ExpectedConditions.visibilityOf(mobileElement));
		 mobileElement.click(); 
	}

	public static void searchItem() throws IOException, InterruptedException {
		 driver.findElementById(getPropertyValue("searchBox")).click();     
		 Thread.sleep(2000);
		 driver.findElementById(getPropertyValue("searchBox")).sendKeys(getStringSearch());
		 Thread.sleep(5000);
		 driver.findElementByXPath(getPropertyValue("searchList")).click(); // TO CLICK AFTER SEARCH RESULT 
		 driver.findElementByXPath(getPropertyValue("secondElement")).click(); 
		
	}
		

}
