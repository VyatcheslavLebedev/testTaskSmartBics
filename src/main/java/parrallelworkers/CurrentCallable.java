package parrallelworkers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CurrentCallable implements Callable<Map<Date,Integer>> {

    private final int numberFile;

    public CurrentCallable(int numberFile) {
        this.numberFile = numberFile;
    }

    @Override
    public Map<Date,Integer> call() throws Exception {

        Map<Date,Integer> numberErrors = new HashMap<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("log" + numberFile + ".csv")) {

            String line = bufferedReader.readLine();

            while(line != null){
                
            }

        }

        return numberErrors;
    }


}
