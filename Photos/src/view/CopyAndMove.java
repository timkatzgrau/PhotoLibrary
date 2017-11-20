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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import application.models.Album;
import application.models.Instagram;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ButtonBar.ButtonData;

public class CopyAndMove {
		
		public application.models.Photo photo;
		public Album album;

	   @FXML ListView<String> listView;   
	   @FXML ListView<String> songListView;
		@FXML TextField Name;
		@FXML TextField Artist;
		@FXML TextField Album;
		@FXML TextField Year;
		@FXML ImageView Display;
		@FXML ChoiceBox<Album> Choices;

	   private ObservableList<String> obsList;              
	  
	   public void start(application.models.Photo photo, Album album) {  
		   this.album = album;
		   this.photo = photo;
		   Display.setImage(photo.getImage());
		   for(int i = 0; i < Instagram.getApp().currentUser.getAlbums().size(); i ++) {
			   Choices.getItems().add(Instagram.getApp().currentUser.getAlbums().get(i));
		   }
		   

	   }
	   public void Move() {
		   if(Choices.getValue() == null) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No album selected.");
			   alert.setHeaderText(
					   "You must select an album to move to first.");
			   alert.showAndWait();
		   }else {
			   Instagram.getApp().movePhotoTo(photo, album, Choices.getValue());
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Success.");
			   alert.setHeaderText(
					   "Your photo has been moved!");
			   alert.showAndWait();
		   }
		   
	   }
	   public void Copy() {
		   if(Choices.getValue() == null) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No album selected.");
			   alert.setHeaderText(
					   "You must select an album to copy to first.");
			   alert.showAndWait();
		   }else {
			   Instagram.getApp().copyPhotoTo(photo, Choices.getValue());
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Success.");
			   alert.setHeaderText(
					   "Your photo has been copied!");
			   alert.showAndWait();
		   }
	   }
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "OpenAlbum", album);
	   }
	   
	  
	   
	   


}

