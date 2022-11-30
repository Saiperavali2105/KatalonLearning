package com.qapitol
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import junit.framework.Assert as Assert


class DefaultFunctions {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	@Keyword
	def login(String loginID) {
		WebUiBuiltInKeywords.setText(findTestObject('Web Objects/Page_Be the Hero/loginID'), loginID)

		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/button_LogIn'))
	}

	@Keyword
	def logout() {
		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/logout'))
		WebUiBuiltInKeywords.closeBrowser()
	}

	@Keyword
	def deleteCase(String caseName) {

		List<WebElement> elements = WebUiBuiltInKeywords.findWebElements(findTestObject('Web Objects/Page_Be the Hero/RegisteredCaseDetails'), 10)

		int size = elements.size()

		int flag = 1

		for (WebElement caseDetail : elements) {
			String casedesc = caseDetail.getText()

			if (casedesc.contains(caseName)) {
				WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/deleteCase', [('case') : flag]))
			} else {
				flag = (flag + 1)
			}
		}

		WebUiBuiltInKeywords.verifyNotEqual(size, flag+1)
	}

	@Keyword
	def verifyDeletedCase(String caseName) {
		List<WebElement> elements = WebUiBuiltInKeywords.findWebElements(findTestObject('Web Objects/Page_Be the Hero/RegisteredCaseDetails'), 10)

		for (WebElement caseDetail : elements) {
			String casedesc = caseDetail.getText()
			WebUiBuiltInKeywords.verifyTextNotPresent(caseName,false)
		}
	}

	@Keyword
	def openBrowserAndNavigateToUrl() {
		WebUiBuiltInKeywords.openBrowser('')
		WebUiBuiltInKeywords.navigateToUrl(GlobalVariable.URL)
	}
	@Keyword
	def String createNewUser(String ngoName,String email,String phone, String city,String state) {

		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/a_New User - SignUp'))

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input'), ngoName)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1'), email)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2'), phone)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3'), city)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3_4'), state)

		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/button_Register'))

		WebDriver driver = DriverFactory.getWebDriver()

		WebUiBuiltInKeywords.waitForAlert(2)

		String AlertText = driver.switchTo().alert().getText()

		String loginID = AlertText.substring(15)

		WebUiBuiltInKeywords.acceptAlert(FailureHandling.CONTINUE_ON_FAILURE)
		return loginID
	}
	@Keyword
	def addNewCase(String casetitle,String casedescription,String casevalue) {
		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/a_Register new case'))

		WebUiBuiltInKeywords.setText(findTestObject('Web Objects/Page_Be the Hero/caseTitle'), casetitle)

		WebUiBuiltInKeywords.setText(findTestObject('Web Objects/Page_Be the Hero/caseDescription'), casedescription)

		WebUiBuiltInKeywords.setText(findTestObject('Web Objects/Page_Be the Hero/caseValue'), casevalue)

		WebUiBuiltInKeywords.click(findTestObject('Web Objects/Page_Be the Hero/button_Register'))
	}
	@Keyword
	def verifyCasePresent(String caseTitle) {
		List<WebElement> elements = WebUiBuiltInKeywords.findWebElements(findTestObject('Web Objects/Page_Be the Hero/RegisteredCaseDetails'), 10)

		int size = elements.size()

		int flag = 0

		for (WebElement caseDetail : elements) {
			String casedesp = caseDetail.getText()

			if (casedesp.contains(caseTitle)) {
				println('Case is created successfully')
			} else {
				flag = (flag + 1)
			}
		}

		WebUiBuiltInKeywords.verifyNotEqual(size, flag)
	}
	@Keyword
	def createInvalidUser(String ngoName,String email,String phone, String city,String state) {

		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/a_New User - SignUp'))

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input'), ngoName)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1'), email)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2'), phone)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3'), city)

		WebUiBuiltInKeywords.setText(findTestObject('Object Repository/Web Objects/Page_Be the Hero/input_1_2_3_4'), state)

		WebUiBuiltInKeywords.click(findTestObject('Object Repository/Web Objects/Page_Be the Hero/button_Register'))

		WebDriver driver = DriverFactory.getWebDriver()

		WebUiBuiltInKeywords.waitForAlert(2)

		String AlertText = driver.switchTo().alert().getText()
		Assert.assertTrue('Invalid Registration', AlertText.contains('Registration error'))
	}
}