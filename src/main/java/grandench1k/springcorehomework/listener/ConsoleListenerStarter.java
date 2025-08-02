package grandench1k.springcorehomework.listener;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ConsoleListenerStarter {

    private final OperationsConsoleListener consoleListener;

    private Thread consoleListenerThread;

    public ConsoleListenerStarter(OperationsConsoleListener consoleListener) {
        this.consoleListener = consoleListener;
    }

    @PostConstruct
    public void postConstruct() {
        this.consoleListenerThread = new Thread(() -> {
            consoleListener.start();
            consoleListener.run();
        });
        consoleListenerThread.start();
    }

    @PreDestroy
    public void preDestroy() {
        consoleListenerThread.interrupt();
        consoleListener.stop();
    }
}
