package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class editWindow extends Button implements IClickable{
    public Texture DoodleTexture;
    private Pixmap _doodleMap;
    private Vector2 _previousPaintPosition;

    public editWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
        super(scale, position, backgroundColor);
        _doodleMap = new Pixmap((int)scale.x, (int)scale.y, Format.RGBA8888);
        _doodleMap.setColor(Color.ORANGE);
        DoodleTexture = new Texture(_doodleMap);
        InputManager.Instance.Clickables.add(this);
    }

    private void paintAtPosition(Vector2 worldPosition) {
        _doodleMap.drawLine(0, 0, 300, 300);
        Vector2 paintPosition = new Vector2(worldPosition.x - Position.x,Scale.y - worldPosition.y);
        int startX = (int) _previousPaintPosition.x;
        int startY = (int)_previousPaintPosition.y;
        int endX = (int) paintPosition.x;
        int endY = (int) paintPosition.y;
        _doodleMap.drawLine(startX, startY, endX, endY);
        _doodleMap.drawLine(startX + 1, startY, endX + 1, endY);
        _doodleMap.drawLine(startX - 1, startY, endX - 1, endY);
        _doodleMap.drawLine(startX, startY + 1, endX, endY + 1);
        _doodleMap.drawLine(startX, startY - 1, endX, endY - 1);

        DoodleTexture = new Texture(_doodleMap);
        
        _previousPaintPosition = paintPosition;

    }

    public void onClickDown (Vector2 clickPosition) {
        if(_previousPaintPosition == null)
            _previousPaintPosition = new Vector2(clickPosition.x - Position.x,Scale.y - clickPosition.y);
        super.onClickDown(clickPosition);
        paintAtPosition(clickPosition);
    }

    public void onClickUp (Vector2 clickposition) {
        _previousPaintPosition = null;
    }
    

    public void onClickDragged(Vector2 clickPosition) {
        paintAtPosition(clickPosition);
    }

}

