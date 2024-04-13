/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageclientapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class MessageClientApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InetAddress addr;
        Socket socket;
        BufferedReader in;
        PrintWriter out;
        int option;
        Scanner sc = new Scanner(System.in);
        String name, msg, data, serverResponse;
        
        try {
            addr = InetAddress.getByName("127.0.0.1");
            socket = new Socket(addr, 9090);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            
            option = getOption();
            
            while(option != 2){
                if(option == 1){
                    System.out.println("\nPlease enter your name: ");
                    name = sc.nextLine();
                    System.out.println("Please enter your message: ");
                    msg = sc.nextLine();     
                    
                    data = msg + "#" + name;
                    
                    out.println(data);
                    
                    //wait for server response
                    serverResponse = in.readLine();
                    
                    //display the server response
                    System.err.println("Server response: " + serverResponse);
                } else {
                    System.err.println(option + " is invalid.");
                }
                
                option = getOption();
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageClientApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MessageClientApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private static int getOption() {
        int option;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Please select one of the following options: " + "\n" +
                "1 - send a message to the server" + "\n" +
                "2 - exit");
        option = sc.nextInt();
        return option;
    }
    
}
