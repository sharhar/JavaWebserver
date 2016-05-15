package core;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class CoreHandler implements HttpHandler{
	String root;
	
	public CoreHandler(String root) {
		this.root = root;
	}
	
	public static byte[] readHTMLFile(HttpExchange ex, String root) {
		File file = new File(root + ex.getRequestURI().toString().replace("%20", " "));
		if(file.isDirectory()) {
			file = new File(root + ex.getRequestURI().toString().replace("%20", " ") + "/index.html");
		}
		Path path = file.toPath();
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(path);
		} catch (IOException e) {
			CoreUtils.log("Could not find file: " + file.getPath(), System.err);
		}
		return bytes;
	}
	
	public void handle(HttpExchange ex) throws IOException {
		CoreUtils.log("Request for " + ex.getRequestURI().toString());
		byte[] bytes = readHTMLFile(ex, root);
		ex.sendResponseHeaders(200, bytes.length);
		OutputStream os = ex.getResponseBody();
		os.write(bytes);
		os.close();
	}
}
