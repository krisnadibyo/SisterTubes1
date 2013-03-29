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
import protocol.MessageBuilder;
import protocol.MessageProtocol;
import view.TrackerView;


/**
 *
 * @author DELL
 */
public class TrackerControllerThread extends Thread{
    private Socket clientsocket;
    private Tracker tracker;
    private TrackerController trackercontroller;
    private TrackerView trackerview;
    private PrintWriter out;
    private BufferedReader in;
    private boolean StatusConnection = true;


    public TrackerControllerThread(Socket _clientSocket,Tracker _tracker,TrackerController TC) {
        clientsocket = _clientSocket;
        tracker = _tracker;
        trackercontroller = TC;
        trackerview = TC.GetTrackerView();
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

    public void run() {
         try {
            out = new PrintWriter(clientsocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                    clientsocket.getInputStream()));

            String inputLine = null;
                     
            while ((inputLine = in.readLine()) != null) {
                byte[] IsiMessage = ReceiveMessage(inputLine);
               
                MessageBuilder MB = new MessageBuilder(IsiMessage);
                if (MB.getCode() == Message.HandShake_Code) {
                    if (tracker.IsTrackerCanAddPeer()) {
                        int giveID = tracker.GetCurrentSumPeer()+1;
                        tracker.AddPeerToTracker(giveID);
                        out.println(SendMessage(Message.Handshake_ResponseMessage(giveID)));
                        trackerview.SetJumlahPeer();
                    }
                    else {
                        
                    }
                }
                else if (MB.getCode() == Message.CreateRoom_Code) {
                    if (tracker.IsTrackerCanAddRoom()) {
                        tracker.AddRoom(MB.GetRoomID(), tracker.GetPeer(MB.getPeerId()), MB.GetJumlahMaxRoom());
                        out.println(SendMessage(Message.Success_ResponseMessage()));
                        trackerview.setJumlahRoom();
                    }
                }
                
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

   
}
