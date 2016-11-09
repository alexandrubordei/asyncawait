package com.bigstep;

import com.ea.async.Async;
import static com.ea.async.Async.await;

/**
 * Created by alex on 11/9/16.
 */
public class Example {


    public static void main(String argv[]) throws InterruptedException {

        Async.init();

        ServerSimulator serverSimulator = new ServerSimulator();

        String result = await(serverSimulator.methodThatEndsInFuture(1000));

        System.out.print(result);

    }
}
