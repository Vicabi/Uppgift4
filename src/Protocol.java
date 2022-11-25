import javax.management.QueryExp;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Protocol {
    final protected int INITIAL = 0;    //Spelare ansluten
    final protected int WAITING = 1;    //Väntar på motståndare
    final protected int SENDING = 2;   //Skickar kategorier till spelaren
    final protected int CHOOSING = 3;  //Skickar frågor från vald kategori
    final protected int ANSWERING = 4;  //Spelarna svarar på frågorna
    final protected int FINISHED = 5;   //Alla omgångar avslutade

    protected int state = INITIAL;

    Properties properties;
    protected int currentRound;
    protected int maxRounds;
    protected int numberOfQuestions;

    Questions historiaQ1 = new Questions("Mellan vilka år pågick först världskriget?", "1912 - 1917", "1914 - 1918", "1914 - 1919", "1913 - 1918", "1914 - 1918");
    Questions historiaQ2 = new Questions("Vad hette personen som höll det kända \"Jag har en dröm\" (\"I have a dream\") talet?", "Nelson Mandela", "Barack Obama", "Martin Luther King, jr", "Napoleon", "Martin Luther King, jr");
    Questions historiaQ3 = new Questions("Vem var Sveriges första statsminister?", "Carl Gustaf", "Carl Johan", "Gustav Vasa", "Louis de Geer", "Louis de Geer");
    Category historia = new Category("Historia", historiaQ1, historiaQ2, historiaQ3 );

    List<String> kategori = List.of("Historia", "Sport", "Film", "Geografi", "Mat", "Matematik");
    List<Category> allCategories = List.of(historia);

    public Object getOutput(String playerChoice) { //Spelaren klickar på "Historia"-knappen och skickar hit
        Object output = null;
        currentRound = 0;
        properties = new Properties();
        try{
            properties.load(new FileInputStream("src/Properties.properties"));
        }catch (IOException e){
            System.out.println("Kunde inte läsa properties");
        }
        maxRounds = Integer.parseInt(properties.getProperty("ROUNDS", "2"));
        numberOfQuestions = Integer.parseInt(properties.getProperty("QUESTIONS", "2"));

        if (state == INITIAL) {
            System.out.println("initial state");
            output = new Intro();

            state = WAITING;
        } else if (state == WAITING) {
            System.out.println("waiting state");
            output = new Waiting();

            state = SENDING;
        } else if (state == SENDING) {
            System.out.println("sending state");
            output = getCategory();

            currentRound++;
            state = CHOOSING;
        } else if (state == CHOOSING) {
            System.out.println("choosing state");
            output = getQuestions(numberOfQuestions, playerChoice);
            System.out.println("efter getquestions");

            state = ANSWERING;
        } else if (state == ANSWERING) {
            System.out.println("answering state");
            if(currentRound < maxRounds){
                state = WAITING;
            }else state = FINISHED;

        } else if (state == FINISHED) {
            System.out.println("finished state");
            output = new GameFinished();
        }

        return output;
    }

    protected List<String> getCategory(){
//        Collections.shuffle(kategori);
        return List.of(kategori.get(0), kategori.get(1), kategori.get(2), kategori.get(3));
    }

    protected List<Questions> getQuestions(int qAmount, String category){
//        System.out.println("inne i getquestions");
//        System.out.println(qAmount + " " + category);
//        System.out.println(allCategories.size());
        List<Questions> questions = new ArrayList<>();
        for (Category c : allCategories) {
//            System.out.println(c.getCategory());
            if (c.getCategory().equals(category)) {
                for (int j = 0; j < qAmount; j++) {
                    questions.add(c.questions.get(j));
                }
            }
        }
//        System.out.println(questions.size() + " " + questions.get(0).getQ());
        //Ta bort använd kategori
        return questions;
    }
}
