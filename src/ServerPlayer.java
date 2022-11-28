import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

public class ServerPlayer extends Thread {
    protected String player;
    protected ServerPlayer opponent;
    protected Socket socket;
    protected ObjectOutputStream objOut;
    protected ObjectInputStream objIn;
    protected Game game;

    protected int points;
    protected int opponentPoints;

    protected boolean[][] resultArray;

    /*

        [true][false][false]
        [null][null][null]
        [null][null][null]

     */

    public ServerPlayer(Socket socket, String player, Game game) throws IOException {
        this.socket = socket;
        this.player = player;
        this.game = game;

        resultArray = generateArray();

        objOut = new ObjectOutputStream(this.socket.getOutputStream());
//        objIn = new ObjectInputStream(this.socket.getInputStream());     //Väntar på något???
        objOut.writeObject("Välkommen " + getPlayer());
//        objOut.writeObject("Väntar på motståndare");

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

    public void checkWinner() {

//        if(points > opponentPoints){
//            textOut.println("Du vann!");
//        }
//        else if(opponentPoints > points){
//            textOut.println("Du förlorade!");
//        }
//        else textOut.println("Det blev oavgjort!");
    }

    public boolean[][] generateArray() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/Properties.properties"));
        } catch (IOException e) {
            System.out.println("Kunde inte läsa properties");
        }
        int questions = Integer.parseInt(properties.getProperty("QUESTIONS", "2"));
        int maxRounds = Integer.parseInt(properties.getProperty("ROUNDS", "2"));
        return new boolean[questions][maxRounds];
    }

    @Override
    public void run() {
        try {
            objIn = new ObjectInputStream(socket.getInputStream());
//            Protocol protocol = new Protocol();
            while (true) {
                if(game.protocol.state == game.protocol.INITIAL){
                    if(game.currentPlayer != opponent){
                        objOut.writeObject(game.protocol.getOutput("",true));

                    }
                    else objOut.writeObject(game.protocol.getOutput("", false));
                }
                if(game.protocol.state == game.protocol.WAITING){
                    if(game.currentPlayer != opponent){
                        objOut.writeObject(game.protocol.getOutput("",true));

                    }
                    else objOut.writeObject(game.protocol.getOutput("", false));

                }
                if(game.protocol.state == game.protocol.SENDING_CATEGORIES){
                    if(game.currentPlayer != opponent){
                        game.questionsReady = false;
                        objOut.writeObject(game.protocol.getOutput("",true));

                    }
                    else objOut.writeObject(game.protocol.getOutput("", false));
                }
                if(game.protocol.state == game.protocol.SENDING_QUESTIONS){
                    if(game.currentPlayer != opponent){
                        String temp = (String) objIn.readObject();
                        List<Questions> tempC = (List<Questions>) game.protocol.getOutput(temp, true);
                        game.setChosenQuestions(tempC);
                        objOut.writeObject(game.getChosenQuestions());
                        game.questionsReady = true;

                        game.tempPlayer = game.currentPlayer;
                        game.currentPlayer = null;
                    }
                    else if(game.questionsReady){
                        objOut.writeObject(game.getChosenQuestions());
                        game.questionsReady = false;
                    }

                }




            }


            //TODO: 1 Protocol i game?


        } catch (Exception ignore) {
        }
    }
}


//if(game.currentPlayer.getPlayer().equals(player)){
//        textOut.println("Välj kategori: ");
//        String[] category = game.getCategory();
//        textOut.println(category[1] + ", " +  category[2] + ", " + category[3] + ", " +  category[4]);
//        choice = textIn.readLine();
//        }
//        Category questions = game.getQuestions(choice);


//if (protocol.state == protocol.CHOOSING) {
//        if (game.currentPlayer != opponent) { //Om det är spelarens tur att välja kategori
//        System.out.println(game.currentPlayer.player + " tur att välja");
//        String temp = (String) objIn.readObject();
//        List<Questions> tempC = (List<Questions>) protocol.getOutput(temp);
//        game.setChosenQuestions(tempC);
//        objOut.writeObject(game.getChosenQuestions());
//
//        if (game.currentPlayer == game.player1) {
//        game.currentPlayer = game.player2;
//        System.out.println(game.currentPlayer.player + " väljer nästa gång");
//
//        } else if (game.currentPlayer == game.player2) {
//        game.currentPlayer = game.player1;
//        System.out.println(game.currentPlayer.player + " väljer nästa gång");
//        }
//        } else if (game.currentPlayer == opponent) {
//        objOut.writeObject(game.getChosenQuestions());
//        System.out.println("Skickar frågor till" + game.player2.player);
//        }
//        } else {
//        objOut.writeObject(protocol.getOutput(""));
//        }