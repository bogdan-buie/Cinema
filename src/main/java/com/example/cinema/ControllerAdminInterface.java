package com.example.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Label usernameTextField;

    private double x = 0;
    private double y = 0;
    private ObservableList<Movie> movieList;

    public void getListOfMovies(){
        DataBase DB = new DataBase();
        DB.connectToDB();
        this.movieList = DB.getListOfMoviesDB();
    }
    public void showMovieList(){
        films_col_title.setCellValueFactory(            new PropertyValueFactory<>("title"));
        films_col_description.setCellValueFactory(      new PropertyValueFactory<>("description"));
        films_col_runtime.setCellValueFactory(          new PropertyValueFactory<>("runtime"));
        films_col_genre.setCellValueFactory(            new PropertyValueFactory<>("genre"));
        films_col_ageRestrictions.setCellValueFactory(  new PropertyValueFactory<>("ageRestrictions"));
        films_col_language.setCellValueFactory(         new PropertyValueFactory<>("language"));
        films_table.setItems(movieList);
        //minut 1:35:00
    };
    public void selectMovie(){
        // cand selectam un rand din tabel informatiile se muta in textfield-uri
        Movie selectedMovie = films_table.getSelectionModel().getSelectedItem();
        int num = films_table.getSelectionModel().getSelectedIndex();
        if( num-1 < -1){
            return;
        }
        films_title.setText(selectedMovie.getTitle());
        films_description.setText(selectedMovie.getDescription());
        films_runtime.setText(selectedMovie.getRuntime());
        films_genre.setText(selectedMovie.getGenre());
        films_ageRestriction.setText(selectedMovie.getAgeRestrictions());
        films_language.setText(selectedMovie.getLanguage());
    }
    public void displayEmailLabel(){
        usernameTextField.setText(dataEmail);
    }
    @FXML
    public void initialize(){
        displayEmailLabel();
        getListOfMovies();
        showMovieList();
    }
}

