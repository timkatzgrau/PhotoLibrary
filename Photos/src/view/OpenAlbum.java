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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.*;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class OpenAlbum {
	
	   @FXML ListView<String> listView; 
	   @FXML TextField Caption;
	   @FXML Label AlbumName;
	   Album album;
	   @FXML ImageView imageView;
	   ArrayList<Image> listOfImages = new ArrayList<Image>();

	   public ObservableList<String> obsList = FXCollections.observableArrayList();   
	   
	   private void showItemInputDialog(Stage mainStage) {                
		   int index = listView.getSelectionModel().getSelectedIndex();
		   imageView.setImage(album.getPhotos().get(index).getImage());
		  
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
			   Image image = new Image(album.getPhotos().get(i).getPhotoFile().toURI().toString(), 100, 100, false, false);
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
	   public void AddImage() throws Exception {
		   FileChooser fileChooser = new FileChooser();
		   fileChooser.setTitle("Open Resource File");
		   fileChooser.getExtensionFilters().addAll(
		           new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		   File selectedFile = fileChooser.showOpenDialog(PhotosApp.mainStage);

		   Instagram.getApp().addPhoto(selectedFile, album);
		   
		   System.out.println(album.getPhotos().size());
		   
		   Image image = new Image(selectedFile.toURI().toString(), 100, 100, false, false);
		   
		   listOfImages.add(image);
		   obsList.add(Integer.toString(obsList.size()));
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
		   
		   
	   
	   


}

