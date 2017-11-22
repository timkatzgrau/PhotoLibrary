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
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Search {
	

	   @FXML ListView<Tag> Tags;   
	   @FXML ListView<String> Results;
		@FXML TextField Key;
		@FXML TextField Value;
		@FXML TextField StartDate;
		@FXML TextField EndDate;
		@FXML TextField Date;
		ArrayList<Image> listOfImages = new ArrayList<Image>();
		ArrayList<application.models.Photo> photoSearchResults = new ArrayList<application.models.Photo>();


	   private ObservableList<Tag> obsListTags; 
	   private ObservableList<String> obsListResults;
	  
	   public void start() {                
		   obsListTags = FXCollections.observableArrayList();
		   obsListResults = FXCollections.observableArrayList();	   
		   
		   Tags.setItems(obsListTags);
		   Results.setItems(obsListResults);

	   }
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "nonAdminView");
	   }
	   public void CreateAlbum() throws Exception {
		   TextInputDialog dialog = new TextInputDialog();
		   dialog.setTitle("Create Album");
		   dialog.setContentText("Choose a name");

		   // Traditional way to get the response value.
		   Optional<String> result = dialog.showAndWait();
		   if (result.isPresent()){
			   	   
			   if(Results.getItems().size() == 0) {
				   Alert alert = new Alert(AlertType.INFORMATION);
				   alert.setTitle("No Photos");
				   alert.setHeaderText("You cannot create an album of zero results");
				   alert.showAndWait();
			   }else {
				   Instagram.getApp().createAlbum(result.toString().substring(9,result.toString().length()-1), photoSearchResults);
				   Alert alert = new Alert(AlertType.INFORMATION);
				   alert.setTitle("Success");
				   alert.setHeaderText("Your album has been created");
				   alert.showAndWait();
			   }
		   }
	   }
	   
	   public void reload(ArrayList<application.models.Photo> results) {
		   photoSearchResults.clear();
		   photoSearchResults = results;
		   
		   listOfImages.clear();
		   Results.getItems().clear();
		   
		   for(int i = 0; i < results.size(); i++) {
			   System.out.print("Hits1");
			   obsListResults.add(Integer.toString(obsListResults.size()));
		   }
		   for(int i = 0; i < results.size(); i++) {
			   System.out.print("Hits2");
			   Image image = new Image(results.get(i).getPhotoFileURI(), 100, 100, false, false);
			   listOfImages.add(image);
		   }
		    
		   Results.setItems(obsListResults);
		   
		   Results.setCellFactory(param -> new ListCell<String>() {
	            private ImageView imageView = new ImageView();
	            @Override
	            public void updateItem(String name, boolean empty) {
	                super.updateItem(name, empty);
	                if (empty) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                	for(int i = 0; i < listOfImages.size(); i++) {
	                		if(name.equals(Integer.toString(i))) {
	                			imageView.setImage(listOfImages.get(i));
	    	                    setText(photoSearchResults.get(Integer.parseInt(name)).getCaption());
	    	                    setGraphic(imageView);
	                		}
	                	}
	                    

	                }
	            }
	        });
	   }
	   
	   public void SearchTags() throws Exception {
		   		   
			   //Search for Pairs
			   Tag[] tagArray = new Tag[obsListTags.size()];
			   for (int i = 0; i < obsListTags.size(); i++) {
				   tagArray[i] = obsListTags.get(i);
			   }
			   ArrayList<application.models.Photo> results = Instagram.getApp().searchByTags(tagArray);
			   System.out.println("This : "+ results.size());
			   reload(results);
			   
		   
	   }
	   public void SearchDates() throws Exception {
		   try{
			   
		   
			   ArrayList<application.models.Photo> results = Instagram.getApp().searchByDates(StartDate.getText(),EndDate.getText());
			   System.out.println("This : "+ results.size());
			   reload(results);
		   
		   } catch (Exception e) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Incorrect Format");
			   alert.setHeaderText("You must enter the correct format MM/DD/YYYY");
			   alert.showAndWait();
		   }
		   
	   }
	   public void Quit() throws Exception {
		   Instagram.getApp().signOut();

		   Instagram.writeApp(Instagram.getApp());
		   System.exit(0);
	   }
	   public void AddTag() throws Exception {
		  if(!Key.getText().equals("") && !Value.getText().equals("")) {
			   Tag tag = new Tag(Key.getText(), Value.getText());
			   obsListTags.add(tag);

		  }else {
			  Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Invalid Key or Value");
			   alert.setHeaderText("You must enter both a Key and Value to Add");
			   alert.showAndWait();
		  }
		   
	   }
	   
	  
	   
	   


}

