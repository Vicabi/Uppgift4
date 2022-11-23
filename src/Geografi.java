import java.util.List;

public class Geografi extends Category {

    protected String category = "Geografi";
    Questions geografi1 = new Questions("Vad är Brasiliens huvudstad?", "Rio de Janeiro", "São Paulo", "Brasilia", "Buenos Aires", "Brasilia");
    Questions geografi2 = new Questions("Vilken är Europas längsta flod?", "Donau", "Nilen", "Kama", "Volga", "Volga");
    Questions geografi3 = new Questions("Vilket land har flest pyramider?", "Egypten", "Sudan", "Mexico", "Sydafrika", "Sudan");


    protected List<Questions> geografi = List.of(geografi1, geografi2, geografi3);

    public List<Questions> getGeografi() {
        return geografi;
    }

    public String getCategory() {
        return category;
    }
}
