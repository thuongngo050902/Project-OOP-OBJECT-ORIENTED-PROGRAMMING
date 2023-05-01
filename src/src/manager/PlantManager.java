package manager;

import component.Plant;
import component.Tile;
import zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlantManager {
    private Image[] plantImages;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private List<Plant> plantList = new ArrayList<>();
    private TileManager tileManager = new TileManager();
    private boolean selected = false;


    public void setIDhold(int IDhold) {
        this.IDhold = IDhold;
    }


    public void setHPhold(int HPhold) {
        this.HPhold = HPhold;
    }


    public void setATKhold(int ATKhold) {
        this.ATKhold = ATKhold;
    }

    private int IDhold, HPhold, ATKhold;
    public void countTile(){
        for(int i =0;i<tileManager.getTiles().length;i++){
            System.out.println(tileManager.getTiles()[i].getBound().getX());
            System.out.println(tileManager.getTiles()[i].getBound().getY());
        }
    }


    public PlantManager() {
        importImg();
    }

    public void initPlants(int plantID,int plantHP, int ATK) {
        plantList.add(new Plant(plantHP, plantID,ATK));
    }

    private void importImg() {
        plantImages = new Image[5];
        try {
            plantImages[0] = t.getImage(getClass().getResource("/plant/sunflower.png"));
            plantImages[1] = t.getImage(getClass().getResource("/plant/pea_shooter.png"));
            plantImages[2] = t.getImage(getClass().getResource("/plant/Wall-nut.png"));
            plantImages[3] = t.getImage(getClass().getResource("/plant/snow_pea_shooter.png"));
            plantImages[4] = t.getImage(getClass().getResource("/plant/cherry_bomb.png"));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error - importImage()");
        }
    }

    public void drawPlant(Graphics g){
        synchronized (plantList){
            for (Plant pl : plantList){
                if (pl.getPlantID() == 0){
                    g.drawImage(plantImages[0], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 1){
                    g.drawImage(plantImages[1], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 2){
                    g.drawImage(plantImages[2], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 3){
                    g.drawImage(plantImages[3], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                } else if (pl.getPlantID() == 4){
                    g.drawImage(plantImages[4], (int)tileManager.getTiles()[pl.getTileHold()].getBound().getX(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getY(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getWidth(), (int)tileManager.getTiles()[pl.getTileHold()].getBound().getHeight(), null);
                }
            }
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void mouse(int x, int y){
        if(selected){
            for (int i = 0; i < tileManager.getTiles().length; i++){
                if(!tileManager.getTiles()[i].isOccupied()){
                    Rectangle r = new Rectangle((int)tileManager.getTiles()[i].getBound().getX(), (int)tileManager.getTiles()[i].getBound().getY(), (int)tileManager.getTiles()[i].getBound().getWidth(), (int)tileManager.getTiles()[i].getBound().getHeight());
                    if (r.contains(x, y)){
                        tileManager.getTiles()[i].setOccupied(true);
                        initPlants(IDhold,HPhold,ATKhold);
                        for (int j = 0; j < plantList.size(); j++){
                            plantList.get(plantList.size() - 1).setTileHold(i);
                            if(!tileManager.getTiles()[i].isPlanted()){
                                plantList.get(plantList.size() - 1).setX(r.x);
                                plantList.get(plantList.size() - 1).setY(r.y);
                                plantList.get(plantList.size() - 1).setWidth(r.width);
                                plantList.get(plantList.size() - 1).setHeight(r.height);
                                tileManager.getTiles()[i].setPlanted(true);
                            }
                        }
                    }
                }
            }
        }
    }
    public void checkPlantDangered(Tile tile){
        Rectangle rPlant = tile.getBound();
        for(Plant plant:plantList){
            if(rPlant.contains(plant.getX(),plant.getY())){
                plant.setDangered(true);
            }
        }
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void alertPlant(TileManager tileManager, ZombieManager zombieManager){
        for(int i = 0;i<tileManager.getTiles().length;i++){
            Rectangle r = tileManager.getTiles()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                if(r.contains(zombie.X(),zombie.Y()+70)){
                    switch (i){
                        case 8:
                            for(int j = 0;j < 9;j++){
                                checkPlantDangered(tileManager.getTiles()[j]);
                            }
                            break;
                        case 17:
                            for(int j = 9;j < 18;j++){
                                checkPlantDangered(tileManager.getTiles()[j]);
                            }
                            break;
                        case 26:
                            for(int j = 18;j < 27;j++){
                                checkPlantDangered(tileManager.getTiles()[j]);
                            }
                            break;
                        case 35:
                            for(int j = 27;j < 36;j++){
                                checkPlantDangered(tileManager.getTiles()[j]);
                            }
                            break;
                        case 44:
                            for(int j = 36;j < 45;j++){
                                checkPlantDangered(tileManager.getTiles()[j]);
                            }
                            break;
                    }
                }
            }
        }
    }
    public void plantAttack(ProjectileManager projectileManager){
//        for (int i = 0;i<plantList.size();i++){
//            if(plantList.get(i).isDangered()){
//                projectileManager.projectileCreated(plantList.get(i));
//            }
//        }
        if(projectileManager.getRealTimeCounter() == 90){
            synchronized (plantList){
                for(Plant plant:plantList){
                    if(plant.isDangered()){
                        projectileManager.projectileCreated(plant);
                    }
                }
            }
            projectileManager.isResetTime();
        }
    }
}
