package application;
import javafx.event.ActionEvent;
import java.util.Optional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
public class ListController 
{
	SongList songlist;
	@FXML Button addbutton;
	@FXML Button editbutton;
	@FXML Button deletebutton;
	@FXML ListView<String> listview;
	 private ObservableList<String> obsList;
	 public void start (Stage mainStage)
	 {
		 
		songlist = new SongList();
		songlist.add("name1", "artist1", "album1", "year1");
		songlist.add("aame1", "artist1", "album1", "year1");
		songlist.add("came1", "artist2", "album1", "year1");
		songlist.add("came1", "artist1", "album1", "year1");
		
		ArrayList<songinfo> array = songlist.getArrayList();
		 createNewObslist();
		 
		 
		 listview.getSelectionModel().select(0);
		 listview.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> showItemInputDialog(mainStage,array));
		 
		 
		 
	 }
	 
	 private void createNewObslist() {
		 ArrayList<songinfo> array = songlist.getArrayList();
		 obsList = FXCollections.observableArrayList();
		 for(int i =0; i < array.size();i++)
		 {
			 obsList.add("Name: " + array.get(i).getName() + " Artist: " + array.get(i).getArtist());
		 }
		 listview.setItems(obsList);
	 }
	 
	 @FXML
	 private void onHandleAdd(ActionEvent event) {
	     // Button was clicked, do something...
		 songinfo song = getEditField();
		 songlist.add(song.name, song.artist, song.year, song.album);
	     createNewObslist();
	 }
	 
	 @FXML
	 private void onHandleEdit(ActionEvent event) {
	     // Button was clicked, do something...
	     songinfo song = getEditField();
	     songlist.edit(listview.getSelectionModel().getSelectedIndex(), song.name, song.artist, song.year, song.album);
	     createNewObslist();
	 }
	 
	 @FXML
	 private void onHandleDelete(ActionEvent event) {
	     // Button was clicked, do something...
	     
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
	 
	 private void showItemInputDialog(Stage mainStage, ArrayList<songinfo> array) {                
		   String itemName = listview.getSelectionModel().getSelectedItem();
		   int index = listview.getSelectionModel().getSelectedIndex();
		   songinfo item = array.get(index);
		   
		   TextInputDialog dialog = new TextInputDialog(item.toString());
		   dialog.initOwner(mainStage); 
		   dialog.setTitle("List Item");
		   dialog.setHeaderText("Selected Item (Index: " + index + ")");
		   dialog.setContentText("Enter name: ");
		   Optional<String> result = dialog.showAndWait();
		   if (result.isPresent()) {
			   item.changeName(result.get()); 
			   obsList.set(index, result.get());
		   }
	   }

	 
	

	
}
