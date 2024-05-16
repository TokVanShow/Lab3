package Gui;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Lab4 {

    public static void main(String args[]) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_persistence_unit");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new GUI_Form(entityManagerFactory).setVisible(true);
            }
        });
    }
}
