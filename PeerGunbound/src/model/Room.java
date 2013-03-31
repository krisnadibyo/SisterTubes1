/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.print.DocFlavor.CHAR_ARRAY;

/**
 *
 * @author DELL
 */
public class Room {
    private String RoomID;
    private Peer PeerCreator;
    private int MaxPeerJoin;
    private int CurrentPeerJoin;
    private ArrayList<Peer> PeerList;
    
    public Room (String _RoomID, byte _MaxPeerJoin, Peer _PeerCreator) {
        RoomID = _RoomID;
        MaxPeerJoin = _MaxPeerJoin;
        PeerCreator = _PeerCreator;
        PeerList = new ArrayList<Peer>();
        PeerList.add(PeerCreator);
        CurrentPeerJoin = 1;
    }

    //getter
    public String GetID() {
        return (RoomID);
    }
    public int GetSumCurrentPeer() {
        return(CurrentPeerJoin);
    }
    public Integer GetMaxPeerJoin() {
        return(new Integer(MaxPeerJoin));
    }
    public Peer GetPeerCreator() {
        return (PeerCreator);
    }


    public void AddPeerToRoom(Peer P) {
        if (!IsThisPeerInThisRoom(P)) {
            PeerList.add(P);
            CurrentPeerJoin++;
        }
    }
    
    public void DeletePeerFromRoom(Peer P) {
        if (IsThisPeerInThisRoom(P)) {
            PeerList.remove(P);
            CurrentPeerJoin--;
        }
    }

    public boolean IsThisPeerInThisRoom(Peer peer) {
        return(PeerList.contains(peer));
    }

}
