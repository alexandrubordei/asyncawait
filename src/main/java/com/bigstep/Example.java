package com.bigstep;

import com.ea.async.Async;

import java.util.concurrent.CompletableFuture;

import static com.ea.async.Async.await;

/**
 * Created by alex on 11/9/16.
 */
public class Example {


    public static void main(String argv[]) throws InterruptedException {


        getResultsFromServerAndPrintResults();

        System.out.println("Putting the main thread to sleep");

        Thread.sleep(2000); //let the async code finish

        System.out.println("And now we're exiting the main");

    }

    public static CompletableFuture<String> getResultsFromServerAndPrintResults() throws InterruptedException {

        ServerSimulator serverSimulator = new ServerSimulator();

        String result = await(serverSimulator.methodThatEndsInFuture(1000));

        System.out.println("Result is: "+result);

        return CompletableFuture.completedFuture(result);
    }



}
