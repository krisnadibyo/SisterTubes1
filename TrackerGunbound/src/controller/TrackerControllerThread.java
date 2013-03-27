/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Tracker;
import protocol.Message;
import protocol.MessageProtocol;


/**
 *
 * @author DELL
 */
public class TrackerControllerThread extends Thread{
    private Socket clientsocket;
    private Tracker tracker;
    private TrackerController trackercontroller;
    private PrintWriter out;
    private BufferedReader in;
    private boolean StatusConnection = true;


    public TrackerControllerThread(Socket _clientSocket,Tracker _tracker,TrackerController TC) {
        clientsocket = _clientSocket;
        tracker = _tracker;
        trackercontroller = TC;
    }

    public void run() {
         try {
            out = new PrintWriter(clientsocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                    clientsocket.getInputStream()));

            byte[] inputLine = null;
            byte[] outputLine = null;
           
            do {
               inputLine =  in.readLine().getBytes();
               System.out.println("Messsage dari peer: " + inputLine);
               outputLine = Message.Handshake_ResponseMessage(1);
               System.out.println("Output Line = " + outputLine);
               out.print(outputLine);
                         
            } while(StatusConnection);
          

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

   
}
