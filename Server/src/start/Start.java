package start;

import java.util.Scanner;

import core.CoreServer;

public class Start {
	public static void main(String[] args) {
		CoreServer core = new CoreServer(80, "C:\\Users\\wiish\\Git\\JavaWebserver\\website", true);
		core.start();
		
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		while(running) {
			String input = scan.nextLine();
			if(input.equals("exit")) {
				running = false;
			} else if (input.equals("restart")) {
				System.out.println("==============RESTARTING=============");
				core.stop();
				core = new CoreServer(80, "C:\\Users\\wiish\\Git\\JavaWebserver\\website", true);
				core.start();
			}
		}
		scan.close();
		core.stop();
	}
}
