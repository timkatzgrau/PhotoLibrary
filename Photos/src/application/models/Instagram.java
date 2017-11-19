package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import application.models.*;

public class Instagram implements Serializable {
	
	ArrayList<User> users;
	
	ArrayList<Photo> photos;
	
	ArrayList<Album> albums;
	
	public static Instagram instagram;
	
	public User currentUser;
	
	public static Instagram getApp() {
		if (instagram == null) {
			instagram = new Instagram();
			instagram.init();
		}
		
		return instagram;
	}
	
	public void init() {
		//will initialize all fields
		//remember to change entire method for serializing
		users = new ArrayList<User>();
		photos = new ArrayList<Photo>();
		albums = new ArrayList<Album>();
	}
	
	public void authenticate(User user) {
		currentUser = user;
	}
	
	public User getUser(String username) {
		//being used currently for logging in and setting currentuser reference
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).username.equals(username)) {
				return users.get(i);
			}
		}
		
		User user = new User(username);
		users.add(user);
		return user;
		
	}
	
	
	public void signOut() {
		currentUser = null;
	}
	
	public void createAlbum(String name) {
		//get currently logged in user
		//create album associated with currently logged in user
		Album album = new Album(name);
		currentUser.getAlbums().add(album);
		albums.add(album);
	}
	
	
	public void deleteAlbum(Album album) {
		//get currently logged in user
		//delete album associated with currently logged in user
		currentUser.getAlbums().remove(album);
		albums.remove(album);
		
	}
	
	public ArrayList<Album> getAllAlbums() {
		return albums;
	}
	
	public void movePhotoTo(String photoLocation, Album fromAlbum, Album toAlbum) {
		
	}
	
	public void copyPhotoTo(String photoLocation, Album originAlbum, Album additionalAlbum) {
		
	}
	
	public void addPhoto(Photo photo, Album album) {
		
	}
	
	public void removePhoto(String photoLocation, Album album) {
		
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void addUser(User user) {
		
	}
	
	public void removeUser(User user) {
		
	}

}
