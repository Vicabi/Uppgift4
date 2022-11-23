import java.util.Arrays;
import java.util.List;

public class Game {
    private int rounds;
    protected Player currentPlayer;
    protected int p1points;
    protected int p2points;

    public int getP1points() {
        return p1points;
    }

    public void setP1points(int p1points) {
        this.p1points = p1points;
    }

    public int getP2points() {
        return p2points;
    }

    public void setP2points(int p2points) {
        this.p2points = p2points;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

//    public String[] getCategory(){
//        Arrays.sort(kategori);
//        return new String[]{kategori[1], kategori[2], kategori[3], kategori[4]};
//    }
//
//    public Category getQuestions(String category){
//        for(Category c: allCategories){
//            if(category.equals(c.category)){
//                return c;
//            }
//        }
//        return historia;
//    }
//
//
//    Historia historia = new Historia();
//    Sport sport = new Sport();
//    Film film = new Film();
//    Geografi geografi = new Geografi();
//    Mat mat = new Mat();
//    Matematik matematik = new Matematik();
//    String[] kategori = {"Historia", "Sport", "Film", "Geografi", "Mat", "Matematik"};
//    List<Category> allCategories = List.of(historia, sport, film, geografi, mat, matematik);

    //Lista med alla kategorier som objekt!
}
