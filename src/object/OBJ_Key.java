package object;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {
    String color;

    public OBJ_Key() {
        name = "key";
        collision = true;
        color = "amarillo";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void action(int i,Player player, GamePanel gp){
        player.hasKey++;
        gp.obj[i] = null;
        System.out.println("Agarraste una llave");
        System.out.println("Key: "+player.hasKey);
    }
}
