package ru.spb.dreamwhite;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.spb.dreamwhite.web.UserRestController;

import java.util.Arrays;

public class TemporaryTest {

    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserRestController userRestController = appCtx.getBean(UserRestController.class);

            System.out.println(userRestController.get(2));

        }
    }

}
