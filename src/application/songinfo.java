package application;

public class songinfo {
	public String name;
	public String artist;
	public String album;
	public String year;
	songinfo(String name, String artist, String album, String year){
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Song: " + name + " Artist: " + artist;
	}
	
	
	

}
