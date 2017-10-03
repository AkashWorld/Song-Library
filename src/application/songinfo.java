package application;

public class songinfo {
	String name;
	String artist;
	String album;
	String year;
	songinfo(String name, String artist, String album, String year){
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	public String getName()
	{
		return name;
	}
	public String getArtist()
	{
		return artist;
	}
	public String getAlbum()
	{
		return album;
	}
	public String getYear()
	{
		return year;
	}
	
	public String toString()
	{
		return name+" - "+artist;
	}
	public void changeName(String name)
	{
		this.name=name;
	}
	public void changeArtist(String artist)
	{
		this.artist=artist;
	}
	public void changeAlbum(String album)
	{
		this.album=album;
	}
	public void changeYear(String year)
	{
		this.year=year;
	}

}