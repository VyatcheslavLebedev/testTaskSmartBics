package parrallelworkers;

import java.awt.print.Book;
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

        final Map<LocalDateTime,Integer> finalMap = new TreeMap<>();

        for(int i = 1; i <= numberFiles; i++){
            Callable<Map<LocalDateTime,Integer>> task = new CurrentCallable(i);
            Future<Map<LocalDateTime,Integer>> future = executorService.submit(task);
            tasks.add(future);
        }
        while(!tasks.isEmpty()) {
            ListIterator<Future<Map<LocalDateTime, Integer>>> iter = tasks.listIterator();
            while (iter.hasNext()) {
                Future<Map<LocalDateTime,Integer>> future = iter.next();
                if (future.isDone()) {
                    Map<LocalDateTime, Integer> logsCurrentThread = future.get();
                    logsCurrentThread.forEach(
                            (key, value) -> finalMap.merge(key, value, (v1, v2) -> v1 + v2)
                    );
                    iter.remove();
                }
                }

            }

        executorService.shutdown();


        WriteFile writeFile = new WriteFile();
        writeFile.write(finalMap);
    }
}

