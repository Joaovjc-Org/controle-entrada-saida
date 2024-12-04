package br.pucrs.poo;
import br.pucrs.poo.view.TelaPrincipalFrame;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import java.awt.*;
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
        EventQueue.invokeLater(() -> {
            TelaPrincipalFrame appFrame = context.getBean(TelaPrincipalFrame.class);
            appFrame.setVisible(true);
        });
    }
}

