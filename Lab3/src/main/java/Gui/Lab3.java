package Gui;


public class Lab3 {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Form().setVisible(true);
            }
        });
    }
}
