package Gui;

import ResponsibilityChain.ReactorInfoProcessor;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.tree.DefaultMutableTreeNode;
import reactors.ReactorType;

public class GUI_Form extends javax.swing.JFrame {

    private final ReactorInfoProcessor reactorInfoProcessor;

    public GUI_Form() {
        initComponents();
        reactorInfoProcessor = new ReactorInfoProcessor(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reactorTree = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loadButton.setText("Загрузить Файл ");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Выход");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        reactorTree.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                reactorTreeAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(reactorTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser(new File("user.dir"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON, YAML, XML Files", "json", "yml", "xml");
        fileChooser.setFileFilter(filter);

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            reactorInfoProcessor.importReactorInfoFromFile(selectedFile);
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void reactorTreeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_reactorTreeAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_reactorTreeAncestorAdded

    public void displayReactorInfoInTree(List<ReactorType> reactorTypes) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Reactor Types");

        for (ReactorType reactor : reactorTypes) {
            DefaultMutableTreeNode reactorNode = new DefaultMutableTreeNode(reactor.getName());
            reactorNode.add(new DefaultMutableTreeNode("Class Type: " + reactor.getClassType()));
            reactorNode.add(new DefaultMutableTreeNode("Burnup: " + reactor.getBurnup()));
            reactorNode.add(new DefaultMutableTreeNode("KPD: " + reactor.getKpd()));
            reactorNode.add(new DefaultMutableTreeNode("Enrichment: " + reactor.getEnrichment()));
            reactorNode.add(new DefaultMutableTreeNode("Thermal Capacity: " + reactor.getThermalCapacity()));
            reactorNode.add(new DefaultMutableTreeNode("Electrical Capacity: " + reactor.getElectricalCapacity()));
            reactorNode.add(new DefaultMutableTreeNode("Life Time: " + reactor.getLifeTime()));
            reactorNode.add(new DefaultMutableTreeNode("First Load: " + reactor.getFirstLoad()));
            reactorNode.add(new DefaultMutableTreeNode("Source: " + reactor.getSource()));

            root.add(reactorNode);
        }

        reactorTree.setModel(new javax.swing.tree.DefaultTreeModel(root));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JTree reactorTree;
    // End of variables declaration//GEN-END:variables
}
