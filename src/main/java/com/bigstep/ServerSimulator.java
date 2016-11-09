package com.bigstep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by alex on 11/9/16.
 * Simulates a very basic interaction with a server.
 */
public class ServerSimulator {

    final static Logger logger = LoggerFactory.getLogger(ServerSimulator.class);

    /**
     * Simulates a call to a server. Executes a thread.sleep of the specified number
     * of milliseconds.
     * @param milliseconds the number of milliseconds to sleep.
     * @return Returns a String Supplier class that can be interrogated for the value.
     * @see Supplier
     */
    private Supplier<String> longRunningFunction(Integer milliseconds) {
        return () -> {
            try {
                logger.debug("longRunningFunction: putting the thread to sleep.");

                Thread.sleep(milliseconds);

                logger.debug("longRunningFunction: resuming from sleep.");

                return "I have waited for "+milliseconds+" milliseconds";
            } catch (InterruptedException e) {
                return "InterruptedException:"+e.getLocalizedMessage();
            }
        };
    }

    /**
     * Returns a new CompletableFuture that is asynchronously completed by a
     * task running in the ForkJoinPool.commonPool() with the value obtained by calling
     * longRunningFunction().
     * @param milliseconds Parameter to pass to longRunningFunction()
     * @return CompletableFuture<String>
     * @throws InterruptedException
     */
    public CompletableFuture<String> methodThatEndsInFuture(Integer milliseconds) throws InterruptedException {

        return CompletableFuture.supplyAsync(longRunningFunction(milliseconds));

    }

}
