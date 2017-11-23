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
import application.models.Album;
import application.models.Instagram;
import application.models.Tag;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * @author Asad Dar
 * @author Tim Katzgrau
 * This class will represent the tags controller
 **/
public class Tags {
	
		public Album album;
		public application.models.Photo photo;

		@FXML TextField Key;
		@FXML TextField Value;
		@FXML ImageView Display;
		@FXML ListView<String> ListDisplay;
	

	   public ObservableList<String> obsList = FXCollections.observableArrayList();      
	  
	   /**
	    * initializes tags scene
	    **/
	   public void start(application.models.Photo photo, Album album) { 
		   this.album = album;
		   this.photo = photo;
		   Display.setImage(photo.getImage());
		   
		   for(int i = 0; i < photo.getTags().size(); i++) {
			   obsList.add(photo.getTags().get(i).toString());
		   }
		   ListDisplay.setItems(obsList);

	   }
	   public void Display() { 
		   

	   }
	   
	   /**
	    * reloads tags in list for photo
	    **/
	   public void reload() {
		   obsList.clear();
		   ListDisplay.getItems().clear();
		   for(int i = 0; i < photo.getTags().size(); i++) {
			   obsList.add(photo.getTags().get(i).toString());
		   }
		   ListDisplay.setItems(obsList);
		   
	   }
	   
	   /**
	    * adds tag for photo
	    **/
	   public void Add() { 
		   if(Key.getText().equals("") || Value.getText().equals("")) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Invalid Key or Value");
			   alert.setHeaderText("Invalid Key or Value");
			   alert.showAndWait();
		   }else {
			   boolean exists = false;
			   for(int i = 0; i < photo.getTags().size(); i++) {
				   if(photo.getTags().get(i).key.equals(Key.getText()) && photo.getTags().get(i).value.equals(Value.getText())) {
					   exists = true;
				   }
			   }
			   if(!exists) {
				   photo.addTag(new Tag(Key.getText(),Value.getText()));
				   reload();
			   }else {
				   Alert alert = new Alert(AlertType.INFORMATION);
				   alert.setTitle("Already Exists");
				   alert.setHeaderText("This Key and Value Pair Already Exists.");
				   alert.showAndWait();
			   }
			   
		   }

	   }
	   
	   /**
	    * deletes tag for photo
	    **/
	   public void Delete() { 
		   int index = ListDisplay.getSelectionModel().getSelectedIndex();
		   photo.getTags().remove(index);
		   reload();
	   }
	   
	   /**
	    * goes back to previous scene
	    **/
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "OpenAlbum", album);
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

