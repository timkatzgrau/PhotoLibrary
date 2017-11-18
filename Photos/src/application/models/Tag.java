package application.models;

import java.io.Serializable;

public class Tag implements Serializable {
	
	public String key;
	
	public String value;
	
	public Tag (String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public boolean equals(Object o) {
		Tag tag = (Tag)o;
		
		if (key == tag.key && value == tag.value) {
			return true;
		} else {
			return false;
		}
	}

}
