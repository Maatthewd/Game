package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class assetSetter {

    GamePanel gp;
    public assetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldY = 39 * gp.tileSize;
        gp.obj[0].worldX = 44 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 43 * gp.tileSize;

        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 31 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 34 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 31 * gp.tileSize;
        gp.obj[4].worldY = 5 * gp.tileSize;
    }
}
