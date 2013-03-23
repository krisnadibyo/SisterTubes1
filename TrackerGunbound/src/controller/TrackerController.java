/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tracker;
import view.TrackerView;

/**
 *
 * @author DELL
 */
public class TrackerController extends Thread{
    TrackerView trackerview;
    Tracker tracker;
    
    public TrackerController(TrackerView _TrackerView) {
        trackerview = _TrackerView;
        tracker = trackerview.GetTracker();
    }

    public Tracker GetTracker() {
        return (tracker);
    }

    public void run () {
         try {
            ServerSocket serverSocket = null;

             boolean listeningSocket = true;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444");
        }

        while(listeningSocket){
            Socket clientSocket = serverSocket.accept();
            TrackerControllerThread mini = new TrackerControllerThread(clientSocket,tracker);
            mini.start();
        }
        serverSocket.close();
           
        } catch (IOException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
