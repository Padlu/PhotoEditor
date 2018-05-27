package com.jhlabs.image; /**
 * Created by Abhishek on 19-09-2016.
 */

import javafx.application.Application;//for all app methods
import javafx.embed.swing.SwingFXUtils;//search
import javafx.geometry.Insets;//to set width and height
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;//to get image path i.e image
import javafx.scene.image.ImageView;//image paper on which image can be shown
import javafx.scene.layout.*;
import javafx.stage.*;//main window
import javafx.geometry.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;


public class Main extends Application {
    Stage window;//variable window is our main window

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Photo Editor 2k16");
        StackPane stackPane = new StackPane();
        BorderPane layout = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVisible(false);
        AnchorPane anchorPane = new AnchorPane();
        GridPane effect = new GridPane();
        effect.setPadding(new Insets(5,5,5,5));
        effect.setHgap(5);
        effect.setVgap(5);
        Group group = new Group();
        group.autoSizeChildrenProperty().setValue(false);
        Slider sliderbright = new Slider(0,1,0.5);
        sliderbright.setVisible(false);
        Slider slidersaturation = new Slider(0,1,0.5);
        slidersaturation.setVisible(false);
        anchorPane.getChildren().addAll(slidersaturation,sliderbright,scrollPane);
        ImageView image = new ImageView();
        image.setFitWidth(600);
        image.setFitHeight(500);
        image.preserveRatioProperty();
        image.setVisible(false);
        group.getChildren().add(image);
        stackPane.getChildren().addAll(anchorPane,layout);
        Scene primary = new Scene(stackPane, 1000, 600);
        Label uploadfirst = new Label("Please Upload a picture first!!");
        uploadfirst.setVisible(false);

        Button effects = new Button("Effects");
        effects.setId("effect");
        effects.setMinWidth(180);
        effects.setMinHeight(50);
        Button collage = new Button("Collage");
        collage.setMinWidth(180);
        collage.setMinHeight(50);
        Button merge = new Button("Merge");
        merge.setMinWidth(180);
        merge.setMinHeight(50);
        Button crop = new Button("Crop");
        crop.setMinWidth(180);
        crop.setMinHeight(50);
        Button brightness = new Button("Brightness");
        brightness.setMinWidth(180);
        brightness.setMinHeight(50);
        Button saturation = new Button("Saturation");
        saturation.setMinWidth(180);
        saturation.setMinHeight(50);

        Separator one = new Separator();
        Separator two = new Separator();
        Separator three = new Separator();
        Separator four = new Separator();

        /*window.widthProperty().addListener((v, oldvalue, newvalue) -> {
            layout.setMinWidth(window.getWidth());
        });
        window.heightProperty().addListener((v1, oldvalue1, newvalue1) ->{
            layout.setMinHeight(window.getHeight());
        });*///when we resize the window

        Menu filemenu = new Menu("_File");
        MenuItem open, settings, save, saveas, Exit;

        //Menu ITEMS
        filemenu.getItems().add(open = new MenuItem("Open..."));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(settings = new MenuItem("Settings..."));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(save = new MenuItem("Save..."));
        filemenu.getItems().add(saveas = new MenuItem("Save As..."));
        filemenu.getItems().add(new SeparatorMenuItem());
        filemenu.getItems().add(Exit = new MenuItem("EXIT..."));

        //fliemenu Items Interaction
        open.setOnAction(e -> /*Open.display()*/{
            Stage win = new Stage();

            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));

            File file = fileChooser.showOpenDialog(win);

            if (file != null) {

              try {
                  BufferedImage bufferedImage = ImageIO.read(file);
                  Image image1 = SwingFXUtils.toFXImage(bufferedImage,null);
                  image.setImage(image1);
                  image.setVisible(true);
                  image.setPreserveRatio(true);
              } catch (IOException ex){
                  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
        });

