package Gui;

import ResponsibilityChain.ReactorInfoProcessor;
import dao.*;
import entity.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import javax.swing.tree.DefaultMutableTreeNode;
import reactors.ReactorType;
import service.*;

public class GUI_Form extends javax.swing.JFrame {

    private final ReactorInfoProcessor reactorInfoProcessor;
    private final EntityManagerFactory entityManagerFactory;
    private final Aggregator aggregator;
    private final TablePopulator tablePopulator;

    public GUI_Form(EntityManagerFactory entityManagerFactory) {
        initComponents();
        this.entityManagerFactory = entityManagerFactory;
        reactorInfoProcessor = new ReactorInfoProcessor(this);
        tablePopulator = new TablePopulator();

        EntityManager em = entityManagerFactory.createEntityManager();
        aggregator = new Aggregator(new CompanyDAO(em, 50),
                new CountryDAO(em, 50),
                new RegionDAO(em, 7),
                new UnitDAO(em, 100),
                new FuelConsumptionCalculator(new KIUMDAO(em, 5)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        loadButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reactorTree = new javax.swing.JTree();
        loadBD = new javax.swing.JButton();
        calculate = new javax.swing.JButton();
        countryRadio = new javax.swing.JRadioButton();
        regionRadio = new javax.swing.JRadioButton();
        ownersRadio = new javax.swing.JRadioButton();
        operatorsRadio = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        agregationTable = new javax.swing.JTable();

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

        loadBD.setText("Залить БД");
        loadBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBDActionPerformed(evt);
            }
        });

        calculate.setText("Калькуляции");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });

        countryRadio.setText("По странам");
        countryRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryRadioActionPerformed(evt);
            }
        });

        regionRadio.setText("По Регионам");

        ownersRadio.setText("По владельцам");

        operatorsRadio.setText("По Операторам");

        agregationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(agregationTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calculate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(countryRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operatorsRadio)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(regionRadio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ownersRadio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadBD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countryRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regionRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ownersRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(operatorsRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(182, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed

        try {
            File currentDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
            JFileChooser fileChooser = new JFileChooser(currentDirectory);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON, YAML, XML Files", "json", "yml", "xml");
            fileChooser.setFileFilter(filter);

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                reactorInfoProcessor.importReactorInfoFromFile(selectedFile);
            }
        } catch (URISyntaxException ex) {
            // Handle the exception, e.g., log it or show an error message
            ex.printStackTrace();
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void reactorTreeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_reactorTreeAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_reactorTreeAncestorAdded

    private void loadBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBDActionPerformed
        try {
            // Получаем директорию, где расположен jar файл
            File currentDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();

            // Создаем файл чузер с базовой директорией текущей директории
            JFileChooser fileChooser = new JFileChooser(currentDirectory);

            // Устанавливаем фильтр на Excel файлы
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
            fileChooser.setFileFilter(filter);

            // Устанавливаем режим выбора файлов
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            // Отображаем диалоговое окно выбора файла
            int returnValue = fileChooser.showOpenDialog(null);

            // Если файл выбран, то продолжаем
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Получаем выбранный файл
                File selectedFile = fileChooser.getSelectedFile();

                // Запускаем таймер перед началом загрузки данных
                long startTime = System.currentTimeMillis();

                // Создаем EntityManager
                EntityManager entityManager = entityManagerFactory.createEntityManager();

                // Устанавливаем размер пакета для загрузки данных
                int batchSize = 100;

                // Показываем первое диалоговое окно с вопросом о загрузке файла
                int confirmResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите загрузить данные из файла Excel?", "Подтверждение", JOptionPane.YES_NO_OPTION);

                // Если пользователь выбрал Да
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Показываем второе диалоговое окно с подтверждением перед началом загрузки
                    int finalConfirmResult = JOptionPane.showConfirmDialog(null, "Это займет минимум 8 минут?", "Подтверждение начала загрузки", JOptionPane.YES_NO_OPTION);

                    // Если пользователь выбрал Да
                    if (finalConfirmResult == JOptionPane.YES_OPTION) {
                        // Вызываем метод загрузки данных с передачей EntityManager и размера пакета
                        new ExcelDataLoader(entityManager, batchSize).loadExcelData(selectedFile);

                        // Закрываем EntityManager
                        entityManager.close();

                        // Останавливаем таймер после завершения загрузки данных
                        long endTime = System.currentTimeMillis();

                        // Вычисляем время выполнения загрузки данных
                        long executionTime = (endTime - startTime) / 60000;

                        // Выводим время выполнения загрузки в консоль
                        System.out.println("Время выполнения загрузки данных: " + executionTime + " миллисекунд");

                        // Выводим сообщение об успешной загрузке данных
                        JOptionPane.showMessageDialog(null, "Данные успешно загружены из файла Excel.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Загрузка данных отменена.", "Отмена", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Загрузка данных отменена.", "Отмена", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            // Обрабатываем исключение, например, выводим сообщение об ошибке
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке данных из файла Excel: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loadBDActionPerformed

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) agregationTable.getModel();

        if (countryRadio.isSelected()) {
            Map<Country, Double> countryConsumption = aggregator.getCountryConsumption();
            tablePopulator.populateTableByCountry(tableModel, countryConsumption);
        } else if (regionRadio.isSelected()) {
            Map<Region, Double> regionConsumption = aggregator.getRegionConsumption();
            tablePopulator.populateTableByRegion(tableModel, regionConsumption);
        } else if (ownersRadio.isSelected()) {
            Map<Company, Double> companyConsumption = aggregator.getCompanyConsumption();
            tablePopulator.populateTableByCompany(tableModel, companyConsumption);
        } else if (operatorsRadio.isSelected()) {
            // Add the aggregation logic for operators if required
            JOptionPane.showMessageDialog(this, "Operator aggregation not implemented.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Select an aggregation option.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_calculateActionPerformed

    private void countryRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryRadioActionPerformed

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
    private javax.swing.JTable agregationTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton calculate;
    private javax.swing.JRadioButton countryRadio;
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadBD;
    private javax.swing.JButton loadButton;
    private javax.swing.JRadioButton operatorsRadio;
    private javax.swing.JRadioButton ownersRadio;
    private javax.swing.JTree reactorTree;
    private javax.swing.JRadioButton regionRadio;
    // End of variables declaration//GEN-END:variables
}
