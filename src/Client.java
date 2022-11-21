import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    protected static int PORT = 44444;
    protected Socket socket;
    protected ObjectInputStream in;
    protected PrintWriter out;


    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
            }
        }
    }
