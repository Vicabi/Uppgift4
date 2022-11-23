import java.io.*;
import java.net.Socket;

public class ServerPlayer extends Thread {
    protected String player;
    protected ServerPlayer opponent;
    protected Socket socket;
    protected ObjectOutputStream objOut;
    protected ObjectInputStream objIn;
    protected Game game;

    protected int points;
    protected int opponentPoints;



    public ServerPlayer(Socket socket, String player, Game game) throws IOException {
        this.socket = socket;
        this.player = player;
        this.game = game;

        objOut = new ObjectOutputStream(this.socket.getOutputStream());
//        objIn = new ObjectInputStream(this.socket.getInputStream());     //Väntar på något???
        objOut.writeObject("Välkommen " + getPlayer());
        objOut.writeObject("Väntar på motståndare");

    }

    public String getPlayer() {
        return player;
    }

    public ServerPlayer getOpponent() {
        return opponent;
    }

    public void setOpponent(ServerPlayer opponent) {
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
            objIn = new ObjectInputStream(socket.getInputStream());
            Protocol protocol = new Protocol();
            while(true){
                if(game.currentPlayer.getPlayer().equals(player)){
                    objOut.writeObject(protocol.getCategory());
                    protocol.getOutput(((String)objIn.readObject()));

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
