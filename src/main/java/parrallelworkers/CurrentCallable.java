package parrallelworkers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CurrentCallable implements Callable<Map<LocalDateTime,Integer>> {

    private static final String error = "ERROR";
    private final int numberFile;

    public CurrentCallable(int numberFile) {
        this.numberFile = numberFile;
    }

    @Override
    public Map<LocalDateTime,Integer> call() throws Exception {

        Map<LocalDateTime,Integer> numberErrors = new HashMap<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("logs"+numberFile+".csv"))){
            String line = bufferedReader.readLine();
            String[] tokens;
            tokens = line.split(";");
            int countByHours = 0;
            LocalDateTime dateTime = null;
            dateTime.parse(tokens[0]);
            dateTime = dateTime.minusMinutes(dateTime.getMinute());
            while(line!= null){
                tokens = line.split(";");
                LocalDateTime time = null;
                time.parse(tokens[0]);
                time = time.minusMinutes(time.getMinute());
                if((time.getHour() > dateTime.getHour()) || (time.getDayOfMonth() > dateTime.getDayOfMonth())){
                    countByHours = 0;
                    dateTime = time;
                }
                if((time.getHour() == dateTime.getHour()) &&
                        (time.getDayOfMonth() == dateTime.getDayOfMonth()) && (tokens[1].equals(error))){
                    countByHours++;
                }
                line = bufferedReader.readLine();
            }


        }
        return numberErrors;

    }
}
