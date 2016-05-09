
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		ExecutorService workers = Executors.newFixedThreadPool(4);

		System.out.println(" 1. server socket run");

		ServerSocket server = null;
		try {
			server = new ServerSocket(4040);
		} catch (IOException e) {
			throw new RuntimeException("Problem with ServerSocket on server side", e);
		}

		while (true) { // as server works constantly we have an infinite loop
			try {

				Socket clientSocket = server.accept(); // get client socket
														// after connection

				System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());
				workers.execute(new WorkerRunnable(clientSocket));

			} catch (IOException ex) {
				System.out.println(ex.getMessage() + " in " + "server socket main");

			}
		}
	}
}

// with out threads

// as server works constantly we have an infinite loop
/*
 * while (true) {
 * 
 * try (ServerSocket server = new ServerSocket(4040); // server tcp socket
 * 
 * Socket clientSocket = server.accept(); // get client socket // after
 * connection
 * 
 * 
 * InputStream is = clientSocket.getInputStream(); OutputStream os =
 * clientSocket.getOutputStream();) {
 * 
 * ExecutorService workers = Executors.newCachedThreadPool();
 * workers.execute(new WorkerRunnable(clientSocket));
 * 
 * 
 * 
 * // accept data from client BufferedReader reader = new BufferedReader(new
 * InputStreamReader(is)); // convert // bytes // stream // to // symbols //
 * stream
 * 
 * PrintStream writer = new PrintStream(os, true);
 * 
 * 
 * 
 * 
 * 
 * String input = reader.readLine(); // receive from client System.out.println(
 * "server receives from a client: " + input);
 * 
 * if ("exit".equals(input)) { break; }
 * 
 * System.out.println("server sends to a client " + input);
 * 
 * writer.println(input); // echo server - send back to client data // what it
 * received
 * 
 * 
 * 
 * 
 * }
 * 
 * catch (IOException ex) { System.out.println(ex.getMessage() + " in " +
 * "server socket main");
 * 
 * } }
 */
