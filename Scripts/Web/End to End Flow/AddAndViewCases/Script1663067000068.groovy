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

WebUI.callTestCase(findTestCase('Web/End to End Flow/Login'), [('loginid') : '5540fc08'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/a_Register new case'))

WebUI.setText(findTestObject('Web Objects/Page_Be the Hero/caseTitle'), casetitle)

WebUI.setText(findTestObject('Web Objects/Page_Be the Hero/caseDescription'), casedescription)

WebUI.setText(findTestObject('Web Objects/Page_Be the Hero/caseValue'), casevalue)

WebUI.click(findTestObject('Web Objects/Page_Be the Hero/button_Register'))

//String caseDetails = WebUI.getText(findTestObject('Web Objects/Page_Be the Hero/RegisteredCaseDetails'))
List<WebElement> elements = WebUI.findWebElements(findTestObject('Web Objects/Page_Be the Hero/RegisteredCaseDetails'), 10)

int size = elements.size()

int flag = 0

for (WebElement caseDetail : elements) {
    String casedesp = caseDetail.getText()

    if (casedesp.contains(casetitle)) {
        println('Case is created successfully')
    } else {
        flag = (flag + 1)
    }
}

WebUI.verifyNotEqual(size, flag)

WebUI.closeBrowser()

