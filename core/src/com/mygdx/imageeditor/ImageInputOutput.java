package com.mygdx.imageeditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class ImageInputOutput {
    public static ImageInputOutput Instance;
    public ImageInputOutput() {
        Instance = this;
    }

    public Pixmap loadImage(String filePath) {
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

        Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
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
}


