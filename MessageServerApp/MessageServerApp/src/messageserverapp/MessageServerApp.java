/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageserverapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.bl.MessageHandlerThread;

/**
 *
 * @author Student
 */
public class MessageServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 
        ServerSocket s;
        Socket socket;
        
        try {
            s = new ServerSocket(9090);
            
            while(true){
                System.out.println("Waiting for client request...");
                socket = s.accept();
                
                MessageHandlerThread mht = new MessageHandlerThread(socket);
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
