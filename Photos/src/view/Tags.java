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

public class Tags {
	
		public Album album;
		public application.models.Photo photo;

		@FXML TextField Key;
		@FXML TextField Value;
		@FXML ImageView Display;
		@FXML ListView<String> ListDisplay;
	

	   private ObservableList<String> obsList = FXCollections.observableArrayList();      
	  
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
	   
	   public void reload() {
		   obsList.clear();
		   ListDisplay.getItems().clear();
		   for(int i = 0; i < photo.getTags().size(); i++) { 
			   System.out.println(photo.getTags().get(i).toString());
			   obsList.add(photo.getTags().get(i).toString());
		   }
		   System.out.println("jhvjhvjhvjhv"+obsList.get(0));

		   ListDisplay.setItems(obsList);
		   
	   }
	   
	   public void Add() { 
		   if(Key.getText().equals("") || Value.getText().equals("")) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Invalid Key or Value");
			   alert.setHeaderText("Invalid Key or Value");
			   alert.showAndWait();
		   }else {
			   System.out.println("== " + Value.getText());
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
	   public void Delete() { 
		   int index = ListDisplay.getSelectionModel().getSelectedIndex();
		   photo.getTags().remove(index);
		   reload();
	   }
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "OpenAlbum", album);
	   }
	   
	   public void Quit() throws Exception {
		   Instagram.getApp().signOut();

		   Instagram.writeApp(Instagram.getApp());
		   System.exit(0);
	   }
	   
	   


}

