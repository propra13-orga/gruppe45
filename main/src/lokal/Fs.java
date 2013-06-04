package lokal;

/*
 *  offers static information like fs.pfad (working dir), fs.os (operating system)
 *  fs.img_pfad (path to image files) and fs.data_pfad (path to level_files)
 *  
 *  called from main.java when start button pressed in GUI
 *
 */

public class Fs {

	public static String pfad;
	public static String img_pfad;
	public static String data_pfad;
	public static String os;
	public static String os_slash;
	
	public Fs () {}
	
	public static void init() {
		Fs.pfad = System.getProperty("user.dir"); // wirking directory
		Fs.os = System.getProperty("os.name").toLowerCase(); // os
		os_slash="/"; // for linux, unix, bsd, mac something ...
		if (Fs.os.indexOf("win")>=0) { // if windows os, change slash to backslash for path
			os_slash="\\";
		}
		Fs.img_pfad=Fs.pfad+Fs.os_slash+"img"+Fs.os_slash; // path to image folder
		Fs.data_pfad=Fs.pfad+Fs.os_slash+"data"+Fs.os_slash; // path to level folder
	}
	
}
