import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Statistics implements Serializable {
    List<ResultTraining> stats = new ArrayList<>();
    private double sumCal = 0;

    public void putNewExercise(String type, double cal, String time){
        stats.add(new ResultTraining(type, cal, time));
        sumCal += cal;
    }

    public String completedExercises(){
        int fromIndex = Math.max(stats.size() - 5, 0);

        return stats.subList(fromIndex, stats.size()).
                stream()
                .limit(5)
                .map(i -> "Упражнение: " + i.getType() + "\nКалории: " + i.getKkal() + "\nВремя: " + i.getTime())
                .collect(Collectors.joining("\n\n"));
    }

    public double getSumCal(){
        return sumCal;
    }
}
