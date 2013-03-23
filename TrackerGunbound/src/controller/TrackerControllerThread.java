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
    private PrintWriter out;
    private BufferedReader in;
    private boolean StatusConnection = true;


    public TrackerControllerThread(Socket _clientSocket,Tracker _tracker) {
        clientsocket = _clientSocket;
        tracker = _tracker;
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

            MessageProtocol MP = new MessageProtocol();
            do {
                inputLine = in.readLine().toCharArray();
                InputProtocol = MP.Process(inputLine);
                if (MessageProtocol.GetMessageCode(InputProtocol.toCharArray())== Message.HandShake_Code) {
                    if (tracker.IsTrackerCanAddPeer()) {
                        tracker.AddPeer(BuildPeerID().toCharArray(),tracker.Address, 4444);
                        outputLine = Message.Handshake_ResponseMessage(BuildPeerID().toCharArray());
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

     private String BuildPeerID() {
        String hostname;
        StringBuilder temp;
        StringBuilder output = new StringBuilder();
        int i = 0;
        hostname = clientsocket.getInetAddress().getHostAddress();
        temp = new StringBuilder();
        while (hostname.charAt(i) != '.') {
            temp.append(hostname.charAt(i));
            i++;
        }
        output.append((char) Integer.parseInt(temp.toString()));
        temp = new StringBuilder();
        i++;
        while (hostname.charAt(i) != '.') {
            temp.append(hostname.charAt(i));
            i++;
        }
        output.append((char) Integer.parseInt(temp.toString()));
        temp = new StringBuilder();
        i++;
        while (hostname.charAt(i) != '.') {
            temp.append(hostname.charAt(i));
            i++;
        }
        output.append((char) Integer.parseInt(temp.toString()));
        temp = new StringBuilder();
        i++;
        while (i < hostname.length()) {
            temp.append(hostname.charAt(i));
            i++;
        }
        output.append((char) Integer.parseInt(temp.toString()));
        //ntar dapet pake IP Peer!
        return output.toString();
    }

   
}
