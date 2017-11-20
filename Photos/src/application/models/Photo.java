package application.models;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {
	
	private String photoFileURI;
	
	private User author;
	
	private ArrayList<Tag> tags;
	

	
	private Calendar date;
	
	private String caption;
	
	public Photo(File file) {
		photoFileURI = file.toURI().toString();
		
	}
	
	public String getPhotoFileURI() {
		return photoFileURI;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public void removeTag(Tag tag) {
		tags.remove(tag);
	}
	
	public Image getImage() {
		Image image = new Image(photoFileURI);
		return image;
	}

}
