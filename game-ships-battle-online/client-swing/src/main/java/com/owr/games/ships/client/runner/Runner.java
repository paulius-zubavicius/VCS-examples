package com.owr.games.ships.client.runner;
import com.owr.games.ships.client.runner.app.FxApp;
import com.owr.games.ships.client.runner.app.SplashScreen;
import com.sun.javafx.application.LauncherImpl;
public class Runner {
//	public static void main(String[] args) {
//
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//
//		ctx.register(AppConfig.class);
//
//		ctx.refresh();
//
////		Street street = ctx.getBean(Street.class);
////		street.isaususRytui();
//
//		ctx.close();
//
//		ShipsClient.launch(ShipsClient.class, args);
//	}

	public static void main(String[] args) {
		LauncherImpl.launchApplication(FxApp.class, SplashScreen.class, args);
	}
}
