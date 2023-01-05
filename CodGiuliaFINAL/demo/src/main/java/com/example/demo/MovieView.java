package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.*;

/**
 * Acestă clasă reprezintă pagina cu filmele.
 */
public class MovieView extends MovieModel implements Initializable {

    @FXML
    private VBox chosenMovie;

    @FXML
    private Label classificationMovieSelected;

    @FXML
    private TextArea descriptionMovieSelected;

    @FXML
    private Label genreMovieSelected;

    @FXML
    private ImageView imgMovieSelected;

    @FXML
    private Label languageMovieSelected;

    @FXML
    private Label timeMovieSelected;

    @FXML
    private Label titleMovieSelected;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    @FXML
    private ComboBox<String> chooseDate;

    @FXML
    private ComboBox<String> chooseHour;

    /**
     * Daca data a fost selectată atunci chooseHour devine vizibil și orele se introduc în combobox în funcție
     * de dată.
     * @param event
     */
    @FXML
    private void DateChoosen(ActionEvent event){
    }

    /**
     * Dacă ora a fost selectată se va deschide o fereastră nouă (fereastra pentru cumpărarea biletelor la
     * filmul selectat).
     * @param event
     */
    @FXML
    void HourChoosen(ActionEvent event) {
    }

    @FXML
    private Label dateTicket;

    @FXML
    private Label movieTicket;

    @FXML
    private Label screenTicket;

    @FXML
    private Label timeTicket;

    private List<MovieView> movies = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    /**
     * Această metodă se conectează la baza de date. În obiectul "movie" setăm titlul, genul, clasificarea,
     * limba, durata, descrierea, id-ul și imaginea unui film, după care adăugăm filmul "movie" în "movies".
     * Returnăm toate filmele.
     * @return
     */
    private List<MovieView> getData(){
        List<MovieView> movies = new ArrayList<>();
        MovieView movie;
        MovieModel conectare=new MovieModel();
        conectare.connectToDB();
        MovieModel movieModel=new MovieModel();
        ArrayList<MovieModel> ListaTitluri=movieModel.Movie();

        for (int i = 0; i < ListaTitluri.size(); i++){
                movie = new MovieView();
                movie.setTitlu(ListaTitluri.get(i).getTitlu());
                movie.setGen(ListaTitluri.get(i).getGen());
                movie.setClasificare(ListaTitluri.get(i).getClasificare());
                movie.setLimbaDublare(ListaTitluri.get(i).getLimbaDublare());
                movie.setDurata(Integer.parseInt(ListaTitluri.get(i).getDurata()));
                movie.setDescriere(ListaTitluri.get(i).getDescriere());
                movie.setId(ListaTitluri.get(i).getId());
                movie.setImgsrc("/img/" + i + ".jpg");

                movies.add(movie);
            }
        return movies;
    }

    /**
     * În acestă metodă adăugăm toate detaliile filmului selectat.
     * @param movieView
     */
    private void setChosenMovie(MovieView movieView){
        MovieModel movieModel=new MovieModel();

        titleMovieSelected.setText(movieView.getTitlu());
        genreMovieSelected.setText(movieView.getGen());
        classificationMovieSelected.setText(movieView.getClasificare());
        languageMovieSelected.setText(movieView.getLimbaDublare());
        timeMovieSelected.setText(movieView.getDurata());
        descriptionMovieSelected.setText(movieView.getDescriere());
        descriptionMovieSelected.setWrapText(true);
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(movieView.getImgsrc())));
        imgMovieSelected.setImage(image);

        ArrayList<String> data=movieModel.data(String.valueOf(movieView.getId()));
        ArrayList<String> ora=movieModel.ora(String.valueOf(movieView.getId()));
        chooseDate.getItems().clear();
        int j=0;
        for(j = 0; j< data.size()-1;j++)
            if(!(data.get(j).equals(data.get(j+1))))
                chooseDate.getItems().add(data.get(j));
        chooseDate.getItems().add(data.get(j));
        chooseDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chooseHour.setVisible(true);
                chooseHour.getItems().clear();
                String d = chooseDate.getSelectionModel().getSelectedItem();
                for (int i = 0; i< data.size(); i++) {
                    String x = data.get(i);
                    if( x.equals(d))
                        chooseHour.getItems().add(ora.get(i));
                }
            }
        });
        chooseHour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticket.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Aici vom adăuga filmele în fereastra "movie-view".
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movies.addAll(getData());
        if(movies.size() > 0){
            setChosenMovie(movies.get(0));
            myListener = new MyListener() {
                /**
                 * Când apăsăm pe un film, acesta va apărea în partea stângă a ecranului.
                 * @param movieView
                 */
                @Override
                public void onClickListener(MovieView movieView) {
                    setChosenMovie(movieView);
                }
            };
        }
        int column = 0;
        int row = 1 ;
        try{
            for (int i = 0; i < movies.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ItemController itemController = fxmlLoader.getController();
            itemController.setData(movies.get(i),myListener);
            if(column == 2) {
                column = 0;
                row++;
            }
             grid.add(anchorPane, column++, row); //(child, column, row)
             //set grid width
             grid.setMinWidth(Region.USE_COMPUTED_SIZE);
             grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
             grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
             //set grid height
             grid.setMinHeight(Region.USE_COMPUTED_SIZE);
             grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
             grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

             GridPane.setMargin(anchorPane, new Insets(10));
        }
        } catch(IOException e){
                e.printStackTrace();
                }
        }
    }

