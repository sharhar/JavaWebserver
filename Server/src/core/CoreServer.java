package core;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class CoreServer{
	HttpServer server;
	int port;
	boolean log;
	String root;
	
	public CoreServer(int port, String root) {
		this(port, root, false);
	}
	
	public CoreServer(int port, String root, boolean log) {
		this.port = port;
		this.root = root;
		this.log = log;
	}
	
	public void start() {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			CoreUtils.log("Started server at " + port);
			server.createContext("/", new CoreHandler(root));
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		server.stop(1);
	}
}
