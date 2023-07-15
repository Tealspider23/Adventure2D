package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.keyhandler;


public class Player extends Entity {

    GamePanel gp;
    keyhandler keyH;

    public Player(GamePanel gp, keyhandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        setdefaultvalues();
        getPlayerimg();
      
    }
    public  void setdefaultvalues(){
        x=100;
        y=100;
        speed=4;
        direction="down";
    }
    public void getPlayerimg(){

        try {

            up1=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            up2=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            down1=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            down2=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            left1=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            left2=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            right1=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            right2=ImageIO.read(getClass().getResourceAsStream(" /player/Idle.png"));
            
        } catch (Exception e) {
         e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed==true){
            direction="up";
            y-=speed;
            
         }
         else if(keyH.downPressed==true){
            direction="down";
            y+=speed;
         }
         else if(keyH.leftPressed==true){
            direction="left";
            x-=speed;
         }
         else if(keyH.rightPressed==true){
            direction="right";
            x+=speed;
         }

    }

    public void draw(Graphics2D g2){
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x,y,gp.tilesize,gp.tilesize);

        BufferedImage img = null;
        switch(direction){
            case "up":
                img=up1;
                break;
            case "down":
                img=down1;
                break;
            case "left":
                img=left1;
                break;
            case "right":
                img=right1;
                break;
        }
        g2.drawImage(img,x,y,gp.tilesize,gp.tilesize,null);
        

    }


    
}




