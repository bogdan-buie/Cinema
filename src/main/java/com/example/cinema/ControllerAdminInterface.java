package com.example.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


import static com.example.cinema.Data.dataEmail;

public class ControllerAdminInterface {

    @FXML
    private Button cinemaRoomsBtn;

    @FXML
    private Button dasboardBtn;

    @FXML
    private AnchorPane dasboard_form;

    @FXML
    private Label dashboard_;

    @FXML
    private Label dashboard_ScreeningToday;

    @FXML
    private Label dashboard_availableMovies;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button filmsBtn;

    @FXML
    private TextField films_ageRestriction;

    @FXML
    private Button films_clearBtn;

    @FXML
    private TableColumn<Movie,String> films_col_ageRestrictions;

    @FXML
    private TableColumn<Movie,String> films_col_description;

    @FXML
    private TableColumn<Movie,String> films_col_genre;

    @FXML
    private TableColumn<Movie,String> films_col_language;

    @FXML
    private TableColumn<Movie,String> films_col_runtime;

    @FXML
    private TableColumn<Movie,String> films_col_title;

    @FXML
    private Button films_deleteBtn;

    @FXML
    private TextArea films_description;

    @FXML
    private AnchorPane films_form;

    @FXML
    private TextField films_genre;

    @FXML
    private Button films_insertBtn;

    @FXML
    private TextField films_language;

    @FXML
    private TextField films_runtime;

    @FXML
    private TextField films_search;

    @FXML
    private TableView<Movie> films_table;

    @FXML
    private TextField films_title;

    @FXML
    private Button films_updateBtn;

    @FXML
    private Button screeningBtn;

    @FXML
    private Button seatsBtn;

    @FXML
    private Button signOutBtn;
    @FXML
    private Button films_refresh;

    @FXML
    private Label usernameTextField;
    private ObservableList<Movie> movieList;

    public void getListOfMovies(){
        DataBase DB = new DataBase();
        DB.connectToDB();
        this.movieList = DB.getListOfMoviesDB();
    }
    public void showMovieList(){
        //minut 1:35:00
        films_col_title.setCellValueFactory(            new PropertyValueFactory<>("title"));
        films_col_description.setCellValueFactory(      new PropertyValueFactory<>("description"));
        films_col_runtime.setCellValueFactory(          new PropertyValueFactory<>("runtime"));
        films_col_genre.setCellValueFactory(            new PropertyValueFactory<>("genre"));
        films_col_ageRestrictions.setCellValueFactory(  new PropertyValueFactory<>("ageRestrictions"));
        films_col_language.setCellValueFactory(         new PropertyValueFactory<>("language"));

        films_table.setItems(movieList);
    }
    public void selectMovie(){
        // cand selectam un rand din tabel informatiile se muta in textfield-uri
        Movie selectedMovie = films_table.getSelectionModel().getSelectedItem();
        int rowIndex= films_table.getSelectionModel().getSelectedIndex();
        if( rowIndex < 0){
            return;
        }
        films_title.setText(selectedMovie.getTitle());
        films_description.setText(selectedMovie.getDescription());
        films_runtime.setText(selectedMovie.getRuntime());
        films_genre.setText(selectedMovie.getGenre());
        films_ageRestriction.setText(selectedMovie.getAgeRestrictions());
        films_language.setText(selectedMovie.getLanguage());
    }
    public void insertMovie(){
        Movie movieForInsert = new Movie( films_title.getText()
                                        , films_description.getText()
                                        , films_runtime.getText()
                                        , films_genre.getText()
                                        , films_ageRestriction.getText()
                                        , films_language.getText()
                                        );
        System.out.println(movieForInsert.getTitle()+ movieForInsert.getRuntime());
        DataBase DB = new DataBase();
        DB.connectToDB();                           /// NU UITA DE ASTA
        int code = DB.insertMovieDB(movieForInsert);
        Alert alert;



        if(code == -1){
            System.out.println("Ceva nu e bine");
        } else if (code == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Filmul introdus exista deja in BD");
            alert.showAndWait();

            System.out.println("Filmul introdus exista deja in BD");
        } else if(code == 1) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Filmul a fost introdus cu succes");
            alert.showAndWait();

            System.out.println("Filmul a fost introdus cu succes");
            getListOfMovies();
            showMovieList();
        }
    }
    public void displayEmailLabel(){
        usernameTextField.setText(dataEmail);
    }
    public void clearTextFields(){
        films_title.setText("");
        films_description.setText("");
        films_runtime.setText("");
        films_genre.setText("");
        films_ageRestriction.setText("");
        films_language.setText("");
    }
    public void refreshFilmsTable(){
        getListOfMovies();
        showMovieList();
    }
    public void viewFilmsForm(){
        dasboard_form.setVisible(false);
        films_form.setVisible(true);
        //de completat cu celelalte
    }
    public void viewDashboardForm(){
        dasboard_form.setVisible(true);
        films_form.setVisible(false);
        //de completat cu celelalte
    }
    public void logOut(){
        signOutBtn.getScene().getWindow().hide();
    }
    @FXML
    public void initialize(){
        displayEmailLabel();
        getListOfMovies();
        showMovieList();
    }
}

