package application.models;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	
	String username;
	
	ArrayList<Album> albums;

}
