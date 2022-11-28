import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;
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
    protected boolean[] answers;
    protected int currentQuestion;
    protected boolean answered;

    public GUI(String serverAddress) throws IOException {

        socket = new Socket(serverAddress, PORT);
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        setVisible(true);
        mainPanel.add(homeScreen);
        mainPanel.add(loadingScreen);
        mainPanel.add(categoryScreen);
        mainPanel.add(gameScreen);
        mainPanel.add(resultScreen);

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
                    objOut.writeObject(listString.get(0));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        category2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(1));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        category3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(2));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        category4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    objOut.writeObject(listString.get(3));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        answerOptions1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //kolla om alternativ 1 är rätt
                answered = true;
                if (listQuestions.get(0).getA1().equals(listQuestions.get(0).getCorrectAnswer())) {
                    answers[currentQuestion] = true;
                    answerOptions1Button.setBackground(Color.GREEN);
                } else {
                    answers[currentQuestion] = false;
                    answerOptions1Button.setBackground(Color.RED);
                }

            }
        });

        answerOptions2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answered = true;
                if (listQuestions.get(0).getA2().equals(listQuestions.get(0).getCorrectAnswer())) {
                    answers[currentQuestion] = true;
                    answerOptions2Button.setBackground(Color.GREEN);
                } else {
                    answers[currentQuestion] = false;
                    answerOptions2Button.setBackground(Color.RED);
                }

            }
        });

        answerOptions3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answered = true;
                if (listQuestions.get(0).getA3().equals(listQuestions.get(0).getCorrectAnswer())) {
                    answers[currentQuestion] = true;
                    answerOptions3Button.setBackground(Color.GREEN);
                } else {
                    answers[currentQuestion] = false;
                    answerOptions3Button.setBackground(Color.RED);
                }

            }
        });

        answerOptions4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answered = true;
                if (listQuestions.get(0).getA4().equals(listQuestions.get(0).getCorrectAnswer())) {
                    answers[currentQuestion] = true;
                    answerOptions4Button.setBackground(Color.GREEN);
                } else {
                    answers[currentQuestion] = false;
                    answerOptions4Button.setBackground(Color.RED);
                }
            }
        });
    }

    public void play() throws Exception {
        Object fromServer;
        String player = "TEMP_PLAYER1";
        String opponent = "TEMP_PLAYER2";
        String turn = player;
        try {
            System.out.println("GUI play");
            fromServer = objIn.readObject();
            if (fromServer instanceof String) {
                String s = (String) fromServer;
                if (s.startsWith("Välkommen")) {
                    player = s.substring(10);
                    if (player.equals("Spelare 1")) {
                        opponent = "Spelare 2";
                    } else opponent = "Spelare 1";
                }
                System.out.println("Spelare - " + player);
                setTitle(player);
            }
            System.out.println("Innan loop i play");
            while (true) {
                fromServer = objIn.readObject();
                if (fromServer instanceof Intro) {
                    System.out.println("instanceof intro");
                    homeScreen.setVisible(true);
                    loadingScreen.setVisible(false);
                    categoryScreen.setVisible(false);
                    gameScreen.setVisible(false);
                    resultScreen.setVisible(false);

                } else if (fromServer instanceof Waiting) {
                    System.out.println("instanceof waiting");
                    homeScreen.setVisible(false);
                    loadingScreen.setVisible(true);
                    categoryScreen.setVisible(false);
                    gameScreen.setVisible(false);
                    resultScreen.setVisible(false);

                }else if (fromServer instanceof GameFinished) {
                    System.out.println("instanceof gamefinished");
                    homeScreen.setVisible(false);
                    loadingScreen.setVisible(false);
                    categoryScreen.setVisible(false);
                    gameScreen.setVisible(false);
                    resultScreen.setVisible(true);

                    //TODO: Check winner

                } else if(fromServer instanceof String) {
                    System.out.println("instance of string");
                }
                else {
                    System.out.println("instanceof list");
                    try {
                        listQuestions = (List<Questions>) fromServer;
                        listString = (List<String>) fromServer;
                    } catch (Exception ignore) {
                    }

                    if (listQuestions != null  && listQuestions.size() != 0 && listQuestions.get(0) instanceof Questions) {
                        homeScreen.setVisible(false);
                        loadingScreen.setVisible(false);
                        categoryScreen.setVisible(false);
                        gameScreen.setVisible(true);
                        resultScreen.setVisible(false);
                        currentQuestion = 0;

                        while (!listQuestions.isEmpty()) {
                            answered = false;
                            answerOptions1Button.setBackground(Color.WHITE);
                            answerOptions2Button.setBackground(Color.WHITE);
                            answerOptions3Button.setBackground(Color.WHITE);
                            answerOptions4Button.setBackground(Color.WHITE);
                            questionLabel.setText(listQuestions.get(0).getQ());
                            answerOptions1Button.setText(listQuestions.get(0).getA1());
                            answerOptions2Button.setText(listQuestions.get(0).getA2());
                            answerOptions3Button.setText(listQuestions.get(0).getA3());
                            answerOptions4Button.setText(listQuestions.get(0).getA4());

                            //Starta timer när spelaren fått frågan
                            //Få nästa fråga om tiden tar slut eller spelaren ger sitt svar
                            LocalTime time = LocalTime.now();
                            if (answered || time.plusSeconds(15) == LocalTime.now()) {
                                if(!answered){
                                    answers[currentQuestion] = false;
                                }
                                listQuestions.remove(0);
                                currentQuestion++;
                            }
                        }

                    }
                    if (listString != null  && listString.size() != 0&& listString.get(0) instanceof String) {
                        categoryJLabel.setText("Välj kategori");
                        category1Button.setText(listString.get(0));
                        category2Button.setText(listString.get(1));
                        category3Button.setText(listString.get(2));
                        category4Button.setText(listString.get(3));

                        homeScreen.setVisible(false);
                        loadingScreen.setVisible(false);
                        categoryScreen.setVisible(true);
                        gameScreen.setVisible(false);
                        resultScreen.setVisible(false);

                    }
                }
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
        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        GUI client = new GUI(serverAddress);
        client.play();

    }
}