import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(55555);

        try {
            while (true) {


            }
        } finally {
            listener.close();
        }
    }
}