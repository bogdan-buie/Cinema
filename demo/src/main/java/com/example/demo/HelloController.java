package com.example.demo;


import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class HelloController extends MovieModel implements Initializable {
    public HelloController() throws FileNotFoundException {
    }

    @FXML
    private Label DatePicked;

    @FXML
    private Button back;

    @FXML
    private Label day;

    @FXML
    private Text genre_page2;

    @FXML
    private Button home_age1;

    @FXML
    private Button home_age2;

    @FXML
    private Button home_age3;

    @FXML
    private Button home_age4;

    @FXML
    private Button home_age5;

    @FXML
    private ComboBox<String> home_genre;

    @FXML
    private Label home_genre1;

    @FXML
    private Label home_genre2;

    @FXML
    private Label home_genre3;

    @FXML
    private Label home_genre4;

    @FXML
    private Label home_genre5;

    @FXML
    private Button home_hour1;

    @FXML
    private Button home_hour2;

    @FXML
    private Button home_hour3;

    @FXML
    private Button home_hour4;

    @FXML
    private Button home_hour5;

    @FXML
    private Button home_hour6;

    @FXML
    private ImageView home_img1;

    @FXML
    private ImageView home_img2;

    @FXML
    private ImageView home_img3;

    @FXML
    private ImageView home_img4;

    @FXML
    private ImageView home_img5;

    @FXML
    private Button home_plus;

    @FXML
    private Hyperlink home_title1;

    @FXML
    private Hyperlink home_title2;

    @FXML
    private Hyperlink home_title3;

    @FXML
    private Hyperlink home_title4;

    @FXML
    private Hyperlink home_title5;

    @FXML
    private Label home_today;

    @FXML
    private Text hour_page2;

    @FXML
    private ImageView image_page2;

    @FXML
    private Text language_page2;

    @FXML
    private TextArea movie_description;

    @FXML
    private DatePicker mydatepicker;

    @FXML
    private AnchorPane page1;

    @FXML
    private ScrollPane page1Scroll;

    @FXML
    private AnchorPane page2;

    @FXML
    private ScrollPane page2Scroll;

    @FXML
    private Text rating_page2;

    @FXML
    private Text title_page2;


    public void creareButoane(){
        Button button123 = new Button();
        button123.setLayoutX(324);
        button123.setLayoutY(123);
    }

    public void switchForm(ActionEvent event){

        if((event.getSource() == home_title1) || (event.getSource() == home_title2) ) {
            page2Scroll.setVisible(true);
            page1Scroll.setVisible(false);
            page2.setVisible(true);
            page1.setVisible(false);



        }
        if(event.getSource() == home_title2) {
            page2Scroll.setVisible(true);
            page1Scroll.setVisible(false);
            page2.setVisible(true);
            page1.setVisible(false);

        }
        if(event.getSource() == home_title3) {
            page2Scroll.setVisible(true);
            page1Scroll.setVisible(false);
            page2.setVisible(true);
            page1.setVisible(false);

        }
        if(event.getSource() == home_title4) {
            page2Scroll.setVisible(true);
            page1Scroll.setVisible(false);
            page2.setVisible(true);
            page1.setVisible(false);


        }
        if(event.getSource() == home_title5) {
            page2Scroll.setVisible(true);
            page1Scroll.setVisible(false);
            page2.setVisible(true);
            page1.setVisible(false);

        }

        if(event.getSource() == back) {
            page1Scroll.setVisible(true);
            page2Scroll.setVisible(false);
            page1.setVisible(true);
            page2.setVisible(false);
        }

    }

    public void home_plus(){
        System.exit(0);
    }

    public void getDate(ActionEvent event){
        LocalDate myDate = mydatepicker.getValue();
        DatePicked.setText(myDate.toString());
        day.setText(myDate.getDayOfWeek().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MovieModel conectare=new MovieModel();
        conectare.connectToDB();
        MovieModel movieModel=new MovieModel();
        ArrayList<MovieModel> ListaTitluri=movieModel.Movie();
        ArrayList<String> data=movieModel.data("1");
        ArrayList<String> ora=movieModel.ora("1");

       System.out.println(data.get(0)+" "+ora.get(0));
        home_hour1.setText(ora.get(0));


       //String [] items = {ListaTitluri.get(0).getGen(), "Horror"};
       //home_genre.getItems().addAll(items);
        //add genre in combobox
        for(int i=0;i<5;i++) {
            home_genre.getItems().add(ListaTitluri.get(i).getGen());
        }

       home_title1.setText(ListaTitluri.get(0).getTitlu());
       home_title2.setText(ListaTitluri.get(1).getTitlu());
       home_title3.setText(ListaTitluri.get(2).getTitlu());
       home_title4.setText(ListaTitluri.get(3).getTitlu());
       home_title5.setText(ListaTitluri.get(4).getTitlu());
       home_age1.setText(ListaTitluri.get(0).getClasificare());
       home_age2.setText(ListaTitluri.get(1).getClasificare());
       home_age3.setText(ListaTitluri.get(2).getClasificare());
       home_age4.setText(ListaTitluri.get(3).getClasificare());
       home_age5.setText(ListaTitluri.get(4).getClasificare());
       home_genre1.setText(ListaTitluri.get(0).getGen());
       home_genre2.setText(ListaTitluri.get(1).getGen());
       home_genre3.setText(ListaTitluri.get(2).getGen());
       home_genre4.setText(ListaTitluri.get(3).getGen());
       home_genre5.setText(ListaTitluri.get(4).getGen());

       Image image1 = new Image("file:C:\\Users\\40770\\demo\\src\\main\\resources\\com\\example\\demo\\homealone_comedy.jpg");
       Image image2 = new Image("file:C:\\Users\\40770\\demo\\src\\main\\resources\\com\\example\\demo\\avatar.jpg");
       Image image3 = new Image("file:C:\\Users\\40770\\demo\\src\\main\\resources\\com\\example\\demo\\enolaHolmes2.jpg");
       Image image4 = new Image("file:C:\\Users\\40770\\demo\\src\\main\\resources\\com\\example\\demo\\it_horror.jpg");
       Image image5 = new Image("file:C:\\Users\\40770\\demo\\src\\main\\resources\\com\\example\\demo\\joker.jpg");
       home_img1.setImage(image1);
       home_img2.setImage(image2);
       home_img3.setImage(image3);
       home_img4.setImage(image4);
       home_img5.setImage(image5);


                title_page2.setText(ListaTitluri.get(0).getTitlu());
                genre_page2.setText(ListaTitluri.get(0).getGen());
                hour_page2.setText(String.valueOf(ListaTitluri.get(0).getDurata()));
                language_page2.setText(ListaTitluri.get(0).getLimbaDublare());
                rating_page2.setText(ListaTitluri.get(0).getClasificare());
                movie_description.setText(ListaTitluri.get(0).getDescriere());
                image_page2.setImage(image1);


               title_page2.setText(ListaTitluri.get(1).getTitlu());
               genre_page2.setText(ListaTitluri.get(1).getGen());
               hour_page2.setText(String.valueOf(ListaTitluri.get(1).getDurata()));
               language_page2.setText(ListaTitluri.get(1).getLimbaDublare());
               rating_page2.setText(ListaTitluri.get(1).getClasificare());

               title_page2.setText(ListaTitluri.get(2).getTitlu());
               genre_page2.setText(ListaTitluri.get(2).getGen());
               hour_page2.setText(String.valueOf(ListaTitluri.get(2).getDurata()));
               language_page2.setText(ListaTitluri.get(2).getLimbaDublare());
               rating_page2.setText(ListaTitluri.get(2).getClasificare());

               title_page2.setText(ListaTitluri.get(3).getTitlu());
               genre_page2.setText(ListaTitluri.get(3).getGen());
               hour_page2.setText(String.valueOf(ListaTitluri.get(3).getDurata()));
               language_page2.setText(ListaTitluri.get(3).getLimbaDublare());
               rating_page2.setText(ListaTitluri.get(3).getClasificare());

               title_page2.setText(ListaTitluri.get(4).getTitlu());
               genre_page2.setText(ListaTitluri.get(4).getGen());
               hour_page2.setText(String.valueOf(ListaTitluri.get(4).getDurata()));
               language_page2.setText(ListaTitluri.get(4).getLimbaDublare());
               rating_page2.setText(ListaTitluri.get(4).getClasificare());




    }

}

