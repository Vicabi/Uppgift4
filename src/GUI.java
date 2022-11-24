import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {

    protected static int PORT = 44444;
    protected Socket socket;
    protected ObjectInputStream objIn;
    protected ObjectOutputStream objOut;

    private JPanel mainPanel;
    private JPanel homeScreen;
    private JButton newGameButton;
    private JPanel loadingScreen;
    private JLabel jLabel1;
    private JPanel categoryScreen;
    private JButton category1Button;
    private JButton category2Button;
    private JButton category3Button;
    private JButton category4Button;
    private JLabel categoryJLabel;
    private JPanel gameScreen;
    private JLabel questionLabel;
    private JButton answerOptions1Button;
    private JButton answerOptions2Button;
    private JButton answerOptions3Button;
    private JButton answerOptions4Button;
    private JPanel resultScreen;
    private JButton newGameButton2;
    private JButton endGameButton;
    private JLabel player1JLabel;
    private JLabel resultJLabel;
    private JLabel player2JLabel;

    protected List<String> listString;
    protected List<Questions> listQuestions;

    public GUI(String serverAddress) throws IOException {

        socket = new Socket(serverAddress, PORT);
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        setVisible(true);

        newGameButton.addActionListener(new ActionListener() { // Knapp för att starta spelet
            @Override
            public void actionPerformed(ActionEvent e) {

                homeScreen.setVisible(false);
                loadingScreen.setVisible(true);

            }
        });
        category1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(1));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        category2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(2));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        category3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(3));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        category4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(4));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public void play() throws Exception {
        Object fromServer;
        String player = "TEMP_PLAYER1";
        String opponent = "TEMP_PLAYER2";
        try {
            fromServer = objIn.readObject();
            if(fromServer instanceof String){
                String s = (String) fromServer;
                if (s.startsWith("Välkommen")) {
                    player = s.substring(10);
                    if (player.equals("Spelare 1")) {
                        opponent = "Spelare 2";
                    } else opponent = "Spelare 1";
                }
            }
            if (fromServer instanceof Intro) {



            } else if (fromServer instanceof Waiting) {
                homeScreen.setVisible(false);
                loadingScreen.setVisible(true);
                categoryScreen.setVisible(false);
                gameScreen.setVisible(false);
                resultScreen.setVisible(false);
            } else if (fromServer instanceof List<?>) {
                homeScreen.setVisible(false);
                loadingScreen.setVisible(false);
                categoryScreen.setVisible(true);
                gameScreen.setVisible(false);
                resultScreen.setVisible(false);

                listString = (List<String>) fromServer;
                categoryJLabel.setText("Välj kategori");
                category1Button.setText(listString.get(1));
                category2Button.setText(listString.get(2));
                category3Button.setText(listString.get(3));
                category4Button.setText(listString.get(4));
            }
        } finally {
            socket.close();
        }

        //Client Rad 93
    }

    public boolean playAgain() {
        return true;
    }

    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            GUI client = new GUI(serverAddress);
            client.play();

            if (!client.playAgain()) {
                break;
            }
        }

    }

}