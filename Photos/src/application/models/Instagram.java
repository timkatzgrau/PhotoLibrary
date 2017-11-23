package application.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	public void createAlbum(String name, ArrayList<Photo> searchResults) {
		//get currently logged in user
		//create album associated with currently logged in user
		
		Album album = new Album(name);
		currentUser.getAlbums().add(album);
		albums.add(album);
		for(int i = 0; i < searchResults.size(); i++) {
			album.addPhoto(searchResults.get(i));
		}
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
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public ArrayList<Photo> searchByTags(Tag[] searchParams) {
		ArrayList<Photo> haveTags = new ArrayList<Photo>();
		ArrayList<Boolean> foundTags = new ArrayList<Boolean>();
		
		for (int i = 0; i < currentUser.getAlbums().size(); i++) {
			for (int j = 0; j < currentUser.getAlbums().get(i).getPhotos().size(); j++) {
				foundTags.clear();
				for (int z = 0; z < searchParams.length; z++) {
					for (int y = 0; y < currentUser.getAlbums().get(i).getPhotos().get(j).getTags().size(); y++) {
						if (currentUser.getAlbums().get(i).getPhotos().get(j).getTags().get(y).key.equals(searchParams[z].key) && currentUser.getAlbums().get(i).getPhotos().get(j).getTags().get(y).value.equals(searchParams[z].value)){
							foundTags.add(true);
						}
					}
				}
				
				if(foundTags.size() == searchParams.length && !haveTags.contains(currentUser.getAlbums().get(i).getPhotos().get(j))) {
					haveTags.add(currentUser.getAlbums().get(i).getPhotos().get(j));
				}
			}
		}
		
		return haveTags;
	}
	
	public ArrayList<Photo> searchByDates(String startDate, String endDate) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date sDate = sdf.parse(startDate);
		long sMillis = sDate.getTime();
		
		Date eDate = sdf.parse(endDate);
		long eMillis = eDate.getTime();
		
		ArrayList<Photo> haveDates = new ArrayList<Photo>();
		
		for (int i = 0; i < currentUser.getAlbums().size(); i++) {
			for (int j = 0; j < currentUser.getAlbums().get(i).getPhotos().size(); j++) {
				System.out.println(sMillis + ", " + currentUser.getAlbums().get(i).getPhotos().get(j).date + ", " + eMillis);
				if (currentUser.getAlbums().get(i).getPhotos().get(j).date < eMillis && currentUser.getAlbums().get(i).getPhotos().get(j).date > sMillis) {
					if (!haveDates.contains(currentUser.getAlbums().get(i).getPhotos().get(j))){
						haveDates.add(currentUser.getAlbums().get(i).getPhotos().get(j));
					}
				}
			}
		}
		
		return haveDates;
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}

}
