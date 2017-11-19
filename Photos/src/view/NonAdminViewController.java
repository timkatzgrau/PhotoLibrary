package view;

// Tim Katzgrau and Asad Dar

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import application.Photo;
import application.PhotosApp;
import application.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class NonAdminViewController {
	

	   @FXML ListView<String> listView;   
	   private ObservableList<String> obsList;   
	   
	  
	   public void start() {         
		   obsList = FXCollections.observableArrayList();
		   
		   for(int i = 0; i < Instagram.getApp().currentUser.getAlbums().size(); i++) {
			   obsList.add(Instagram.getApp().currentUser.getAlbums().get(i).toString());
		   }
		   listView.setItems(obsList);

	   }
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "login");
	   }
	   
	   public void openAlbum() throws Exception {
		   int index = listView.getSelectionModel().getSelectedIndex();
		   if(index >= 0) {
			   Scene scene = PhotosApp.mainStage.getScene();
			   PhotosApp.changeScene(scene, "OpenAlbum", Instagram.getApp().currentUser.getAlbums().get(index));
		   }
	   }
	   public void Create() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Create");
	   }
	   public void Search() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Search");
	   }
	   public void DeleteAlbum() throws Exception{
		   int index = listView.getSelectionModel().getSelectedIndex();
		   obsList.remove(index);
		   Instagram.getApp().deleteAlbum(Instagram.getApp().currentUser.getAlbums().get(index));
		   
	   }
	   
	   public void RenameAlbum() throws Exception{
		   
		   int index = listView.getSelectionModel().getSelectedIndex();
		   if(index >= 0) {
			   TextInputDialog dialog = new TextInputDialog();
			   dialog.setTitle("Rename");
			   dialog.setContentText("Please enter a name:");
	
			   // Traditional way to get the response value.
			   Optional<String> result = dialog.showAndWait();
			   if (result.isPresent()){
			       Instagram.getApp().currentUser.getAlbums().get(index).setName(result.toString());
			       obsList.set(index, Instagram.getApp().currentUser.getAlbums().get(index).name);
			       for (int i = 0; i < Instagram.getApp().currentUser.getAlbums().size(); i++) {
			    	   	System.out.println(Instagram.getApp().currentUser.getAlbums().get(i));
			    	   	System.out.println(Instagram.getApp().getAllAlbums().get(i));
			       }
			   }
		   }else {
			   System.out.println(obsList);
		   }
		   
	   }
	   


}

