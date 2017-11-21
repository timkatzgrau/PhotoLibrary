package application.models;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {
	
	private String photoFileURI;
	
	private User author;
	
	private ArrayList<Tag> tags;
	
	public String stringDate;
	public long date;
	
	
	private String caption;
	
	public Photo(File file) {
		photoFileURI = file.toURI().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		date = file.lastModified();
		stringDate = sdf.format(date);
		tags = new ArrayList<Tag>();
		
		
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
	
	public ArrayList<Tag> getTags() {
		return tags;
	}

	public Image getImage() {
		Image image = new Image(photoFileURI);
		return image;
	}

}
