package Plant;

import Projectile.Shooter;
import component.Tile;
import manager.*;
import manager.TileManager;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

public class Plant implements Shooter {
    private double plantHP;
    private int plantATK;
    private int frameCountIdleLimit;
    private int frameCountAttackLimit;
    public int getFrameCountIdle() {
        return frameCountIdle;
    }

    public void setFrameCountIdle(int frameCountIdle) {
        this.frameCountIdle = frameCountIdle;
    }

    public void setFrameCDIdle(int frameCDIdle) {
        this.frameCDIdle = frameCDIdle;
    }

    private int plantID;
    private int frameCountIdle = 0;
    private int frameCountAttack = 0;
    private int frameCountSun = 0;
    private int frameCountSunLimit = 600;
    private int frameCDIdle = 0;
    private int frameCDAttack = 0;

    public boolean isAlived() {
        return isAlived;
    }

    public void setAlived(boolean alived) {
        isAlived = alived;
    }

    private boolean isAlived = false;
    private Random random = new Random();

    public int getFrameCountAttack() {
        return frameCountAttack;
    }
    public void sunCreatedBySunFlower(sunManager sunManager){
        if(x > 0 && y > 0){
            frameCountSun++;
            if(frameCountSun == frameCountSunLimit){
                sunManager.sunCreatedBySunFlower(this);
                frameCountSunLimit = random.nextInt(600)+900;
                frameCountSun = 0;
            }
        }
    }

    public void updateFrameCountIdle(){
        frameCDIdle++;
        if(frameCDIdle%3 == 0){
            frameCountIdle++;
            if(frameCountIdle == frameCountIdleLimit){
                frameCountIdle = 0;
            }
        }
    }

    public int getFrameCountAttackLimit() {
        return frameCountAttackLimit;
    }

    public void updateFrameCountAttack(){
        frameCDAttack++;
        if(frameCDAttack%4 == 0){
            frameCountAttack++;
            if (frameCountAttack == frameCountAttackLimit) {
                frameCountAttack = 0;
            }
        }
    }
    public void setFrameCountAttack(int frameCountAttack) {
        this.frameCountAttack = frameCountAttack;
    }
    private int explodeCD;
    private boolean isDangered = false;

    public boolean isDangered() {
        return isDangered;
    }

    public void setDangered(boolean dangered) {
        isDangered = dangered;
    }

    public int getATK() {
        return plantATK;
    }

    @Override
    public int getID() {
        return plantID;
    }


    private int tileHold;
    private int x, y;
    private int width, height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Plant(double plantHP, int plantID, int ATK, int frameCountIdleLimit,int frameCountAttackLimit,int x, int y,int width, int height){
        this.frameCountIdleLimit = frameCountIdleLimit;
        this.frameCountAttackLimit = frameCountAttackLimit;
        this.plantHP = plantHP;
        this.plantID = plantID;
        this.plantATK = ATK;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setTileHold(int tileHold) {
        this.tileHold = tileHold;
    }

    public int getTileHold() {
        return tileHold;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public double getPlantHP() {
        return plantHP;
    }

    public void setPlantHP(double plantHP) {
        this.plantHP = plantHP;
    }
    public void removePlant(Plant plant, Iterator<Plant> iterator, TileManager tileManager,PlantManager plantManager){
        if(plant.getPlantHP() <= 0){
            for(Tile tile:tileManager.getTiles()){
                Rectangle r = tile.getBound();
                if(r.contains(plant.getX()+1,plant.getY()+1)){
                    tile.setOccupied(false);
                    tile.setPlanted(false);
                }
            }
            isAlived = false;
        }
    }
}