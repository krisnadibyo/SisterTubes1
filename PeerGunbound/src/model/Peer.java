/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author DELL
 */
public class Peer {
    private char[] Pid;
    private String Tracker_Address;
    private int Tracker_Port;
    private boolean StatusConnection;

    public Peer(String _Pid,String _Tracker_address, int _Tracker_Port) {
        Pid = _Pid.toCharArray();
        Tracker_Address = _Tracker_address;
        Tracker_Port = _Tracker_Port;
    }

    //Getter

    public char[] GetID () {
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

    //setter


    public void SetID(String _Pid) {
        Pid = _Pid.toCharArray();
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


}
