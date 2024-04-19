package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {

    public static int bytesToInt(byte[] bytes) {
        int result = 0;
        for(int i = 0; i < bytes.length; i++) {
            result += (int) bytes[i] << (8 * i);
        }
        return result;
    }

    public static int[] unsignBytes(byte[] bytes) {
        int[] ints = new int[bytes.length];
        for(int i = 0; i < bytes.length; i++) {
            if (bytes[i] >= 0) {
                ints[i] = bytes[i];
            } else {
                int distance = bytes[i] + 129;
                ints[i] = 127 + distance;
            }
        }
        return ints;
    }

    public static byte [] intToSignedBytes (int value) {
        byte[] result = new byte[4];
        result[0] = (byte) (value >> 24); // Leftmost byte
        result[1] = (byte) ((value >> 16) & 0xFF); // 2nd leftmost byte
        result[2] = (byte) ((value >> 8) & 0xFF); // 3rd leftmost byte
        result[3] = (byte) (value & 0xFF);
        return result;
    }

    public static void testIntToSignedBytes() {
        byte[] testResults = intToSignedBytes(543152314);
        int[] expectedResults = {32,95,-40,-70};
        for(int i = 0; i < testResults.length; i++) {
            if((int) testResults[i] != expectedResults[i])
                System.out.println("TEST FAILED! INDEX " + i + " IS " + testResults[i] + " EXPECTED: " + expectedResults[i]);
        }
    }

    public static Pixmap scalePixmap(Pixmap source, Vector2 desiredSize) {
        Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
        int targetWidth = target.getWidth();
        int targetHeight = target.getHeight();
        for(int targetX = 0; targetX < source.getWidth(); targetX ++) {
            for(int targetY = 0; targetY < source.getHeight(); targetY ++) {
                int sourceX = (int) Math.round((double) targetX / targetWidth * source.getWidth());
                int sourceY = (int) Math.round((double) targetY / targetHeight * source.getHeight());
                int color = source.getPixel(sourceX, sourceY);
                target.drawPixel(targetX, targetY, color);
            }

        }
        return target;

    }


}