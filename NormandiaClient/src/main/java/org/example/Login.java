package org.example;

//import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import repo.*;
//import service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Login implements IRezervareObserver{

    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
   /* private Service service;
    private CursaDBRepository cursaDBRepository;
    private ClientDBRepository clientDBRepository;
    private RezervareDBRepository rezervareDBRepository;
    private SeatDBRepository seatDBRepository;
    private UserDBRepository userDBRepository;*/
    @FXML
    public TextField errorField;
    @FXML
    private PasswordField hiddenPasswordTextField;
    @FXML
    private CheckBox showPassword;
    Stage myStage;
    private IRezervareServices server;

    private User user;
    /*private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();*/
    @FXML
    void changeVisibility(ActionEvent event) {
        if (showPassword.isSelected()) {
            passwordTextField.setText(hiddenPasswordTextField.getText());
            passwordTextField.setVisible(true);
            hiddenPasswordTextField.setVisible(false);
            return;
        }
        hiddenPasswordTextField.setText(passwordTextField.getText());
        hiddenPasswordTextField.setVisible(true);
        passwordTextField.setVisible(false);
    }

    public void loginHandler(ActionEvent event) throws SQLException, IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        user=server.connect(username,password,controller);
        if (user != null) {
            System.out.println("Login successful");
            openUserView(loader,root,controller);
        } else {
            System.out.println("Login failed");
            // Afisează un dialog de alertă pentru parolă greșită
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
        usernameTextField.clear();
        passwordTextField.clear();
    }

   /*public void loginHandler(ActionEvent event) throws SQLException {
        String username=usernameTextField.getText();
        String password=passwordTextField.getText();
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<User>users=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?")){
            preStmt.setString(1,username);
            preStmt.setString(2,password);
            ResultSet resultSet=preStmt.executeQuery();
            if(resultSet.next()){
                errorField.setText("Login successful");
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("hello-view.fxml"));
                Parent root=loader.load();
                Scene scene=new Scene(root);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                HelloController helloController=loader.getController();
                User user=new User(resultSet.getString("username"),resultSet.getString("password"));

                helloController.setService(service,cursaDBRepository,user);
                helloController.initializeT();
                stage.setScene(scene);
                stage.show();
                logger.info("Login successful");
            }else{
                errorField.setVisible(true);
                errorField.setText("Login failed");
                logger.error("Login failed");
            }
*/
       /* try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/socialnetwork", "postgres", "12345678")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilizatori WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                errorField.setText("Login successful");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                logger.info("Login successful");
            } else {
                errorField.setText("Login failed");
                logger.error("Login failed");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }*/
       /* logger.traceExit();
    } catch (IOException e) {
            throw new RuntimeException(e);
        }}
    public void createAccount(ActionEvent event) {

    }
    public void setDbUtils(JdbcUtils dbUtils,Service service){
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        this.dbUtils = dbUtils;
        userDBRepository=new UserDBRepository(props);
        clientDBRepository=new ClientDBRepository(props);
        cursaDBRepository=new CursaDBRepository(props);
        rezervareDBRepository=new RezervareDBRepository(props);
        seatDBRepository= new SeatDBRepository(props);
        this.service=new Service(userDBRepository,clientDBRepository,cursaDBRepository,rezervareDBRepository,seatDBRepository);
    }
    private String getPassword(){
        if(passwordTextField.isVisible()){
            return passwordTextField.getText();
        } else {
            return hiddenPasswordTextField.getText();
        }
    }*/
    public void setController(IRezervareServices server, Stage primaryStage) {
        this.server = server;
//        initModel();
        this.myStage = primaryStage;

        this.myStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
        //initialize();
    }
    public void handleLogin() throws IOException {
        //String username = usernameField.getText();
        //String password = passwordField.getText();
        String username=usernameTextField.getText();
        String password=passwordTextField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        user=server.connect(username,password,controller);
        if (user != null) {
            System.out.println("Login successful");
            openUserView(loader,root,controller);
        } else {
            System.out.println("Login failed");
            // Afisează un dialog de alertă pentru parolă greșită
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password. Please try again.");
            alert.showAndWait();
        }
        usernameTextField.clear();
        passwordTextField.clear();
    }

    public void createAccount(ActionEvent event)
    {

    }
    private void openUserView(FXMLLoader loader,Parent root ,HelloController controller) {

        myStage.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        controller.setController(server,stage,myStage,user);
    }
    @Override
    public void registerRezervare() {

    }
}
