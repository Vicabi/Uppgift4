import java.util.List;

public class Matematik extends Category{

    protected String category = "Matematik";
    Questions matematik1 = new Questions("Vad väger mer, ett kilo fjäder eller ett kilo stål?", "Ett kilo stål", "Dem väger lika mycket", "Ett kilo fjäder", "Går ej att avgöra", "Dem väger lika mycket");
    Questions matematik2 = new Questions("Täljare / nämnare = ?", "Produkt", "Summa", "Kvot", "Differens", "Kvot");
    Questions matematik3 = new Questions("Vad är värdet på pi (fyra decimaler)", "3,1415", "3,1417", "3,1414", "3,1314", "3,1415");


    protected List<Questions> matematik = List.of(matematik1, matematik2, matematik3);

    public List<Questions> getMatematik() { return matematik; }

    public String getCategory() {
        return category;
    }
}
