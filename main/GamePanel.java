package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class GamePanel extends JPanel implements Runnable{
    
    
    
    //Screen Settings
    final int originalTilesize = 16;   //16X16 Tile
    final int scale =3;

    final int tilesize = originalTilesize*scale; //48 X 48 Tile

    final int maxscreenCol =16;
    final int maxscreenRow = 12;
    final int screenwidth = tilesize*maxscreenCol;  // 768 pixels
    final int screenheight = tilesize*maxscreenRow;  // 576 pixels
     
    //FPS
    int fps=60;



    keyhandler keyH = new keyhandler();


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
        double nextDrawtime =System.nanoTime() + drawInterval;

       
        while(gamethread!=null){

        update();

        repaint();


        try{
        double remainingTime = nextDrawtime - System.nanoTime();
        remainingTime = remainingTime/1000000;

        if(remainingTime<0){
            remainingTime=0;
        }
        Thread.sleep((long) remainingTime);

        nextDrawtime+=drawInterval;
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }



        }
        
    

    public void update(){
     if(keyH.upPressed==true){
        playerY -=playerspeed;
        
     }
     else if(keyH.downPressed==true){
        playerY+=playerspeed;
     }
     else if(keyH.leftPressed==true){
        playerX-=playerspeed;
     }
     else if(keyH.rightPressed==true){
        playerX+=playerspeed;
     }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 =(Graphics2D)g;

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX,playerY,tilesize,tilesize);
        g2.dispose();

    }
}




    

