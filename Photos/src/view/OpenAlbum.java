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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;

public class OpenAlbum {
	

	   @FXML Label AlbumName;
	   Album album;

	   private ObservableList<String> obsList;              
	  
	   public void start(Album album) {                
		   AlbumName.setText(album.toString());
		   this.album = album;
		   

	   }
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "nonAdminView");
	   }
	   
	   public void Slideshow() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Slideshow");
	   }
	   
	   public void CopyAndMove() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "CopyAndMove");
	   }
	   public void Tags() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Tags");
	   }
	   public void Add() throws Exception {
		   FileChooser fileChooser = new FileChooser();
		   fileChooser.setTitle("Open Resource File");
		   fileChooser.getExtensionFilters().addAll(
		           new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		   File selectedFile = fileChooser.showOpenDialog(PhotosApp.mainStage);
		   if (selectedFile != null) {
		      Instagram.getApp().addPhoto(selectedFile, album);
		   }
	   }
	   


}

