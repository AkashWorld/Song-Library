package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	String names[] = {"I fall apart", "Mask off", "Novacane", "EmSong", "Its lit"};
	String artist[] = {"Post Malone", "Future", "Post Malone", "Eminem", "Post Malone"};
	String album[] = {"Stoney", "Drugs", "Stoney", "Hard", "Stoney"};
	String year[] = {"2017", "2016", "", "2009","2017"};
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		SongList songlist = new SongList();
		int i = 0;
		while(i<5) {
			songlist.add(names[i], artist[i], album[i], year[i]);
			i = i + 1;
		}
		songlist.printList();
		songlist.deleteSong(names[2], artist[2]);
		songlist.printList();
		songlist.add("I fall apart", "Post Malone", "", "");
		songlist.printList();
		
			
	}
	
	public static void main(String[] args) {
			launch(args);
	}
}
