package com.owr.games.ships.client.app;

import com.owr.games.ships.client.panels.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    Media sound = new Media(new File("/home/owr/Public/-NEW-/Deftones - Greatest Hits - 2012/Disc 1/01. Rocket Skates.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxConfig.class);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        mediaPlayer.play();
        //		//ctx.getEnvironment().setActiveProfiles(Profiles.LOCAL);
       // ctx.register(AppCtxConfig.class);
//        ctx.refresh();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        //System.out.println("done");

        //FIXME
       // ctx.refresh();
		MainWindow mainWin = ctx.getBean(MainWindow.class);
//        MainWindow mainWin = new MainWindow();

        // Creates main window pane
        BorderPane mainWinPane = new BorderPane();

        // ... pass it to scene
        mainWin.init(mainWinPane);
        Scene scene = new Scene(mainWinPane);
        // scene.

        // HD 720
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setTitle("Ships battle");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(false);
        //primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("torp.png")));
        //primaryStage.getIcons().add(new Image("logo.jpg"));
        primaryStage.show();

        // Default first form
        //Nav nav = ctx.getBean(Nav.class);
        //nav.navClear(LoginForm.class);

        primaryStage.setOnCloseRequest((a) -> System.exit(0));


    }

    @Override
    public void stop() {
        ctx.close();
    }


}
