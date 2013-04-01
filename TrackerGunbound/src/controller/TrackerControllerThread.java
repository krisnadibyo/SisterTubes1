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
import model.Tracker;
import protocol.Message;
import protocol.MessageBuilder;
import view.RoomView;
import view.TrackerView;


/**
 *
 * @author Krisna Dibyo Atmojo
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
        MessageBuilder MB = new MessageBuilder(msgLength);
        MB.writeStrToMsgBytes(0, S.length(), S);
        return (MB.getMessageBytes());
    }

    @Override
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
                        int giveID = tracker.GiveIDPeer();
                        tracker.AddPeerToTracker(giveID);
                        out.println(SendMessage(Message.Handshake_ResponseMessage(giveID)));
                        trackerview.SetJumlahPeer();
                        trackerview.ListPeer();
                    }
                    else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                    }
                }
                else if (MB.getCode() == Message.CreateRoom_Code) {
                    if (tracker.IsTrackerCanAddRoom()) {
                        tracker.AddRoom(MB.GetRoomID(), tracker.GetPeer(MB.getPeerId()), MB.GetJumlahMaxRoom());
                        out.println(SendMessage(Message.Success_ResponseMessage()));
                        trackerview.setJumlahRoom();
                        trackerview.ListRoom();
                        RoomView roomview = new RoomView(tracker.GetRoom(MB.GetRoomID()));
                        trackercontroller.roomviews.add(roomview);
                    }
                    else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                    }
                }
                else if (MB.getCode() == Message.List_Code) {
                    if (tracker.IsThisPeerExist(MB.getPeerId())) {
                        out.println(SendMessage(Message.ListRoom_ResponseMessage((byte) tracker.GetCurrentSumRoom(), tracker.GetAllRoomID())));
                    }
                    else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                    }
                }
               else if (MB.getCode() == Message.Join_Code) {
                   if ((tracker.IsThisPeerExist(MB.getPeerId())) && tracker.GetRoom(MB.GetRoomIDJoin()).CanAddPeer()) {
                       tracker.AddPeerToRoom(tracker.GetPeer(MB.getPeerId()), tracker.GetRoom(MB.GetRoomIDJoin()));
                       trackercontroller.GetRoomViewByID(MB.GetRoomIDJoin()).UpdateRoom(tracker.GetRoom(MB.GetRoomIDJoin()));
                       out.println(SendMessage(Message.Success_ResponseMessage()));
                    }
                   else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                   }
               }
               else if (MB.getCode() == Message.Start_Code) {
                   if (tracker.IsThisPeerExist(MB.getPeerId())) {
                       String IDroomStarted = MB.GetRoomIDJoin();
                       trackercontroller.DeleteRoomViewByID(IDroomStarted);
                       tracker.DeleteRoom(IDroomStarted);
                       trackerview.setJumlahRoom();
                       trackerview.ListRoom();
                       out.println(SendMessage(Message.Success_ResponseMessage()));
                    }
                   else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                   }
               }
              else if (MB.getCode() == Message.Quit_Code) {
                  if (tracker.IsThisPeerExist(MB.getPeerId())) {
                       int IDPeer = MB.getPeerId();
                       String IDroom = MB.GetRoomIDJoin();
                       tracker.GetRoom(IDroom).DeletePeerFromRoom(tracker.GetPeer(IDPeer));
                       trackercontroller.GetRoomViewByID(IDroom).UpdateRoom(tracker.GetRoom(IDroom));
                       out.println(SendMessage(Message.Success_ResponseMessage()));
                    }
                   else {
                        out.println(SendMessage(Message.Failed_ResponseMessage()));
                   }
              }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TrackerView getTrackerView() {
        return trackerview;
    }
   

   
}
