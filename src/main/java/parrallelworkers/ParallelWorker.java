package parrallelworkers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ParallelWorker {

    void executionStart() throws ExecutionException, InterruptedException, IOException;
}
