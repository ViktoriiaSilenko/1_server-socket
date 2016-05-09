
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

	private Socket clientSocket;

	public WorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;

	}

	public void run() {

		try (InputStream is = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream();) {

			// accept data from client
			BufferedReader reader = new BufferedReader(new InputStreamReader(is)); // convert
																					// bytes
																					// stream
																					// to
																					// symbols
																					// stream

			PrintStream writer = new PrintStream(os, true); // bytes stream

			String input = reader.readLine(); // receive from client
			System.out.println("server receives from a client: " + input);

			

			writer.println(input); // echo server - send back to client data
									// what it received
			System.out.println("server sends to a client " + input);

			clientSocket.close();

		}

		catch (IOException ex) {
			System.out.println(ex.getMessage() + " in WorkerRunnable");

		}

	}
}
