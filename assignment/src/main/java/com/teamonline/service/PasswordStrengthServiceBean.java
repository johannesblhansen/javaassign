package com.teamonline.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.teamonline.model.PasswordStrengthModel;

@Service
public class PasswordStrengthServiceBean implements PasswordStrengthService {

	private static Logger logger = Logger.getLogger(PasswordStrengthServiceBean.class);

	/**
	 * Takes a password as a String and validates if the password is Strong. For
	 * a password to be strong it must fulfill the following criteria:
	 * <ul>
	 * <li>Must be at least 8 characters long</li>
	 * <li>Must be no more than 20 characters long</li>
	 * <li>Must contain at least one digit</li>
	 * <li>Must have at least one upper case letter</li>
	 * <li>Must have at least one lower case letter</li>
	 * </ul>
	 * 
	 * returns true if the above criteria is met.
	 */
	@Override
	public PasswordStrengthModel isPasswordStrong(String password) {

		PasswordStrengthModel passwordStrengthModel = new PasswordStrengthModel();

		if (password == null) {
			logger.debug("Checking strenght of password: " + password + " password is null");
			passwordStrengthModel.invalidateAll();
			return passwordStrengthModel;
			
		}
		if ((password.length() < 8) || (password.length() > 20)) { // Check length constraints
			logger.debug("Checking strenght of password: " + password + " password has a wrong lenghth: "
					+ password.length());
			passwordStrengthModel.setLengthCondition(false);
		}
		if (password.equals(password.toLowerCase()) || password.equals(password.toUpperCase())) {
			logger.debug("Checking strenght of password: " + password + " password fails multible case contraint");
			passwordStrengthModel.setUpperLowerCaseCondition(false);
		}
		if (!password.matches(".*\\d+.*") || !password.matches(".*[a-zA-Z]+.*")) { // Check digits constraints
			logger.debug("Checking strenght of password: " + password + " password has no digit");
			passwordStrengthModel.setDigitCondition(false);
		}
		if (containsNonDigitOrChar(password)) { // Check symbol constraints
			logger.debug("Checking strenght of password: " + password + " password contains non digit char");
			passwordStrengthModel.setSymbolCondition(false);
		}
		return passwordStrengthModel;
	}

	private boolean containsNonDigitOrChar(String password) {
		Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(password);
		return matcher.find();
	}
}
