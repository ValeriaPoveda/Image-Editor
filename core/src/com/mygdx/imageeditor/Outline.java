package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Outline {
    public Texture OutlineTex;
    
    public Outline(Vector2 recSize, Color color, int thicc) {
        Pixmap map = new Pixmap((int)recSize.x, (int)recSize.y, Format.RGBA8888);
        map.setColor(color);
        OutlineTex = new Texture(map);
        
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < thicc; y++) {
                map.drawPixel(x, y);
            }
        }
    }

}
