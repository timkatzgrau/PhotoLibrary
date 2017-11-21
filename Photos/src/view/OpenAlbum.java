package view;

// Tim Katzgrau and Asad Dar

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javafx.util.Callback;

import java.io.File;
import java.rmi.RemoteException;

import application.PhotosApp;
import application.models.Album;
import application.models.Instagram;
import application.models.Photo;
import application.models.Tag;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonBar.ButtonData;

public class OpenAlbum {
	
	   @FXML ListView<String> listView; 
	   @FXML TextField Caption;
	   @FXML TextField Date;
	   @FXML TextArea Tags;
	   @FXML Label AlbumName;
	   Album album;
	   @FXML ImageView imageView;
	   ArrayList<Image> listOfImages = new ArrayList<Image>();

	   public ObservableList<String> obsList = FXCollections.observableArrayList();  
	   public ObservableList<Tag> tagList = FXCollections.observableArrayList();  
	   
	   
	   private void showItemInputDialog(Stage mainStage) {    
		   tagList.clear();
		   
		   int index = listView.getSelectionModel().getSelectedIndex();
		   imageView.setImage(album.getPhotos().get(index).getImage());
		   Caption.setText(album.getPhotos().get(index).getCaption());
		   Date.setText(album.getPhotos().get(index).stringDate);
		   for(int i = 0; i < album.getPhotos().get(index).getTags().size(); i++) {
		   		tagList.add(album.getPhotos().get(index).getTags().get(i));
		   }
		   Tags.setText(tagList.toString());
		   
		  
	   }
	   public void start(Album album) {
		// set listener for the items
		      listView
		        .getSelectionModel()
		        .selectedIndexProperty()
		        .addListener(
		           (obs, oldVal, newVal) -> 
		               showItemInputDialog(PhotosApp.mainStage));
		   
		   AlbumName.setText(album.toString());
		   this.album = album;
		   
		   for(int i = 0; i < album.getPhotos().size(); i++) {
			   obsList.add(Integer.toString(obsList.size()));
		   }
		   
		   for(int i = 0; i < album.getPhotos().size(); i++) {
			   Image image = new Image(album.getPhotos().get(i).getPhotoFileURI(), 100, 100, false, false);
			   listOfImages.add(image);
		   }
		   
		   listView.setItems(obsList);
		   
		   listView.setCellFactory(param -> new ListCell<String>() {
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
	    	                    setText(name);
	    	                    setGraphic(imageView);
	                		}
	                	}
	                    

	                }
	            }
	        });
		       
		   }

	   
	   
	   public void goBack() throws Exception {
		   Scene scene = PhotosApp.mainStage.getScene();
		   PhotosApp.changeScene(scene, "nonAdminView");
	   }
	   
	   public void DeletePhoto() throws Exception {
		   int index = listView.getSelectionModel().getSelectedIndex();		  
		   album.getPhotos().remove(index);
		   reload();
	   }
	   
	   public void reload() {
		   obsList.clear();
		   listOfImages.clear();
		   listView.getItems().clear();
		   
		   for(int i = 0; i < album.getPhotos().size(); i++) {
			   obsList.add(Integer.toString(obsList.size()));
		   }
		   for(int i = 0; i < album.getPhotos().size(); i++) {
			   Image image = new Image(album.getPhotos().get(i).getPhotoFileURI(), 100, 100, false, false);
			   listOfImages.add(image);
		   }
		    
		   listView.setItems(obsList);
		   
		   listView.setCellFactory(param -> new ListCell<String>() {
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
	    	                    setText(name);
	    	                    setGraphic(imageView);
	                		}
	                	}
	                    

	                }
	            }
	        });
	   }
	   public void EditCaption() throws Exception {
		   
		   int index = listView.getSelectionModel().getSelectedIndex();
		   
		   if(index >= 0) {
			   TextInputDialog dialog = new TextInputDialog();
			   dialog.setTitle("Edit Caption");
			   dialog.setContentText("Please enter a caption:");
	
			   // Traditional way to get the response value.
			   Optional<String> result = dialog.showAndWait();
			   if (result.isPresent()){
			       album.getPhotos().get(index).setCaption(result.toString().substring(9,result.toString().length()-1));
			       Caption.setText(album.getPhotos().get(index).getCaption());
			   }
		   }else {
			   System.out.println(obsList);
		   }
	   }
	   public void Slideshow() throws Exception {
			   if(album.getPhotos().size() == 0) {
				   Alert alert = new Alert(AlertType.INFORMATION);
				   alert.setTitle("No Photos");
				   alert.setHeaderText(
						   "This album has no photos to display.");
				   alert.showAndWait();
			   }else {
				   Scene scene = PhotosApp.mainStage.getScene();
				   PhotosApp.changeScene(scene, "Slideshow", album);
			   }

	   }
	   
	   public void CopyAndMove() throws Exception {
		   int index = listView.getSelectionModel().getSelectedIndex();
		   if(index < 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No Photo Selected");
			   alert.setHeaderText(
					   "You must select a photo to move first.");
			   alert.showAndWait();
		   }else {
			   application.models.Photo photo = album.getPhotos().get(index);
			   Scene scene = PhotosApp.mainStage.getScene();
			   PhotosApp.changeScene(scene, "CopyAndMove", photo, album);
		   }

	   }
	   public void Tags() throws Exception {
		   
		   int index = listView.getSelectionModel().getSelectedIndex();
		   
		   if(index < 0) {
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("No Photo Selected");
			   alert.setHeaderText(
					   "You must select a photo to move first.");
			   alert.showAndWait();
		   }else {
			   application.models.Photo photo = album.getPhotos().get(index);
			   Scene scene = PhotosApp.mainStage.getScene();
			   PhotosApp.changeScene(scene, "Tags", photo, album);
		   }

	   }
	   public void AddImage() throws Exception {
		   FileChooser fileChooser = new FileChooser();
		   fileChooser.setTitle("Open Resource File");
		   fileChooser.getExtensionFilters().addAll(
		           new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		   File selectedFile = fileChooser.showOpenDialog(PhotosApp.mainStage);
		   
		   if(selectedFile != null) {
			   Instagram.getApp().addPhoto(selectedFile, album);
			   
			   System.out.println(album.getPhotos().size());
			   
			   reload(); 
		   }

	   }
		   
		   
	   
	   


}

