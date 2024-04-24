package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utility.IClickable;
import com.mygdx.Utility.IHoverable;
import com.mygdx.Utility.InputManager;
import com.mygdx.imageeditor.Rec2D;

public class Button extends Rec2D implements IClickable, IHoverable{
    public String ButtonText;
    protected Color _startColor;
    private Color _hoveredColor;
    public enum ButtonState{Clicked, Hovered,None};
    private ButtonState _State;
    
    
    public Button (Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
        _startColor = _recColor;
        _hoveredColor = new Color (_startColor.r / 2f, _startColor.g  / 2f, _startColor.b / 2f, 1f);
        InputManager.Instance.Clickables.add(this);
        InputManager.Instance.Hoverables.add(this);
        _State = ButtonState.None;
        
    }
    
    public void onHovered() {
        if (_State == ButtonState.Clicked) return;
        if (_State == ButtonState.Hovered) return;
        _State = ButtonState.Hovered;
        _recColor = _hoveredColor;
        generateTexture();
      
    }
    
    public void onHoverExit() {
        _State = ButtonState.None;
        _recColor = _startColor;
        generateTexture();
    }
    
    public void onClickDown(Vector2 clickPosition) {
        if (_State == ButtonState.Clicked) return;
        _State = ButtonState.Clicked;
        _recColor = new Color (_startColor.r/4f, _startColor.g/4f, _startColor.b/4f, 1);
        generateTexture();
    }
    
    
    public void onClickUp(Vector2 clickPosition) {
        _State = ButtonState.Hovered;
        _recColor = _hoveredColor;
        generateTexture();
    }
    @Override
    public void onClickDragged(Vector2 clickPosition) {
    // TODO Auto-generated method stub
    }

    

    

}
