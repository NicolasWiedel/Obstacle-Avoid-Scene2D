package com.obstacleavoid.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Pool;
import com.obstacleavoid.config.GameConfig;

public class ObstacleActor extends ActorBase implements Pool.Poolable {

    // == attributes ==
    private float ySpeed = GameConfig.MEDIUM_OBSTACLE_SPEED;
    private boolean hit;

    // == constructors ==
    public ObstacleActor() {
        setCollisionRadius(GameConfig.OBSTACLE_BOUNDS_RADIUS);
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    // == public methods ==
    @Override
    public void act(float delta) {
        super.act(delta);
        update();
    }

    public void update() {
        setY(getY() - ySpeed);
    }

    public void setYSpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public boolean isPlayerColliding(PlayerActor player) {
        Circle playerBounds = player.getCollisionShape();
        // check if playerBounds overlap obstacle bounds
        boolean overlaps = Intersector.overlaps(playerBounds, getCollisionShape());
        hit = overlaps;
        return overlaps;
    }

    public boolean isNotHit() {
        return !hit;
    }

    @Override
    public void reset() {
        setRegion(null);
        hit = false;
    }
}

