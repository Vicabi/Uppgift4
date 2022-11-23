import java.io.*;
import java.net.Socket;

public class Player extends Thread {
    protected String player;
    protected Player opponent;
    protected Socket socket;
    protected ObjectOutputStream objOut;
    protected BufferedReader textIn;
    protected Game game;

    protected int points;
    protected int opponentPoints;



    public Player(Socket socket, String player,Game game) throws IOException {
        this.socket = socket;
        this.player = player;
        this.game = game;

        objOut = new ObjectOutputStream(socket.getOutputStream());
        textIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        objOut.writeObject("Välkommen " + player);
        objOut.writeObject("Väntar på motståndare");

    }

    public String getPlayer() {
        return player;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void checkWinner(){

//        if(points > opponentPoints){
//            textOut.println("Du vann!");
//        }
//        else if(opponentPoints > points){
//            textOut.println("Du förlorade!");
//        }
//        else textOut.println("Det blev oavgjort!");
    }

    @Override
    public void run() {
        try {
//            objOut = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
            String command = textIn.readLine();
            while(true){
                if(command.startsWith("CHOICE") && game.currentPlayer.getPlayer().equals(player)){
                    String[] categories = game.getCategory();
                    System.out.println(categories[1] + ", " +  categories[2] + ", " + categories[3] + ", " +  categories[4]);
                    objOut.writeObject(game.getQuestions(categories[sysIn.read()]));
                }


            }

            //Player Rad 83


        }catch (Exception ignore){}
    }
}



//if(game.currentPlayer.getPlayer().equals(player)){
//        textOut.println("Välj kategori: ");
//        String[] category = game.getCategory();
//        textOut.println(category[1] + ", " +  category[2] + ", " + category[3] + ", " +  category[4]);
//        choice = textIn.readLine();
//        }
//        Category questions = game.getQuestions(choice);
