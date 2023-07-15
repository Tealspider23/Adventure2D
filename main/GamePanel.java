package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    
    
    
    //Screen Settings
    final int originalTilesize = 16;   //16X16 Tile
    final int scale =3;

    public final int tilesize = originalTilesize*scale; //48 X 48 Tile

    final int maxscreenCol =16;
    final int maxscreenRow = 12;
    final int screenwidth = tilesize*maxscreenCol;  // 768 pixels
    final int screenheight = tilesize*maxscreenRow;  // 576 pixels
     
    //FPS
    int fps=60;



    keyhandler keyH = new keyhandler();
    Player player = new Player(this,keyH);


   //Player's default position
   int playerX=100;
   int playerY=100;
   int playerspeed=4;

    Thread gamethread;


    public GamePanel(){
        this.setPreferredSize(new DimensionUIResource(screenwidth,screenheight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startgamethread(){
        gamethread = new Thread(this);
        gamethread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/fps; //0.01666 secs
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

       
        while(gamethread!=null){

            currentTime = System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=currentTime-lastTime;
            lastTime=currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;  
                drawCount++; //counts how many times the screen is drawn
            }
            if(timer>=1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }

        }
    }

    public void update(){
  
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;

        player.draw(g2);
        g2.dispose();

    }
}




    

