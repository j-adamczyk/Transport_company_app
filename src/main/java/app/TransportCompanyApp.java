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
        //this.primaryStage.show();

    }

    public static void main(String[] args) {
        DbConnector.getInstance().setDbTypeAndLoad(true);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        try{
            db.getName();
        }catch (Exception e){
            e.printStackTrace();
//            TODO
        }

        launch(args);
    }
}

