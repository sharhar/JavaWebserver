package core;

import java.io.PrintStream;

public class CoreUtils {
	public static void log(String message) {
		log(message, System.out);
	}
	
	public static void log(String message, PrintStream out) {
		out.println("SERVER>> " + message);
	}
}
