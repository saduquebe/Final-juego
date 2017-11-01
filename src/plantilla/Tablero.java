/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantilla;

import Modelo.Enemigo;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import Modelo.Jugador;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
        private final int VELOCIDAD = 10;
    private final int ANGULO = 45;
        private double vx, vy,t;
    private Timer timer ;
    private Jugador jugador;
    private Enemigo enemigo;
    public int cont=0;
    
    
    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.jugador= new Jugador(300,500);
        this.enemigo= new Enemigo(1000,500);
        this.timer = new Timer(25, this);
        //Registrar evento del Teclado
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento
        addKeyListener(new EventosTeclado()); //Inner class que procesa los eventos del teclado
        this.timer.start(); //Inicio con el escenario
    }
        
    //Inner class Que captura los eventos del teclado
 private class EventosTeclado extends KeyAdapter {
        //Cuando se suelta una tecla
         @Override
        public void keyReleased(KeyEvent e) {
         
        }
        //Cuando se presiona una tecla
         @Override
        public void keyPressed(KeyEvent e) {
            //Toca validar los limites
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_LEFT) {
                     jugador.setX(jugador.getX()-5);
           }
           
           if (key == KeyEvent.VK_RIGHT) {
               jugador.setX(jugador.getX()+5);
           }
           if(key== KeyEvent.VK_SPACE){
        vx =  VELOCIDAD* Math.cos(Math.toRadians(ANGULO));
        vy =  VELOCIDAD * Math.sin(Math.toRadians(ANGULO));
        jugador.setX((int)(jugador.getX() + vx*t));
        jugador.setY((int)(jugador.getY()- vx*t - (0.5*-9.8)*t*t));
        t += 0.08;

           }
        }
    }
    
    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){

      super.paintComponent(g);
           Image personajefoto= loadImage("personaje.png");
      g.drawImage(personajefoto, this.jugador.getX(),this.jugador.getY(), this);
      Rectangle rpersonaje=getBounds(this.jugador.getX(),this.jugador.getY(),personajefoto.getWidth(this),personajefoto.getHeight(this));
           Image enemigofoto= loadImage("enemigo.png");   
      Rectangle renemigo=getBounds(this.enemigo.getX(),this.enemigo.getY(),enemigofoto.getWidth(this),enemigofoto.getHeight(this));
      if(!rpersonaje.intersects(renemigo)){
          g.drawImage(enemigofoto, this.enemigo.getX(),this.enemigo.getY(), this);
      }
      else if(rpersonaje.intersects(renemigo)){
          cont++;
          enemigofoto=loadImage("2D_GOBLIN__Die_00"+cont+".png");
          g.drawImage(enemigofoto, this.enemigo.getX(),this.enemigo.getY(), this);
          if(cont==7){
              /*              this.timer.stop();*/
          }
      }
      
    }
    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
    public Rectangle getBounds (int x, int y, int h, int w){
        Rectangle bordes =new Rectangle(x,y,h,w);
        return bordes;
    }
 
}
