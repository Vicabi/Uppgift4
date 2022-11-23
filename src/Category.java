import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    protected String category;

    Questions q1 = new Questions();
    Questions q2 = new Questions();
    Questions q3 = new Questions();

    protected List<Questions> questions;
}
