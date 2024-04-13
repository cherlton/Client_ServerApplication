/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.entity.Message;

/**
 *
 * @author Student
 */
public class MessageHandlerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    public MessageHandlerThread(Socket socket) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        start();
    }
    
    @Override
    public void run(){
        String data;
        String text, originator;
        Message message;
        MessageHandlerManager mhm;
        
        try {           
            data = in.readLine();
            
            while(data != null){
                String[] tokens = data.split("#");
                text = tokens[0];
                originator = tokens[1];
                
                message = new Message(text, originator);
                mhm = new MessageHandlerManager();
                
                mhm.storeMessage(message);
                
                out.println("Message received and stored.");
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageHandlerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MessageHandlerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
