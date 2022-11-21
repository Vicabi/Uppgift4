import java.io.*;
import java.net.Socket;

public class Player extends Thread {
    protected String player;
    protected Player opponent;
    protected Socket socket;
    protected ObjectInputStream in;
    protected PrintWriter out;
    protected BufferedReader read;
    protected Game game;

    public Player(Socket socket, String player,Game game) {
        this.socket = socket;
        this.player = player;
        this.game = game;
        System.out.println(player + " ansluten");

        try{
            in = new ObjectInputStream(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Välkommen:" + player);
            System.out.println("Väntar på motståndare");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    @Override
    public void run() {
        try{
            System.out.println("Väntar på motståndaren");
            String alt1 = read.readLine();
            String alt2 = read.readLine();
            String alt3 = read.readLine();
            String alt4 = read.readLine();

            System.out.println(alt1);
            System.out.println(alt2);
            System.out.println(alt3);
            System.out.println(alt4);

        }catch (Exception ignore){}
    }
}
