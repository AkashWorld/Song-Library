package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import application.ListController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	
	
	@Override
	public void start(Stage primaryStage) 
		throws Exception 
		{

        primaryStage.setTitle("Song Library");
        //primaryStage.show();
        FXMLLoader loader = new FXMLLoader();   
	      loader.setLocation(getClass().getResource("/application/libraryscene.fxml"));
	     
	      AnchorPane root = (AnchorPane)loader.load();
	      
	      ListController listController = loader.getController();
	      listController.start(primaryStage);
	      
	      Scene scene = new Scene(root, 200, 300);
	      
	      primaryStage.setScene(scene);
	      
	      primaryStage.show(); 

		}	
	
	public static void main(String[] args) {
			launch(args);
	}
}