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
		 listview.getSelectionModel().select(songlist.add(song.name, song.artist, song.year, song.album));
	     createNewObslist();
	 }
	 
	 @FXML
	 private void onHandleEdit(ActionEvent event) {
	     // Button was clicked, do something...
	     songinfo song = getEditField();
	     listview.getSelectionModel().select(songlist.edit(listview.getSelectionModel().getSelectedIndex(), song.name, song.artist, song.year, song.album));
	     createNewObslist();
	 }
	 
	 @FXML
	 private void onHandleDelete(ActionEvent event) {
	     // Button was clicked, do something...
		int tempLoc = listview.getSelectionModel().getSelectedIndex();
		deleteSong(tempLoc);
		createNewObslist();
	     
	 }
	 
	 
	 public boolean deleteSong(int index)
		{
		 	ArrayList<songinfo >array = songlist.getArrayList();
			if(index>=0 && index< array.size())
			array.remove(index);
			else
				return false;
			return true;
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
			String name = editname.getText();
			String artist = editartist.getText();
			String album = editalbum.getText();
			String year = edityear.getText();
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
	 
	 

	 
	

	
}
