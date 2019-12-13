package app.presenter;

import app.TransportCompanyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainAppPresenter {
    private Stage primaryStage;

    public MainAppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initRootLayout() {
        try {
            this.primaryStage.setTitle("My second JavaFX app");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            URL url = new URL(new URL("file:"), "src/main/java/app/view/TransportsView.fxml");
            loader.setLocation(url);
            Pane rootLayout = loader.load();

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
                Exception e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }
}
