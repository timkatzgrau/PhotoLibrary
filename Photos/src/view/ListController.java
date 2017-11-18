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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import application.models.*;

public class ListController {
	
		@FXML TextField Login;

		
		

	   private ObservableList<String> obsList;              
	  
	   
	   public void goToUserView() throws Exception {
		   if(Login.getText().length() == 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No Username Entered");
			   alert.setHeaderText(
					   "You must fill out the required field Username");
			   alert.showAndWait();
		   }else {
			   String username = Login.getText();
			   User user = Instagram.getApp().getUser(username);
			   Instagram.getApp().authenticate(user);
			   
			   Scene scene = PhotosApp.mainStage.getScene();
			   PhotosApp.changeScene(scene, "nonAdminView");
		   }
		   
	   }
	   
	   public void start() {
		   
	   }


}

