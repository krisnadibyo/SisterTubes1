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
import protocol.MessageProtocol;


/**
 *
 * @author DELL
 */
public class TrackerControllerThread extends Thread{
    private Socket clientsocket;
    private Tracker tracker;
    private TrackerController trackercontroller;
    private PrintWriter out;
    private BufferedReader in;
    private boolean StatusConnection = true;


    public TrackerControllerThread(Socket _clientSocket,Tracker _tracker,TrackerController TC) {
        clientsocket = _clientSocket;
        tracker = _tracker;
        trackercontroller = TC;
    }

    public void run() {
         try {
            out = new PrintWriter(clientsocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                    clientsocket.getInputStream()));

            char[] inputLine = null;
            char[] outputLine = null;
            String InputProtocol=null;
            String OutputProtocol=null;
            System.out.println("Masuk run tracker thread");
            MessageProtocol MP = new MessageProtocol();
            do {
                inputLine = in.readLine().toCharArray();
                InputProtocol = MP.Process(inputLine);            
                if (MessageProtocol.GetMessageCode(InputProtocol.toCharArray())== Message.HandShake_Code) {
                    if (tracker.IsTrackerCanAddPeer()) {
                        System.out.println("Peer ID : " + BuildPeerID());
                        tracker.AddPeer(BuildPeerID(),tracker.Address, 4444);
                        outputLine = Message.Handshake_ResponseMessage(BuildPeerID());
                        trackercontroller.GetTrackerView().AddPeerView(BuildPeerIP(BuildPeerID()));
                        trackercontroller.GetTrackerView().SetJumlahPeer();
                        out.println(outputLine);
                    }
                    else {
                        outputLine = Message.Failed_ResponseMessage();
                        out.println(outputLine);
                    }
                }

            } while(StatusConnection);
          

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

    public Tracker getTracker() {
        return tracker;
    }

     private char[] BuildPeerID() {
        char byte1 = 127;
        char byte2 = 0;
        char byte3 = 0;
        char byte4 = (char) (tracker.CurSumPeers+1) ;
        char[] chars = new char[4];
        chars[0] = byte1;
        chars[1] = byte2;
        chars[2] = byte3;
        chars[3] = byte4;
        return chars;
    }

     private String BuildPeerIP(char[] chars) {
         String output="";
         Integer byte1 = (int) chars[0];
         Integer byte2 = (int) chars[1];
         Integer byte3 = (int) chars[2];
         Integer byte4 = (int) chars[3];
         output = byte1.toString() + "." + byte2.toString() + "." + byte3.toString()+ "." + byte4.toString();
         return output;
     }

   
}
