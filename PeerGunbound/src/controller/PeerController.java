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
public class PeerController extends Thread{
    private Peer peer;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private PeerView peerview;

    public PeerController(PeerView _peerview) {
        peerview = _peerview;
        peer = peerview.GetPeer();
    }

    public void HandshakeTracker() {
         String tmp;
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
            out.println(Message.Handshake_RequestMessage());           
            try {
                tmp=in.readLine();
                System.out.println(tmp);
                if(MessageProtocol.GetMessageCode(tmp.toCharArray()) == Message.HandShake_Code){ 
                    String peerIP = ((int)tmp.charAt(20)+"."+(int)tmp.charAt(21)+"."+(int)tmp.charAt(22)+"."+(int)tmp.charAt(23));
                    String peerID = ((int)tmp.charAt(20)+""+(int)tmp.charAt(21)+""+(int)tmp.charAt(22)+""+(int)tmp.charAt(23)+"");
                    
                    peer.SetID(peerID);
                    peer.SetStatusConnection(true);
                    peerview.setLabel_IP(peerIP);
                    peerview.seLabel_ID(peerID);
                    peerview.setLabel_Status(true);
                    System.out.println(peerID);
                }

             } catch (IOException ex) {
         
              }
        }
      
    }
        public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                  HandshakeTracker();
              
               }
            }
        }

}
    
    
  




