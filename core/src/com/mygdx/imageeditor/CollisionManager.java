package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
    public static CollisionManager Instance;
    
    
    public CollisionManager() {
        Instance = this;
    }


    public Button getCollision(Vector2 position) {
        Button iteratingButton;
        for (int i = 0; i < InputManager.Instance.Buttons.size; i++) {
            iteratingButton = InputManager.Instance.Buttons.get(i);
            if(position.x >= iteratingButton.Position.x && position.x <= iteratingButton.Position.x + iteratingButton.Scale.x) { 
                if(position.y >= iteratingButton.Position.y && position.y <= iteratingButton.Position.y + iteratingButton.Scale.y) { 
                    return iteratingButton;}

            }
        }
        return null;
    }
}



