import java.io.*;
import java.net.Socket;

public class Client {

    protected static int PORT = 44444;
    protected Socket socket;
    protected ObjectInputStream objIn;
    protected PrintWriter textOut;


    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        objIn = new ObjectInputStream(socket.getInputStream());
        textOut = new PrintWriter(socket.getOutputStream(), true);
    }

    public void play() throws Exception {
        Object choice;
        String player = "TEMP_PLAYER1";
        String opponent = "TEMP_PLAYER2";
        Object tempObj;
        Category question;
        System.out.println("I början av play - " + player);
        try {
            System.out.println("Början av try i play");
            choice = objIn.readObject();
            String s = (String) choice;
            if (s.startsWith("Välkommen")) {
                player = s.substring(10);
                System.out.println("Spelarens namn " + player);
                if (player.equals("Spelare 1")) {
                    opponent = "Spelare 2";
                } else opponent = "Spelare 1";
            }
            while(true){
//                tempObj = objIn.readObject();
//                if(tempObj instanceof Category){
//                    question = (Category) tempObj;
//                }



            }
        } finally {
            socket.close();
        }

        //Client Rad 93
    }
    public boolean playAgain(){
        return true;
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
            client.play();

            if (!client.playAgain()) {
                break;
            }
        }
    }
}
