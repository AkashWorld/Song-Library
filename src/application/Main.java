package application;
	
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class Main extends Application {

	@FXML
	private ListView<songinfo> list;
	@FXML
	private Button addbutton;
	@FXML
	private AnchorPane root;
	ArrayList<songinfo> array;
	
	
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("libraryscene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SongList songlist = new SongList();
		array = songlist.getArrayList();
		songlist.add("Post Malone", "RockStar","", "1963");
		songlist.add("Hello", "World", "First", "1351");
		list = new ListView<>();
		ObservableList<songinfo> ObservArray = FXCollections.observableArrayList(array);
		list.setItems(ObservArray);
		
        primaryStage.setTitle("Song Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


			
	}
	
	
	@FXML
	TextField editname;
	@FXML
	TextField editartist;
	@FXML
	TextField editalbum;
	@FXML
	TextField edityear;
	public songinfo getEditField() { //returns songinfo object with the values in the edit field
		String name = editname.getCharacters().toString();
		String artist = editartist.getCharacters().toString();
		String album = editalbum.getCharacters().toString();
		String year = edityear.getCharacters().toString();
		return new songinfo(name, artist,album,year);
		
	}
	
	
	@FXML
	TextFlow textbox; //textflow reference
	
	public void onSelectSong(int index, ArrayList<songinfo> array) {
		songinfo selectedSong = array.get(index); //index of selected song
		Text text = new Text("Name: " + selectedSong.name+ "\nArtist: " + selectedSong.artist 
				+ "\nAlbum: " + selectedSong.album + "\nYear: " + selectedSong.year); //text in textflow box
        textbox.getChildren().add(text); 
        return;
	}
	
	
	
	public static void main(String[] args) {
			launch(args);
	}
}
