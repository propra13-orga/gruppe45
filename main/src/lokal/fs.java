package lokal;

/*
 *  offers static information like fs.pfad (working dir), fs.os (operating system)
 *  fs.img_pfad (path to image files) and fs.data_pfad (path to level_files)
 *  
 *  called from main.java when start button pressed in GUI
 *
 */

public class fs {

	public static String pfad;
	public static String img_pfad;
	public static String data_pfad;
	public static String os;
	public static String os_slash;
	
	public fs () {}
	
	public static void init() {
		fs.pfad = System.getProperty("user.dir"); // wirking directory
		fs.os = System.getProperty("os.name").toLowerCase(); // os
		os_slash="/"; // for linux, unix, bsd, mac something ...
		if (fs.os.indexOf("win")>=0) { // if windows os, change slash to backslash for path
			os_slash="\\";
		}
		fs.img_pfad=fs.pfad+fs.os_slash+"img"+fs.os_slash; // path to image folder
		fs.data_pfad=fs.pfad+fs.os_slash+"data"+fs.os_slash; // path to level folder
	}
	
}
