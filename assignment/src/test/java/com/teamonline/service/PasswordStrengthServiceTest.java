package com.teamonline.service;

import org.junit.Before;
import org.junit.Test;

import com.teamonline.model.PasswordStrengthModel;

import static org.junit.Assert.*;

public class PasswordStrengthServiceTest {

	private PasswordStrengthService passwordStrengthService;
	
	@Before
	public void setup(){
		passwordStrengthService = new PasswordStrengthServiceBean();
	}
	
	@Test
	public void passwordIsNullTest() {	
		String password = null;
		boolean result =  passwordStrengthService.isPasswordStrong(password).isStrengthValid();
		assertFalse("An uninstantiated string should return false", result);
	}
	
	@Test
	public void passwordLenghtTest() {
		//Password of length 0
		String password = "";
		PasswordStrengthModel result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password of length 0 (empty string) should return a model with lengthcondition false", result.isLengthCondition());
		
		//Password is not long enough
		password = "1qaz1qA";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password of length 7 should return a model with lengthcondition false", result.isLengthCondition());
		
		//Edge values 8 and 19 tested for positive results 
		password = "1qaz1qaZ";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertTrue("A password of length 8 should return true", result.isLengthCondition());
		
		password = "1qaz1qaZ1qaz1qaZ1qaZ";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertTrue("A password of length 10 should return a model with lengthcondition false", result.isLengthCondition());
		
		//Password is too long
		password = "1qaz1qaZ1qaz1qaZ1qaz1";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password of length 21 should return a model with lengthcondition false", result.isLengthCondition());
	}
	
	@Test
	public void passwordCaseTest() {
		//Password with no upper case letter
		String password = "1qaz1qaz";
		PasswordStrengthModel result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with no upper case letter should return false", result.isUpperLowerCaseCondition());
		
		//Password with no lower case letter
		password = "1QAZ1QAZ";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with no lower case letter should return false", result.isUpperLowerCaseCondition());
		
		//Password with no letters a all.
		password = "1";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with no letters at all should return false", result.isUpperLowerCaseCondition());
	}
	
	@Test
	public void passwordDigitsTest() {
		//Password with no digit
		String password = "qazqazqA";
		PasswordStrengthModel result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with no digit should return false", result.isDigitCondition());
		
		//Password with only digits
		password = "12345678";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with only digits should return false", result.isDigitCondition());
	}
	
	@Test
	public void passwordSymbolsTest() {
		//Password with a symbol
		String password = "1qaz<qaZ";
		PasswordStrengthModel result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with a symbol should return false", result.isSymbolCondition());
		
		//password with encoded char
		password = "\u041f1qaz1qaZ";
		result =  passwordStrengthService.isPasswordStrong(password);
		assertFalse("A password with an encoded char should return false", result.isSymbolCondition());
	}
}
