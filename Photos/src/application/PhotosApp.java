// Tim Katzgrau and Asad Dar

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.ListController;
import view.UserViewController;
import view.Admin;
import view.CopyAndMove;
import application.models.*;
import application.models.Photo;
import view.Create;
import view.Tags;
import view.Search;
import view.OpenAlbum;
import view.Slideshow;
import view.NonAdminViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class PhotosApp extends Application {
	
	public static Stage mainStage;
	
	public void start(Stage primaryStage) 
			throws Exception {
//			Instagram instagram = Instagram.getApp();
			Instagram.create();
			Instagram instagram = Instagram.getApp();
			mainStage = primaryStage;
			FXMLLoader loader = new FXMLLoader();   
		      loader.setLocation(
		         getClass().getResource("/view/List.fxml"));

		      AnchorPane root = (AnchorPane)loader.load();

		      ListController listController = 
		         loader.getController();

		      listController.start();

		      Scene scene = new Scene(root, 900, 600);
		      primaryStage.setScene(scene);
		      primaryStage.setResizable(false);
		      primaryStage.show(); 
			      

	}
	public static void changeScene(Scene stage, String s, application.models.Photo photo, Album album) throws Exception{
		AnchorPane root;
		if(s.equals("CopyAndMove")) {
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(
					PhotosApp.class.getResource("/view/CopyAndMove.fxml"));
			root = (AnchorPane)loader.load();

		    CopyAndMove copyandmove = loader.getController();
		    copyandmove.start(photo,album);
	      
		}else if(s.equals("Tags")) {
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(
					PhotosApp.class.getResource("/view/Tags.fxml"));
			root = (AnchorPane)loader.load();

		    Tags tag = loader.getController();
		    tag.start(photo, album);
	      
		}else {
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(PhotosApp.class.getResource("/view/List.fxml"));
			root = (AnchorPane)loader.load();

	      
			
		}
		Scene scene = new Scene(root, 900, 600);
	      mainStage.setScene(scene);
	      mainStage.setResizable(false);
	      mainStage.show(); 
	}
	public static void changeScene(Scene stage, String s, Album album) throws Exception {
		AnchorPane root;
		if(s.equals("OpenAlbum")) {
			System.out.println("test");
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(
					PhotosApp.class.getResource("/view/OpenAlbum.fxml"));
			root = (AnchorPane)loader.load();

		    OpenAlbum openalbum = loader.getController();
		    openalbum.start(album);
	      
		}else if(s.equals("Slideshow")) {
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(
					PhotosApp.class.getResource("/view/Slideshow.fxml"));
			root = (AnchorPane)loader.load();

		    Slideshow slideshow = loader.getController();
		    slideshow.start(album);
	      
		}else {
			FXMLLoader loader = new FXMLLoader(); 

			loader.setLocation(PhotosApp.class.getResource("/view/List.fxml"));
			root = (AnchorPane)loader.load();

		    ListController listController = loader.getController();
	      
			
		}
		Scene scene = new Scene(root, 900, 600);
	      mainStage.setScene(scene);
	      mainStage.setResizable(false);
	      mainStage.show(); 
	}
	
	public static void changeScene(Scene stage, String s) 
			throws Exception {
				AnchorPane root;
				if(s.equals("userView")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(PhotosApp.class.getResource("/view/userView.fxml"));

					root = (AnchorPane)loader.load();

					UserViewController userViewController = loader.getController();
					
				}else if(s.equals("login")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(
							PhotosApp.class.getResource("/view/List.fxml"));
					root = (AnchorPane)loader.load();

				    ListController listController = loader.getController();
				    listController.start();
			      
				}else if(s.equals("nonAdminView")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(
							PhotosApp.class.getResource("/view/nonAdminView.fxml"));
					root = (AnchorPane)loader.load();

				    NonAdminViewController nonAdminViewController = loader.getController();
				    nonAdminViewController.start();
			      
				}else if(s.equals("AdminView")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(
							PhotosApp.class.getResource("/view/Admin.fxml"));
					root = (AnchorPane)loader.load();

				    Admin nonAdminViewController = loader.getController();
				    nonAdminViewController.start();
			      
				}else if(s.equals("Search")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(
							PhotosApp.class.getResource("/view/Search.fxml"));
					root = (AnchorPane)loader.load();

				    Search search = loader.getController();
				    search.start();
			      
				}else if(s.equals("Create")) {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(
							PhotosApp.class.getResource("/view/Create.fxml"));
					root = (AnchorPane)loader.load();

				    Create create = loader.getController();
				    create.start();
			      
				}else {
					FXMLLoader loader = new FXMLLoader(); 

					loader.setLocation(PhotosApp.class.getResource("/view/List.fxml"));
					root = (AnchorPane)loader.load();

				    ListController listController = loader.getController();
			      
					
				}
			      
			      Scene scene = new Scene(root, 900, 600);
			      mainStage.setScene(scene);
			      mainStage.setResizable(false);
			      mainStage.show(); 
			      

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
