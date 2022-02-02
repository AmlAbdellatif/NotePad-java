package com.example.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HelloApplication extends Application {
    MenuBar mymenu;
    Menu file;
    Menu edit;
    Menu help;
    MenuItem inew;
    MenuItem iopen;
    MenuItem isave;
    MenuItem iexit;
    MenuItem icut;
    MenuItem iundo;
    MenuItem icopy;
    MenuItem ipast;
    MenuItem idelete;
    MenuItem iselectall;
    MenuItem aboutnodepad;
    TextArea textarea;
    SeparatorMenuItem sep1;
    SeparatorMenuItem sep2;
    SeparatorMenuItem sep3;
    BorderPane root = new BorderPane();
    Label copyright;
    Scene scene;
    TextField selecttext;
    String stringtext;
    @Override
    public void start(Stage stage) throws IOException {
        mymenu = new MenuBar();
        file = new Menu("File");
        inew = new MenuItem("New");
        iopen = new MenuItem("Open");
        isave = new MenuItem("Save");
        iexit = new MenuItem("Exit");
        file.getItems().add(inew);
        file.getItems().add(iopen);
        file.getItems().add(isave);
        file.getItems().add(iexit);
        sep1 = new SeparatorMenuItem();
        file.getItems().add(3, sep1);

        edit = new Menu("Edit");
        iundo = new MenuItem("Undo");
        icut = new MenuItem("Cut");
        icopy = new MenuItem("Copy");
        ipast = new MenuItem("Past");
        idelete = new MenuItem("Delete");
        iselectall = new MenuItem("SelectAll");
        edit.getItems().add(iundo);
        edit.getItems().add(icut);
        edit.getItems().add(icopy);
        edit.getItems().add(ipast);
        edit.getItems().add(idelete);
        edit.getItems().add(iselectall);
        sep2 = new SeparatorMenuItem();
        edit.getItems().add(1, sep2);
        sep3 = new SeparatorMenuItem();
        edit.getItems().add(6, sep3);

        help= new Menu("Help");
        aboutnodepad = new MenuItem("AboutNotePad");
        help.getItems().add(aboutnodepad);

        mymenu.getMenus().addAll(file,edit,help);
        textarea = new TextArea();
        selecttext = new TextField();
        stringtext = textarea.getSelectedText();
        copyright = new Label(" By:Aml Abdellatif");
        root.setTop(mymenu);
        root.setCenter(textarea);


        iundo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textarea.undo();

            }
        });

        icopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            textarea.copy();

            }
        });
        icut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textarea.cut();
            }
        });

        ipast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textarea.paste();
            }
        });
        idelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textarea.deleteNextChar();
            }
        });
        iselectall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textarea.selectAll();
            }
        });
         aboutnodepad.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 root.setBottom(copyright);

             }
         });
         inew.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 textarea.clear();
             }
         });
       iopen.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               FileChooser fileChooser = new FileChooser();
               fileChooser.setTitle("Open Resource File");
               try {

                   FileInputStream fr = new FileInputStream(fileChooser.showOpenDialog(stage));
                   byte[] myarr = new byte[fr.available()];
                   fr.read(myarr);
                   String str = new String(myarr);
                   System.out.println(str);
                   textarea.setText(str);
                   fr.close();
               } catch (Exception e) {
                   e.getMessage();

               }
           }

       });
       isave.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               FileChooser fileChooser = new FileChooser();
               fileChooser.setTitle("Save");
               fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

               byte[] str=textarea.getText().getBytes();
               FileOutputStream fw = null;
               try {
                   fw = new FileOutputStream(fileChooser.showSaveDialog(stage));
                   fw.write(str);
                   fw.close();
               } catch (Exception e) {
                   e.getMessage();
               }
              /* FileWriter fw = null;
               try {
                   fw = new FileWriter(fileChooser.showSaveDialog(stage));
                   fw.write(str);
                   fw.close();
               } catch (Exception e) {
                   e.getMessage();
               }*/



           }
       });


        scene = new Scene(root, 320, 240);
        stage.setTitle("NotePad");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}