package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public int tileImagesCount = countTileImages();

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[tileImagesCount];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public int countTileImages() {
        String folderPath = "res/tiles";
        int count = 0;
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".png")); // Filtra los archivos PNG
            if (files != null) {
                count = files.length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row <gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getTileImage() {
        try {

            for (int i = 0; i <= (tileImagesCount - 1); i++) {
                tile[i] = new Tile();
                String formattedNumber = String.format("%03d", i);
                tile[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                        "/tiles/" + formattedNumber + ".png")));
            }

            int[] collisionTiles = {18, 16, 38, 33, 32, 35, 19, 20, 21, 22, 23, 24, 25, 26, 27, 39};
            for (int tileIndex : collisionTiles) {
                tile[tileIndex].collision = true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldRow < gp.maxWorldRow) {
            while (worldCol < gp.maxWorldCol) {
                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                }
                worldCol++;
            }
            worldCol = 0;
            worldRow++;
        }
    }
}
