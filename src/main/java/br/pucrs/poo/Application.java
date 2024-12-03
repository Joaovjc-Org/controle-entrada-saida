package br.pucrs.poo;
import br.pucrs.poo.controller.ClienteController;
import br.pucrs.poo.view.TelaPrincipalFrame;
import br.pucrs.poo.view.cliente.ClienteCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).headless(false).run(args);
        EventQueue.invokeLater(() -> {
            TelaPrincipalFrame appFrame = context.getBean(TelaPrincipalFrame.class);
            appFrame.setVisible(true);
        });
    }
}

