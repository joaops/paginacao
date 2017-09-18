package br.com.joaops.paginacao;

import br.com.joaops.paginacao.config.ApplicationConfig;
import br.com.joaops.paginacao.config.MapperConfig;
import br.com.joaops.paginacao.config.PersistenceConfig;
import br.com.joaops.paginacao.controller.HomeLayoutController;
import br.com.joaops.paginacao.controller.PessoaLayoutController;
import br.com.joaops.paginacao.controller.RootLayoutController;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author João Paulo
 */
public class Main extends Application {
    
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    
    private Stage stage;
    private BorderPane rootLayout;
    private AnnotationConfigApplicationContext context;
    
    @Override
    public void init() throws Exception {
        LOGGER.debug("Configurando o Spring.");
        context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.register(PersistenceConfig.class);
        context.register(MapperConfig.class);
        context.refresh();
    }
    
    @Override
    public void stop() throws Exception {
        LOGGER.debug("Finalizando Aplicação.");
        context.close();
        System.exit(0); // Forçar o Encerramento
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/favicon.png")));
        this.stage.setTitle("Paginação");
        showRootLayout();
        showHomeLayout();
    }
    
    /**
     * Inicializa o root layout (layout base).
     */
    public void showRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootLayout.fxml"));
            loader.setControllerFactory(context::getBean);
            rootLayout = (BorderPane) loader.load();
            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            stage.show();
        } catch (IOException e) {
            System.err.println("ERRO: " + e.toString());
        }
    }
    
    public void showHomeLayout() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeLayout.fxml"));
            loader.setControllerFactory(context::getBean);
            AnchorPane pane = (AnchorPane) loader.load();
            // Define o pane dentro do root layout.
            rootLayout.setCenter(pane);
            // Fornecer uma instância de acesso ao Main.
            HomeLayoutController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            System.err.println("ERRO: " + e.toString());
        }
    }
    
    public void showPessoaLayout() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PessoaLayout.fxml"));
            loader.setControllerFactory(context::getBean);
            AnchorPane pane = (AnchorPane) loader.load();
            // Define o pane dentro do root layout.
            rootLayout.setCenter(pane);
            // Fornecer uma instância de acesso ao Main.
            PessoaLayoutController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            System.err.println("ERRO: " + e.toString());
        }
    }
    
    public static void main(String[] args) {
        LOGGER.info("Iniciando Aplicação");
        launch(args);
    }
    
}