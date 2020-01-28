package com.sxau.soft.andsix;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(value = org.junit.runners.JUnit4.class)
public final class YFTest {
	public YFTest(){
		super();
		System.out.println("junit test constructor load.");
	}
	private static boolean isStrongPswd(String pswd){
		if(pswd == null) return false;
		if(pswd.length() < 6) {
			System.out.println("at least 6 characters needed");
			return false;
		}
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(?![a-zA-z]+$)(?!\\d+$)[a-zA-Z\\d]{6,18}$");
		return pattern.matcher(pswd).matches();
	}
	@Test public void fox() {
		System.out.println("[start]");
		try {
			org.junit.Assert.assertEquals(4, 4);
		}catch (Throwable yc){
			yc.printStackTrace(System.out);
		}
		try {
			org.junit.Assert.assertThat(isStrongPswd("Pig:qdesa123sddd"),org.hamcrest.CoreMatchers.is(true));
		}catch (Throwable yc){
			yc.printStackTrace(System.out);
		}
		try {
			org.junit.Assert.assertThat(isStrongPswd(""),org.hamcrest.CoreMatchers.is(true));
		}catch (Throwable yc){
			yc.printStackTrace(System.out);
		}
		System.out.println("[end]");
	}
}
