/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

import java.util.ArrayList;

/**
 *
 * @author Krisna Dibyo Atmojo
 */
public class Message{
    public static byte HandShake_Code = (byte) 135;
    public static byte KeepAlive_Code = (byte) 182;
    public static byte CreateRoom_Code = (byte) 255;
    public static byte List_Code = (byte) 254;
    public static byte Room_Code = (byte) 200;
    public static byte Success_Code = (byte)127;
    public static byte Failed_Code = (byte) 128;
    public static byte Join_Code = (byte) 253;
    public static byte Start_Code = (byte) 252;
    public static byte Quit_Code = (byte) 235;

    


    public static byte[] Handshake_RequestMessage() {
        MessageBuilder MB = new MessageBuilder(20);
        MB.setCode(HandShake_Code);
        return (MB.getMessageBytes());
    }
    public static byte[] Handshake_ResponseMessage(int Pid) {
        MessageBuilder MB = new MessageBuilder(24);
        MB.setCode(HandShake_Code);
        MB.writeIntToMsgBytes(20, Pid);
        return (MB.getMessageBytes());
    }
   public static byte[] KeepAlive_RequestMessage(int Pid) {
        MessageBuilder MB = new MessageBuilder(24);
        MB.setCode(KeepAlive_Code);
        MB.writeIntToMsgBytes(20, Pid);
        return (MB.getMessageBytes());
   }
   public static byte[] CreateRoom_RequestMessage(int Pid,byte max_player,String roomID) {
        MessageBuilder MB = new MessageBuilder(75);
        MB.setCode(CreateRoom_Code);
        MB.writeIntToMsgBytes(20, Pid);
        MB.writeByteToMsgBytes(24, max_player);
        MB.writeStrToMsgBytes(25, 50, roomID);
        return (MB.getMessageBytes());
   }
   public static byte[] ListRoom_RequestMessage(int Pid) {
       MessageBuilder MB = new MessageBuilder(24);
       MB.setCode(List_Code);
       MB.writeIntToMsgBytes(20, Pid);
       return (MB.getMessageBytes());
   }

    public static byte[] ListRoom_ResponseMessage(byte room_count,ArrayList<String> rooms) {
       int size = 21 + (50*room_count);
       MessageBuilder MB = new MessageBuilder(size);
       MB.setCode(Room_Code);
       MB.writeByteToMsgBytes(20, room_count);
       int offset = 21;
       for (String s : rooms) {
           MB.writeStrToMsgBytes(offset, 50, s);
       }
       return (MB.getMessageBytes());
    }

    public static byte[] Success_ResponseMessage() {
       MessageBuilder MB = new MessageBuilder(20);
       MB.setCode(Success_Code);
       return(MB.getMessageBytes());
    }

    public static byte[] Failed_ResponseMessage() {
       MessageBuilder MB = new MessageBuilder(20);
       MB.setCode(Failed_Code);
       return(MB.getMessageBytes());
    }

    public static byte[] JoinRoom_RequestMessage(int PId, String roomId) {
       MessageBuilder MB = new MessageBuilder(74);
       MB.setCode(Join_Code);
       MB.writeIntToMsgBytes(20,PId);
       MB.writeStrToMsgBytes(24, 50, roomId);
       return (MB.getMessageBytes());
    }
    public static byte[] StartGame_RequestMessage(int PId, String roomId) {
       MessageBuilder MB = new MessageBuilder(74);
       MB.setCode(Start_Code);
       MB.writeIntToMsgBytes(20,PId);
       MB.writeStrToMsgBytes(24, 50, roomId);
       return (MB.getMessageBytes());
    }    
    public static byte[] QuitRoom_RequestMessage(int PId,String roomId) {
       MessageBuilder MB = new MessageBuilder(74);
       MB.setCode(Quit_Code);
       MB.writeIntToMsgBytes(20,PId);
       MB.writeStrToMsgBytes(24, 50, roomId);
       return (MB.getMessageBytes());
    }

    

}
