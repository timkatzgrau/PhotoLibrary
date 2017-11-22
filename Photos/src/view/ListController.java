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
	   
	   public void Quit() throws Exception {
		   Instagram.getApp().signOut();

		   Instagram.writeApp(Instagram.getApp());
		   System.exit(0);
	   }
	   
	   public void goToUserView() throws Exception {
		   if(Login.getText().length() == 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No Username Entered");
			   alert.setHeaderText(
					   "You must fill out the required field Username");
			   alert.showAndWait();
		   }else {
			   if(Login.getText().toLowerCase().equals("admin")) {
				   Instagram.create();
				   Instagram insta = Instagram.getApp();
				   Scene scene = PhotosApp.mainStage.getScene();
				   PhotosApp.changeScene(scene, "AdminView");				   
			   }else if(Login.getText().toLowerCase().equals("stock")) {
				   Instagram.create();
				   Instagram insta = Instagram.getApp();
				   User user = Instagram.getApp().getUser("stock");

				   Instagram.getApp().authenticate(user);

				   Scene scene = PhotosApp.mainStage.getScene();
				   if(Instagram.getApp().getUser("stock").getAlbums().size() < 1) {
					   ArrayList<application.models.Photo> photos = new ArrayList<application.models.Photo>();
					   String userDir = System.getProperty("user.dir");
					   File file = new File(userDir+"/Photos/src/stock/stock1.jpg");
					   File file2 = new File(userDir+"/Photos/src/stock/stock2.jpg");
					   File file3 = new File(userDir+"/Photos/src/stock/stock3.jpeg");
					   File file4 = new File(userDir+"/Photos/src/stock/stock4.jpeg");
					   File file5 = new File(userDir+"/Photos/src/stock/stock5.jpg");
					   
					   if(!file.exists()) {
						   file = new File(userDir+"/src/stock/stock1.jpg");
					   }
					   if(!file2.exists()) {
						   file2 = new File(userDir+"/src/stock/stock2.jpg");
					   }
					   if(!file3.exists()) {
						   file3 = new File(userDir+"/src/stock/stock3.jpeg");
					   }
					   if(!file4.exists()) {
						   file4 = new File(userDir+"/src/stock/stock4.jpeg");
					   }
					   if(!file5.exists()) {
						   file5 = new File(userDir+"/src/stock/stock5.jpg");
					   }
					   System.out.println(userDir);

					   application.models.Photo temp = new application.models.Photo(file);
					   application.models.Photo temp2 = new application.models.Photo(file2);
					   application.models.Photo temp3 = new application.models.Photo(file3);
					   application.models.Photo temp4 = new application.models.Photo(file4);
					   application.models.Photo temp5 = new application.models.Photo(file5);
					   
					   photos.add(temp);
					   photos.add(temp2);
					   photos.add(temp3);
					   photos.add(temp4);
					   photos.add(temp5);
					   
					   Instagram.getApp().createAlbum("stock");
					   System.out.print(temp.getPhotoFileURI());
					   Instagram.getApp().addPhoto(file, Instagram.getApp().currentUser.getAlbums().get(0));
					   Instagram.getApp().addPhoto(file2, Instagram.getApp().currentUser.getAlbums().get(0));
					   Instagram.getApp().addPhoto(file3, Instagram.getApp().currentUser.getAlbums().get(0));
					   Instagram.getApp().addPhoto(file4, Instagram.getApp().currentUser.getAlbums().get(0));
					   Instagram.getApp().addPhoto(file5, Instagram.getApp().currentUser.getAlbums().get(0));

  
				   }
				   PhotosApp.changeScene(scene, "nonAdminView");	
				   
			   }else {
				   
				   String username = Login.getText();
				   Instagram.create();
				   User user = Instagram.getApp().getUser(username);
				   Instagram.getApp().authenticate(user);
				   
				   Scene scene = PhotosApp.mainStage.getScene();
				   PhotosApp.changeScene(scene, "nonAdminView");
			   
			   }
		   }
		   
	   }
	   
	   public void start() {
		   
	   }


}

