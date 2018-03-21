package fr.epsi.myEpsi.beans;

public class logLevel {
	public final static int DEBUG = 0;
	public final static int INFO = 1;
	public final static int ERROR = 2;
	
	public static int actualLogLevel = DEBUG;
	
	public static String getLevel() {
		String lvl = "";
		if(actualLogLevel == 0) {
			lvl = "DEBUG";
		}
		if(actualLogLevel == 1) {
			lvl = "INFO";
		}
		if(actualLogLevel == 2) {
			lvl = "ERROR";
		}
		return lvl;
	}
	
	public static void setLevel(int lvl) {
		actualLogLevel = lvl;
	}
}
