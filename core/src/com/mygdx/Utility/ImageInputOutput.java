package com.mygdx.Utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Util;
import com.mygdx.imageeditor.editWindow;

public class ImageInputOutput {
    public String ImageFolderLocation ;
    public static ImageInputOutput Instance;
    private byte[] _fileHeader;
    private Pixmap _pixels;
    public ImageInputOutput() {
        Instance = this;
    }
    private String scrapeFolderLocation(String filePath) {
        StringBuilder builder = new StringBuilder(filePath);
        for(int i = filePath.length() - 1; i >= 0; i--) {
        if(filePath.charAt(i) != '\\') continue;
        return builder.substring(0,i);
        }
        return null;
    }
    
    public Pixmap loadImage(String filePath) {     
        ImageFolderLocation = scrapeFolderLocation(filePath);
        byte[] filebytes = Gdx.files.internal(filePath).readBytes();
        int[] fileIntData = Util.unsignBytes(filebytes);

        if(filebytes[0] != 'B' || filebytes[1] != 'M') {
            System.out.println(filePath + " is NOT a bitmap image");
            return null;
        }

        byte[] fileSize = {filebytes[2], filebytes[3], filebytes[4], filebytes[5]};
        byte[] start = {filebytes[10], filebytes[11], filebytes[12], filebytes[13]};
        byte[] widthBytes = {filebytes[18], filebytes[19], filebytes[20], filebytes[21]};
        byte[] heightBytes = {filebytes[22], filebytes[23], filebytes[24], filebytes[25]};
        byte[] bitsPerPixel = {filebytes[28], filebytes[29]};
        int startPoint = Util.bytesToInt(start);
        int width = Util.bytesToInt(widthBytes);
        int height = Util.bytesToInt(heightBytes);
        int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
        if(bytesPerPixel != 3) {
            System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); 
            return null;
        }

        _fileHeader = new byte [startPoint];

        for (int i = 0; i < startPoint; i++) {
            _fileHeader[i] = filebytes[i];
        }



        Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
        _pixels = pixels;
        int r,g,b;
        int x = 0, y = height;
        for(int i = startPoint; i < fileIntData.length - 3; i += 3) {
            b = fileIntData[i];
            g = fileIntData[i + 1];
            r = fileIntData[i + 2];

            float rf = r / 256f;
            float gf = g / 256f;
            float bf = b / 256f;

            pixels.setColor(rf, gf, bf, 1);
            pixels.drawPixel(x, y);
            x++;

            if (x >= width) {
                x = 0;
                y--;
            }
        }
        return pixels;
    }

    public void saveImage(String filePath) throws IOException {
        FileOutputStream output = new FileOutputStream(filePath);
        byte[] color;
        byte[] colorData = new byte [_pixels.getWidth()* _pixels.getHeight()*3];
        int colorIndex = 0;
        for(int y = _pixels.getHeight() - 1; y >= 0 ; y--) {
            for(int x = 0; x < _pixels.getWidth(); x++) {
                color = Util.intToSignedBytes(_pixels.getPixel(x, y));
                colorData[colorIndex] = color[2];
                colorData[colorIndex + 1] = color[1];
                colorData[colorIndex + 2] = color[0];
                colorIndex += 3;
                int tempColor = _pixels.getPixel(x,y); 
            }
        }

        Pixmap doodle = Util.scalePixmap(
                editWindow.Instance._doodleMap, new Vector2(_pixels.getWidth(), _pixels.getHeight())
                );
        colorIndex = 0;
        for(int y = doodle.getHeight() - 1; y >= 0; y--) {
            for(int x = 0; x < doodle.getWidth(); x++) {
                color = Util.intToSignedBytes(doodle.getPixel(x, y));
                if(color[3] != -1 ) {colorIndex += 3; continue;
                }
                colorData[colorIndex] = color[2];
                colorData[colorIndex + 1] = color[1];
                colorData[colorIndex + 2] = color[0];
                colorIndex += 3;
            }
        }
        
        
        output.write(_fileHeader);
        output.write(colorData);
        output.close();
    }
    
   
    
}


