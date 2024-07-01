package org.example;

import org.example.utils.AbstractServer;
import org.example.utils.RpcConcurentServer;

import java.io.FileReader;
import java.util.Properties;

public class StartRpcServer {
    private static int defaultPort = 55555;

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\Asus\\Desktop\\proiect MPP\\FirmaTransport\\bd.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find bd.properties " + e);
        }
        Properties propertiesServer = new Properties();
        try {
            propertiesServer.load(new FileReader("C:\\Users\\Asus\\Desktop\\proiect MPP\\FirmaTransport\\NormandiaServer\\server.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Cannot find server.properties " + e);
        }



        UserHibernateRepository userRepository =  new UserHibernateRepository();
        ClientDBRepository clientDBRepository = new ClientDBRepository(properties);
        CursaDBRepository cursaDBRepository = new CursaDBRepository(properties);
        RezervareDBRepository rezervareDBRepository = new RezervareDBRepository(properties);
        SeatDBRepository seatDBRepository = new SeatDBRepository(properties);
        Service service = new Service(userRepository, clientDBRepository, cursaDBRepository, rezervareDBRepository, seatDBRepository);

       // System.out.println(properties);
        System.out.println(userRepository.findByUsername("Ionel"));


        System.out.println("Starting server on port " + propertiesServer.getProperty("port"));
        var portInt = Integer.parseInt(propertiesServer.getProperty("port"));
        AbstractServer server = new RpcConcurentServer(portInt, service);
        try{
            server.start();
        } catch (Exception e) {
            System.err.println("Error starting the server " + e.getMessage());
        }
    }
}
