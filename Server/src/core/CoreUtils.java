package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CoreUtils {
	public static String[] Nbyte = {"B", "K", "M", "G", "T"};
	public static String fileImage = "data:image/png;base64,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";
	public static String folderImage = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAAxRJREFUeJzt3b1qFFEYh/FHEzGCGtCoYGVjI1h5EZLSO9BC7yA34BWYTsQLEGwl2KVWsBDEj0qsREWNxiAxXxaTgBg22d15z5kze54fnPZlZuef953NzM6AJEmSJEmSJEmSJEmSJEmSJElDOQIcTVRXhboGLAM/gZ3EawtYBd4B94DTGfZPB5gBPpL+wA9az4GTyfdSA83T3cHfW0vAdOod7bMUM3nP1YS1hzUPPMDzhIFSBuBiwtqjuEVzTqDMHtH9CPh3LWInyGqZ7g/6/+sxMJtyp/tmKmHtBeB8wvrjuALcBE4A67trE9jucJsm1he6/4vv+1oHXgI3Rvzsh5ZqJk4DfxLWr80GcBn4EF041beAOTz4kY4B11MUThWA0mb/JLiUoqgB6I8k1zYMQH8kua6RKgAXEtWtWa8CYAeIZwAqdypF0VQBOJeobs161QE8B4jXqwDMJapbM+9ukiRJxbkNPKO5L7/ra+iTvtaAV8BdmlvvO7dA9x9KrevhEMfnQBHX7N+T6FKlDvUNONumQEQAtoPqaHRbtPzhS8Q/grYCamg8v9oWiAjAt4AaGs/3tgUMQL+ttC1gAPrNDlA5A1A5R0DliugAXwNqaDxFBMAO0B1HQOXsAJUzAJVzBFSuiA7gt4DutA5A1GXcDXweXxdmaJ4iMrao3wW0nkUa2W9aHnyIC4DnAfm1bv9gAPospOtGBcATwfzsAJUzAJUragQYgPzsAJUrKgCeBObnCKhcUR3AAORnACrnCKhcUR1gBV+6kFtRAdghaIM0tKJGADgGctqkeRpLawagn8LuvzAA/WQAKhd2vhUZAP8dnE+RAbAD5OMIqJwdoHIGoHKOgMoV2QH8FpBPkQGwA+RT5AjwimA+RXaAbeBHYD0NVmQAwDGQS5EjADwRzGEHA1C1VQKf0B4dgM/B9bTfp8hi0QF4HVxP+72JLBYdgCfB9bTfUtcbcJhFun+Z0qSuF8Dx4Q/F4aYii+16SvMiqTPALM0G+06h8a0Bb4H7wB2aZwNJkiRJkiRJkiRJkiRJkiRJkiTxFzBrhnXae7wQAAAAAElFTkSuQmCC";
	
	public static void log(String message) {
		log(message, System.out);
	}
	
	public static void log(String message, PrintStream out) {
		out.println("SERVER>> " + message);
	}
	
	public static String readLocalFile(String path) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Could not read file!");
			e.printStackTrace();
			System.exit(-1);
		}
		return result.toString();
	}
	
	public static byte[] getNav(File root, String uri) {
		String[] navParts = readLocalFile("res/nav.html").split("SEPERATOR");
		String result = navParts[0].replace("[directory]",uri);
		
		File[] files = root.listFiles();
		for(File file:files) {
			String name = file.getName();
			String size = "-";
			String image = "";
			if(file.isFile()) {
				long byteAmount = file.length();
				int index = 0;
				for(int i = 0; i < 5;i++) {
					if(byteAmount < 1000) {
						index = i;
						break;
					}
					byteAmount /= 1000;
				}
				size = byteAmount + Nbyte[index];
				
				image = fileImage;
			} else if (file.isDirectory()) {
				image = folderImage;
			}
			
			String table = navParts[1].replace("[file]", name).replace("[size]", size).replace("[img]", image);
			if(uri.equals("/")) {
				table = table.replace("[root]", "/");
			} else {
				table = table.replace("[root]", uri + "/");
			}
			
			result += table;
		}
		
		result += navParts[2];
		
		return result.getBytes();
	}
	
	public static byte[] readFile(File root) {
		Path path = root.toPath();
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
		} catch (IOException e) {
			CoreUtils.log("Could not find file: " + root.getPath(), System.err);
		}
		return bytes;
	}
	
	public static byte[] error(String uri) {
		String result = "No such file or directory with name " + uri.replace("%20", " ");
		return result.getBytes();
	}
}
