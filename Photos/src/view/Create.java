package view;

// Tim Katzgrau and Asad Dar

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import application.models.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import application.Photo;
import application.PhotosApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
 * This class will represent the create controller
 **/
public class Create {
	
		@FXML TextField Name;

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
	   
	   /**
	    * goes back to previous scene
	    **/
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "nonAdminView");
	   }
	   
	   /**
	    * creates album
	    **/
	   public void SaveAlbum() throws Exception {
		   String name = Name.getText();
		   boolean nameExists = false;
		   
		   if(name.length() == 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No Name Entered");
			   alert.setHeaderText(
					   "You must fill out the required field Name before saving");
			   alert.showAndWait();
		   }
		   for(int i = 0; i < Instagram.getApp().currentUser.getAlbums().size(); i++) {
			   if(Instagram.getApp().currentUser.getAlbums().get(i).name.equals(name)) {
				   nameExists = true;
				   break;
			   }
		   }
		   if(nameExists) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Name Exists");
			   alert.setHeaderText(
					   "Please choose another name.");
			   alert.showAndWait();
		   }else {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Success");
			   alert.setHeaderText(
					   "Your album has been created!");
			   alert.showAndWait();
			   Instagram.getApp().createAlbum(name);
		   }

		   
	   }
	   
	  
	   
	   


}

