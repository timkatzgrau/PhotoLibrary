package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Photo implements Serializable {
	
	String filePath;
	
	User author;
	
	ArrayList<Tag> tags;
	
	Calendar date;
	
	String caption;
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public void removeTag(Tag tag) {
		tags.remove(tag);
	}

}
