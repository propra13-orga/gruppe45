package lokal;

public class fs {

	public static String pfad;
	public static String img_pfad;
	public static String data_pfad;
	public static String os;
	public static String os_slash;
	
	public fs () {}
	
	public static void init() {
		fs.pfad = System.getProperty("user.dir");
		fs.os = System.getProperty("os.name").toLowerCase();
		os_slash="/";
		if (fs.os.indexOf("win")>=0) {
			os_slash="\\";
		}
		fs.img_pfad=fs.pfad+fs.os_slash+"img"+fs.os_slash;
		fs.data_pfad=fs.pfad+fs.os_slash+"data"+fs.os_slash;
		//System.out.println("Bilder in: " + fs.img_pfad);
		//System.out.println("Level in: " + fs.data_pfad);
	}
	
}
