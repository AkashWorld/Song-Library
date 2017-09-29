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
	public SongList(){
		path = System.getProperty("user.dir");
		path = path + "\\songlist.txt";
		System.out.println("Path being used for cache: "+path);
		array = new ArrayList<songinfo>();
		this.load();
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
	
	
	
	
	//sort comparator for array.sort() function that is used in the add function
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
	
	
	
	//adds a song to the songinfo arraylist assuming a copy does not exist
	public boolean add(String name, String artist, String album, String year) {
		if(array.size() != 0 && checkIfCopyExists(name, artist)) {
			return false;
		}
		songinfo song = new songinfo(name, artist, album, year);
		array.add(song);
		if(array.size()>1) {
		Collections.sort(array, cmp);
		}
		this.Save();
		return true;
	}
	
	
	
	
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
	public boolean edit(int index, String name, String artist, String year, String album) {
		songinfo Obj = array.get(index);
		String[] placeholder = new String[4];
			if((!checkIfCopyExists(name, Obj.artist) && artist == "") || (!checkIfCopyExists(name, artist))) {
			placeholder[0] = name;
			placeholder[1] = artist;
			}
			else {
				System.out.println("Copy exists - edit failed");
				return false;
			}
			placeholder[2] = year;
		    placeholder[3] = album;
		    
		Obj.name = placeholder[0];
		Obj.artist = placeholder[1];
		Obj.year = placeholder[2];
		Obj.album = placeholder[3];
		Collections.sort(array, cmp);
		return true;
	}
	
	
	
	
	//loads the entire list structure from the path file
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
	
	
	
	
	//saves the entire list to the file identified by the path variable
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
	public class saveThread extends Thread{
		public void run() {
			Save();
		}
	}
	
	
	
}
