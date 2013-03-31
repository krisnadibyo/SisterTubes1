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

import model.Peer;
import protocol.Message;
import protocol.MessageBuilder;
import view.PeerView;

/**
 *
 * @author Krisna Dibyo Atmojo
 */
public class PeerController extends Thread {
    private Peer peer;
    private Socket socketTracker = null;
    private Socket socketRoom = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private PeerView peerview;
    private int Cur_Command;
    String bufferString;
    
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
        Cur_Command = Listen_Command;
    }

    public String SendMessage(byte [] msg) {
        MessageBuilder MB = new MessageBuilder(msg);
        return (MB.GetAllMessageInString());
    }

    public byte[] ReceiveMessage(String S) {
        int msgLength = S.length();
        MessageBuilder MB = new MessageBuilder(msgLength);
        MB.writeStrToMsgBytes(0, S.length(), S);
        return (MB.getMessageBytes());
    }

    public void SetCurrentCommandtoListen() {
        Cur_Command = Listen_Command;
    }
    public void SetCurrentCommandtoHandshake() {
        Cur_Command = Handshake_Command;
        HandshakeTracker();
    }
    public void SetCurrentCommandtoCreateRoom(int jumlahPemain,String namaRoom) {
        Cur_Command = CreateRoom_Command;
        byte jml = (byte) jumlahPemain;
        CreateRoom(jml, namaRoom);
        bufferString = namaRoom;
    }
    public void SetCurrentCommandtoListRoom() {
        Cur_Command = ListRoom_Command;
        ListRoom();
    }
    public void SetCurrentCommandtoJoinRoom(String roomID) {
        Cur_Command = JoinRoom_Command;
        JoinRoom(roomID);
    }
    public void SetCurrentCommandtoStartGame() {
        Cur_Command = StartGame_Command;
        StartGame();
    }
    public void SetCurrentCommandtoQuitGame() {
        Cur_Command = QuitRoom_Command;
    }


    public void HandshakeTracker() {
        byte[] input;
        boolean connectionStatus = true;
        try {
            socketTracker = new Socket(peer.GetTrackerAddress(), peer.GetTrackerPort());
            out = new PrintWriter(socketTracker.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socketTracker.getInputStream()));
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
    public void ListRoom() {
        if (peer.GetStatusConnection()) {
            out.println(SendMessage(Message.ListRoom_RequestMessage(peer.GetID())));
        }
        else {
            System.out.println("This Peer is not connected to tracker");
        }
    }
    public void JoinRoom(String roomID) {
        if (peer.GetStatusConnection()) {
            out.println(SendMessage(Message.JoinRoom_RequestMessage(peer.GetID(), roomID)));
        }
        else {
            System.out.println("This Peer is not connected to tracker");
        }
    }
    public void StartGame() {
        if (peer.GetStatusConnection()) {
            out.println(SendMessage(Message.StartGame_RequestMessage(peer.GetID(), peer.GetIDCreatedRoom())));
        }
        else {
            System.out.println("This Peer is not connected to tracker");
        }
    }
    public void QuitGame() {
        if (peer.GetStatusConnection()) {
            out.println(SendMessage(Message.QuitRoom_RequestMessage(peer.GetID())));
        }
        else {
            System.out.println("This Peer is not connected to tracker");
        }
    }

    
    @Override
    public void run() {
        try {
            //Pada saat user klik connect thread baru start
            SetCurrentCommandtoHandshake();
            String inputLine = null;
          
            // Respond dari TRACKER
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
                          System.out.println("Room has been succesfull created !");
                          peer.SetIDCreatedRoom(bufferString);  
                    }
                    else if(MB.getCode() == Message.Failed_Code) {
                         System.out.println("Room has been Failed created!");
                    }
                }
                else if (Cur_Command == ListRoom_Command) {
                    byte[] msg = ReceiveMessage(inputLine);
                    MessageBuilder MB = new MessageBuilder(msg);
                    if (MB.getCode() == Message.Room_Code) {
                         peerview.SetListRoom(MB.GetListRoom());
                         peerview.SetComboBoxJoin(MB.GetListRoom());
                    }
                    else if(MB.getCode() == Message.Failed_Code) {
                         System.out.println("This Peer is not listed in the tracker");
                    }
                }
                else if (Cur_Command == JoinRoom_Command) {
                    byte[] msg = ReceiveMessage(inputLine);
                    MessageBuilder MB = new MessageBuilder(msg);
                    if (MB.getCode() == Message.Success_Code) {
                          System.out.println("This peer has been added to desired room");
                    }
                    else if(MB.getCode() == Message.Failed_Code) {
                         System.out.println("Join Failed");
                    }
                }
                else if (Cur_Command == StartGame_Command) {
                    byte[] msg = ReceiveMessage(inputLine);
                    MessageBuilder MB = new MessageBuilder(msg);
                    if (MB.getCode() == Message.Success_Code) {
                          System.out.println("Start game success");
                          peer.SetIDCreatedRoom(null);
                    }
                    else if(MB.getCode() == Message.Failed_Code) {
                         System.out.println("Start game Failed");
                    }
                }
                SetCurrentCommandtoListen();

                //nunggu
                while (Cur_Command ==  Listen_Command) {

                }
            }
        


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
}
    
    
  




