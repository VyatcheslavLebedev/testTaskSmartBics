package parrallelworkers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class ParallelWorkerImpl implements ParallelWorker{

    int numberFiles;

    public ParallelWorkerImpl(int numberFiles) {
        this.numberFiles = numberFiles;
    }

    public void executionStart() throws ExecutionException, InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberFiles);
        List<Future<Map<LocalDateTime,Integer>>> tasks = new LinkedList<>();

        final Map<LocalDateTime,Integer> finalMap = new HashMap<>();

        for(int i = 0; i < numberFiles; i++){
            Callable<Map<LocalDateTime,Integer>> task = new CurrentCallable(i);
            Future<Map<LocalDateTime,Integer>> future = executorService.submit(task);
            tasks.add(future);
        }

        while(!tasks.isEmpty()){

            Map<LocalDateTime, Integer> logsCurrentThread = null;


            for(Future<Map<LocalDateTime,Integer>> x : tasks) {
                if (x.isDone()) {
                    logsCurrentThread = x.get();
                    logsCurrentThread.forEach((key, value) -> finalMap.merge(key, value, (v1, v2) -> v1 + v2));

                }
            }

            if(logsCurrentThread != null) {
                tasks.remove(logsCurrentThread);
            }
        }

        executorService.shutdown();

        Map<LocalDateTime,Integer> treeMap = new TreeMap<>(finalMap);

        WriteFile writeFile = new WriteFile();
        writeFile.write(treeMap);
    }
}

