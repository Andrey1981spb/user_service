package ru.spb.dreamwhite.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.spb.dreamwhite.util.emailUtil.PostUserListener;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages =
        {"ru.spb.dreamwhite.util.emailUtil",
                "ru.spb.dreamwhite.model"})
@PropertySource("classpath:email/email.properties")
public class MailConfig {

    @Autowired
    Environment env;

    @Bean(name = "mailSender")
    public MailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setProtocol("smtp");
        javaMailSender.setUsername("testcase26022020@gmail.com");
        javaMailSender.setPassword("controller4rest");
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", Boolean.TRUE);
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.put("mail.smtp.starttls.enable", Boolean.TRUE);
        mailProperties.put("mail.smtp.debug", Boolean.TRUE);
        javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public PostUserListener getListener() {
        return new PostUserListener();
    }

    @Bean(name = "email")
    public SimpleMailMessage getMessage() {
        return new SimpleMailMessage();
    }

    @Bean
    public SendGrid getSendGrid(){
        return new SendGrid(env.getProperty("grid.key"));
    }

}
