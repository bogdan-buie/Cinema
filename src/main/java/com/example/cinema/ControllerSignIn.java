package com.example.cinema;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Driver;

import static com.example.cinema.Data.dataEmail;

/**
 * Clasa controller al ferestrei SignIn
 * @author Buie Bogdan
 */

public class ControllerSignIn {
    @FXML
    private TextField SignInEmail_tf;

    @FXML   // Doesn't have an account yet?
    private Hyperlink SignInHaveAccount_hlnk;

    @FXML
    private TextField SignInPassword_pf;

    @FXML
    private Button SignIn_btn;

    @FXML
    private AnchorPane SignIn_form;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private User user;

    /**
     * Ascunde ferestra SignIn si face vizibila fereastra de SignUp
     * @throws IOException
     */
    @FXML   // trecem pe formularul de SignUp
    public void handleSignInHaveAccount_hlnk() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage window = (Stage) SignInHaveAccount_hlnk.getScene().getWindow();
        window.setTitle("Sign Up");
        window.setScene(new Scene(root, 700, 500));
    }
    /**
     * Ascunde ferestra SignIn si face vizibila fereastra de administratorului
     * @throws IOException
     */
    public void openAdminInterface() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminInterface.fxml"));
        Stage window = (Stage) SignIn_btn.getScene().getWindow();
        window.setTitle("Admin Interface");
        window.setResizable(false);
        window.setScene(new Scene(root, 1200, 700));
    }
    /**
     * Ascunde ferestra SignIn si face vizibila fereastra de Userului
     * @throws IOException
     */
    public void openUserInterface() throws IOException{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("movie-view.fxml"));
        Stage window = (Stage) SignIn_btn.getScene().getWindow();
        window.setTitle("Cinema");
        window.setScene(new Scene(root));

         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
        Parent root2= (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.show();
    }
    /**
     * Logheaza in aplicatie utilizatorul. In functie de tipul de utilizator i se deschide fereastra destinata lui
     */
    public void SignInDb() {
        try {
            String email = SignInEmail_tf.getText();
            String password = SignInPassword_pf.getText();
            Alert alert;

            // verifica daca emailul si parola este introdusa in textfield-uri
            if (email.isEmpty() || password.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                user = new User(email, password);                   // user posibil valid
                String messageFromDB = user.doLogin();
                System.out.println(messageFromDB);                  ///
                if (messageFromDB.equals("Logat cu succes!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully Login!");
                    alert.showAndWait();
                    openUserInterface();
                }else if (messageFromDB.equals("Logat cu succes ca administrator!")) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("ADMIN Succesfully Login!");
                    alert.showAndWait();
                    dataEmail = email;
                    openAdminInterface();

                } else if (messageFromDB.equals("Date de logare incorecte!")) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
