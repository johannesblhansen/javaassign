//package com.teamonline.service;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PasswordStrengthServiceTest {
//
//	private PasswordStrengthService passwordStrengthService;
//	
//	@Before
//	public void setup(){
//		passwordStrengthService = new PasswordStrengthServiceBean();
//	}
//	
//	@Test
//	public void passwordIsNullTest() {	
//		String password = null;
//		boolean result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("An uninstantiated string should return false", result);
//	}
//	
//	@Test
//	public void passwordLenghtTest() {
//		//Password of length 0
//		String password = "";
//		boolean result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password of length 0 (empty string) should return false", result);
//		
//		//Password is not long enough
//		password = "1qaz1qA";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password of length 7 should return false", result);
//		
//		//Edge values 8 and 19 tested for positive results 
//		password = "1qaz1qaZ";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertTrue("A password of length 8 should return true", result);
//		
//		password = "1qaz1qaZ1qaz1qaZ1qaZ";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertTrue("A password of length 10 should return true", result);
//		
//		//Password is too long
//		password = "1qaz1qaZ1qaz1qaZ1qaz1";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password of length 21 should return false", result);
//	}
//	
//	@Test
//	public void passwordCaseTest() {
//		//Password with no upper case letter
//		String password = "1qaz1qaz";
//		boolean result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with no upper case letter should return false", result);
//		
//		//Password with no lower case letter
//		password = "1QAZ1QAZ";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with no lower case letter should return false", result);
//	}
//	
//	@Test
//	public void passwordDigitsTest() {
//		//Password with no digit
//		String password = "qazqazqA";
//		boolean result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with no digit should return false", result);
//		
//		//Password with only digits
//		password = "12345678";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with only digits should return false", result);
//	}
//	
//	@Test
//	public void passwordSymbolsTest() {
//		//Password with a symbol
//		String password = "1qaz<qaZ";
//		boolean result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with a symbol should return false", result);
//		
//		//password with encoded char
//		password = "\u041f1qaz1qaZ";
//		result =  passwordStrengthService.isPasswordStrong(password);
//		assertFalse("A password with an encoded char should return false", result);
//	}
//}
