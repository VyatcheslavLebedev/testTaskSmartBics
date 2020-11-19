package parrallelworkers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class WriteFile {

    public void write(Map<Date,Integer> map) throws IOException {

        FileWriter fw = new FileWriter("finallogs.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fw);


        for (Map.Entry<Date,Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            Date key = entry.getKey();
            bufferedWriter.write(key.getYear() + "-" + key.getMonth() + "-" + key.getDay() +
                    ", " + key.getHours() + ".00 - " + (key.getHours() + 1) + ".00  Count of Errors: " + value.toString());
            bufferedWriter.newLine();
        }
        fw.close();
    }

}
