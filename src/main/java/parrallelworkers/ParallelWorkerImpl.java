package parrallelworkers;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class ParallelWorkerImpl implements ParallelWorker{

    int numberFiles;

    public ParallelWorkerImpl(int numberFiles) {
        this.numberFiles = numberFiles;
    }

    public void executionStart() throws ExecutionException, InterruptedException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberFiles);
        List<Future<Map<Date,Integer>>> tasks = new LinkedList<>();

        final Map<Date,Integer> finalMap = new HashMap<>();

        for(int i = 0; i < numberFiles; i++){
            Callable<Map<Date,Integer>> task = new CurrentCallable(i);
            Future<Map<Date,Integer>> future = executorService.submit(task);
            tasks.add(future);
        }

        while(!tasks.isEmpty()){

            Map<Date, Integer> logsCurrentThread = null;


            for(Future<Map<Date,Integer>> x : tasks) {
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

        Map<Date,Integer> treeMap = new TreeMap<>(finalMap);

        WriteFile writeFile = new WriteFile();
        writeFile.write(treeMap);
    }
}

