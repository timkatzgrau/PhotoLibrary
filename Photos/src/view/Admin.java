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
import application.models.Instagram;
import application.models.User;
import application.PhotosApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * @author Asad Dar
 * @author Tim Katzgrau
 * This class will represent the Admin controller
 **/
public class Admin {
	
		public Album album;
		public int currentIndex = 0;
	    @FXML ListView<User> listDisplay;   

	    /**
		 * observable list of users
		 **/
	   private ObservableList<User> obsList = FXCollections.observableArrayList();              
	  
	   /**
		 * sets list of users using app
		 **/
	   public void start() {     
		   
		   for(int i = 0; i < Instagram.getApp().getUsers().size(); i++) {
			   obsList.add(Instagram.getApp().getUsers().get(i));
		   }
		   listDisplay.setItems(obsList);
		   
	   }
	   
	   /**
		 * reloads list of users
		 **/
	   public void reload() {
		   obsList.clear();
		   listDisplay.getItems().clear();
		   
		   for(int i = 0; i < Instagram.getApp().getUsers().size(); i++) {
			   obsList.add(Instagram.getApp().getUsers().get(i));
		   }
		   listDisplay.setItems(obsList);
		   
	   }
	   
	   /**
		 * adds user to app
		 **/
	   public void AddUser() {
		   
		   TextInputDialog dialog = new TextInputDialog();
		   dialog.setTitle("Add User");
		   dialog.setContentText("Please enter a name:");
		   
		   Optional<String> result = dialog.showAndWait();
		   if (result.isPresent()){
			   String formattedResult = result.toString().substring(9, result.toString().length()-1);
			   boolean exists = false;
			   for(int i = 0; i < Instagram.getApp().getUsers().size(); i++) {
				   if(Instagram.getApp().getUsers().get(i).username.equals(formattedResult)) {
					   exists = true;
				   }
			   }
			   
			   if(exists) {
				   Alert alert = new Alert(AlertType.INFORMATION);
				   alert.setTitle("Already Exists");
				   alert.setHeaderText(
						   "This User already Exists");
				   alert.showAndWait();
			   } else {
				   User user = new User(formattedResult);
				   Instagram.getApp().addUser(user);
			   }
			   reload();

		   }
		   
	   }
	   
	   /**
		 * removes user from app
		 **/
	   public void DeleteUser() {
		   int index = listDisplay.getSelectionModel().getSelectedIndex();
		   Instagram.getApp().removeUser(listDisplay.getItems().get(index));
		   reload();
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
	   
	   public void listDisplay() throws Exception {

	   }
	   
}

