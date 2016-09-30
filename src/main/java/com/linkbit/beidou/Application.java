
package com.linkbit.beidou;

import com.linkbit.beidou.liseners.AppilicationSessionExpire;
import com.linkbit.beidou.liseners.AppilicationStartUp;
import com.linkbit.beidou.liseners.AppilicationStopped;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 */
@EnableTransactionManagement //启用事务管理
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication

public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new AppilicationStartUp());
        app.addListeners(new AppilicationSessionExpire());
        app.addListeners(new AppilicationStopped());
        app.run(args);
    }
}
