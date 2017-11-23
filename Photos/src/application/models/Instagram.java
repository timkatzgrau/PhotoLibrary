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
import java.util.concurrent.TimeUnit;

import application.models.*;

/**
 * @author Asad Dar
 * @author Tim Katzgrau
 * This class will represent a manager for the photo app and a host of utility functions
 **/
public class Instagram implements Serializable {
	
	/**
	 * file used for serialization
	 **/
	public static final String storeFile = "database.dat";
	
	/**
	 * serial version uid
	 **/
	static final long serialVersionUID = 1L;
	
	/**
	 * all of the users using the photo album app
	 **/
	ArrayList<User> users;
	
	/**
	 * all of the photos uploaded to the photo album app
	 **/
	ArrayList<Photo> photos;
	
	/**
	 * all of the albums uploaded to the photo album app
	 **/
	ArrayList<Album> albums;
	
	/**
	 * an instance used to access fields and call methods to use app functionality
	 **/
	public static Instagram instagram;
	
	/**
	 * the currently signed in user
	 **/
	public User currentUser;
	
	/**
	 * sets the instance of the class by deserializing
	 **/
	public static void create() throws ClassNotFoundException, IOException {
		instagram = readApp();
	}
	
	/**
	 * @return the instance of the class
	 **/
	public static Instagram getApp() {
		if (instagram == null) {
			instagram = new Instagram();
			instagram.init();
		}
		
		return instagram;
	}
	
	/**
	 * initializes fields for brand new instance
	 **/
	public void init() {
		users = new ArrayList<User>();
		photos = new ArrayList<Photo>();
		albums = new ArrayList<Album>();
	}
	
	/**
	 * @param iapp
	 * the instance that will be serialized for data persistance
	 **/
	public static void writeApp(Instagram iapp) throws IOException {
		 ObjectOutputStream oos = new ObjectOutputStream(
		 new FileOutputStream(storeFile));
		 oos.writeObject(iapp);
	}
	
	/**
	 * @return deserialized instance of the class
	 **/
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
	
	/**
	 * @param user
	 * user to be signed in
	 **/
	public void authenticate(User user) {
		currentUser = user;
	}
	
	/**
	 * @param username
	 * returns object of user with specific username
	 **/
	public User getUser(String username) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).username.equals(username)) {
				return users.get(i);
			}
		}
		
		User user = new User(username);
		users.add(user);
		return user;
		
	}
	
	/**
	 * signs user out
	 **/
	public void signOut() {
		currentUser = null;
	}
	
	/**
	 * @param name
	 * name for album to be created
	 **/
	public void createAlbum(String name) {
		Album album = new Album(name);
		currentUser.getAlbums().add(album);
		albums.add(album);
	}
	
	/**
	 * @param name
	 * name for album to be created
	 * @param searchResults
	 * search results to construct a new album
	 **/
	public void createAlbum(String name, ArrayList<Photo> searchResults) {
		Album album = new Album(name);
		currentUser.getAlbums().add(album);
		albums.add(album);
		for(int i = 0; i < searchResults.size(); i++) {
			album.addPhoto(searchResults.get(i));
		}
	}
	
	
	/**
	 * @param album
	 * album to be deleted
	 **/
	public void deleteAlbum(Album album) {
		currentUser.getAlbums().remove(album);
		albums.remove(album);
		
	}
	
	/**
	 * @return all albums made in the photo album app
	 **/
	public ArrayList<Album> getAllAlbums() {
		return albums;
	}
	
	/**
	 * @param photo
	 * photo to be moved
	 * @param fromAlbum
	 * album the photo is moved from
	 * @param toAlbum
	 * album the photo is moved to
	 **/
	public void movePhotoTo(Photo photo, Album fromAlbum, Album toAlbum) {
		fromAlbum.getPhotos().remove(photo);
		toAlbum.getPhotos().add(photo);
		
	}
	
	/**
	 * @param photo
	 * photo to be copied
	 * @param additionalAlbum
	 * album the photo is copied to
	 **/
	public void copyPhotoTo(Photo photo, Album additionalAlbum) {
		additionalAlbum.getPhotos().add(photo);
	}
	
	/**
	 * @param file
	 * file uploaded for photo
	 * @param album
	 * album the photo is created in
	 **/
	public void addPhoto(File file, Album album) {
		Photo photo = new Photo(file);
		
		photos.add(photo);
		album.getPhotos().add(photo);
	}
	
	/**
	 * @return list of users using photo album app
	 **/
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * @param searchParams
	 * tags the user is searching for
	 * @return list of photos with searched for tags
	 **/
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
	
	/**
	 * @param startDate
	 * earliest date in range
	 * @param endDate
	 * latest date in range
	 * @return list of photos in dates range
	 **/
	public ArrayList<Photo> searchByDates(String startDate, String endDate) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date sDate = sdf.parse(startDate);
		long sMillis = sDate.getTime();
		
		Date eDate = sdf.parse(endDate);
		long eMillis = eDate.getTime() + TimeUnit.DAYS.toMillis(1);
		
		ArrayList<Photo> haveDates = new ArrayList<Photo>();
		
		for (int i = 0; i < currentUser.getAlbums().size(); i++) {
			for (int j = 0; j < currentUser.getAlbums().get(i).getPhotos().size(); j++) {
				if (currentUser.getAlbums().get(i).getPhotos().get(j).date <= eMillis && currentUser.getAlbums().get(i).getPhotos().get(j).date >= sMillis) {
					if (!haveDates.contains(currentUser.getAlbums().get(i).getPhotos().get(j))){
						haveDates.add(currentUser.getAlbums().get(i).getPhotos().get(j));
					}
				}
			}
		}
		
		return haveDates;
	}
	
	/**
	 * @param user
	 * user to be added to photo album app
	 **/
	public void addUser(User user) {
		users.add(user);
	}
	
	/**
	 * @param user
	 * user to be removed from photo album app
	 **/
	public void removeUser(User user) {
		users.remove(user);
	}

}
