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
public class PeerController{
    private Peer peer;
    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private PeerView peerview;

    public PeerController(PeerView _peerview) {
        peerview = _peerview;
        peer = peerview.GetPeer();
    }


    public void HandshakeTracker() throws IOException{
        byte[] input = null;
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
            boolean ack = false;
            while ((input = in.readLine().getBytes()) != null) {
                System.out.println("masuk sini");
                System.out.println(input);
                if (input != null) {
                    break;
                }
            }
        }
      
    }
       
}
    
    
  




