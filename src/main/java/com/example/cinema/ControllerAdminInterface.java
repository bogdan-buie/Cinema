package com.example.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Optional;

import static com.example.cinema.Data.dataEmail;

/**
 * Clasa controller al ferestrei AdminInterface
 * @author Buie Bogdan
 */
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
    private TableColumn<Movie, String> films_col_ageRestrictions;

    @FXML
    private TableColumn<Movie, String> films_col_description;

    @FXML
    private TableColumn<Movie, String> films_col_genre;

    @FXML
    private TableColumn<Movie, String> films_col_language;

    @FXML
    private TableColumn<Movie, String> films_col_runtime;

    @FXML
    private TableColumn<Movie, String> films_col_title;

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
    ///////////////////////////// SCREENING
    @FXML
    private Button screening_DeleteBtn;

    @FXML
    private Button screening_UpdateBtn;

    @FXML
    private Button screening_clearBtn;

    @FXML
    private DatePicker screening_datePicker;

    @FXML
    private TextField screening_film;

    @FXML
    private AnchorPane screening_form;

    @FXML
    private TextField screening_hour;

    @FXML
    private Button screening_insertBtn;
    @FXML
    private Button screening_refreshBtn;
    @FXML
    private TableColumn<Screening, String> screening_id_col;
    @FXML
    private TableColumn<Screening, String> screening_dateTime_col;
    @FXML
    private TableColumn<Screening, String> screening_film_col;
    @FXML
    private TableColumn<Screening, Integer> screening_room_col;
    @FXML
    private TableView<Screening> screening_table;
    @FXML
    private AnchorPane rooms_form;
    private ObservableList<Movie> movieList;
    private ObservableList<Screening> screeingList;

    /**
     * Preia lista de filme din baza de date
     */
    public void getListOfMovies() {
        DataBase DB = new DataBase();
        DB.connectToDB();
        this.movieList = DB.getListOfMoviesDB();
    }

    /**
     * Afiseaza lista de filme in tabel cu filme
     */
    public void showMovieList() {
        //minut 1:35:00
        films_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        films_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        films_col_runtime.setCellValueFactory(new PropertyValueFactory<>("runtime"));
        films_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        films_col_ageRestrictions.setCellValueFactory(new PropertyValueFactory<>("ageRestrictions"));
        films_col_language.setCellValueFactory(new PropertyValueFactory<>("language"));

        films_table.setItems(movieList);
    }

    /**
     * Selecteaza o inregistrare din tabelul cu filme
     */
    public void selectMovie() {
        // cand selectam un rand din tabel informatiile se muta in textfield-uri
        Movie selectedMovie = films_table.getSelectionModel().getSelectedItem();
        int rowIndex = films_table.getSelectionModel().getSelectedIndex();
        if (rowIndex < 0) {
            return;
        }
        films_title.setText(selectedMovie.getTitle());
        films_description.setText(selectedMovie.getDescription());
        films_runtime.setText(selectedMovie.getRuntime());
        films_genre.setText(selectedMovie.getGenre());
        films_ageRestriction.setText(selectedMovie.getAgeRestrictions());
        films_language.setText(selectedMovie.getLanguage());
    }

    /**
     * Insereaza un film in baza de date cu datele introduse in textfield-uri
     */
    public void insertMovie() {
        String title = films_title.getText();
        String description = films_description.getText();
        String runtime = films_runtime.getText();
        String genre = films_genre.getText();
        String ageRestriction = films_ageRestriction.getText();
        String language = films_language.getText();
        Alert alert;
        if (!title.isEmpty() && !description.isEmpty() && !runtime.isEmpty() && !genre.isEmpty() && !ageRestriction.isEmpty() && !language.isEmpty()) {
            Movie movieForInsert = new Movie(title, description, runtime, genre, ageRestriction, language);
            System.out.println(movieForInsert.getTitle() + movieForInsert.getRuntime());

            DataBase DB = new DataBase();
            DB.connectToDB();
            int code = DB.insertMovieDB(movieForInsert);

            if (code == -1) {
                System.out.println("Ceva nu e bine");
            } else if (code == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Filmul introdus exista deja in BD");
                alert.showAndWait();

                System.out.println("Filmul introdus exista deja in BD");
            } else if (code == 1) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Filmul a fost introdus cu succes");
                alert.showAndWait();

                System.out.println("Filmul a fost introdus cu succes");
                //pentru update tabel
                getListOfMovies();
                showMovieList();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    /**
     * Sterge din baza de date inregistrarea selectata in tabelul cu filme al aplicatiei
     */
    public void deleteMovie() {
        DataBase DB = new DataBase();
        DB.connectToDB();
        String title = films_title.getText();
        String description = films_description.getText();
        String runtime = films_runtime.getText();
        String genre = films_genre.getText();
        String ageRestriction = films_ageRestriction.getText();
        String language = films_language.getText();
        Alert alert;
        if (!title.isEmpty() || !description.isEmpty() || !runtime.isEmpty() || !genre.isEmpty() || !ageRestriction.isEmpty() || !language.isEmpty()) {
            Movie movieForDelete = new Movie(title, description, runtime, genre, ageRestriction, language);
            int code;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Are you sure you want to delete the movie?");
            Optional<ButtonType> option = alert.showAndWait();
            if (ButtonType.OK.equals(option.get())) {
                code = DB.deleteMovieBD(movieForDelete);            /// de tratat celelalte cazuri
                if (code == 1) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully delete!");
                    alert.showAndWait();
                }
                else if (code == -1){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Error when deleting movies");
                    alert.showAndWait();
                }
            } else {
                return;
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields to delete film");
            alert.showAndWait();
        }
        //pentru update tabel
        getListOfMovies();
        showMovieList();
        clearTextFields();
    }

    /**
     * Updateaza o inregistare din baza de date corespunzatoare celei din tabelul cu filme
     */
    public void upDateMovie() {
        String title = films_title.getText();
        String description = films_description.getText();
        String runtime = films_runtime.getText();
        String genre = films_genre.getText();
        String ageRestriction = films_ageRestriction.getText();
        String language = films_language.getText();
        Alert alert;

        if (title.isEmpty() || description.isEmpty() || runtime.isEmpty() || genre.isEmpty() || ageRestriction.isEmpty() || language.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields to update the film");
            alert.showAndWait();
        } else {
            Movie movieForUpdate = new Movie(title, description, runtime, genre, ageRestriction, language);
            int code;
            DataBase DB = new DataBase();
            DB.connectToDB();
            code = DB.updateMovieDB(movieForUpdate);
            if (code == -1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error");
                alert.showAndWait();
            } else if (code == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error at update");
                alert.showAndWait();
            } else if (code == 1) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Succesfully update");
                alert.showAndWait();

                //pentru update tabel
                getListOfMovies();
                showMovieList();
            }
        }
    }

    /**
     * Arata email-ul cu care ne-am logat in aplicatie
     */
    public void displayEmailLabel() {
        usernameTextField.setText(dataEmail);
    }

    /**
     * Sterge continutul din toate textfield-urile pagini de filme
     */
    public void clearTextFields() {
        films_title.setText("");
        films_description.setText("");
        films_runtime.setText("");
        films_genre.setText("");
        films_ageRestriction.setText("");
        films_language.setText("");
    }

    /**
     * Preia din baza de date toate inregistrarile aferente filmului
     */
    public void refreshFilmsTable() {
        getListOfMovies();
        showMovieList();
    }
    /////////////////// SCREENING

    /**
     * Preia din baza de date toate inregistrarile cu ecranizarile
     */
    public void getListOfScreening() {
        DataBase DB = new DataBase();
        DB.connectToDB();
        this.screeingList = DB.getListOfScreeningDB();
    }

    /**
     * Afiseaza inregitrarile in tabelul cu ecranizari
     */
    public void showScreeningList() {
        screening_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        screening_dateTime_col.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        screening_film_col.setCellValueFactory(new PropertyValueFactory<>("film"));
        screening_room_col.setCellValueFactory(new PropertyValueFactory<>("id_sala"));

        screening_table.setItems(screeingList);

    }

    /**
     * Preia din baza de date toate inregistrarile aferente ecranizarii
     */
    public void refreshScreeningTable() {
        getListOfScreening();
        showScreeningList();
    }

    /**
     * afiseaza pagina pentru management-ul filmelor
     */
    public void viewFilmsForm() {
        dasboard_form.setVisible(false);
        films_form.setVisible(true);
        rooms_form.setVisible(false);
        screening_form.setVisible(false);
        //de completat cu celelalte
    }

    /**
     * afiseaza pagina dashboard
     */
    public void viewDashboardForm() {
        dasboard_form.setVisible(true);
        films_form.setVisible(false);
        rooms_form.setVisible(false);
        screening_form.setVisible(false);
        //de completat cu celelalte
        setAvailableFilms();
    }

    /**
     * afiseaza pagina pentru management-ul salilor
     */
    public void viewRoomsForm() {
        dasboard_form.setVisible(false);
        films_form.setVisible(false);
        rooms_form.setVisible(true);
        screening_form.setVisible(false);
    }

    /**
     * afiseaza pagina pentru management-ul ecranizarilor
     */
    public void viewScreeningForm() {
        rooms_form.setVisible(false);
        dasboard_form.setVisible(false);
        films_form.setVisible(false);
        screening_form.setVisible(true);
    }

    /**
     * Calculeaza si afiseaza in dashboard nr de filme disponibile la cinema
     */
    public void setAvailableFilms() {
        // calculeaza nr de filme disponibile
        DataBase DB = new DataBase();
        DB.connectToDB();
        int nrOfFilms = DB.countAvailableFilmsDB();
        String nrOfFilmsString = String.valueOf(nrOfFilms);
        dashboard_availableMovies.setText(nrOfFilmsString);
    }

    /**
     * Inchide fereastra adminului si deschide fereastra de Sign In
     *
     * @throws IOException
     */
    public void logOut() throws IOException {
        //signOutBtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage window = (Stage) signOutBtn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setResizable(false);
        window.setScene(new Scene(root, 700, 500));
    }

    /**
     * Functia de initializarea a ferestrei adminului
     */
    @FXML
    public void initialize() {
        displayEmailLabel();
        getListOfMovies();
        getListOfScreening();
        showMovieList();
        showScreeningList();
        setAvailableFilms();
    }
}