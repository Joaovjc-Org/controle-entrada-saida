package br.pucrs.poo;
import br.pucrs.poo.view.TelaPrincipalFrame;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;

@SpringBootApplication
@EnableJpaRepositories
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
    @Override
    public void run(String... args) {
        SwingUtilities.invokeLater(() -> TelaPrincipalFrame.startGUI(args));
    }
}

