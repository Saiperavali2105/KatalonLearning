import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

CustomKeywords.'com.qapitol.DefaultFunctions.openBrowserAndNavigateToUrl'()

WebUI.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/a_New User - SignUp'))

WebUI.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input'), ngoName)

WebUI.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1'), email)

WebUI.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2'), phone)

WebUI.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3'), city)

WebUI.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3_4'), state)

WebUI.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/button_Register'))

WebDriver driver = DriverFactory.getWebDriver()

WebUI.waitForAlert(2 // that's a LONG time!
    )

String AlertText = driver.switchTo().alert().getText()

String loginID = AlertText.substring(15)

WebUI.acceptAlert(FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Web/End to End Flow/LoginTest'), [('loginId') : loginID], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementNotPresent(findTestObject('Web Objects/Page_Be the Hero/CaseDetails'), 5)

CustomKeywords.'com.qapitol.DefaultFunctions.logout'()

