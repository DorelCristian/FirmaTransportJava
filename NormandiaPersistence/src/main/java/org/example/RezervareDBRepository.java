package org.example;

/*import domain.Client;
import domain.Cursa;
import domain.Rezervare;
import domain.User;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RezervareDBRepository implements IRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public RezervareDBRepository(Properties props) {
        logger.info("Initializing ClientDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public void save(Object elem) {
        logger.traceEntry("saving rezervare {}",elem);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Rezervare rezervare=(Rezervare)elem;
        try(PreparedStatement preStmt=con.prepareStatement("insert into Rezervare(id_client,id_cursa, locuri) values (?,?,?)")){
            preStmt.setInt(1,rezervare.getClient().getId().intValue());
            preStmt.setInt(2,rezervare.getCursa().getId().intValue());
            preStmt.setInt(3,rezervare.getLocuri());
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
        logger.traceEntry("updating rezervare {}",o);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Rezervare rezervare=(Rezervare)o;
        try(PreparedStatement preStmt=con.prepareStatement("update Rezervare set id_client=?,id_cursa=?,locuri=? where id=?")){
            preStmt.setInt(1,rezervare.getClient().getId().intValue());
            preStmt.setInt(2,rezervare.getCursa().getId().intValue());
            preStmt.setInt(3,rezervare.getLocuri());
            preStmt.setInt(4, Integer.parseInt(rezervare.getId().toString()));
            int result=preStmt.executeUpdate();
            logger.trace("Updated {} instances",result);
        }catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();

    }

    @Override
    public void update() {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public List<Rezervare> findAll() {
    logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Rezervare> rezervari=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervare")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id= result.getInt("id");
                    int id_client = result.getInt("id_client");
                    //Client client = ClientDBRepository.findbyId(id_client);
                    User user = UserDBRepository.findbyId(id_client);
                    int id_cursa = result.getInt("id_cursa");
                    Cursa cursa = CursaDBRepository.findbyId(id_cursa);
                    int locuri = result.getInt("locuri");
                    Rezervare rezervare = new Rezervare(user,cursa,locuri);
                    rezervare.setId((long) id);
                    rezervari.add(rezervare);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(rezervari);
        return rezervari;
    }

    @Override
    public Object findOne(Object o) {
        return null;
    }

    @Override
    public Object findOne(Client client) {
        return null;
    }

    public Rezervare findByRezervare(User user,Cursa cursa,Integer locuri)
    {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        Rezervare rezervare=null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Rezervare where id_client=? and id_cursa=? and locuri=?")) {
            preStmt.setInt(1,user.getId().intValue());
            preStmt.setInt(2,cursa.getId().intValue());
            preStmt.setInt(3,locuri);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id= result.getInt("id");
                    int id_client = result.getInt("id_client");
                    //Client client = ClientDBRepository.findbyId(id_client);
                    User user1 = UserDBRepository.findbyId(id_client);
                    int id_cursa = result.getInt("id_cursa");
                    Cursa cursa1 = CursaDBRepository.findbyId(id_cursa);
                    int locuri1 = result.getInt("locuri");
                    rezervare = new Rezervare(user1,cursa1,locuri1);
                    rezervare.setId(Long.valueOf(id));
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(rezervare);
        return rezervare;
    }
}