        /*
        settings.setOnAction(e -> /*openwindow newstage newclass which will change default editing settings* /);
        save.setOnAction(e -> /*overwrite function* /);
        saveas.setOnAction(e -> /*openwindow newstage newclass* /);
        s = SaveAs.saveas();
        if(s == 1)
        {
        saveas.setDisable(true);
        }
        Exit.setOnAction(e -> window.close()/*Make sure to ask save the edited photo if not saved!!* /);
        window.setOnCloseRequest(e -> {e.consume();/*openwindow askingtosave or Exitwithoutsaving* /});* /
        Main Menu Bar
        */

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu);



        sliderbright.setBlockIncrement(10);
        slidersaturation.setBlockIncrement(10);

        VBox leftBox = new VBox(10);
        VBox rightBox = new VBox(10);

        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(5,5,5,5));
        bottomBox.setMaxSize(1000,100);

        leftBox.setMinWidth(180);
        leftBox.setMinHeight(400);
        leftBox.setPadding(new Insets(10,10,10,10));
        leftBox.alignmentProperty().set(Pos.CENTER);

        rightBox.setMinWidth(180);
        rightBox.setMinHeight(400);
        rightBox.setPadding(new Insets(10,10,10,10));
        rightBox.alignmentProperty().set(Pos.CENTER);

        Button greyscale = new Button();
        greyscale.setMinSize(115,115);
        greyscale.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label greyS = new Label("GreyScale");
        greyS.setMinSize(75,10);
        greyS.setAlignment(Pos.BASELINE_CENTER);

       /* Button exposure = new Button();
        exposure.setMinSize(60,60);
        exposure.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label expo = new Label("Exposure");
        expo.setMinSize(75,10);
        expo.setAlignment(Pos.BASELINE_CENTER);
*/
        Button invert = new Button();
        invert.setMinSize(115,115);
        invert.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label inver = new Label("Invert");
        inver.setMinSize(75,10);
        inver.setAlignment(Pos.BASELINE_CENTER);

        Button rgb = new Button();
        rgb.setMinSize(115,115);
        rgb.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label rgbb = new Label("RGB");
        rgbb.setMinSize(75,10);
        rgbb.setAlignment(Pos.BASELINE_CENTER);

        /*Button circle = new Button();
        circle.setMinSize(60,60);
        circle.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label cir = new Label("Circle");
        cir.setMinSize(75,10);
        cir.setAlignment(Pos.BASELINE_CENTER);

        Button flip = new Button();
        flip.setMinSize(60,60);
        flip.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label fli = new Label("Flip");
        fli.setMinSize(75,10);
        fli.setAlignment(Pos.BASELINE_CENTER);
*/
        Button mirror = new Button();
        mirror.setMinSize(115,115);
        mirror.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label mirr = new Label("Mirror");
        mirr.setMinSize(75,10);
        mirr.setAlignment(Pos.BASELINE_CENTER);

        /*Button offset = new Button();
        offset.setMinSize(60,60);
        offset.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label offs = new Label("Offset");
        offs.setMinSize(75,10);
        offs.setAlignment(Pos.BASELINE_CENTER);

        Button swim = new Button();
        swim.setMinSize(60,60);
        swim.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label swimm = new Label("Swim");
        swimm.setMinSize(75,10);
        swimm.setAlignment(Pos.BASELINE_CENTER);

        Button tileimagefilter = new Button();
        tileimagefilter.setMinSize(60,60);
        tileimagefilter.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label tileI = new Label("Tile Image");
        tileI.setMinSize(75,10);
        tileI.setAlignment(Pos.BASELINE_CENTER);

        Button twirl = new Button();
        twirl.setMinSize(60,60);
        twirl.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label twir = new Label("Twirl");
        twir.setMinSize(75,10);
        twir.setAlignment(Pos.BASELINE_CENTER);

      */Button water = new Button();
        water.setMinSize(115,115);
        water.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label wat = new Label("Water");
        wat.setMinSize(75,10);
        wat.setAlignment(Pos.BASELINE_CENTER);

      /*  Button colorhalftone = new Button();
        colorhalftone.setMinSize(60,60);
        colorhalftone.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label colorH = new Label("ColorHalfTone");
        colorH.setMinSize(75,10);
        colorH.setAlignment(Pos.BASELINE_CENTER);

        Button halftone = new Button();
        halftone.setMinSize(60,60);
        halftone.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label halfT = new Label("HalfTone");
        halfT.setMinSize(75,10);
        halfT.setAlignment(Pos.BASELINE_CENTER);

        Button light = new Button();
        light.setMinSize(60,60);
        light.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label ligh = new Label("Light");
        ligh.setMinSize(75,10);
        ligh.setAlignment(Pos.BASELINE_CENTER);

        Button smear = new Button();
        smear.setMinSize(60,60);
        smear.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label smea = new Label("Smear");
        smea.setMinSize(75,10);
        smea.setAlignment(Pos.BASELINE_CENTER);

        Button variableblur = new Button();
        variableblur.setMinSize(60,60);
        variableblur.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label variableB = new Label("Variable Blur");
        variableB.setMinSize(75,10);
        variableB.setAlignment(Pos.BASELINE_CENTER);

        Button oil = new Button();
        oil.setMinSize(60,60);
        oil.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label oill = new Label("Oil");
        oill.setMinSize(75,10);
        oill.setAlignment(Pos.BASELINE_CENTER);

        */Button smartblur = new Button();
        smartblur.setMinSize(115,115);
        smartblur.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label smartB = new Label("Smart Blur");
        smartB.setMinSize(75,10);
        smartB.setAlignment(Pos.BASELINE_CENTER);

       /* Button edge = new Button();
        edge.setMinSize(60,60);
        edge.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label edgee = new Label("Edge");
        edgee.setMinSize(75,10);
        edgee.setAlignment(Pos.BASELINE_CENTER);

        Button opacity = new Button();
        opacity.setMinSize(60,60);
        opacity.setStyle("-fx-background-image: url('gm_largerview_swm.jpg')");

        Label opaci = new Label("Opacity");
        opaci.setMinSize(75,10);
        opaci.setAlignment(Pos.BASELINE_CENTER);
*/
        GridPane.setConstraints(greyscale,0,0);
        GridPane.setConstraints(invert,1,0);
        GridPane.setConstraints(rgb,0,2);
        GridPane.setConstraints(mirror,1,2);
        GridPane.setConstraints(water,0,4);
        GridPane.setConstraints(smartblur,1,4);
        GridPane.setConstraints(greyS,0,1);
        GridPane.setConstraints(inver,1,1);
        GridPane.setConstraints(rgbb,0,3);
        GridPane.setConstraints(mirr,1,3);
        GridPane.setConstraints(wat,0,5);
        GridPane.setConstraints(smartB,1,5);

        effect.getChildren().addAll(greyscale,invert,rgb,mirror,water,smartblur,greyS,inver,rgbb,mirr,wat,smartB);
        GridPane.setHalignment(greyscale,HPos.CENTER);
        GridPane.setHalignment(invert,HPos.CENTER);
        GridPane.setHalignment(rgb,HPos.CENTER);
        GridPane.setHalignment(mirror,HPos.CENTER);
        GridPane.setHalignment(water,HPos.CENTER);
        GridPane.setHalignment(smartblur,HPos.CENTER);


        effect.setVisible(false);


        effects.setOnAction(e -> {

            if(image.isVisible()) {

                effect.setVisible(true);
                scrollPane.setVisible(true);

                scrollPane.setContent(effect);
                scrollPane.setMaxSize(295, 250);
                scrollPane.setMinWidth(260);
                anchorPane.getChildren().setAll(slidersaturation, sliderbright, scrollPane);

                stackPane.getChildren().setAll(layout, anchorPane);

                AnchorPane.setTopAnchor(scrollPane, 200.0);
                AnchorPane.setLeftAnchor(scrollPane, 205.0);

                effect.setStyle("-fx-background-color: #d6d6d6");

                primary.setOnMouseClicked(p -> {
                    stackPane.getChildren().setAll(anchorPane, layout);
                    scrollPane.setVisible(false);
                    effect.setVisible(false);

                });
            }
            else {
                bottomBox.getChildren().setAll(uploadfirst);
                uploadfirst.setVisible(true);
                uploadfirst.setStyle("-fx-background-color: darkred");
                uploadfirst.setStyle("-fx-font-weight: bold");

                primary.setOnMouseClicked(p -> uploadfirst.setVisible(false) );
            }
        });



        crop.setOnAction(e -> {
            anchorPane.setVisible(false);
            effects.setVisible(false);
            collage.setVisible(false);
            merge.setVisible(false);
            brightness.setVisible(false);
            saturation.setVisible(false);
            one.setVisible(false);
            two.setVisible(false);
            three.setVisible(false);
            four.setVisible(false);


            ImageCropWithRubberBand.RubberBandSelection rubberBandSelection = new ImageCropWithRubberBand.RubberBandSelection(group);

            Bounds bounds = rubberBandSelection.getBounds();

            int width = (int) bounds.getWidth();
            int height = (int) bounds.getHeight();

            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            parameters.setViewport(new Rectangle2D( bounds.getMinX(), bounds.getMinY(), width, height));

            WritableImage wi = new WritableImage( width, height);
            image.snapshot(parameters, wi);

            BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(wi, null);
            BufferedImage bufImageRGB = new BufferedImage(bufImageARGB.getWidth(), bufImageARGB.getHeight(), BufferedImage.OPAQUE);

            Graphics2D graphics = bufImageRGB.createGraphics();
            graphics.drawImage(bufImageARGB, 0, 0, null);

            crop.setOnAction(a -> {
                /*File file = null;

                try {

                    ImageIO.write(bufImageRGB, "jpg", file);

                    System.out.println( "Image saved to " + file.getAbsolutePath());

                } catch (IOException p) {
                    p.printStackTrace();
                }
                image.setImage(file);
*/
                SwingFXUtils.toFXImage(bufImageRGB,wi);
                image.setImage(wi);
            });



        });

        brightness.setOnAction(e -> {
            if(image.isVisible()) {
                sliderbright.setVisible(true);
                stackPane.getChildren().setAll(layout, anchorPane);
                anchorPane.getChildren().setAll(slidersaturation, scrollPane, sliderbright);
                sliderbright.setMaxSize(230, 50);
                sliderbright.prefHeightProperty().bind(brightness.heightProperty());
                sliderbright.prefWidthProperty().bind(brightness.widthProperty());
                sliderbright.setStyle("-fx-background-color: inherit");
                AnchorPane.setRightAnchor(sliderbright, 10.0);
                AnchorPane.setTopAnchor(sliderbright, 275.0);
                sliderbright.setValueChanging(true);

                primary.setOnMouseClicked(p -> {
                    stackPane.getChildren().setAll(anchorPane, layout);
                    sliderbright.setVisible(false);
                });
            }
            else {
                uploadfirst.setVisible(true);
                bottomBox.getChildren().setAll(uploadfirst);
                uploadfirst.setStyle("-fx-font-weight: bold");

                primary.setOnMouseClicked(p -> uploadfirst.setVisible(false));
            }

        });



        saturation.setOnAction(e -> {
            if(image.isVisible()) {
                slidersaturation.setVisible(true);
                stackPane.getChildren().setAll(layout, anchorPane);
                anchorPane.getChildren().setAll(sliderbright, scrollPane, slidersaturation);
                slidersaturation.setMaxSize(230, 50);
                slidersaturation.prefHeightProperty().bind(saturation.heightProperty());
                slidersaturation.prefWidthProperty().bind(saturation.widthProperty());
                slidersaturation.setStyle("-fx-background-color: inherit");

                slidersaturation.setValueChanging(true);

                AnchorPane.setRightAnchor(slidersaturation, 10.0);
                AnchorPane.setTopAnchor(slidersaturation, 348.0);

                primary.setOnMouseClicked(p -> {
                    stackPane.getChildren().setAll(anchorPane, layout);
                    slidersaturation.setVisible(false);
                });
            }
            else {
                bottomBox.getChildren().setAll(uploadfirst);
                uploadfirst.setStyle("-fx-font-weight: bolder");
                uploadfirst.setVisible(true);

                primary.setOnMouseClicked(p -> uploadfirst.setVisible(false));
            }
        });





        leftBox.getChildren().addAll(effects,one,collage,two,merge);

        rightBox.getChildren().addAll(crop,three,brightness,four,saturation);

        one.setValignment(VPos.CENTER);
        two.setValignment(VPos.CENTER);

        bottomBox.getChildren().addAll(uploadfirst);
        uploadfirst.setAlignment(Pos.BASELINE_LEFT);

        layout.setTop(menuBar);
        layout.setLeft(leftBox);
        layout.setRight(rightBox);
        layout.setCenter(group);
        layout.setBottom(bottomBox);
        layout.getStylesheets().add("MainCss.css");

        stackPane.prefHeightProperty().bind(primary.heightProperty());
        stackPane.prefWidthProperty().bind(primary.widthProperty());

        window.setScene(primary);
        window.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}
