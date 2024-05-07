package org.example;
//import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ClientDBRepository implements IRepository {
    private static JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public ClientDBRepository(Properties props) {
        logger.info("Initializing ClientDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    public static Client findbyId(int idClient) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        if(con==null)
            return null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Client where id=?")){
            preStmt.setInt(1,idClient);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    String nume=result.getString("nume");
                    String password=result.getString("password");
                    Client client=new Client(nume,password);
                    client.setId((long) idClient);
                    return client;
                }
            }
        }
        catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public void save(Object elem) {
        logger.traceEntry("saving client {}",elem);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Client client=(Client)elem;
        try(PreparedStatement preStmt=con.prepareStatement("insert into Client(nume,password) values (?,?)")){

            preStmt.setString(1,client.getNume());
            preStmt.setString(2,client.getPassword());
            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances",result);
        }catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void update(Object o) {
        logger.traceEntry("updating client {}",o);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Client client=(Client)o;
        try(PreparedStatement preStmt=con.prepareStatement("update Client set nume=?,password=? where id=?")){
            preStmt.setString(1,client.getNume());
            preStmt.setString(2,client.getPassword());
            preStmt.setInt(3, Integer.parseInt(client.getId().toString()));
            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        }catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public List<Client> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Client> clients= new ArrayList<>();
        if(con==null)
            return null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Client")){
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){

                    String nume=result.getString("nume");
                    String password=result.getString("password");
                    Client client=new Client(nume,password);


                    clients.add(client);
                }
            }
        }
        catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(clients);
        return clients;

    }

    @Override
    public Object findOne(Object o) {
        return null;
    }

    @Override
    public Object findOne(Client client) {
        return null;
    }




}
