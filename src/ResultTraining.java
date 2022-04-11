import java.io.Serializable;

public class ResultTraining implements Serializable {
    private final String type;
    private final double kkal;
    private final String time;

    ResultTraining(String type, double kkal, String time){
        this.type = type;
        this.kkal = kkal;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public double getKkal() {
        return kkal;
    }

    public String getTime() {
        return time;
    }
}
