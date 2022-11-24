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

    Historia historia = new Historia();
    Sport sport = new Sport();
    Film film = new Film();
    Geografi geografi = new Geografi();
    Mat mat = new Mat();
    Matematik matematik = new Matematik();
    List<String> kategori = List.of("Historia", "Sport", "Film", "Geografi", "Mat", "Matematik");
    List<Category> allCategories = List.of(historia, sport, film, geografi, mat, matematik);

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

            state = ANSWERING;
        } else if (state == ANSWERING) {
            System.out.println("answering state");
            if(currentRound < maxRounds){
                state = SENDING;
            }else state = FINISHED;

        } else if (state == FINISHED) {
            System.out.println("finished state");
            output = new GameFinished();
        }

        return output;
    }

    protected List<String> getCategory(){
        Collections.shuffle(kategori);
        return List.of(kategori.get(1), kategori.get(2), kategori.get(3), kategori.get(4));
    }

    protected List<Questions> getQuestions(int qAmount, String category){
        List<Questions> questions = new ArrayList<>();
        for(Category c: allCategories){
            if(c.category.equals(category)){
                for (int i = 0; i < qAmount; i++) {
                    questions.add(c.questions.get(i));
                }
            }
        }
        //Ta bort använd kategori
        return questions;
    }
}
