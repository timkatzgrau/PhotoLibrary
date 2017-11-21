package application.models;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	
	public String username;
	
	private ArrayList<Album> albums;
	
	public User(String username) {
		this.username = username;
		albums = new ArrayList<Album>(); //will need to adjust for serializing
	}
	
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public String toString() {
		return username;
	}

}
