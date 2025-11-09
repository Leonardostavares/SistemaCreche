package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import main.frontend.CrecheFXApplication;
import javafx.application.Application;

@SpringBootApplication
public class CrecheApplication {

    public static void main(String[] args) {
        // Iniciar Spring Boot primeiro
        Thread springThread = new Thread(() -> {
            try {
                ConfigurableApplicationContext context = SpringApplication.run(CrecheApplication.class, args);
                System.out.println("Spring Boot iniciado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao iniciar Spring Boot: " + e.getMessage());
                e.printStackTrace();
            }
        });
        
        springThread.setDaemon(true);
        springThread.start();
        
        // Aguardar um pouco para o Spring Boot inicializar
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Iniciar JavaFX na thread principal
        Application.launch(CrecheFXApplication.class, args);
    }
}