/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robotsimulator.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import robotsimulator.Simulator;
import robotsimulator.world.CellTheme;
import robotsimulator.world.CellType;
import robotsimulator.world.World;

/**
 *
 * @author garret
 */
public class MazeBuilderNB extends javax.swing.JPanel 
{
    
    Simulator sim;
    MainApplet main;
    Stage simStage;
    
    int fps;

    /**
     * Creates new form MazeBuilderNB
     */
    public MazeBuilderNB(int f, Simulator s, MainApplet m) 
    {
        fps = f;
        sim = s;
        main = m;
        initComponents();
    }
    
    public void createStage(Simulator s)
    {
        sim = s;
        simStage = main.createStagePanel(main.stageWidth, main.stageHeight, fps, sim);
        stageScrollPane.setViewportView(simStage);
    }
    
    public void preLaunchSetup()
    {
        horzSpinner.setValue(sim.getWorld().getGridWidth());
        vertSpinner.setValue(sim.getWorld().getGridHeight());
        horzSpinner.addChangeListener(c);
        vertSpinner.addChangeListener(c);
        populatePalette();
    }
    
    public void populatePalette()
    {
        JPanel palettePanel = new JPanel();
        for(CellType ctype : sim.getWorld().getCellTypes())
        {
            String cellTypeID = ctype.getID();
            if(sim.getWorld().getCellThemes().containsKey(cellTypeID))
            {
                CellTheme cellTheme = sim.getWorld().getCellThemes().get(cellTypeID);
                JButton cellBtn = new JButton(new ImageIcon(cellTheme.getImage()));
                cellBtn.setSize(100, 100);
                cellBtn.setName(cellTypeID);
                cellBtn.addActionListener(a);
                palettePanel.add(cellBtn);
            }
        }
        paletteScrollPane.setViewportView(palettePanel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stageScrollPane = new javax.swing.JScrollPane();
        paletteScrollPane = new javax.swing.JScrollPane();
        importBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();
        horzSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vertSpinner = new javax.swing.JSpinner();
        newMazeBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        stageScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        stageScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        paletteScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        paletteScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        importBtn.setText("Import");
        importBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBtnActionPerformed(evt);
            }
        });

        exportBtn.setText("Export");
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });

        horzSpinner.setName(""); // NOI18N

        jLabel1.setText("Horizontal");

        jLabel2.setText("Vertical");

        newMazeBtn.setText("New");
        newMazeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMazeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stageScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paletteScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(importBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newMazeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(horzSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vertSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(stageScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(paletteScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(importBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newMazeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(vertSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(horzSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void importBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBtnActionPerformed
        JComboBox combo = new JComboBox(main.getMazesFromWeb());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select a Maze:"));
        panel.add(combo);
        int result = JOptionPane.showConfirmDialog(null, panel, "Import Maze", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
            main.mazeId = (String) combo.getSelectedItem();
            if(sim != null)
            {
                main.mazeXml = sim.mainApp.getMazeData(main.mazeId);
                sim.importStage(main.mazeXml);
                populatePalette();
            }
        }
    }//GEN-LAST:event_importBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
	String mazeXml = sim.exportStage();	
        JOptionPane.showInputDialog(null, mazeXml, mazeXml);
    }//GEN-LAST:event_exportBtnActionPerformed

    private void newMazeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMazeBtnActionPerformed
        JComboBox combo = new JComboBox(main.getThemesFromWeb());
        JSpinner hs = new JSpinner();
        JSpinner vs = new JSpinner();
        hs.setValue(horzSpinner.getValue());
        vs.setValue(vertSpinner.getValue());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select a Theme:"));
        panel.add(combo);
        panel.add(new JLabel("Width:"));
        panel.add(hs);
        panel.add(new JLabel("Height:"));
        panel.add(vs);
        int result = JOptionPane.showConfirmDialog(null, panel, "New Maze", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
            sim.themeid = (String) combo.getSelectedItem();
            sim.getWorld().setTheme(sim.themeid);

            //TODO: Change palette buttons!
            sim.resetStage((int) hs.getValue() * sim.getWorld().getCellWidth(), (int) vs.getValue() * sim.getWorld().getCellHeight());
            horzSpinner.setValue(hs.getValue());
            vertSpinner.setValue(vs.getValue());
            populatePalette();
        }
    }//GEN-LAST:event_newMazeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportBtn;
    private javax.swing.JSpinner horzSpinner;
    private javax.swing.JButton importBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton newMazeBtn;
    private javax.swing.JScrollPane paletteScrollPane;
    private javax.swing.JScrollPane stageScrollPane;
    private javax.swing.JSpinner vertSpinner;
    // End of variables declaration//GEN-END:variables

    ActionListener a = new ActionListener() 
    {
        public void actionPerformed(ActionEvent a) 
        {
            JButton b = (JButton)a.getSource();
            ArrayList<CellType> types = sim.getWorld().getCellTypes();
            String id = b.getName();
            for(CellType ct : types)
            {
                if(ct.getID().equals(id))
                {
                    sim.getWorld().setCurrentCellType(ct);
                    break;
                }
            }
        }
    };
    
    ChangeListener c = new ChangeListener()
    {
        @Override
        public void stateChanged(ChangeEvent e) 
        {
            int mazeWidth = (Integer) horzSpinner.getValue();
            int mazeHeight = (Integer) vertSpinner.getValue();

            World simWorld = sim.getWorld();
            simWorld.resizeWorld(mazeWidth, mazeHeight);
            simStage.resizeStage(simWorld);
        }		
    };
}