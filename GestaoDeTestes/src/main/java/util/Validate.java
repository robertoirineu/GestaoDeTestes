package util;

public class Validate {
	public static boolean pswdValidate(String pswd) {
		
		return !(pswd==null || pswd.length()<8);
	}
	
}
