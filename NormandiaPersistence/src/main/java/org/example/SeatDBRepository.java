package org.example;

//import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.IRepository;
import org.example.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SeatDBRepository implements IRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public SeatDBRepository(Properties props) {
        logger.info("Initializing ClientDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public void save(Object elem) {
        logger.traceEntry("saving seat {}",elem);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Seat seat=(Seat)elem;
        try(PreparedStatement preStmt=con.prepareStatement("insert into Seat(id_rezervare, seatNumber) values (?,?)")){
            preStmt.setInt(1,seat.getRezervare().getId().intValue());
            preStmt.setInt(2,seat.getSeatNumber());
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
        logger.traceEntry("updating seat {}",o);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        Seat seat=(Seat)o;
        try(PreparedStatement preStmt=con.prepareStatement("update Seat set id_rezervare=?,seatNumber=? where id=?")){
            preStmt.setInt(1,seat.getRezervare().getId().intValue());
            preStmt.setInt(2,seat.getSeatNumber());
            preStmt.setInt(3, Integer.parseInt(seat.getId().toString()));
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
    public List findAll() {
        return null;
    }

    public List<Seat> findByRezervare(Rezervare r) {
        Connection con=dbUtils.getConnection();
        if(con==null)
            return null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from Seat where id_rezervare=?")){
            preStmt.setInt(1,r.getId().intValue());
            try(ResultSet result=preStmt.executeQuery()) {
                List<Seat> seats = new ArrayList<>();
                while (result.next()) {
                    Integer id = result.getInt("id");
                    Integer id_rezervare = result.getInt("id_rezervare");
                    Integer seatNumber = result.getInt("seatNumber");

                    Seat seat = new Seat(r, seatNumber);
                    seats.add(seat);
                }
                return seats;

        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
        @Override
    public Object findOne(Object o) {
        return null;
    }

    @Override
    public Object findOne(Client client) {
        return null;
    }
}
