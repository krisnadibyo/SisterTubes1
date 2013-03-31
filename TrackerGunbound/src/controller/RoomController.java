/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;

import protocol.Message;
import protocol.MessageBuilder;
import view.RoomView;


/**
 *
 * @author Krisna
 */
public class RoomController {
    private PrintWriter out;
    private BufferedReader in;
    private ServerSocket serversocket;
  
    private boolean StatusConnection = true;

    public RoomController() {
         try {
                serversocket = new ServerSocket(3333);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 3333");
         }
    }

    

    public void ListenSocket(Room room) {
        boolean tunggu = true;
        while (tunggu) {
            try {
                Socket socket = serversocket.accept();
     //           RoomControllerThread rm = new RoomControllerThread(socket, room);
                tunggu = false;
            } catch (IOException ex) {
                Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
    }
   
   

}
