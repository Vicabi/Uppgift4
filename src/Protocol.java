import javax.print.attribute.standard.Finishings;
import java.util.List;

public class Protocol {
    final protected int INITIAL = 0;    //Spelare ansluten
    final protected int WAITING = 1;    //Väntar på motståndare
    final protected int CHOOSING = 2;   //Spelare väljer kategori
    final protected int ANSWERING = 3;  //Spelarna svarar på frågorna

    protected int state = INITIAL;

    Historia historia = new Historia();
    Sport sport = new Sport();
    Film film = new Film();
    Geografi geografi = new Geografi();
    Mat mat = new Mat();
    Matematik matematik = new Matematik();
    String[] kategori = {"Historia", "Sport", "Film", "Geografi", "Mat", "Matematik"};
    List<Category> allCategories = List.of(historia, sport, film, geografi, mat, matematik);

    public Object getOutput(String playerChoice) {
        Object output = null;
        if (state == INITIAL) {
            state = WAITING;

            output = new Intro();
        } else if (state == WAITING) {
            output = new Waiting();

            state = CHOOSING;
        } else if (state == CHOOSING) {
            output = allCategories.get(Integer.parseInt(playerChoice));

            state = ANSWERING;
        } else if (state == ANSWERING) {
            state = CHOOSING;
        }


        return output;
    }


}
