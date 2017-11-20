package application.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import application.models.*;

public class Instagram implements Serializable {
	
	public static final String storeFile = "database.dat";
	static final long serialVersionUID = 1L;
	
	ArrayList<User> users;
	
	ArrayList<Photo> photos;
	
	ArrayList<Album> albums;
	
	public static Instagram instagram;
	
	public User currentUser;
	
	public static void create() throws ClassNotFoundException, IOException {
		instagram = readApp();
	}
	
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
	
	public static void writeApp(Instagram iapp) throws IOException {
		 ObjectOutputStream oos = new ObjectOutputStream(
		 new FileOutputStream(storeFile));
		 oos.writeObject(iapp);
	}
	
	//what is returned on the very first start
	public static Instagram readApp()
			throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream ois = new ObjectInputStream(
			new FileInputStream(storeFile));
			Instagram iapp = (Instagram)ois.readObject();
			return iapp;
		} catch (Exception e) {
			return null;
		}
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
	
	public void movePhotoTo(Photo photo, Album fromAlbum, Album toAlbum) {
		fromAlbum.getPhotos().remove(photo);
		toAlbum.getPhotos().add(photo);
		
	}
	
	public void copyPhotoTo(Photo photo, Album additionalAlbum) {
		additionalAlbum.getPhotos().add(photo);
	}
	
	public void addPhoto(File file, Album album) {
		//add to insta photo list
		//add to album
		Photo photo = new Photo(file);
		photos.add(photo);
		album.getPhotos().add(photo);
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
