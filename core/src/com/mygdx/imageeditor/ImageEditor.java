package com.mygdx.imageeditor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
    public static ImageEditor Instance;
    public Vector2 _screenSize;
    public Array <Rec2D> Rectangles = new Array<Rec2D>();
    private editWindow _editWindow;
    SpriteBatch batch;
   
  
    

    @Override
    public void create () {
        Instance = this;
        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
        
        batch = new SpriteBatch();
        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector2 editWindowSize = new Vector2(500, _screenSize.y - 50);
        _editWindow = new editWindow(editWindowSize, new Vector2(_screenSize.x - editWindowSize.x, 0), Color.GRAY);
        Button b = new Button (new Vector2(50,50), Vector2.Zero, Color.GOLD);
        CollisionManager collisionManager = new CollisionManager();
             
    }

    @Override
    public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
        for(Rec2D rec : Rectangles) {
           batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
        }
        batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
