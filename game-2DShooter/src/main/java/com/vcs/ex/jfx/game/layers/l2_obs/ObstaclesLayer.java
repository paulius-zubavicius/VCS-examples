package com.vcs.ex.jfx.game.layers.l2_obs;

import com.vcs.ex.jfx.game.layers.GraphicalLayer;
import com.vcs.ex.jfx.game.resources.ImgRs;
import com.vcs.ex.jfx.game.shotimpact.ShotImpact;
import com.vcs.ex.jfx.game.utils.ImgLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObstaclesLayer implements GraphicalLayer {

    private static final double WIND_SPEED = 0.1;
    private static final double SEC = 1000000000.0;
    private static final long FX_SPEED = 3000000L;

    private Image cloud;
    private GraphicsContext gc;
    private long startNanoTime;
    private Random rnd;
    private Image[][] bulletSmoke = null;

    private double w;
    private double h;
    private double centerOffsetX;
    private double centerOffsetY;

    private List<ShootSmokePos> shootSmokePosPool = new ArrayList<>();

    private double x, y;


    @Override
    public void init(long startNanoTime, GraphicsContext gc) {
        this.gc = gc;
        this.startNanoTime = startNanoTime;
        this.cloud = ImgLoader.img(ImgRs.CLOUD2);

        bulletSmoke = new Image[4][];

        bulletSmoke[0] = ImgLoader.sprite(ImgRs.SMOKE1_1);
        bulletSmoke[1] = ImgLoader.sprite(ImgRs.SMOKE2_1);
        bulletSmoke[2] = ImgLoader.sprite(ImgRs.SMOKE2_2);
        bulletSmoke[3] = ImgLoader.sprite(ImgRs.SMOKE3_1);

        w = bulletSmoke[0][0].getWidth();
        h = bulletSmoke[0][0].getHeight();

        centerOffsetX = w / 2;
        centerOffsetY = h / 2;

        rnd = new Random(startNanoTime);
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.drawImage(cloud, 0, 0);
    }

    @Override
    public void updateTimer(long currentNanoTime) {

        // Clear the smoke effects before repaint & remove old ones
        shootSmokePosPool.removeIf(this::isNoNextSprite);
        shootSmokePosPool.stream()
                .filter(fxPos -> isItTimeForNextSprite(fxPos, currentNanoTime))
                .forEach(fxPos -> fxPos.spriteIndex++);
        shootSmokePosPool.forEach(fxPos -> fxPos.lastTime = currentNanoTime);

//        System.out.println(shootSmokePosPool.size());


        x += WIND_SPEED;
        y = 128 * Math.sin(((currentNanoTime - startNanoTime) / SEC) * WIND_SPEED);
        if (x > gc.getCanvas().getWidth()) {
            x = -cloud.getWidth();
        }

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        gc.drawImage(cloud, x, y);
        shootSmokePosPool.forEach(pos -> gc.drawImage(bulletSmoke[pos.spriteFxIndex][pos.spriteIndex], pos.x - centerOffsetX - pos.cloudOffsetX + x,
                pos.y - centerOffsetY - pos.cloudOffsetY + y, w, h));


    }

    private boolean isItTimeForNextSprite(ShootSmokePos fxPos, long currentNanoTime) {
        return currentNanoTime - fxPos.lastTime > FX_SPEED;
    }

    private boolean isNoNextSprite(ShootSmokePos fxPos) {
        return fxPos.spriteIndex >= bulletSmoke[fxPos.spriteFxIndex].length - 1;
    }


    @Override
    public ShotImpact shotImpact(double x, double y, ShotImpact impact) {

        shootSmokePosPool.add(new ShootSmokePos(x, y, this.x, this.y, rnd.nextInt(bulletSmoke.length)));

        return impact;
    }

    private class ShootSmokePos {

        private double x;
        private double y;

        private double cloudOffsetX;
        private double cloudOffsetY;

        private int spriteIndex;
        private int spriteFxIndex;
        private long lastTime;

        public ShootSmokePos(double x, double y, double cloudOffsetX, double cloudOffsetY, int spriteFxIndex) {
            this.x = x;
            this.y = y;
            this.cloudOffsetX = cloudOffsetX;
            this.cloudOffsetY = cloudOffsetY;
            this.spriteIndex = -1;
            this.spriteFxIndex = spriteFxIndex;
            this.lastTime = System.currentTimeMillis();
        }

    }

}
