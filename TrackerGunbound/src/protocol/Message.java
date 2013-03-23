/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

/**
 *
 * @author DELL
 */
public class Message {
    public static char HandShake_Code = 135;
    public static char KeepAlive_Code = 182;
    public static char CreateRoom_Code = 255;
    public static char List_Code = 254;
    public static char Room_Code = 200;
    public static char Success_Code = 127;
    public static char Failed_Code = 128;
    public static char Join_Code = 253;
    public static char Start_Code = 252;
    public static char Quit_Code = 135;

    public static String pstr = "GunbondGame";
    public static int reserved_Length = 8;

    public static char[] Handshake_RequestMessage() {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(HandShake_Code);
        return(SB.toString().toCharArray());
    }
    public static char[] Handshake_ResponseMessage(char[] Pid) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(HandShake_Code);
        SB.append(Pid);
        return(SB.toString().toCharArray());
    }
    public static char[] KeepAlive_RequestMessage(char[] Pid) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(KeepAlive_Code);
        SB.append(Pid);
        return(SB.toString().toCharArray());
    }
    public static char[] CreateRoom_RequestMessage(char[] Pid,int max_player,char[] roomID) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(CreateRoom_Code);
        SB.append(Pid);
        SB.append(max_player);
        SB.append(roomID);
        return(SB.toString().toCharArray());
    }
    public static char[] ListRoom_RequestMessage(char[] Pid) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(List_Code);
        SB.append(Pid);
        return(SB.toString().toCharArray());
    }

    public static char[] ListRoom_ResponseMessage(int room_count,char [][] rooms) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Room_Code);
        SB.append(room_count);
        for (int i=0; i < room_count; i++) {
            SB.append(rooms[i]);
        }
        return(SB.toString().toCharArray());
    }

    public static char[] Success_ResponseMessage() {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Success_Code);
        return(SB.toString().toCharArray());
    }

     public static char[] Failed_ResponseMessage() {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Failed_Code);
        return(SB.toString().toCharArray());
    }

    public static char[] JoinRoom_RequestMessage(char PId, char roomId) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Join_Code);
        SB.append(PId);
        SB.append(roomId);
        return(SB.toString().toCharArray());
    }
    public static char[] StartGame_RequestMessage(char PId, char roomId) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Start_Code);
        SB.append(PId);
        SB.append(roomId);
        return(SB.toString().toCharArray());
    }
    public static char[] QuitRoom_RequestMessage(char PId) {
        StringBuilder SB = new StringBuilder();
        SB.append(pstr);
        for (int i=0; i < reserved_Length; i++) {
            SB.append(0);
        }
        SB.append(Quit_Code);
        SB.append(PId);
        return(SB.toString().toCharArray());
    }

    

}
