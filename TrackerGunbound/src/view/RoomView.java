/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RoomVieew.java
 *
 * Created on 31 Mar 13, 15:18:16
 */

package view;

import controller.RoomControllerThread;
import java.util.ArrayList;
import javax.swing.JFrame;
import model.Peer;
import model.Room;

/**
 *
 * @author Krisna
 */
public class RoomView extends javax.swing.JFrame {

    /** Creates new form RoomVieew */
    private RoomControllerThread roomcontrollerThread;
    private Room room;
    private String IDRoomView;

    public RoomView(Room _room) {
        initComponents();
        room = _room;
        IDRoomView = room.GetID();
        initRoom();
        this.setVisible(true);
        this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
    }

    public String getRoomViewID() {
        return IDRoomView;
    }

    public void UpdateRoom(Room _room) {
        room = _room;
        initRoom();
        ArrayList<Peer> arr = new ArrayList<Peer>();
        arr = room.getPeerList();
        int i=0;
        for (Peer P : arr) {
            Table_ListPeer.getModel().setValueAt(P.GetID(), i, 0);
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label_idRoom = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        label_idCreator = new javax.swing.JLabel();
        label_maxPeer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_ListPeer = new javax.swing.JTable();
        label_curPeer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel1.setText("ID Room");

        label_idRoom.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        label_idRoom.setText("jLabel2");
        label_idRoom.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel3.setText("ID Creator");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Cur Peer");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel5.setText("Max Peer");

        label_idCreator.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        label_idCreator.setText("jLabel6");
        label_idCreator.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        label_maxPeer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        label_maxPeer.setText("jLabel8");
        label_maxPeer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        Table_ListPeer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Peer"
            }
        ));
        Table_ListPeer.setDoubleBuffered(true);
        Table_ListPeer.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(Table_ListPeer);

        label_curPeer.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        label_curPeer.setText("jLabel6");
        label_curPeer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(label_maxPeer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(label_idRoom))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(label_curPeer))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_idCreator)))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(label_idRoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(label_idCreator))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(label_curPeer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(label_maxPeer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     public void initRoom() {
        String roomname = room.GetID().substring(0, 10);
        label_idRoom.setText(roomname);
        label_idCreator.setText(new Integer(room.GetPeerCreator().GetID()).toString());
        label_curPeer.setText(new Integer(room.GetSumCurrentPeer()).toString());
        label_maxPeer.setText(room.GetMaxPeerJoin().toString());
        Table_ListPeer.getModel().setValueAt(new Integer(room.GetPeerCreator().GetID()), 0, 0);
    }
    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table_ListPeer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_curPeer;
    private javax.swing.JLabel label_idCreator;
    private javax.swing.JLabel label_idRoom;
    private javax.swing.JLabel label_maxPeer;
    // End of variables declaration//GEN-END:variables

}
