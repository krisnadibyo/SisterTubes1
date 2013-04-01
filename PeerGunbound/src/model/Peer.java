/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Peer {
    private int Pid;
    private String Tracker_Address;
    private int Tracker_Port;
    private boolean StatusConnection;
    private ArrayList<String> IDCreatedRoom;
    private ArrayList<String> IDJoinedRoom;


    public Peer(int _Pid){
        Pid = _Pid;
        Tracker_Address = "localhost";
        Tracker_Port = 4444;
        StatusConnection = false;
        IDCreatedRoom = new ArrayList<String>();
        IDJoinedRoom = new ArrayList<String>();
    }
    public Peer(int _Pid,String _Tracker_address, int _Tracker_Port) {
        Pid = _Pid;
        Tracker_Address = _Tracker_address;
        Tracker_Port = _Tracker_Port;
        StatusConnection = false;
        IDCreatedRoom = new ArrayList<String>();
        IDJoinedRoom = new ArrayList<String>();
    }

    //Getter

    public int GetID () {
        return (Pid);
    }
    public String GetTrackerAddress() {
        return (Tracker_Address);
    }
    public int GetTrackerPort() {
        return (Tracker_Port);
    }
    public boolean GetStatusConnection() {
        return (StatusConnection);
    }
    public ArrayList<String> GetIDCreatedRoom() {
        return (IDCreatedRoom);
    }
    public ArrayList<String> GetIDJoinedRoom() {
        return (IDJoinedRoom);
    }
    public int getJumlahRoomCreated() {
        return(IDCreatedRoom.size());
    }
    public int getJumlahRoomJoin() {
        return (IDJoinedRoom.size());
    }

    //setter


    public void SetID(int _Pid) {
        Pid = _Pid;
    }
    public void SetTrackerAddress(String _Tracker_Address) {
        Tracker_Address = _Tracker_Address;
    }
    public void SetTrackerPort(int _Tracker_Port) {
        Tracker_Port = _Tracker_Port;
    }
    public void SetStatusConnection(boolean _StatusConnection) {
        StatusConnection = _StatusConnection;
    }
    public void addIDCreatedRoom(String _IDCreatedRoom) {
        IDCreatedRoom.add(_IDCreatedRoom);
    }
    public void addIDJoinedRoom(String _IDJoinedRoom) {
        IDJoinedRoom.add(_IDJoinedRoom);
    }
    public void DeleteCreatedRoom(String _IDroom) {
        for (String s : IDCreatedRoom) {
            if (s.equals(_IDroom)) {
                IDCreatedRoom.remove(s);
                return;
            }
        }
    }
    public void DeleteJoinedRoom(String _IDroom) {
        for (String s : IDJoinedRoom) {
            if (s.equals(_IDroom)) {
                IDJoinedRoom.remove(s);
                return;
            }
        }
    }


    public boolean IsHasRoomCreated() {
        return(!IDCreatedRoom.isEmpty());
    }
    public boolean IsHasRoomJoined() {
        return(!IDJoinedRoom.isEmpty());
    }

}
