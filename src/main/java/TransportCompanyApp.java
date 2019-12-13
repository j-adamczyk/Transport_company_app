import javafx.application.Application;
import javafx.stage.Stage;

public class TransportCompanyApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My first JavaFX app");
        this.primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

