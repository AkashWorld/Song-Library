package application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.IOException;


public class SongList {
	private ArrayList<songinfo> array;
	private String path;
	
	//SongList construct that takes in a path to the file to be saved
	public SongList(){ //when new SongList object is created, the previous two variables
		//are declared and the following lines are executed
		path = System.getProperty("user.dir"); //gets the current file path of this folder
		path = path + "\\songlist.txt"; //string to add a songlist.txt text file to store song data
		System.out.println("Path being used for cache: "+path); //printout of the path, no computation is done
		array = new ArrayList<songinfo>(); //new ArrayList of type songinfo is created
		//this.load(); //the load function is executed which loads the textfile from the path initalized above,
		//if there is no data or textfile already, nothing will be done. If there is a textfile, it will
		//be parsed and the data will be loaded into the ArrayList. The purpose of this is to 
		//ensure that the state of the program is preserved if the user exits the program
		// and reopens it (song list stays the same)
	}
	
	//prints the entire list for debugging
	public int printList() {
		if(array.size() == 0) {
			return -1;
		}
		songinfo temp = null;
		int count = 0;
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		Iterator<songinfo> it = array.iterator();
		while(it.hasNext()) {
			count++;
			temp = it.next();
			System.out.println("Song " + count +"\nName: " + temp.name + " Artist:" + temp.artist + " Album:" +
			temp.album + " Year:" + temp.year + "\n");
			System.out.println("-------------------------------------------------------------------------");
		}
		return 1;
	}
	
	
	//returns arraylist inside the object
	public ArrayList<songinfo> getArrayList(){
		return array;
	}
	
	
	//returns songinfo object when given the name of the song and the artist's name as parameters.
	//The songinfo object has the following fields: Name, Artist, Album, Year
	public songinfo getSonginfoObj(String name, String artist) {
		songinfo target;
		Iterator<songinfo> songinfoIterator = array.iterator();
		while(songinfoIterator.hasNext()) {
			target = songinfoIterator.next();
			if(target.name.equals(name) && target.artist.equals(artist)) {
				return target;
			}
		}
		return null;
	}
	
	
	
	
	//sort comparator for array.sort() function that is used in the 'add()' and 'edit()' functions
	//sorts based on name of song first, then takes the artist into consideration
	Comparator<songinfo> cmp = new Comparator<songinfo>() {
	    @Override
	    public int compare(songinfo s1, songinfo s2) {
	    	if(s1.name.compareTo(s2.name) < 0) {
				return -1;
			}
			else if(s1.name.compareTo(s2.name) > 0) {
				return 1;
			}
			else {
				String currArtist = s1.artist;
				String compareArtist = s2.artist;
				if(currArtist.compareTo(compareArtist) < 0) {
					return -1;
				}
				else if(currArtist.compareTo(compareArtist) > 0) {
					return 1;
				}
			}
			return 0;    
	    }
	};
	
	public int getIndex(String name, String artist) {
		Iterator<songinfo> it = array.iterator();
		songinfo song;
		int index = 0;
		while(it.hasNext()) {
			song = it.next();
			if(song.name.equals(name) && song.artist.equals(artist)) {
				System.out.println("Index is: " +index);
				return index;
			}
			index++;
		}
		return -1;
	}
	
	//adds a song to the songinfo ArrayList assuming a copy does not exist
	public int add(String name, String artist, String album, String year) {
		if((array.size() != 0 && checkIfCopyExists(name, artist)) || ("".equals(name)||"".equals(artist)) ) {
			return -1;
		}
		songinfo song = new songinfo(name, artist, album, year);
		array.add(song);
		if(array.size()>1) {
		Collections.sort(array, cmp);
		}
		this.Save();
		return getIndex(name,artist);
	}
	
	
	
	//deletes song from the songinfo ArrayList taking in the name and artist as parameters
	public boolean deleteSong(String name, String artist) {
		Iterator<songinfo> it = array.iterator();
		songinfo temp = null;
		int index = 0;
		while(it.hasNext()) {
			temp = it.next();
			if(temp.name == name && temp.artist == artist) {
				array.remove(index);
				break;
			}
			if(!it.hasNext()) {
				return false;
			}
			index = index + 1;
		}
		return true;
	}
	
	
	
	
	

	
	
	
	//edits a songinfo object assuming a copy does not exist, takes in the index of the changed song, and the changed values
	public int edit(int index, String name, String artist, String year, String album) {
		songinfo Obj = array.get(index);
		String[] placeholder = new String[4];
			if((!checkIfCopyExists(name, Obj.artist) && artist == "") || (!checkIfCopyExists(name, artist))) {
			placeholder[0] = name;
			placeholder[1] = artist;
			}
			else {
				System.out.println("Copy exists - edit failed");
				return -1;
			}
			placeholder[2] = year;
		    placeholder[3] = album;
		    
		Obj.name = placeholder[0];
		Obj.artist = placeholder[1];
		Obj.year = placeholder[2];
		Obj.album = placeholder[3];
		Collections.sort(array, cmp);
		int i = getIndex(name, artist);
		return i;
	}
	
	
	
	
	//loads the entire list structure from the path file if the pathfile exists, otherwise
	//nothing is executed
	private boolean load() {
		String tempName; String tempArtist; String tempAlbum; 
		String tempYear; String buff; 
		try {
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            
            while ((buff = br.readLine())  != null) {
            	tempName = null; tempArtist = null; tempAlbum = null; tempYear = null;
            	tempName = buff;
            	
            	
            	
            	if((buff = br.readLine()) != null) {
            		tempArtist = buff;
            	}
            	else {
            		return false;
            	}
            	if((buff = br.readLine()) != null) {
            		tempAlbum = buff;
            	}
            	else {
            		return false;
            	}
            	if((buff = br.readLine()) != null) {
            		tempYear = buff;
            	}
            	else {
            		return false;
            	}
            	array.add(new songinfo(tempName, tempArtist, tempAlbum, tempYear));
            }
            br.close();
        }
		catch(FileNotFoundException e) {	
			return false;
		}
        catch (IOException e) {
            return false;
        }
			
	
		return true;	
	}
	
	
	
	
	//saves the entire list to the file identified by the path variable, this is done
	//to preserve the state of the list upon termination of the program. Once
	//the program is relaunched and a SongList object in re-initialized, the textfile
	//will be loaded into the ArrayList structure.
	private boolean Save() {
		if(array.size() == 0) {
			return false;
		}
		try {
			PrintWriter pw = new PrintWriter(new File(path));
			Iterator<songinfo> songiterator = array.iterator();
			songinfo temp;
			while(songiterator.hasNext()) {
				temp = songiterator.next();
				pw.println(temp.name);
				pw.println(temp.artist);
				pw.println(temp.album);
				pw.println(temp.year);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	//checks if an object with the input song name and song artist already exists in the arraylist
	private boolean checkIfCopyExists(String name, String artist) {
		songinfo target;
		Iterator<songinfo> songinfoIterator = array.iterator();
		while(songinfoIterator.hasNext()) {
			target = songinfoIterator.next();
			if(target.name.equals(name) && target.artist.equals(artist)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	
	
	//thread class for saving
	private class saveThread extends Thread{
		public void run() {
			Save();
		}
	}
	
	
	
}
