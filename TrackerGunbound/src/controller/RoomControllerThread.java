/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import model.Room;
import protocol.Message;
import protocol.MessageBuilder;
import view.RoomView;

/**
 *
 * @author DELL
 */
public class RoomControllerThread extends Thread{

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientsocket;
    private Room room;
    private RoomView roomview;

    public RoomControllerThread(Socket _clientsocket,Room _room) {
        clientsocket = _clientsocket;
        room = _room;
  //      roomview = new RoomView(TCT.getTrackerView(), true, this);
        roomview.setVisible(true);
        
    }
    
    public Room GetRoom() {
        return(room);
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
                   
                }
                else if (MB.getCode() == Message.CreateRoom_Code) {
                   
                }
                else if (MB.getCode() == Message.List_Code) {

                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
