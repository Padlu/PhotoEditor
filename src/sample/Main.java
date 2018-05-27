/*package sample;

import javafx.application.Application;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;
import javafx.geometry.*;


public class Main extends Application {
    Stage window;//variable window is our main window

    BorderPane layout ;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        window.setTitle("Photo Editor 2k16");
        Scene primary;

        Menu filemenu = new Menu("File");
        MenuItem open, settings, save, saveas, Exit;

        //Menu ITEMS
        filemenu.getItems().add(open = new MenuItem("Open..."));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(settings = new MenuItem("Settings"));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(save = new MenuItem("Save..."));
        filemenu.getItems().add(saveas = new MenuItem("Save As..."));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(Exit = new MenuItem("EXIT..."));
        */
        //fliemenu Items Interaction
        //open.setOnAction(e -> /*openwindow newstage newclass);
        //settings.setOnAction(e -> /*openwindow newstage newclass which will change default editing settings*/);
        //save.setOnAction(e -> /*overwrite function*/);
        //saveas.setOnAction(e -> /*openwindow newstage newclass*/);
        //Exit.setOnAction(e -> window.close()/*Make sure to ask save the edited photo if not saved!!*/);
        //window.setOnCloseRequest(e -> {e.consume();/*openwindow askingtosave or Exitwithoutsaving*/});
        /*
        //Main Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu);

        layout = new BorderPane();
        layout.setTop(menuBar);
        window.setScene(primary = new Scene(layout, 1400, 775));
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
*/