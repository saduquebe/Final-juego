/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantilla;

import javax.swing.JFrame;

/**
 *
 * @author Santiago
 */
public class Plantilla extends JFrame {
    public Plantilla(){
        initUI();
    }
    public static void main(String[] args) {
     Plantilla ex= new Plantilla();
     ex.setVisible(true);
    }

    private void initUI() {
      add(new Tablero());
      setTitle("Juego");
      setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
}
