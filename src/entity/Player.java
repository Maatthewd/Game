package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class    Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public int hasKey = 0;


    public Player(GamePanel gp , KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(4,12,28,28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/elfo_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() throws InterruptedException {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed){
                direction = "up";

            } else if(keyH.downPressed){
                direction = "down";

            } else if(keyH.leftPressed){
                direction = "left";

            } else {
                direction = "right";

            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if(!collisionOn){
                switch (direction){
                    case"up": worldY-=speed;
                        break;
                    case"down": worldY+=speed;
                        break;
                    case"left": worldX-=speed;
                        break;
                    case"right": worldX+=speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter> 12){
                if(spriteNum==1) {
                    spriteNum = 2;
                } else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }


        }

    }

    public void pickUpObject(int i) throws InterruptedException {
        if(i != 999) {
            String objectName = gp.obj[i].name;
            if(gp.obj[i].color=="amarillo"){
                Thread.sleep(2000);
            }
            switch (objectName) {
                case "key":
                    gp.obj[i].action(i,this, gp);
                    break;
                case "door":
                    gp.obj[i].action(i, this, gp);
                    break;
                case"chest":
                    gp.obj[i].action(i, this, gp);
            }


        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up":

                if (spriteNum ==1){
                    image = up1;
                }
                if(spriteNum ==2){
                    image = up2;
                }

                break;
            case "down":

                if (spriteNum ==1){
                    image = down1;
                }
                if(spriteNum ==2){
                    image = down2;
                }
                break;
            case "left":

                if (spriteNum ==1){
                    image = left1;
                }
                if(spriteNum ==2){
                    image = left2;
                }
                break;
            case "right":

                if (spriteNum ==1){
                    image = right1;
                }
                if(spriteNum ==2){
                    image = right2;
                }

                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
