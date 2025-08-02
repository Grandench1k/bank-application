package grandench1k.springcorehomework;

import grandench1k.springcorehomework.listener.OperationsConsoleListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class SpringCoreHomeworkApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringCoreHomeworkApplication.class);
        OperationsConsoleListener listener = context.getBean(OperationsConsoleListener.class);

    }

}
