package data;

import java.awt.image.BufferedImage;

import game.Animation;

public interface ImageCollectionEnum {
    public boolean isAnimated();
    public Animation getAnim();
    
    public BufferedImage getImg();
}
