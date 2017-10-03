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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class ListController 
{
	SongList songlist;
	ArrayList<songinfo> array;
	@FXML Button addbutton;
	@FXML Button editbutton;
	@FXML Button deletebutton;
	@FXML ListView<String> listview;
	private ObservableList<String> obsList;
	Alert missingInfo;
	Alert matchExists;
	 public void start (Stage mainStage)
	 {
		 
		songlist = new SongList();
		songlist.add("name1", "artist1", "album1", "year1");
		songlist.add("aame1", "artist1", "album1", "year1");
		songlist.add("came1", "artist2", "album1", "year1");
		songlist.add("came1", "artist1", "album1", "year1");
		createNewObslist();
		listview.getSelectionModel().select(0);
		updateDescription(0); 
			missingInfo = new Alert(AlertType.INFORMATION);
		   //dialog.initOwner(mainStage); 
		   
		   missingInfo.setTitle("Alert!");
		  
		   missingInfo.setHeaderText("Missing info needed");
		  
		   missingInfo.setContentText("Please check that you have filled in both the name and artist fields.");
		   matchExists = new Alert(AlertType.INFORMATION);
		   //dialog.initOwner(mainStage); 
		   matchExists.setTitle("Alert!");
		   matchExists.setHeaderText("Repeat entry");
		   matchExists.setContentText("You cannot add a song with the same name and artist");
		 
		 
	 }
	 
	 public void updateDescription(int index){
		 textBox.getChildren().clear();
		 ArrayList<songinfo> array = songlist.getArrayList();
		 songinfo song = array.get(index);
		 Text text = new Text("Name: " + song.name +"\nArtist: "+ song.artist + "\nAlbum: " + song.album + "\nYear: " + song.year);
	     textBox.getChildren().add(text);
	 
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
	 TextFlow textBox;
	 
	 @FXML
	 private void mouseClick(MouseEvent arg0) {
		 textBox.getChildren().clear();
		 int index = listview.getSelectionModel().getSelectedIndex();
		 ArrayList<songinfo> array = songlist.getArrayList();
		 songinfo song = array.get(index);
		 Text text = new Text("Name: " + song.name +"\nArtist: "+ song.artist + "\nAlbum: " + song.album + "\nYear: " + song.year);
	     textBox.getChildren().add(text);
	 }
	 
	 @FXML
	 private void onHandleAdd(ActionEvent event) {
	     // Button was clicked, do something...
		 songinfo song = getEditField();
		 int holder = songlist.add(song.name, song.artist, song.year, song.album);
		 if(holder==-1)
		 {
			 matchExists.show();
			 return;
		 }
		 if(holder==-2)
		 {
		 missingInfo.show();
		 return;
		 }
	     createNewObslist();
	     listview.getSelectionModel().select(holder);
	 }
	 
	 @FXML
	 private void onHandleEdit(ActionEvent event) {
	     // Button was clicked, do something...
	     songinfo song = getEditField();
	     int index = songlist.edit(listview.getSelectionModel().getSelectedIndex(), song.name, song.artist, song.year, song.album);
	     System.out.print("Index in handle: " + index);
	     updateDescription(index);
	     createNewObslist();
	     listview.getSelectionModel().select(index);
	 }
	 
	 @FXML
	 private void onHandleDelete(ActionEvent event) {
	     // Button was clicked, do something...
		int tempLoc = listview.getSelectionModel().getSelectedIndex();
		deleteSong(tempLoc);
		if(tempLoc == songlist.getArrayList().size()) {
			listview.getSelectionModel().select(tempLoc-1);
			updateDescription(tempLoc-1);
			createNewObslist();
			return;
		}
		else if(songlist.getArrayList().isEmpty()){
			createNewObslist();
			return;
		}
		
		
		createNewObslist();
		listview.getSelectionModel().select(tempLoc);
		updateDescription(tempLoc);
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
