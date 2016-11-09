import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by alex on 11/9/16.
 */
public class ServerSimulator {
    private Supplier<String> longRunningFunction(Integer milliseconds)
    {
        return () -> {
            try {
                Thread.sleep(milliseconds);
                return "I have waited for "+milliseconds+" milliseconds";
            } catch (InterruptedException e) {
                return "InterruptedException:"+e.getLocalizedMessage();
            }
        };
    }

    public CompletableFuture<String> methodThatEndsInFuture(Integer milliseconds) throws InterruptedException {

        return CompletableFuture.supplyAsync(longRunningFunction(milliseconds));

    }

}
