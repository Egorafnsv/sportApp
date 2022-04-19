import java.io.Serializable;

public class ResultTraining implements Serializable {
    private final String type;
    private final double kcal;
    private final String time;

    ResultTraining(String type, double kcal, String time){
        this.type = type;
        this.kcal = kcal;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public double getKkal() {
        return kcal;
    }

    public String getTime() {
        return time;
    }
}
