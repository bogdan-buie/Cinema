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

public class ControllerSignUp {

    @FXML   // Already have an account? Log in
    private Hyperlink SignInHaveAccount_hlnk;

    @FXML
    private TextField SignUpConfirmPassword_pf;

    @FXML
    private TextField SignUpEmail_tf;

    @FXML
    private TextField SignUpPassword_pf;

    @FXML
    private Button SignUp_btn;

    @FXML
    private AnchorPane SignUp_form;
    private User user;
    @FXML   // trecem pe formularul de SignIn
    public void handleSignInHaveAccount_hlnk() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage window = (Stage) SignInHaveAccount_hlnk.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 700, 500));
    }

    public void RegisterDb() {
        try {
            String email = SignUpEmail_tf.getText();
            String password = SignUpPassword_pf.getText();
            String confirmPassword = SignUpConfirmPassword_pf.getText();
            Alert alert;

            // verifica daca emailul ,parola si confirmarea parolei este introdusa in textfield-uri
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if(password.equals(confirmPassword)){
                    user = new User(email, password);
                    String messageFromDB = user.doRegister();

                    if (messageFromDB.equals("Contul a fost creat cu succes")) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Succesfully Registred!");
                        alert.showAndWait();
                    } else if (messageFromDB.equals("Exista deja un cont cu acest email")) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("An account exists with this email!");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please confirm with same password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
