/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TrackerView.java
 *
 * Created on 23 Mar 13, 16:42:36
 */

package view;

import controller.TrackerController;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import model.Peer;
import model.Room;

import model.Tracker;

/**
 *
 * @author Krisna Dibyo Atmojo
 */
public class TrackerView extends javax.swing.JFrame {

    Tracker tracker;
    TrackerController trackercontroller;
    String temporary="";


    public TrackerView() {
        initComponents();
        tracker = new Tracker("localhost", 4444, 10, 10);
        trackercontroller = new TrackerController(this);
        trackercontroller.start();
        SetJumlahPeer();
        setJumlahRoom();
       
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Label_JumlahPeer = new javax.swing.JLabel();
        Label_JumlahRoom = new javax.swing.JLabel();
        Button_ListPeer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_ListRoom = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_ListPeer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel1.setText("Tracker Status");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel2.setText("List Peer Join");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15));
        jLabel3.setText("List Room");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setText("Jumlah Peer  : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("Jumlah Room :");

        Label_JumlahPeer.setFont(new java.awt.Font("Tahoma", 0, 14));
        Label_JumlahPeer.setText("jLabel6");

        Label_JumlahRoom.setFont(new java.awt.Font("Tahoma", 0, 14));
        Label_JumlahRoom.setText("jLabel7");

        Button_ListPeer.setText("Refresh");
        Button_ListPeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_ListPeerActionPerformed(evt);
            }
        });

        Table_ListRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Peer Creator", "ID Room"
            }
        ));
        Table_ListRoom.setDoubleBuffered(true);
        Table_ListRoom.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(Table_ListRoom);

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
                "ID Peer"
            }
        ));
        Table_ListPeer.setDoubleBuffered(true);
        Table_ListPeer.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(Table_ListPeer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(Button_ListPeer)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_JumlahPeer)))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Label_JumlahRoom))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Label_JumlahPeer)
                    .addComponent(jLabel5)
                    .addComponent(Label_JumlahRoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button_ListPeer)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_ListPeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_ListPeerActionPerformed
       ListPeer();
       ListRoom();
    }//GEN-LAST:event_Button_ListPeerActionPerformed

    /**
    * @param args the command line arguments
    */
    public Tracker GetTracker() {
        return tracker;
    }

//    public void AddPeerView(String Pid) {
//        temporary = temporary + " [" +Pid+"] ";
//        this.TextArea_ListPeer.setText(temporary);
//    }

    public void SetJumlahPeer() {
        Integer jumlahpeer = tracker.GetCurrentSumPeer();
        this.Label_JumlahPeer.setText(jumlahpeer.toString());
    }

    public void setJumlahRoom() {
        Integer jumlahroom = tracker.GetCurrentSumRoom();
        this.Label_JumlahRoom.setText(jumlahroom.toString());
    }

    public void ListPeer() {
        ArrayList<Peer> temp = tracker.GetAllPeers();
        String S = "";
        refreshTablePeer();
        int i = 0;
        for (Peer P : temp) {
            Integer id = P.GetID();
            Table_ListPeer.getModel().setValueAt(id, i, 0);
            i++;
        }
       
    }

    public void ListRoom() {
        ArrayList<Room> temp = tracker.GetAllRooms();
        int i = 0;
        refreshTableRoom();
       System.out.println("Jumlah Room = "+tracker.GetCurrentSumRoom());
        for (Room R : temp) {
            Table_ListRoom.getModel().setValueAt(R.GetPeerCreator().GetID(), i, 0);
            i++;
        }
        i = 0;
        for (Room R : temp) {
            Table_ListRoom.getModel().setValueAt(R.GetID(), i, 1);
            i++;
        }
    }

    public void refreshTableRoom() {
         for (int i = 0; i <= Table_ListRoom.getColumnCount()-1; i++) {
            for (int j = 0; j <= Table_ListRoom.getRowCount() -1; j ++) {
                Table_ListRoom.setValueAt(null, j, i);
            }
        }
    }
    public void refreshTablePeer() {
         for (int i = 0; i <= Table_ListPeer.getColumnCount()-1; i++) {
            for (int j = 0; j <= Table_ListPeer.getRowCount() -1; j ++) {
                Table_ListPeer.setValueAt(null, j, i);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrackerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_ListPeer;
    private javax.swing.JLabel Label_JumlahPeer;
    private javax.swing.JLabel Label_JumlahRoom;
    private javax.swing.JTable Table_ListPeer;
    private javax.swing.JTable Table_ListRoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
}
