/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Peer;
import protocol.Message;
import protocol.MessageProtocol;
import view.PeerView;

/**
 *
 * @author DELL
 */
public class PeerController extends Thread {
    private Peer peer;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private PeerView peerview;
       byte[] inputLine = null;
       
    public PeerController(PeerView _peerview) {
        peerview = _peerview;
        peer = peerview.GetPeer();
    }


    public void HandshakeTracker() {
     
        boolean connectionStatus = true;
        try {
            socket = new Socket(peer.GetTrackerAddress(), peer.GetTrackerPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException ex) {
            System.out.println("unknownhost");
            connectionStatus=false;
        } catch (IOException ex) {
            connectionStatus=false;
            System.out.println("IOException");
        }
        if(connectionStatus) {
            peer.SetStatusConnection(true);
            System.out.println("MAsuk sini");
            out.println(Message.Handshake_RequestMessage());
           
          
        }
      
    }

    public void run() {
        while (true) {
            System.out.println("Masuk while");
            try {
                Thread.sleep(100);
                if (peer.GetStatusConnection()) {
                    System.out.println("masuk status connected");
                    try {
                       inputLine=  in.readLine().getBytes();
                        System.out.println("Message dari tracker = " + inputLine);
                    } catch (IOException ex) {
                        Logger.getLogger(PeerController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("masuk IOException");
                    }
                }
                
            } catch (InterruptedException ex) {
                HandshakeTracker();
            }
        }
    }
       
}
    
    
  




