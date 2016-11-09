import com.ea.async.Async;
import com.sun.xml.internal.ws.api.server.ThreadLocalContainerResolver;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static com.ea.async.Async.await;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static junit.framework.TestCase.assertEquals;


/**
 * Created by alex on 11/9/16.
 */
public class TestAsyncAwait {

    @Before
    public void init()
    {
        Async.init();
    }

    @Test
    public void testAsyncAwait() throws InterruptedException {

           int returnedResult = await(methodThatEndsInFuture(1000));

           assertEquals(2000, returnedResult);

    }

    private CompletableFuture<Integer> methodThatEndsInFuture(Integer milliseconds) throws InterruptedException {

        return CompletableFuture.supplyAsync(longRunningFunction(milliseconds));

    }

    private Supplier<Integer> longRunningFunction(Integer milliseconds)
    {
        return () -> {
            try {
                Thread.sleep(milliseconds);
                return milliseconds*2;
            } catch (InterruptedException e) {
                return -1;
            }
        };
    }

}
