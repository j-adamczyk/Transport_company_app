package presenter;

import demo.TransportCompanyApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainAppPresenter {
    private Stage primaryStage;

    public MainAppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("My second JavaFX app");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppPresenter.class
                    .getResource("view/MainView.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            // set initial data into controller
            /*AccountOverviewController controller = loader.getController();
            controller.setAppController(this);
            controller.setData(DataGenerator.generateAccountData());
            controller.setCommandRegistry(commandRegistry);*/

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (
                IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }
}
