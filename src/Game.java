import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends Thread{
    private int rounds;
    protected Player currentPlayer;
    protected Player player1;
    protected Player player2;


    public Player getPlayer() {
        return currentPlayer;
    }

    public void setPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    Historia historia = new Historia();

    String[] kategori = {"Historia", "Sport", "Film & Serier", "Geografi", "Mat", "Matematik"};

    @Override
    public void run(){
        int round = 0;
        int p1points = 0;
        int p2points = 0;

        while(round < rounds){
            try(ObjectOutputStream objectOut1 = new ObjectOutputStream(player1.socket.getOutputStream());
                ObjectOutputStream objectOut2 = new ObjectOutputStream(player2.socket.getOutputStream());
                PrintWriter out = new PrintWriter(currentPlayer.socket.getOutputStream());

                ) {
                Arrays.sort(kategori);
                out.println(kategori[1]);
                out.println(kategori[2]);
                out.println(kategori[3]);
                out.println(kategori[4]);

                //TODO: CurrentPlayer väljer en kategori
                //TODO: Båda spelarna får samma frågor
                //TODO: Spelarna skickar tillbaka antal rätt
                //TODO: I början av varje omgång uppdaterar servern poängen

                //Server skickar kategorier
                //Spelaren väljer 1 kategori
                //Servern skickar frågorna till BÅDA spelarna
                //Spelarna skickar sina svar till Servern

            } catch (IOException ignore) {}

            round++;
        }
    }

}
