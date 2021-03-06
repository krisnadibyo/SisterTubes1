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

    private int MaxSumPeers;
    private int CurSumPeers;
    private int MaxSumRooms;
    private int CurSumRooms;
    private ArrayList<Peer> Peers;
    private ArrayList<Room> Rooms;
    private String Address;
    private int Port;
    private static int GiveIDPeer;
  
   

    public Tracker(String _Address,int _Port,int _MaxSumPeers,int _MaxSumRooms) {
        Peers = new ArrayList<Peer>();
        Rooms = new ArrayList<Room>();
        MaxSumPeers = _MaxSumPeers;
        CurSumPeers = 0;
        MaxSumRooms = _MaxSumRooms;
        CurSumRooms = 0;
        Address = _Address;
        Port = _Port;
        GiveIDPeer = 1;
    }
    

    //getter

    public String GetAddress() {
        return Address;
    }
    public int GetPort() {
        return Port;
    }
    public int GetCurrentSumPeer() {
        return CurSumPeers;
    }
    public int GetCurrentSumRoom() {
        return CurSumRooms;
    }
    public ArrayList<Peer> GetAllPeers() {
        return Peers;
    }
    public ArrayList<Room> GetAllRooms() {
        return Rooms;
    }



    // fungsi lainnya
     public boolean IsTrackerCanAddPeer() {
        return (CurSumPeers+1 < MaxSumPeers);
    }
    public boolean IsTrackerCanAddRoom() {
        return(CurSumRooms+1 < MaxSumRooms);

    }
    public void AddPeerToRoom(Peer peer, Room room) {
       for (Room r : Rooms) {
           if(r.equals(room)) {
               r.AddPeerToRoom(peer);
           }
       }
    }
   
    public void AddPeerToTracker(int Pid) {
        if (IsTrackerCanAddPeer()) {
            Peer newPeer = new Peer(Pid, Address, Port);
            Peers.add(newPeer);
            CurSumPeers++;
        }
    }
    public void DeletePeer(int Pid) {
        for (Peer P : Peers) {
           if(P.GetID() == Pid) {
               Peers.remove(P);
                CurSumPeers--;
               return;
           }
       }
      
    }
    public boolean IsThisPeerExist(int Pid) {
        boolean result = false;
        for (Peer P : Peers) {
            if (P.GetID() == Pid) {
                result = true;
            }
        }
        return result;
    }

    public void AddRoom(String RoomID,Peer PeerCreator,byte maxpeerjoin) {
        if (IsTrackerCanAddRoom()) {
            Room newRoom = new Room(RoomID, maxpeerjoin, PeerCreator);
            Rooms.add(newRoom);
            CurSumRooms++;
        }
    }

    public void DeleteRoom(String RoomId) {
        for (Room R : Rooms) {
           if(R.GetID().equals(RoomId)) {
               Rooms.remove(R);
               CurSumRooms--;
               return;
           }
       }
       
    }

    public boolean IsThisRoomExist(Room room) {
        return(Rooms.contains(room));
    }

    public Peer GetPeer(int pID) {
        Peer Presult = null;
        for (Peer P : Peers) {
            if (P.GetID() == pID) {
                Presult = P;
            }
        }
        return (Presult);
    }

     public Room GetRoom(String RoomID) {
        Room Rresult = null;
        for (Room R : Rooms) {
            if (R.GetID().equals(RoomID)) {
                Rresult = R;
            }
        }
        return (Rresult);
    }
    public int GiveIDPeer() {
        return (GiveIDPeer++);
    }

    public ArrayList<Integer> GetAllPeerID() {
        ArrayList<Integer> buff = new ArrayList<Integer>();
        for (Peer P : Peers) {
            buff.add(P.GetID());
        }
        return (buff);
    }
    public ArrayList<String> GetAllRoomID() {
        ArrayList<String> buff = new ArrayList<String>();
        for (Room R : Rooms) {
            buff.add(R.GetID());
        }
        return (buff);
    }

    

    
    

    

    

    
}
