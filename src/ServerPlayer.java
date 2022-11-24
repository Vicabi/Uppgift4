import java.io.*;
import java.net.Socket;
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

    public ServerPlayer(Socket socket, String player, Game game) throws IOException {
        this.socket = socket;
        this.player = player;
        this.game = game;

        resultArray = generateArray();

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
    public boolean[][] generateArray(){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("src/Properties.properties"));
        }catch (IOException e){
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
            Protocol protocol = new Protocol();
            while(true){
                System.out.println("ServerPlayer run");
                if(protocol.state != protocol.CHOOSING){
                    objOut.writeObject(protocol.getOutput(null));
                }
                else objOut.writeObject(protocol.getOutput((String) objIn.readObject()));
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
