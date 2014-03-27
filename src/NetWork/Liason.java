package NetWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class Liason extends Thread {
	private Socket socketFromServer;

	private ObjectInputStream readFromClient;
	private ObjectOutputStream writeToClient;
	private Server server;

	public Liason(Socket socketFromServer,
			Server server) {
		this.socketFromServer = socketFromServer;
		this.server = server;

	}

	@Override
	public void run() {
		server.get().add(this);
		// Open the input and output streams so the
		// client can interact with the collection
		try {
			writeToClient = new ObjectOutputStream(
					socketFromServer.getOutputStream());
			readFromClient = new ObjectInputStream(
					socketFromServer.getInputStream());
			
		} catch (IOException e) {
			System.out
					.println("Exception thrown while obtaining input & output streams");
//			e.printStackTrace();
			return;
		}

		boolean stayConnected = true;
		while (stayConnected) {
			try {
				Object fromClient = readFromClient.readObject();
				if(fromClient instanceof Vector){
				if(!(boolean) ((Vector<Object>) fromClient).get(4)){
					this.sendMessage(fromClient);
					socketFromServer.close();
					writeToClient.close();
					readFromClient.close();
					server.get().remove(this);
					stayConnected = false;
				}
				else
					this.sendMessage(fromClient);
				}
				else if(fromClient.equals('q')){
					socketFromServer.close();
					writeToClient.close();
					readFromClient.close();
					server.get().remove(this);
					stayConnected = false;
				}
				else
				this.sendMessage(fromClient);
			} catch (IOException e1) {
				System.out.println("In Liason.run when reading from client");
			
				server.get().remove(this);
				stayConnected = false;
				return;
			} catch (ClassNotFoundException e1) {
				System.out
						.println("Error in Liason.run when trying to read from client");
				stayConnected = false;

//				e1.printStackTrace();
			}
		}

	}

	public void doWrite(Object o) {
		try {

			writeToClient.reset();
			writeToClient.writeObject(o);
			writeToClient.flush();


		} catch (Exception e) {
			server.get().remove(this);
		}
	}

	public void sendMessage(Object o) {

		Vector<Thread> readFromServer = server.get();
			for (int i = 0; i < readFromServer.size(); i++) {
				if (!readFromServer.get(i).equals(this))
					((Liason) readFromServer.get(i)).doWrite(o);
			}
		
	}
}
