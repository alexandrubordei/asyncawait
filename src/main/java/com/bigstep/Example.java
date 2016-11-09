package com.bigstep;

import com.ea.async.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

import static com.ea.async.Async.await;

/**
 * Created by alex on 11/9/16.
 * Exemplifies the use of async await in java. There are no threads.
 */
public class Example {

    final static Logger logger = LoggerFactory.getLogger(Example.class);


    public static void main(String argv[]) throws InterruptedException {


        getResultsFromServerAndPrintResults();

        logger.info("Putting the thread to sleep");

        Thread.sleep(3000); //let the async code finish

        logger.info("Exiting the main");

    }

    /**
     * Simulate two asynchronous executions by calling ServerSimulator.
     * The first call waits for 1000 seconds, the second for 1500 seconds.
     * Within the context of this function the asynchronous calls are written in a
     * synchronous manner. The way this is implemented is either through reflection
     * at runtime or by static 'instrumentation' at compile time.
     * @return The last result returned by the last call to methodThatEndsInFuture()
     * @throws InterruptedException
     */
    private static CompletableFuture<String> getResultsFromServerAndPrintResults() throws InterruptedException {

        ServerSimulator serverSimulator = new ServerSimulator();

        String result = await(serverSimulator.methodThatEndsInFuture(1000));

        logger.info("First Result is: "+result);

        result = await(serverSimulator.methodThatEndsInFuture(1500));

        logger.info("Second Result is: "+result);

        return CompletableFuture.completedFuture(result);
    }



}
