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
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Pixmap rectangleMap;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    public Vector2 _screenSize;
    public static ImageEditor Instance;
  
    public ImageEditor () {
        Instance = this;
    }
    

    @Override
    public void create () {
        batch = new SpriteBatch();
        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
        
        
        Vector2 rectangleScale = new Vector2(100,100);
    
        button1 = new Button(rectangleScale,new Vector2(_screenSize.x / 2f - rectangleScale.x*2, _screenSize.y / 2f - rectangleScale.y / 4f),Color.RED);
        
        button2 = new Button(rectangleScale, new Vector2(_screenSize.x / 2f + rectangleScale.x, _screenSize.y / 2f - rectangleScale.y / 4f),Color.BLUE);
//        
//        button3 = new Button(rectangleScale,new Vector2(_screenSize.x / 2f - rectangleScale.x * 2, _screenSize.y / 2f - rectangleScale.y / 2f),Color.ORANGE);
//        
//        button4 = new Button(rectangleScale, new Vector2(_screenSize.x / 2f + rectangleScale.x, _screenSize.y / 2f - rectangleScale.y / 2f),Color.GREEN);
//        
//        button5 = new Button(rectangleScale, new Vector2(_screenSize.x / 2f + rectangleScale.x, _screenSize.y / 2f - rectangleScale.y / 2f),Color.WHITE);
//        
        
        CollisionManager collisionManager = new CollisionManager();
             
    }

    @Override
    public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
      //if(rectangle.position.x is greater than the width of the screen)
     // rectangle.velocity.x *= -1;
        batch.draw(button1.RecTexture, button1.Position.x, button1.Position.y);
        batch.draw(button2.RecTexture, button2.Position.x, button2.Position.y);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
