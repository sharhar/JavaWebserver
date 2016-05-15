package core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class CoreServer{
	HttpServer server;
	int port;
	boolean log;
	String root;
	
	public CoreServer(int port, String root) {
		this(port, root, false);
	}
	
	public void log(String message) {
		System.out.println("SERVER>> " + message);
	}
	
	public CoreServer(int port, String root, boolean log) {
		this.port = port;
		this.root = root;
		this.log = log;
	}
	
	public void start() {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			log("Started server at " + port);
			server.createContext("/", new Handler());
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Handler implements HttpHandler{
		public void handle(HttpExchange ex) throws IOException {
			log("Request for " + ex.getRequestURI().toString());
			String path = root + ex.getRequestURI().toString();
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			char[] data = new char[(int)file.length()];
			fileReader.read(data);
			fileReader.close();
			String response = new String(data).trim();
			ex.sendResponseHeaders(200, response.length());
			OutputStream os = ex.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	public void stop() {
		server.stop(1);
	}
}
