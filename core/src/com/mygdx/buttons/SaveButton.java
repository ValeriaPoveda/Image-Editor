package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utility.ImageInputOutput;

public class SaveButton extends Button {
    public SaveButton(Vector2 scale, Vector2 position, Color recColor) {
        super(scale, position, recColor);
        ButtonText = "Save";
    }

    public void onClickUp(Vector2 clickPosition) {
        super.onClickUp(clickPosition );
        if(ImageInputOutput.Instance.ImageFolderLocation == null) {
            return;
        } try {
            ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.ImageFolderLocation + "\\output.bmp");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
