package object;

import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {
        name = "door";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }

    @Override
    public void action(int i, Player player, GamePanel gp) {
        if (player.hasKey > 0) {
            gp.obj[i] = null;
            player.hasKey--;
            System.out.println("You used a key!");
            System.out.println("Keys left: "+player.hasKey);
        }else {
            System.out.println("You need another key!");
        }
    }
}
