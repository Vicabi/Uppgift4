import java.util.List;

public class Mat {

    protected String category = "Mat";
    Questions mat1 = new Questions("Vilket datum firas kanelbullens dag?", "4 september", "4 oktober", "3 september", "4 juni", "4 oktober");
    Questions mat2 = new Questions("Hur många procent är fetthalten i lättmjölk?", "0,5%", "1,5%", "1%", "3%", "0,5%");
    Questions mat3 = new Questions("Vilken är den näst mest populäraste läsken?", "Coca-Cola", "Mountain Dew", "Fanta", "Pepsi", "Pepsi");


    protected List<Questions> mat = List.of(mat1, mat2, mat3);

    public List<Questions> getMat() {
        return mat;
    }

    public String getCategory() {
        return category;
    }
}
