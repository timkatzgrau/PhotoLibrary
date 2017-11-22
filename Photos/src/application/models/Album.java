package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.image.Image;

public class Album implements Serializable {
	
	public String name;
	
	private ArrayList<Photo> photos;
	
	public Album(String name) {
		this.name = name;
		photos = new ArrayList<Photo>();
	}
	
	public void addPhoto(Photo photo) {
		photos.add(photo);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	
	public String dateRange() {
		if (photos.size() == 0) {
			return "";
		}
		
		ArrayList<Photo> temp = new ArrayList<Photo>();
		for (int i = 0; i < photos.size(); i++) {
			temp.add(photos.get(i));
		}
		
		temp.sort(Comparator.comparing(Photo::getDate));
		
		return temp.get(0).stringDate + " - " + temp.get(temp.size()-1).stringDate;
	}
	
	public String toString() {
		return name;
	}
	

}
