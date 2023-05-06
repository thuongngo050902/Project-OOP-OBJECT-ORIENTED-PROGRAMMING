package manager;

import component.MyButtons;
import scenes.Playing;

import java.awt.*;

public class ButtonManager {
    private MyButtons bMenu, bQuit, bStart;
    private Playing playing;

    public ButtonManager(Playing playing) {
        this.playing = playing;
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButtons("Main menu", 0,0,150,70);
        bQuit = new MyButtons("End game", 0, 80, 150, 70);
        bStart = new MyButtons("Start", 0, 160, 150, 70);
    }

    public void drawButtons(Graphics g) {
        bMenu.draw(g);
        bQuit.draw(g);
        if(!playing.isStartWave()) {
            bStart.draw(g);
        }
    }

    public MyButtons getbMenu() {
        return bMenu;
    }
    public MyButtons getbQuit() {
        return bQuit;
    }
    public MyButtons getbStart() {return bStart;}
}
