package com.mygdx.Utility;
import com.badlogic.gdx.Input.Keys;

import java.io.IOException;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.buttons.Button;
import com.mygdx.imageeditor.ImageEditor;

public class InputManager implements InputProcessor {
    public Array <Button> Buttons = new Array <Button>();
    public Array<IClickable> Clickables = new Array<IClickable>();
    public Array<IHoverable> Hoverables = new Array<IHoverable>();
    public static InputManager Instance;
    private IHoverable _currentlyHovered;
    private IClickable _currentlyClicked;
    private boolean _controlPressed;

    public InputManager() {
        Instance = this;
    }

    public boolean keyDown(int keycode) {
       if(_controlPressed && keycode == Keys.S) {
           
           if(ImageInputOutput.Instance.ImageFolderLocation == null) {
               return false;
           } else try {
               ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.ImageFolderLocation+"\\output.bmp");
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           
       }
        if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
        return false;
        }
    
    public boolean keyUp(int keycode) {
        if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
        return false;
        }
    
    public boolean keyTyped(char character) {
        return false;
        }
    
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY);
        IClickable collision = CollisionManager.Instance.getClicked(worldPosition);
   
        if(collision != null) {
            collision.onClickDown(worldPosition);
        }
        _currentlyClicked = collision;
        return true;
    }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (_currentlyClicked != null) { _currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
            _currentlyClicked = null;
        }
        // if(_currentlyHovered != null) _currentlyHovered.onClickUp();
        return false;
    }
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseMoved(screenX, screenY);
        if(_currentlyClicked != null)
            _currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY));
        return false;
    }
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 worldPosition = new Vector2(screenX, ImageEditor.Instance._screenSize.y - screenY);
        IHoverable collision = CollisionManager.Instance.getHovered(worldPosition);

        if (_currentlyHovered != collision && _currentlyHovered != null) _currentlyHovered.onHoverExit();
        if(collision != null) {
            collision.onHovered();
            _currentlyHovered = collision;
        } 
        if(collision != _currentlyHovered) {
            _currentlyClicked = null;
        }
        return true;
    }
    public boolean scrolled(float amountX, float amountY) {return false;}

}
