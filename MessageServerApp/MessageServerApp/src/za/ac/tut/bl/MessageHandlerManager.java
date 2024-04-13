/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import za.ac.tut.entity.Message;

/**
 *
 * @author Student
 */
public class MessageHandlerManager implements MessageHandlerInterface, MessageHandlerDBValues {
    private Connection connection;
    
    public MessageHandlerManager() throws SQLException{
        connection = getNewConnection();
    }
    
    @Override
    public synchronized void storeMessage(Message message) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        
        //generate the id randomly
        int id = 1 + (int) Math.floor((Math.random()*100));
        
        ps.setInt(1, id);
        ps.setString(2, message.getText());
        ps.setString(3, message.getOriginator());
        ps.setTimestamp(4, Timestamp.from(Instant.now()));
        
        ps.executeUpdate();
        ps.close();
    }

    private Connection getNewConnection() throws SQLException {
        Connection theConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return theConnection;
    }
    
}
