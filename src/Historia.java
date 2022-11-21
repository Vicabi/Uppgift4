import java.util.List;

public class Historia {
    Questions historia1 = new Questions("Fråga", "svar1", "svar2", "svar3", "svar4", "svar2");
    Questions historia2 = new Questions("Fråga", "svar1", "svar2", "svar3", "svar4", "svar2");
    Questions historia3 = new Questions("Fråga", "svar1", "svar2", "svar3", "svar4", "svar2");


    protected List<Questions> historia = List.of(historia1, historia2, historia3);

    public List<Questions> getHistoria() {
        return historia;
    }
}
