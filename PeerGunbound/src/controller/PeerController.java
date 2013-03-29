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
import protocol.MessageBuilder;
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
    private int Cur_Command;
    
    public static final int Listen_Command = 0;
    public static final int Handshake_Command = 1;
    public static final int CreateRoom_Command = 2;
    public static final int ListRoom_Command = 3;
    public static final int JoinRoom_Command = 4;
    public static final int StartGame_Command = 5;
    public static final int QuitRoom_Command = 6;


       
    public PeerController(PeerView _peerview) {
        peerview = _peerview;
        peer = peerview.GetPeer();
        Cur_Command = Handshake_Command;
    }

    public String SendMessage(byte [] msg) {
        MessageBuilder MB = new MessageBuilder(msg);
        return (MB.GetAllMessageInString());
    }

    public byte[] ReceiveMessage(String S) {
        int msgLength = S.length();
        System.out.println(msgLength);
        MessageBuilder MB = new MessageBuilder(msgLength);
        MB.writeStrToMsgBytes(0, S.length(), S);
        return (MB.getMessageBytes());
    }

    public void SetCurrentCommand(int x) {
        Cur_Command = x;
    }
     public void SetCurrentCommand(int x,int jumlahPemain,String namaRoom) {
        Cur_Command = x;
        byte jml = (byte) jumlahPemain;
        CreateRoom(jml, namaRoom);
    }

    public void HandshakeTracker() {
        byte[] input;
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
            out.println(SendMessage(Message.Handshake_RequestMessage()));
         }  
    }

    public void CreateRoom(byte JmlPemain,String namaRoom) {
        if (peer.GetStatusConnection()) {
            out.println(SendMessage(Message.CreateRoom_RequestMessage(peer.GetID(), JmlPemain, namaRoom)));
        }
        else {
            System.out.println("This Peer is not connected to tracker");
        }
    }

    public void run() {
        try {
          
            HandshakeTracker();
	    

            String inputLine = null;
          
       
        while ((inputLine = in.readLine()) != null) {
            if (Cur_Command == Handshake_Command) {
                byte[] msg = ReceiveMessage(inputLine);
                MessageBuilder MB = new MessageBuilder(msg);
                int id = MB.getPeerId();
                peer.SetID(id);
                peerview.seLabel_ID(id);
                peerview.setLabel_Status(true);
            }
            else if(Cur_Command == CreateRoom_Command) {
                      byte[] msg = ReceiveMessage(inputLine);
                      MessageBuilder MB = new MessageBuilder(msg);
                      if (MB.getCode() == Message.Success_Code) {
                          System.out.println("Room success !");
                      }
                      else if(MB.getCode() == Message.Failed_Code) {
                          System.out.println("Room Failed !");
                      }
                }
            SetCurrentCommand(Listen_Command);

	    while (Cur_Command ==  Listen_Command) {
                
            }
	}
        


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
}
    
    
  




