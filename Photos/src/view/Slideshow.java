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
import application.models.Album;
import application.PhotosApp;
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

public class Slideshow {
	
		public Album album;
		public int currentIndex = 0;
	   @FXML ListView<String> listView;   
	   @FXML ListView<String> songListView;
		@FXML TextField Name;
		@FXML TextField Artist;
		@FXML TextField Album;
		@FXML TextField Year;
		@FXML ImageView Display;

	   private ObservableList<String> obsList;              
	  
	   public void start(Album album) {                
		    this.album = album;
		    Display.setImage(album.getPhotos().get(0).getImage());
	   }
	   
	   public void Previous() throws Exception {
		   if(currentIndex == 0){
			   currentIndex = album.getPhotos().size()-1;
			   Display.setImage(album.getPhotos().get(currentIndex).getImage());	
		   }else {
			   currentIndex--;
			   Display.setImage(album.getPhotos().get(currentIndex).getImage());   
		   }
	   }
	   public void Next() throws Exception {
		   if(currentIndex == album.getPhotos().size()-1){
			   currentIndex = 0;
			   Display.setImage(album.getPhotos().get(currentIndex).getImage());	
		   }else {
			   currentIndex++;
			   Display.setImage(album.getPhotos().get(currentIndex).getImage());
		   }
		   
		   
	   }
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "OpenAlbum", album);
	   }
	   
	  
	   
	   


}

