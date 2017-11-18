package application.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
	
	public String name;
	
	private ArrayList<Photo> photos;
	
	public Album(String name) {
		this.name = name;
	}
	
	public void addPhoto(Photo photo) {
		
	}
	
	public void removePhoto(Photo photo) {
		
	}
	
	public ArrayList<Photo> getPhotos() {
		return new ArrayList(); //change this obviously
	}
	
	public String toString() {
		return name + " " + photos.size() + " photos";
	}
	

}
