package org.example;

//import domain.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import repo.CursaDBRepository;
//import service.Service;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HelloController implements IRezervareObserver{


    @FXML
    public TextField NumeTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public TextField LocuriTextField;
    @FXML
    public ChoiceBox<String> CursaChoiceBox;

    @FXML
    ListView<String> listViewRezervari ;
    public Button butonAdd;
    public Button butonFilter;

    public Button logout;

    //Service service;
    User user;
    //CursaDBRepository cursaDBRepository;

    Cursa selected;
    @FXML
    private Label welcomeText;

    @FXML
    TableView<Cursa>tableViewCurse;
    @FXML
    TextField destinatieField;

    @FXML
    TextField dataField;

    @FXML
    TextField oraField;

    Stage primaryStage;

    Stage stageFilter;

    @FXML
    TableColumn<Cursa, String> ColoanaDestinatie;
    @FXML
    TableColumn<Cursa, Date> ColoanaData;
    @FXML
    TableColumn<Cursa, Time> ColoanaOra;
    @FXML
    TableColumn<Cursa, Integer> ColoanaLocuri;

    private IRezervareServices server;
    ObservableList<Cursa> model = FXCollections.observableArrayList();
    List<Cursa>model2=new ArrayList<>();
   // List<Cursa> model=new ArrayList<>();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setController(IRezervareServices server,Stage stage,Stage primaryStage,User user){
        this.server = server;
        this.stage = stage;
        this.primaryStage = primaryStage;
        for (User user1:server.getAllUsers())
        {
            System.out.println(user1);
            if (user1.getUsername().equals(user.getUsername()))
            {
                this.user=user1;
            }
        }
        //this.user=user;
        //initData();
        initializeT();
    }

    /*public void setService(Service service, CursaDBRepository cursaDBRepository, User user)
    {
        this.service=service;
        this.cursaDBRepository=cursaDBRepository;
        this.user=user;
        User user1=service.findbyUsername(user.getUsername());
        this.user.setId(user1.getId());
       // ObservableList<Cursa> cursaObservableList= (ObservableList<Cursa>) service.getCurse();
       // ObservableList<Cursa> cursaObservableList= (ObservableList<Cursa>) service.getCurse();
        //tableViewCurse.setItems( cursaObservableList);
    }*/

    @FXML
    public void initialize() {

    }

    @FXML
    public void initializeT() {

        //setService(service,cursaDBRepository,user);
        listViewRezervari.getItems().clear();
      /* ColoanaDestinatie.setCellValueFactory(new PropertyValueFactory<>("destinatie"));
        ColoanaData.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColoanaOra.setCellValueFactory(new PropertyValueFactory<>("ora"));*/


        //ColoanaLocuri.setCellValueFactory(new PropertyValueFactory<>("locuri"));
        /*ColoanaLocuri.setCellFactory(tc -> new TableCell<Cursa, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                List<Rezervare> rezervariList=service.getRezervari();
                item=18;
                for(Rezervare r:rezervariList)
                {
                    System.out.println("suntem in for");
                    System.out.println(r.getCursa().getDestinatie());
                    System.out.println(ColoanaDestinatie.get);
                    if(r.getCursa().getDestinatie().equals(ColoanaDestinatie.getText()))
                    {
                        item=item-r.getLocuri();
                        System.out.println(item);
                    }
                }
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });*/


       /* ColoanaLocuri.setCellFactory(tc -> new TableCell<Cursa, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    Cursa cursa = getTableView().getItems().get(getIndex()); // Obține obiectul Cursa din rând
                    //List<Rezervare> rezervariList = service.getRezervari();
                    List<Rezervare>rezervariList=server.getAllRezervari();
                    item = 18;
                    for (Rezervare r : rezervariList) {
                        if (r.getCursa().getDestinatie().equals(cursa.getDestinatie())) { // Compară destinațiile
                            System.out.println("suntem in for");
                            System.out.println(r.getCursa().getDestinatie());
                            System.out.println(cursa.getDestinatie());
                            System.out.println(r.getCursa());
                            System.out.println(r.getLocuri());
                            item = item - r.getLocuri();
                        }
                    }
                    setText(item.toString());
                }
            }
        });

        tableViewCurse.setItems(model);*/



       // tableViewCurse.setItems((ObservableList<Cursa>) model2);
        //ArrayList<String> curse=new ArrayList<>();
        //curse.add(ColoanaDestinatie.getText());
        //CursaChoiceBox.setItems((ObservableList<String>) curse);
        initData();
    }
    public void initData() {
        model.clear();
        listViewRezervari.getItems().clear();
        /*Page<Utilizator> utilizatorPage = service.getUtilizatoriOnPage(new Pageable(currentPage, numberOfRecordsPerPage));
        totalNumberOfElements = utilizatorPage.getTotalNumberOfElements();
        List<Utilizator> utilizatorList = StreamSupport.stream(utilizatorPage.getElementsOnPage().spliterator(), false).toList();*/
       // List<Cursa> cursaList= cursaDBRepository.findAll();

        //create a list manual



        ObservableList<Cursa>cursaList=FXCollections.observableArrayList();
        List<Cursa>lista=server.getAllCurse();
        ObservableList<String> cursaListString=FXCollections.observableArrayList();
        for(Cursa c:lista)
        {
            System.out.println(c);
            cursaListString.add(c.toString());
            System.out.println(c.getId());
            List<Rezervare>rezervariList=server.getAllRezervari();
            Integer item = 18;
            for (Rezervare r : rezervariList) {
                if (r.getCursa().getDestinatie().equals(c.getDestinatie())) { // Compară destinațiile
                    System.out.println("suntem in for");
                    System.out.println(r.getCursa().getDestinatie());
                    System.out.println(c.getDestinatie());
                    System.out.println(r.getCursa());
                    System.out.println(r.getLocuri());
                    item = item - r.getLocuri();
                }
            }
            listViewRezervari.getItems().add(c.getId()+" "+c +"locuri libere:"+item);
        }
        listViewRezervari.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewRezervari.setOnMouseClicked(event -> {
            if (!listViewRezervari.getSelectionModel().isEmpty()) {
                String selectedCompetitionString = listViewRezervari.getSelectionModel().getSelectedItem();
                System.out.println(selectedCompetitionString);
                int id= Integer.parseInt(selectedCompetitionString.split(" ")[0]);
                System.out.println(id);

                Cursa cursa=fromString(selectedCompetitionString);
                System.out.println("afisam cursa");
                System.out.println(cursa);
                cursa.setId((long) id);
                System.out.println(cursa.getId());
                //TODO GASESTE CURSA, AFLAM ID UL CURSEI

                selected=cursa;
                destinatieField.setText(selected.getDestinatie());
                dataField.setText(selected.getDate().toString());
                oraField.setText(selected.getOra().toString());




                //competitionId = Integer.parseInt(selectedCompetitionString.split("\\.")[0]); // Extrage ID-ul competiției selectate
                //displayParticipants(competitionId);
            }
        });

        System.out.println("here");
        //System.out.println(cursaList);





        CursaChoiceBox.setItems(cursaListString);
        NumeTextField.setText(user.getUsername());
       // System.out.println(cursaList);
        //model.addAll(cursaList);
        model.setAll(cursaList);


    }
    @FXML
    public void handleFilter(ActionEvent event) throws IOException {
        stage.close();
        System.out.println("filtram");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../filter.fxml"));
        Parent root = loader.load();
        stageFilter = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneFilter = new Scene(root);
        stageFilter.setScene(sceneFilter);
        FilterController filterController = loader.getController();
        filterController.setService(server, selected,stage,stageFilter,user);
        filterController.initializeT();


        stageFilter.show();

    }
    /*@FXML
    public void handleFilter(ActionEvent event) throws IOException {
        System.out.println("filtram");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("filter.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        FilterController filterController = loader.getController();
        filterController.setService(service, cursaDBRepository, selected);
        filterController.initializeT();
        stage.show();
    }*/

    @FXML
    public void handleclick(){
        System.out.println("click");
        Cursa selectedCurse=tableViewCurse.getSelectionModel().getSelectedItem();
        selected=selectedCurse;
        System.out.println(selectedCurse);
        destinatieField.setText(selectedCurse.getDestinatie());
        dataField.setText(selectedCurse.getDate().toString());
        oraField.setText(selectedCurse.getOra().toString());

    }
    @FXML
    public void handleSave() throws Exception
    {

        Integer locuri=Integer.parseInt(LocuriTextField.getText());
        String nume=user.getUsername();
        String cursa=CursaChoiceBox.getValue();
        Integer LastSeat=0;
        System.out.println("TEST1");
        for (Rezervare rezevare : server.getAllRezervari())
        {
            if(rezevare.getCursa().toString().equals(cursa))
            {
                LastSeat=LastSeat+rezevare.getLocuri();
            }
        }
        System.out.println("LastSeat"+LastSeat);
        System.out.println("TEST2");
        if(LastSeat+locuri>18)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Nu sunt suficiente locuri");
            alert.showAndWait();
        }
        else
        {
            System.out.println("TEST3");
            System.out.println(cursa);
            Cursa cursa2=fromString(cursa);
            System.out.println(cursa2);
            Cursa cursa3=null;
            System.out.println(cursa2);
            for (Cursa c : server.getAllCurse())
            {
                System.out.println("Test4");
                System.out.println("suntem aici");
                System.out.println(c);
                System.out.println(cursa2);
                if (c.equals(cursa2))
                {
                    cursa3=c;
                    System.out.println("si aici");
                }
            }
            //System.out.println(cursa3.getId());
            System.out.println(cursa3.getDestinatie());
            System.out.println(cursa3.getDate());
            System.out.println(cursa3.getOra());
            System.out.println("facem rezervare");
            Rezervare rezervare5=new Rezervare(user,cursa3,locuri);
            server.registerRezervare(user,cursa3,locuri);
            for (Rezervare rezervareCheck:server.getAllRezervari())
            {
                System.out.println(rezervareCheck);
            }
            System.out.println("---------------------");
            System.out.println("user"+user.getId());
            System.out.println(LastSeat);

            //TODO GASESTE REZERVAREA DUPA CURSA SI USER
            for (Rezervare rezervare: server.getAllRezervari())
            {
                System.out.println("REZERVARI"+rezervare.getId()+rezervare.getCursa()+rezervare.getLocuri());
                if(rezervare.getCursa().equals(cursa3)&&rezervare.getClient().equals(user))
                {
                    System.out.println("id="+rezervare.getId());
                    System.out.println("am gasit rezervare");

                    System.out.println(rezervare);
                    LastSeat=LastSeat+1;
                    System.out.println(LastSeat);
                    for(int i=0;i<locuri;i++) {
                        Seat seat = new Seat(rezervare, LastSeat);
                        System.out.println("rezervare"+rezervare.getId());

                        server.addSeat(rezervare,LastSeat);
                        LastSeat = LastSeat + 1;
                    }
                }
            }
            System.out.println(LastSeat);


            /*service.addRezervare(rezervare5);
            Rezervare rezervare6=service.findbyrezervare(user,cursa3,locuri);

            System.out.println(rezervare6.toString());

            System.out.println(rezervare6);
            LastSeat=LastSeat+1;
            System.out.println(LastSeat);
            for(int i=0;i<locuri;i++) {
                Seat seat = new Seat(rezervare6, LastSeat);
                service.addSeat(seat);
                LastSeat = LastSeat + 1;
            }
            System.out.println(LastSeat);*/
            initData();
        }
    }
   /* @FXML
    public void handleSave() throws Exception{
        Integer locuri=Integer.parseInt(LocuriTextField.getText());
        String nume=user.getUsername();
        String cursa=CursaChoiceBox.getValue();
        Integer LastSeat=0;

        for (Rezervare rezevare : server.getAllRezervari())
        {
            if(rezevare.getCursa().toString().equals(cursa))
            {
                LastSeat=LastSeat+rezevare.getLocuri();
            }
        }
        if(LastSeat+locuri>18)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Nu sunt suficiente locuri");
            alert.showAndWait();
        }
        else
        {
            System.out.println(cursa);
            Cursa cursa2=fromString(cursa);
            System.out.println(cursa2);
            Cursa cursa3=null;
            System.out.println(cursa2);
            for (Cursa c : server.getAllCurse())
            {
                System.out.println("suntem aici");
                System.out.println(c);
                System.out.println(cursa2);
                if (c.equals(cursa2))
                {
                    cursa3=c;
                    System.out.println("si aici");
                }
            }
            //System.out.println(cursa3.getId());
            System.out.println(cursa3.getDestinatie());
            System.out.println(cursa3.getDate());
            System.out.println(cursa3.getOra());
            System.out.println("facem rezervare");
            Rezervare rezervare5=new Rezervare(user,cursa3,locuri);
            service.addRezervare(rezervare5);
            Rezervare rezervare6=service.findbyrezervare(user,cursa3,locuri);

            System.out.println(rezervare6.toString());

            System.out.println(rezervare6);
            LastSeat=LastSeat+1;
            System.out.println(LastSeat);
            for(int i=0;i<locuri;i++) {
                Seat seat = new Seat(rezervare6, LastSeat);
                service.addSeat(seat);
                LastSeat = LastSeat + 1;
            }
            System.out.println(LastSeat);
            initData();
        }
        System.out.println(LastSeat);
    }*/
    public static Cursa fromString(String input) {
        // Exemplu de format de intrare: "destinatie,data,ora"
        String[] parts = input.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Formatul de intrare trebuie să fie: destinatie,data,ora");
        }
     /*   for (String part : parts) {
            System.out.println(part);
        }*/
        String[] destinatieParts = parts[0].split("=");
        String destinatie= destinatieParts[1].trim();
        String[] deParts=destinatie.split("'");
        String destinatie2=deParts[1];
       // System.out.println(destinatie2);
        String[] dateParts = parts[1].trim().split("=")[1].split("-"); // Extragem partea numerică a datei și descompunem șirul de dată în componente
       /* for (String part : dateParts) {
            System.out.println(part);
        }*/
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        java.sql.Date data = java.sql.Date.valueOf(LocalDate.of(year, month, day));
       // System.out.println(data);

        String[] oraParts = parts[2].trim().split("=")[1].split(":"); // Extragem partea numerică a orei și descompunem șirul de oră în componente
      /*  for (String part : oraParts) {
            System.out.println(part);
        }*/
        int hour = Integer.parseInt(oraParts[0]);
        int minute = Integer.parseInt(oraParts[1]);
        Time ora = Time.valueOf(LocalTime.of(hour, minute));
       // System.out.println(ora);

        return new Cursa(destinatie2, data, ora);
    }
    public void logoutButton() {
        stage.close();
        server.logout(user.getUsername());
        primaryStage.show();
    }
    @Override
    public void registerRezervare() {
        Platform.runLater(this::initData);
    }
}