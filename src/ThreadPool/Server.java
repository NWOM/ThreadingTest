package ThreadPool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    private volatile boolean running = true;

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket) {
        try (
                PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader fromSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String clientMessage;
            while ((clientMessage = fromSocket.readLine()) != null) {
                toSocket.println("Server received: " + clientMessage);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        int port = Integer.parseInt(System.getenv().getOrDefault("SERVER_PORT", "8010"));
        int poolSize = Integer.parseInt(System.getenv().getOrDefault("POOL_SIZE", "10"));
        Server server = new Server(poolSize);

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(70000);
            System.out.println("Server is listening on port " + port);

            while (server.running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    server.threadPool.execute(() -> server.handleClient(clientSocket));
                } catch (IOException e) {
                    if (!server.running) {
                        System.out.println("Server is shutting down...");
                        break;
                    }
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
