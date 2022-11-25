import java.util.List;

public class Historia {
    protected String category = "Historia";

    Questions historia1 = new Questions("Mellan vilka år pågick först världskriget?", "1912 - 1917", "1914 - 1918", "1914 - 1919", "1913 - 1918", "1914 - 1918");
    Questions historia2 = new Questions("Vad hette personen som höll det kända \"Jag har en dröm\" (\"I have a dream\") talet?", "Nelson Mandela", "Barack Obama", "Martin Luther King, jr", "Napoleon", "Martin Luther King, jr");
    Questions historia3 = new Questions("Vem var Sveriges första statsminister?", "Carl Gustaf", "Carl Johan", "Gustav Vasa", "Louis de Geer", "Louis de Geer");

    protected List<Questions> historia = List.of(historia1, historia2, historia3);

    public List<Questions> getHistoria() {
        return historia;
    }

    public String getCategory() {
        return category;
    }
}
