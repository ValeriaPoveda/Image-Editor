package com.mygdx.imageeditor;

import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.Utility.CollisionManager;
import com.mygdx.Utility.ImageInputOutput;
import com.mygdx.Utility.InputManager;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;

public class ImageEditor extends ApplicationAdapter {
    public static ImageEditor Instance;
    public Vector2 _screenSize;
    public Array <Rec2D> Rectangles = new Array<Rec2D>();
    private editWindow _editWindow;
    SpriteBatch batch;
    private BitmapFont _font;
   
  
    

//    @Override
//    public void create () {
//        Util.testIntToSignedBytes();
//        Instance = this;
//        InputManager inputManager = new InputManager();
//        Gdx.input.setInputProcessor(inputManager);
//        
//        new ImageInputOutput();
//        
//       
//        CollisionManager collisionManager = new CollisionManager();
//        batch = new SpriteBatch();
//        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//        Vector2 editWindowSize = new Vector2(500, _screenSize.y - 50);
//        _editWindow = new editWindow(editWindowSize, new Vector2(_screenSize.x - editWindowSize.x, 0));
//        Button b = new Button (new Vector2(50,50), Vector2.Zero, Color.GOLD);
//        
//        
//    }
    
    public void create() {
        Instance = this;
        initializeUtilityClasses();
        createGraphicalElements();
    }
    
    public void initializeUtilityClasses() {
        new CollisionManager();
        new ImageInputOutput();
        InputManager inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);
        _font = new BitmapFont();
    }
    
    public void createGraphicalElements() {
        batch = new SpriteBatch();
        _screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector2 editWindowSize = new Vector2(500, _screenSize.y - 25);
        _editWindow = new editWindow(editWindowSize, new Vector2(_screenSize.x - editWindowSize.x, 0));
        ColorButton yellow = new ColorButton (new Vector2(42,42), Vector2.Zero, Color.GOLD);
        ColorButton orange = new ColorButton (new Vector2(42,42), new Vector2(42,0), Color.ORANGE);
        
        ColorButton black = new ColorButton (new Vector2(42,42), new Vector2(0,42), Color.BLACK);
        ColorButton white = new ColorButton (new Vector2(42,42), new Vector2(42,42), Color.WHITE);
        ColorButton purple = new ColorButton (new Vector2(42,42), new Vector2(0,84), Color.PURPLE);
        ColorButton green = new ColorButton (new Vector2(42,42), new Vector2(42,84), Color.GREEN);
        ColorButton red = new ColorButton (new Vector2(42,42), new Vector2(0,126), Color.RED);
        ColorButton blue = new ColorButton (new Vector2(42,42), new Vector2(42,126), Color.BLUE);
        ColorButton pink = new ColorButton (new Vector2(42,42), new Vector2(0,168), Color.PINK);
        ColorButton brown = new ColorButton (new Vector2(42,42), new Vector2(42,168), Color.BROWN);
        ColorButton skyBlue = new ColorButton (new Vector2(42,42), new Vector2(0,210), Color.SKY);
        ColorButton forest = new ColorButton (new Vector2(42,42), new Vector2(42,210), Color.FOREST);
        
        new SaveButton(new Vector2(75,25), new Vector2(0, _screenSize.y - 25), Color.GRAY);
        new ExitButton(new Vector2(75,25), new Vector2(75,_screenSize.y - 25), Color.GRAY);
        new ClearDoodleButton (new Vector2 (75,25), new Vector2 (150, _screenSize.y - 25), Color.GRAY);
    }

    @Override
    // this is where outline doesn't work
    public void render () {
        ScreenUtils.clear(0f, 0f, 0f, 1);
        batch.begin();
        

        for (int i = 0; i < Rectangles.size; i++) {
            Rec2D rec = Rectangles.get(i);
            batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
        }
    
        for(Rec2D rec : Rectangles) {
           batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
           
        }     
        batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x, _editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
        
        
        for (int i = 0; i < Rectangles.size; i++) {
            Rec2D rec = Rectangles.get(i);
            if (rec instanceof Button) {
                Button button = (Button)rec;
                if (button.ButtonText == null) continue;
                _font.draw(batch, button.ButtonText, button.Position.x, button.Position.y+ button.Scale.y*0.75f, button.Scale.x, Align.center, false);
            }
            
        }
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
    
    public void filesImported(String[] filePaths) {
        Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
        if(map == null) return;
        _editWindow.RecTexture = new Texture(map);
    }
}
