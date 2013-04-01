/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tracker;
import view.RoomView;
import view.TrackerView;

/**
 *
 * @author DELL
 */
public class TrackerController extends Thread{
    public TrackerView trackerview;
    public Tracker tracker;
    public ArrayList<RoomView> roomviews;
  
    
    public TrackerController(TrackerView _TrackerView) {
        trackerview = _TrackerView;
        tracker = trackerview.GetTracker();
        roomviews = new ArrayList<RoomView>();
    }

    public Tracker GetTracker() {
        return (tracker);
    }

    public TrackerView GetTrackerView() {
        return (trackerview);
    }

    public RoomView GetRoomViewByID(String ID) {
        RoomView buff = null;
        for (RoomView rv : roomviews) {
            if (rv.getRoomViewID().equals(ID)) {
                buff = rv;
            }
        }
        return buff;
    }

    public void DeleteRoomViewByID(String ID) {
        RoomView buff = null;
        for (RoomView rv : roomviews) {
            if (rv.getRoomViewID().equals(ID)) {
                rv.setVisible(false);
                rv.dispose();
                roomviews.remove(rv);
                return;
            }
        }
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
                TrackerControllerThread mini = new TrackerControllerThread(clientSocket,tracker,this);
                mini.start();
            }
            serverSocket.close();

            } catch (IOException ex) {
                Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
