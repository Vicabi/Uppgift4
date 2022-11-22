import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(44444);

        try {
            while (true) {
                Game game = new Game();

                Player player1 = new Player(listener.accept(), "Spelare 1",game);
                Player player2 = new Player(listener.accept(),"Spelare 2",game);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.currentPlayer = player1;
                player1.start();
                player2.start();
                System.out.println("Slutet av main loop");

            }
        } finally {
            listener.close();
        }
    }
}