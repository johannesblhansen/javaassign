package com.teamonline.model;

public class PasswordStrengthModel {

	private boolean lengthCondition;	
	private boolean digitCondition;	
	private boolean symbolCondition;	
	private boolean upperLowerCaseCondition;
	private boolean nonValidInputCondition;
	
	public PasswordStrengthModel(){
		lengthCondition = true;
		digitCondition = true;
		symbolCondition = true;
		upperLowerCaseCondition = true;
		nonValidInputCondition = true;
	}
	
	public boolean isLengthCondition() {
		return lengthCondition;
	}
	public void setLengthCondition(boolean lengthCondition) {
		this.lengthCondition = lengthCondition;
	}
	public boolean isDigitCondition() {
		return digitCondition;
	}
	public void setDigitCondition(boolean digitCondition) {
		this.digitCondition = digitCondition;
	}
	public boolean isSymbolCondition() {
		return symbolCondition;
	}
	public void setSymbolCondition(boolean symbolCondition) {
		this.symbolCondition = symbolCondition;
	}
	public boolean isUpperLowerCaseCondition() {
		return upperLowerCaseCondition;
	}
	public void setUpperLowerCaseCondition(boolean upperLowerCaseCondition) {
		this.upperLowerCaseCondition = upperLowerCaseCondition;
	}
	
	public boolean isNonValidInputCondition() {
		return nonValidInputCondition;
	}
	public void setNonValidInputCondition(boolean nonValidInputCondition) {
		this.nonValidInputCondition = nonValidInputCondition;
	}
	
	public void invalidateAll(){
		lengthCondition = false;	
		digitCondition = false;
		symbolCondition = false;
		upperLowerCaseCondition  = false;
		nonValidInputCondition  = false;
	}
	
	public boolean isStrengthValid() {
		if (lengthCondition && digitCondition && symbolCondition && upperLowerCaseCondition && nonValidInputCondition){
			return true;
		} else {
			return false;
		}
	}
}
