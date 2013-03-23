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
         System.out.println("masuk handshake");
        boolean connectionStatus = true;
        try {
            socket = new Socket(peer.GetTrackerAddress(), peer.GetTrackerPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException ex) {
     //       peerGUI.setConnectionStatusLabel("Host Unreachable [" + peerInfo.getTrackerAddress() + "]");
            System.out.println("unknownhost");
            connectionStatus=false;
        } catch (IOException ex) {
      //      peerGUI.setConnectionStatusLabel("Host Unreachable [" + peerInfo.getTrackerAddress() + "]");
            connectionStatus=false;
            System.out.println("IOException");
        }
        if(connectionStatus) {
            out.println(Message.Handshake_RequestMessage());

           
            try {
                tmp=in.readLine();
//            if(RebetProtocol.getCode(tmp.toCharArray())==RebetMsgType.HANDSHAKE_CODE){
//                peer.SetStatusConnection(true);
//                String peerIP = ((int)tmp.charAt(20)+"."+(int)tmp.charAt(21)+"."+(int)tmp.charAt(22)+"."+(int)tmp.charAt(23));
//                peerGUI.setConnectionIPLabel(peerIP);
//                String peerID = (tmp.charAt(20)+""+tmp.charAt(21)+""+tmp.charAt(22)+""+tmp.charAt(23)+"");
//                peerGUI.setPeerIdLabel(peerID);
//                peerGUI.setConnButtonText(true);
//                peerGUI.setConnectionStatusLabel("Connected");
//                peerInfo.setPeerId(peerID.toCharArray());
//                refreshRoom();
//            }

             } catch (IOException ex) {
           // Logger.getLogger(PeerConToTracker.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
      
    }
        public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
//                if (peerInfo.isConnected) {
//                    sendKeepAlive();
//                    try {
//                        in.readLine();
//                    } catch (IOException ex) {
//                        Logger.getLogger(PeerConToTracker.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            } catch (InterruptedException ex) {
                  HandshakeTracker();
//                switch (command) {
//                    case ConnectToTracker:
//                        createConnection();
//                        break;
//                    case RefreshRoom:
//                        if(peerInfo.isConnected)
//                            refreshRoom();
//                        break;
//                    case ConnectToRoom:
//                        break;
//                    case CreateRoom:
//                        if(peerInfo.isConnected)
//                            createRoom(tempString2, tempInt);
//                        break;
//                    case DisconnectFromTracker :
//                        if(peerInfo.isConnected) disconnect();
//                        break;
//                    case JoinRoom:
//                        if(peerInfo.isConnected)
//                            joinRoom();
//                        break;
//                    case StartGame:
//                        if(peerInfo.isConnected)
//                            startGame();
//                    case Quit:
//                        break;
//                    default:
                }
            }
        }

}
    
    
  




