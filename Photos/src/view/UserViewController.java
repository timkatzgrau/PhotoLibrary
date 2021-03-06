package view;

// Tim Katzgrau and Asad Dar

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import application.Photo;
import application.PhotosApp;
import application.models.Instagram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * @author Asad Dar
 * @author Tim Katzgrau
 * This class will represent the user view controller
 **/
public class UserViewController {
	

	   @FXML ListView<String> listView;   
	   @FXML ListView<String> songListView;
		@FXML TextField Name;
		@FXML TextField Artist;
		@FXML TextField Album;
		@FXML TextField Year;

	   private ObservableList<String> obsList;              
	  
	   public void start() {                
		      // create an ObservableList 
		      // from an ArrayList  
			   

	   }
	   
	   /**
	    * quits app
	    **/
	   public void Quit() throws Exception {
		   Instagram.getApp().signOut();

		   Instagram.writeApp(Instagram.getApp());
		   System.exit(0);
	   }
	   


}

