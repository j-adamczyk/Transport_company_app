package app;

import javafx.application.Application;
import javafx.stage.Stage;
import app.presenter.MainAppPresenter;

public class TransportCompanyApp extends Application {

    private Stage primaryStage;
    private MainAppPresenter presenter;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("My first JavaFX app");
        presenter = new MainAppPresenter(primaryStage);
        this.presenter.initRootLayout();
        //this.primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

