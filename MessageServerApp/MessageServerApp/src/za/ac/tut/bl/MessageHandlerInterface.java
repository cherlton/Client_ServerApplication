/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.sql.SQLException;
import za.ac.tut.entity.Message;

/**
 *
 * @author Student
 */
public interface MessageHandlerInterface {
    public void storeMessage(Message message) throws SQLException;
}
