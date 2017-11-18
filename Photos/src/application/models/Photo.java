package application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Photo implements Serializable {
	
	private String filePath;
	
	private User author;
	
	private ArrayList<Tag> tags;
	
	private Calendar date;
	
	private String caption;
	
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
