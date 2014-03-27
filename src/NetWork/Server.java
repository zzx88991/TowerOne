package NetWork;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread{
	public static final int PORT_NUMBER = 4000;
	public static final String HOST_NAME = "localhost";
	private ServerSocket myServerSocket; // client request source
	private Vector<Thread> ThreadsCollection;
	  
	public static void main(String args[]) {
		Server server = new Server(PORT_NUMBER);
		// always call the start() method on a Thread. Don't call the run method.
		server.start();
	}
	
	public Server(int port) {
		ThreadsCollection = new Vector<Thread>();
		try {
			myServerSocket = new ServerSocket(PORT_NUMBER);
		} catch (IOException e) {
			System.out.println("In Server.Server(),the constructor");
		}
	}
	
	public void run() {
		try {
			while (true) {
				// accept blocks until request comes over socket
				Socket intoServer = myServerSocket.accept();
				// For every new connection, start a new Thread that will
				// communicate with that client. Each one will have access
				// to the common collection of all users who are connected.
				Thread t = new Liason(intoServer,this);
				// always call the start() method on a Thread. Don't call the run method.
				t.start();
			}
		} catch (IOException e) {
//			System.out.println("In Server.run");
//			e.printStackTrace();
		}
	}
	public Vector<Thread> get(){
		return ThreadsCollection;
	}
}
