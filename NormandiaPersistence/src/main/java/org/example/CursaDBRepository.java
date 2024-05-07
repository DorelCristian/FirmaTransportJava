package org.example;

//import domain.Client;
//import domain.Cursa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CursaDBRepository implements IRepository {
    private static JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public CursaDBRepository(Properties props) {
        logger.info("Initializing CursaDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    public static Cursa findbyId(int idCursa) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        if(con==null)
            return null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cursa where id=?")){
            preStmt.setInt(1,idCursa);
            try(ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    String destinatie=result.getString("destinatie");
                    Date date = result.getDate("date");
                    Time ora =  result.getTime("ora");
                    Cursa cursa=new Cursa(destinatie,date,ora);
                    cursa.setId((long) idCursa);
                    return cursa;
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
        logger.traceEntry("saving cursa {}",elem);
        Connection con=dbUtils.getConnection();




        Cursa cursa=(Cursa)elem;
        try(PreparedStatement preStmt=con.prepareStatement("insert into Cursa(destinatie,date,ora) values (?,?,?)")){

            preStmt.setString(1,cursa.getDestinatie());
            preStmt.setDate(2, cursa.getDate());
            preStmt.setTime(3, cursa.getOra());
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

    }


    public void updatebUN(Integer I,Object o) {
        logger.traceEntry("updating cursa {}",o);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Cursa cursa=(Cursa)o;
        try(PreparedStatement preStmt=con.prepareStatement("update Cursa set destinatie=?,date=?,ora=? where id=?")){
            preStmt.setString(1,cursa.getDestinatie());
            preStmt.setDate(2, cursa.getDate());
            preStmt.setTime(3, cursa.getOra());
            preStmt.setInt(4, I);
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
    public List<Cursa> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Cursa> curse=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Cursa")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Long id = result.getLong("id");
                    String destinatie = result.getString("destinatie");
                    Date date = result.getDate("date");
                    Time ora =  result.getTime("ora");
                    Cursa cursa = new Cursa(destinatie, date, ora);
                    cursa.setId(id);
                    curse.add(cursa);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(curse);
        return curse;
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
