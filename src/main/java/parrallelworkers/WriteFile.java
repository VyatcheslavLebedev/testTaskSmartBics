package parrallelworkers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class WriteFile {

    public void write(Map<LocalDateTime,Integer> map) throws IOException {

        FileWriter fw = new FileWriter("finallogs.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fw);


        for (Map.Entry<LocalDateTime,Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            LocalDateTime key = entry.getKey();
            bufferedWriter.write(key.getYear()+"-"+key.getMonth()+"-"+key.getDayOfYear()+
                    ",  "+key.getHour()+".00 - "+(key.getHour()+1)+".00  "+"Count of Errors: " + value.toString());
            bufferedWriter.newLine();
        }
        fw.close();
    }

}
