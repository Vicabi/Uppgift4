import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    Questions sportQ1 = new Questions("Var hölls sommar-OS år 2016?", "Rio de Janeiro", "London", "Peking", "Seoul", "Rio de Janeiro");
    Questions sportQ2 = new Questions("Vad heter högsta professionella ligan i England?", "Champions League", "La Liga", "Premier League", "Serie A", "Premier League");
    Questions sportQ3 = new Questions("Vem vann Uefa Champions League 2005/2006?", "AC Milan", "Manchester United", "FC Barcelona", "Chelsea", "FC Barcelona");
    Category sport = new Category ("Sport", sportQ1, sportQ2, sportQ3);

    Questions filmQ1 = new Questions("Vilken är den mest inkomstbringande filmen genom tiderna?", "Avatar", "Avengers: Endgame", "Titanic", "Frozen", "Avatar");
    Questions filmQ2 = new Questions("Vilket år kom filmen \"2012\" ut?", "2012", "2009", "2015", "2010", "2009");
    Questions filmQ3 = new Questions("För vilken film vann skådespelerskan \"Jennifer Lawrence\" Oscar 2013 (Bästa kvinnliga huvudroll)?", "The Hunger Games", "X-Men", "American Hustle", "Silver Linings Playbook", "Silver Linings Playbook");
    Category film = new Category("Film", filmQ1, filmQ2, filmQ3);

    Questions geografiQ1 = new Questions("Vad är Brasiliens huvudstad?", "Rio de Janeiro", "São Paulo", "Brasilia", "Buenos Aires", "Brasilia");
    Questions geografiQ2 = new Questions("Vilken är Europas längsta flod?", "Donau", "Nilen", "Kama", "Volga", "Volga");
    Questions geografiQ3 = new Questions("Vilket land har flest pyramider?", "Egypten", "Sudan", "Mexico", "Sydafrika", "Sudan");
    Category geografi = new Category("Geografi", geografiQ1, geografiQ2, geografiQ3);

    Questions matQ1 = new Questions("Vilket datum firas kanelbullens dag?", "4 september", "4 oktober", "3 september", "4 juni", "4 oktober");
    Questions matQ2 = new Questions("Hur många procent är fetthalten i lättmjölk?", "0,5%", "1,5%", "1%", "3%", "0,5%");
    Questions matQ3 = new Questions("Vilken är den näst mest populäraste läsken?", "Coca-Cola", "Mountain Dew", "Fanta", "Pepsi", "Pepsi");
    Category mat = new Category("Mat", matQ1, matQ2, matQ3);

    Questions matematikQ1 = new Questions("Vad väger mer, ett kilo fjäder eller ett kilo stål?", "Ett kilo stål", "Dem väger lika mycket", "Ett kilo fjäder", "Går ej att avgöra", "Dem väger lika mycket");
    Questions matematikQ2 = new Questions("Täljare / nämnare = ?", "Produkt", "Summa", "Kvot", "Differens", "Kvot");
    Questions matematikQ3 = new Questions("Vad är värdet på pi (fyra decimaler)", "3,1415", "3,1417", "3,1414", "3,1314", "3,1415");
    Category matematik = new Category("Matematik",matematikQ1, matematikQ2, matematikQ3);

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
