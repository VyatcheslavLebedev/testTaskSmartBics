import parrallelworkers.ParallelWorker;
import parrallelworkers.ParallelWorkerImpl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        int numberFiles = Integer.parseInt(args[0]);
        ParallelWorker parallelWorker = new ParallelWorkerImpl(numberFiles);
        parallelWorker.executionStart();
    }

}
