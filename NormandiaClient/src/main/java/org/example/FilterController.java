package org.example;

//import domain.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
//import repo.CursaDBRepository;
//import service.Service;

import java.util.List;
import java.util.Objects;

public class FilterController implements IRezervareObserver{

    public Button logout;
    //Service service;
    @FXML
    ListView<String> listView ;
  //  CursaDBRepository cursaDBRepository;
    Cursa selected;
    Stage stage;
    Stage stageHello;
    User user;

    private IRezervareServices server;

    /*public void setService(Service service, CursaDBRepository cursaDBRepository, Cursa selected) {
        this.service = service;
        this.cursaDBRepository = cursaDBRepository;
        this.selected = selected;
    }*/

    public void setService(IRezervareServices server, Cursa selected, Stage stage,Stage stageFilter, User user) {
        this.server = server;
        this.selected = selected;
        this.stage = stageFilter;
        this.stageHello = stage;
        this.user = user;
    }
    public void initializeT()
    {
        listView.getItems().clear();
        Integer LastSeat=0;
        for(Rezervare r:server.getAllRezervari()){
            if(Objects.equals(r.getCursa().getId(), selected.getId())){
                int locuri=r.getLocuri();
                System.out.println(locuri);
                System.out.println("id r="+r.getId());
               // Rezervare rezervare = server.findbyrezervare(r.getClient(),r.getCursa(),r.getLocuri());

                //rezervare r
                List<Seat> seat = server.getAllSeats(r);
                for (Seat s: seat) {
                    System.out.println("seat="+s.getSeatNumber()+s.getRezervare().getClient().getUsername());
                }
                System.out.println(seat);
                //for(int i=0;i<locuri;i++) {
                //   listView.getItems().add(r.getClient().getUsername());
                // }
                for (Seat s: seat) {
                    String item = "Seat Number: " + s.getSeatNumber() + " - Client: " + r.getClient().getUsername();
                    listView.getItems().add(item);
                    LastSeat = s.getSeatNumber();
                }
            }
        }
        for (int i=LastSeat+1;i<=18;i++) {
            String item = "Seat Number: " + i + " - Client: - ";
            listView.getItems().add(item);
        }
    }

    @FXML
    public void initialize() {

    }

    public void logoutButton(ActionEvent event) {
        stage.close();
        stageHello.show();
        initialize();
        initializeT();
       // stage.show();

        //server.logout(user.getUsername());
        //primaryStage.show();
    }

        @Override
        public void registerRezervare() {
            Platform.runLater(this::initializeT);
        }


   /*/ @FXML
    public void initializeT() {
    setService(service, cursaDBRepository, selected);
    listView.getItems().clear();
    for(Rezervare r:service.getRezervari()){
        if(Objects.equals(r.getCursa().getId(), selected.getId())){
            int locuri=r.getLocuri();
            System.out.println(locuri);
            for(int i=0;i<locuri;i++) {
                listView.getItems().add(r.getClient().getUsername());
            }
        }
    }
    initData();

    }*/
   /* public void initData()
    {
        listView.getItems().clear();
        Integer LastSeat=0;
        for(Rezervare r:service.getRezervari()){
            if(Objects.equals(r.getCursa().getId(), selected.getId())){
                int locuri=r.getLocuri();
                System.out.println(locuri);
                Rezervare rezervare = service.findbyrezervare(r.getClient(),r.getCursa(),r.getLocuri());
                List<Seat> seat = service.getSeat(rezervare);
                System.out.println(seat);
                //for(int i=0;i<locuri;i++) {
                 //   listView.getItems().add(r.getClient().getUsername());
               // }
                for (Seat s: seat) {
                    String item = "Seat Number: " + s.getSeatNumber() + " - Client: " + r.getClient().getUsername();
                    listView.getItems().add(item);
                    LastSeat = s.getSeatNumber();
                }
            }
        }
        for (int i=LastSeat+1;i<=18;i++) {
            String item = "Seat Number: " + i + " - Client: - ";
            listView.getItems().add(item);
        }
    }*/


}

