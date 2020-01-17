package app;

import app.dao.DbConnector;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.stage.Stage;
import app.presenter.MainAppPresenter;

public class TransportCompanyApp extends Application {
    private Stage primaryStage;
    private MainAppPresenter presenter;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        presenter = new MainAppPresenter(primaryStage);
        this.presenter.initRootLayout();
    }

    public static void main(String[] args) {
        try {
            DbConnector.getInstance().setDbTypeAndLoad(true);
        } catch (Exception e) {
            launch(args);
            MainAppPresenter.showErrorDialog(e, "Cannot connect database - check internet connection");
            return;
        }
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        try {
            db.getName();
        } catch (Exception e) {
            e.printStackTrace();
            launch(args);
            MainAppPresenter.showErrorDialog(e, "Cannot connect database - check internet connection");
            return;
        }

        launch(args);
    }
}

