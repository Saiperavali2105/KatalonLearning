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
import org.openqa.selenium.*
import org.openqa.selenium.By as findElements

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.URL)

WebUI.callTestCase(findTestCase('Web/End to End Flow/LoginTest'), [('loginId') : '5540fc08'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web Objects/Page_Be the Hero/a_Account details'))

//WebUI.verifyElementText(findTestObject('Page_Be the Hero/acctDetailsDescp'), 'TestNGO2')
List<WebElement> elements = WebUI.findWebElements(findTestObject('Object Repository/Page_Be the Hero/acctDetailsDescp'),
	10)

int size = elements.size()

int flag = 0

for (WebElement actDetail : elements) {
	String acctdesp = actDetail.getText()

	if (acctdesp.contains('5540fc08')) {
		println('Login is created successfully')
	} else {
		flag = (flag + 1)
	}
}

WebUI.verifyNotEqual(size, flag)

WebUI.closeBrowser()

