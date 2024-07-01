/*package org.example;

//import domain.Client;
//import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.IRepository;
import org.example.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class UserDBRepository implements IRepository {
    private static JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public UserDBRepository(Properties props) {
        logger.info("Initializing ClientDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    public static User findbyId(int idClient) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        if(con==null)
            return null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from User where id=?")){
            preStmt.setInt(1,idClient);
            try(java.sql.ResultSet result=preStmt.executeQuery()){
                if(result.next()){
                    String username=result.getString("username");
                    String password=result.getString("password");
                    User user=new User(username,password);
                    user.setId((long) idClient);
                    return user;
                }
            }
        }
        catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        return null;
    }

    @Override
    public void save(Object elem) {
        logger.traceEntry("saving user {}",elem);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        User user=(User)elem;
        try(PreparedStatement preStmt=con.prepareStatement("insert into User(username,password) values (?,?)")){

            preStmt.setString(1,user.getUsername());
            preStmt.setString(2,user.getPassword());
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
        logger.traceEntry("updating user {}",o);
        Connection con=dbUtils.getConnection();
        if(con==null)
            return;
        User user=(User)o;
        try(PreparedStatement preStmt=con.prepareStatement("update User set username=?,password=? where id=?")){
            preStmt.setString(1,user.getUsername());
            preStmt.setString(2,user.getPassword());
            preStmt.setInt(3, Integer.parseInt(user.getId().toString()));
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
    public List<User> findAll() {
        Connection con=dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from User")) {
            try(java.sql.ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Long id = result.getLong("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    User user = new User(username, password);
                    user.setId(id);
                    users.add(user);
                }
            }
            return users;
       // return null;
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

    public User findByUsername(String username)
    {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        User user = null;
        try(PreparedStatement preStmt=con.prepareStatement("select * from User where username=?")) {
            preStmt.setString(1, username);
            try(java.sql.ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Long id = result.getLong("id");
                    String password = result.getString("password");
                    user = new User(username, password);
                    user.setId(id);
                }
            }
        }catch (Exception ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit(user);
        return user;
    }
}
*/