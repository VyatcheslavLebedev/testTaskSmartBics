package parrallelworkers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WriteFile {

    public void write(Map<LocalDateTime,Integer> map) throws IOException {

        FileWriter fw = new FileWriter("src/main/resources/finallogs.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(fw);


        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            LocalDateTime key = (LocalDateTime) i.next();
            Integer value = (Integer) map.get(key);
            String line = key.getYear()+"-"+key.getMonth()+"-"+key.getDayOfMonth()+
                    ",  "+key.getHour()+".00 - "+(key.getHour()+1)+".00  "+"Count of Errors: " + value.toString();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

}
