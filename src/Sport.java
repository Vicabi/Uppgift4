import java.util.List;

public class Sport {

    protected String category = "Sport";
    Questions sport1 = new Questions("Var hölls sommar-OS år 2016?", "Rio de Janeiro", "London", "Peking", "Seoul", "Rio de Janeiro");
    Questions sport2 = new Questions("Vad heter högsta professionella ligan i England?", "Champions League", "La Liga", "Premier League", "Serie A", "Premier League");
    Questions sport3 = new Questions("Vem vann Uefa Champions League 2005/2006?", "AC Milan", "Manchester United", "FC Barcelona", "Chelsea", "FC Barcelona");


    protected List<Questions> sport = List.of(sport1, sport2, sport3);

    public List<Questions> getSport() { return sport; }

    public String getCategory() {
        return category;
    }
}
