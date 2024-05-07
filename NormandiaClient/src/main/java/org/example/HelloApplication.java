package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {

   /* private Service service;
    private CursaDBRepository cursaDBRepository;
    private ClientDBRepository clientDBRepository;
    private RezervareDBRepository rezervareDBRepository;
    private SeatDBRepository seatDBRepository;
    private UserDBRepository userDBRepository;
    private JdbcUtils dbUtils;*/
   private static int defaultPort = 55555;

    private static String defaultServer = "localhost";



   /* @Override
    public void start(Stage stage) throws IOException {

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }*/
       /* userDBRepository=new UserDBRepository(props);
        clientDBRepository=new ClientDBRepository(props);
        cursaDBRepository=new CursaDBRepository(props);
        rezervareDBRepository=new RezervareDBRepository(props);
        seatDBRepository= new SeatDBRepository(props);
        service=new Service(userDBRepository,clientDBRepository,cursaDBRepository,rezervareDBRepository,seatDBRepository);*/
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 420);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
        //dbUtils=new JdbcUtils(props);
        /*initView(stage);
        stage.setTitle("Normania");
        stage.show();
    }*/
   // private void initView(Stage primaryStage) throws IOException {
   /* FXMLLoader loader=new FXMLLoader();
    loader.setLocation(getClass().getResource("hello-view.fxml"));
        // loader.setLocation(getClass().getResource("sample.fxml"));
       AnchorPane root=loader.load();
    primaryStage.setScene(new javafx.scene.Scene(root));
    Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img_4.png")));
    primaryStage.getIcons().add(icon);
    UtilizatoriController utilizatoriController=loader.getController();
    utilizatoriController.setService(srv,prietenieService);
    utilizatoriController.initializeT();*/

       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        controller_login controller_login=new controller_login();
        controller_login.setService(srv,prietenieService);
        controller_login.initizalizeT();
        primaryStage.show();*/

        //FXMLLoader loader=new FXMLLoader();
        //loader.setLocation(getClass().getResource("hello-view.fxml"));
        //loader.setLocation(getClass().getResource("sample.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("C:\\Users\\Asus\\Desktop\\proiect MPP\\FirmaTransport\\NormandiaClient\\src\\main\\resources\\com\\example\\mpp_javafx\\login.fxml"));
        Parent root=fxmlLoader.load();
        primaryStage.setScene(new javafx.scene.Scene(root));

        //HelloController controller=fxmlLoader.getController();
        Login login =fxmlLoader.getController();*/
        //login.setDbUtils(dbUtils,service);
        //controller.setService(service,cursaDBRepository);
       // controller.initializeT();


        //AddController addController=new AddController();
        //  addController.setServ(srv);

   // }
    public static void main(String[] args) {
        System.out.println("e bine");
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initView(primaryStage);
//        Scene scene = new Scene(fxmlLoader.load(), 620, 340);
        primaryStage.setTitle("SignIn");
        primaryStage.setWidth(750);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader messageLoader = new FXMLLoader();
        messageLoader.setLocation(getClass().getResource("../../login.fxml"));
        Parent root=messageLoader.load();
        Scene scene=new Scene(root);
        messageLoader.setRoot(primaryStage);
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("../../login.fxml"));
        Parent root=fxmlLoader.load();
        primaryStage.setScene(new javafx.scene.Scene(root));*/
        Properties propertiesClient = new Properties();
        try {
            propertiesClient.load(new FileReader("client.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find client.properties " + e);
        }

        var portInt = Integer.parseInt(propertiesClient.getProperty("port"));
        var host = propertiesClient.getProperty("host");

        //ICompetitionServices server= new CompetitionServicesRpcProxy(host, portInt);
        IRezervareServices server=new RezervareServicesRpcProxy(host,portInt);

       // Scene scene=new Scene(messageLoader.load());

        primaryStage.setScene(scene);

        Login AppController = messageLoader.getController();
        AppController.setController(server,primaryStage);
    }
}