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

/**
 * @author Asad Dar
 * @author Tim Katzgrau
 * This class will represent the non admin view controller
 **/
public class NonAdminViewController {
	

	   @FXML ListView<String> listView;   
	   private ObservableList<String> obsList;   
	   
	   /**
	    * loads user's albums list
	    **/
	   public void start() {         
		   obsList = FXCollections.observableArrayList();
		   
		   for(int i = 0; i < Instagram.getApp().currentUser.getAlbums().size(); i++) {
			   obsList.add(Instagram.getApp().currentUser.getAlbums().get(i).toString()+"   " + Instagram.getApp().currentUser.getAlbums().get(i).getPhotos().size() + " Photos   "+Instagram.getApp().currentUser.getAlbums().get(i).dateRange());
		   }
		   
		   listView.setItems(obsList);

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
		   Instagram.getApp().signOut();

		   Instagram.writeApp(Instagram.getApp());
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "login");
	   }
	   
	   /**
	    * changes scene to album's contents
	    **/
	   public void openAlbum() throws Exception {
		   int index = listView.getSelectionModel().getSelectedIndex();
		   if(index >= 0) {
			   Scene scene = PhotosApp.mainStage.getScene();
			   PhotosApp.changeScene(scene, "OpenAlbum", Instagram.getApp().currentUser.getAlbums().get(index));
		   }
	   }
	   
	   /**
	    * goes to create scene
	    **/
	   public void Create() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Create");
	   }
	   
	   /**
	    * goes to search scene
	    **/
	   public void Search() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "Search");
	   }
	   
	   /**
	    * removes album
	    **/
	   public void DeleteAlbum() throws Exception{
		   int index = listView.getSelectionModel().getSelectedIndex();
		   obsList.remove(index);
		   Instagram.getApp().deleteAlbum(Instagram.getApp().currentUser.getAlbums().get(index));
		   
	   }
	   
	   /**
	    * renames album
	    **/
	   public void RenameAlbum() throws Exception{
		   
		   int index = listView.getSelectionModel().getSelectedIndex();
		   if(index >= 0) {
			   TextInputDialog dialog = new TextInputDialog();
			   dialog.setTitle("Rename");
			   dialog.setContentText("Please enter a name:");
	
			   // Traditional way to get the response value.
			   Optional<String> result = dialog.showAndWait();
			   if (result.isPresent()){
			       Instagram.getApp().currentUser.getAlbums().get(index).setName(result.toString().substring(9,result.toString().length()-1));
			       obsList.set(index, Instagram.getApp().currentUser.getAlbums().get(index).name +"   " + Instagram.getApp().currentUser.getAlbums().get(index).getPhotos().size() + " Photos   "+Instagram.getApp().currentUser.getAlbums().get(index).dateRange());

			   }
		   }else {
			   System.out.println(obsList);
		   }
		   
	   }
	   


}

