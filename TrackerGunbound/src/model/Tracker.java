/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class Tracker {

    public int MaxSumPeers;
    public int CurSumPeers;
    public int MaxSumRooms;
    public int CurSumRooms;
    public HashMap<char[],Peer> Peers; //key : Id Peer; value : Peer
    public HashMap<char[],Room> Rooms; // key : Id Room; value : Room
    public String Address;
   

    public Tracker(String ip,int _MaxSumPeers,int _MaxSumRooms) {
        Peers = new HashMap<char[],Peer>();
        Rooms = new HashMap<char[],Room>();
        MaxSumPeers = _MaxSumPeers;
        CurSumPeers = 0;
        MaxSumRooms = _MaxSumRooms;
        CurSumRooms = 0;
        Address = ip;
    }

    public void StartGame(char[] RoomId) {
        DeleteRoom(RoomId);
    }
    

    
    public void AddPeerToRoom(char[] Pid,char[] RoomId) {
        Rooms.get(RoomId).AddPeerToRoom(Peers.get(Pid));
    }


    public boolean IsTrackerCanAddPeer() {
        return (CurSumPeers+1 < MaxSumPeers);
    }
    public boolean IsTrackerCanAddRoom() {
        return(CurSumRooms+1 < MaxSumRooms);
    }

    public void AddPeer(char[] Pid,String _Address,int _Port) {
        if (IsTrackerCanAddPeer()) {
            Peer newPeer = new Peer(Pid.toString(), _Address, _Port);
            Peers.put(Pid, newPeer);
            CurSumPeers++;
        }
    }
    public void DeletePeer(char[] Pid) {
        if (IsThisPeerExist(Pid)) {
            Peers.remove(Pid);
        }
    }
    public boolean IsThisPeerExist(char[] Pid) {
        return(Peers.containsKey(Pid));
    }

    public void AddRoom(char[] RoomID,char[] PeerCreatorID,int maxpeerjoin) {
        if (IsTrackerCanAddRoom()) {
            Room newRoom = new Room(RoomID, maxpeerjoin, Peers.get(PeerCreatorID));
            Rooms.put(RoomID, newRoom);
            CurSumRooms++;
        }
    }

    public void DeleteRoom(char[] RoomId) {
        if (IsThisRoomExist(RoomId)) {
            Rooms.remove(RoomId);
            CurSumRooms--;
        }
    }

    public boolean IsThisRoomExist(char[] RoomId) {
        return(Rooms.containsKey(RoomId));
    }

    public ArrayList<char[]> GetAllPeer() {
        ArrayList<char[]> temp = new ArrayList<char[]>();
       
        return temp;
    }
    

    

    

    
}
