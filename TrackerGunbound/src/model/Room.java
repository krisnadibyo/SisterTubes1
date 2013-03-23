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
    public char[] RoomID;
    public Peer PeerCreator;
    public int MaxPeerJoin;
    public int CurrentPeerJoin;
    public HashMap<char[],Peer> PeerList;
    
    public Room (char[] _RoomID, int _MaxPeerJoin, Peer _PeerCreator) {
        RoomID = _RoomID;
        MaxPeerJoin = _MaxPeerJoin;
        PeerCreator = _PeerCreator;
        PeerList = new HashMap<char[], Peer>();
        PeerList.put(PeerCreator.GetID(), PeerCreator);
        CurrentPeerJoin = 1;
    }

    public void AddPeerToRoom(Peer P) {
        PeerList.put(P.GetID(), P);
        CurrentPeerJoin++;
    }
    public void DeletePeerFromRoom(char[] PeerID) {
        PeerList.remove(PeerID);
        CurrentPeerJoin--;
    }
    public boolean IsThisPeerInThisRoom(char[] PeerID) {
        return(PeerList.containsKey(PeerID));
    }

}
