package ie.pt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class ExternalResourcesInvestigation {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Environment env = ctx.getEnvironment();
        String title = env.getProperty(
                "app.title",
                "DEFAULT VALUE");

        System.out.println(title);
    }
}
