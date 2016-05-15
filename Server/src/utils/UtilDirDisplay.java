package utils;

import java.io.File;

public class UtilDirDisplay {
	
	public static byte[] getHTML(String dir) {
		String result = "";
		File root = new File(dir);
		File[] files = root.listFiles();
		for(File file:files) {
			if(file.isFile()) {
				result += "F: " + file.getName() + "\n";
			} else if(file.isDirectory()) {
				result += "D: " + file.getName() + "\n";
			}
		}
		return result.getBytes();
	}
}
