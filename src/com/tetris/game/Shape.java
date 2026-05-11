package com.tetris.game;

import java.awt.Color;

/**
 * Enum representing the 7 standard Tetromino shapes.
 */
public enum Shape {
    I(new Color(0, 255, 255), new int[][][] { // Cyan
        {{0,0,0,0}, {1,1,1,1}, {0,0,0,0}, {0,0,0,0}},
        {{0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}},
        {{0,0,0,0}, {0,0,0,0}, {1,1,1,1}, {0,0,0,0}},
        {{0,1,0,0}, {0,1,0,0}, {0,1,0,0}, {0,1,0,0}}
    }),
    J(new Color(0, 0, 255), new int[][][] { // Blue
        {{1,0,0}, {1,1,1}, {0,0,0}},
        {{0,1,1}, {0,1,0}, {0,1,0}},
        {{0,0,0}, {1,1,1}, {0,0,1}},
        {{0,1,0}, {0,1,0}, {1,1,0}}
    }),
    L(new Color(255, 165, 0), new int[][][] { // Orange
        {{0,0,1}, {1,1,1}, {0,0,0}},
        {{0,1,0}, {0,1,0}, {0,1,1}},
        {{0,0,0}, {1,1,1}, {1,0,0}},
        {{1,1,0}, {0,1,0}, {0,1,0}}
    }),
    O(new Color(255, 255, 0), new int[][][] { // Yellow
        {{1,1}, {1,1}} // O shape doesn't rotate, single state is fine or repeat 4 times
    }),
    S(new Color(0, 255, 0), new int[][][] { // Green
        {{0,1,1}, {1,1,0}, {0,0,0}},
        {{0,1,0}, {0,1,1}, {0,0,1}},
        {{0,0,0}, {0,1,1}, {1,1,0}},
        {{1,0,0}, {1,1,0}, {0,1,0}}
    }),
    T(new Color(128, 0, 128), new int[][][] { // Purple
        {{0,1,0}, {1,1,1}, {0,0,0}},
        {{0,1,0}, {0,1,1}, {0,1,0}},
        {{0,0,0}, {1,1,1}, {0,1,0}},
        {{0,1,0}, {1,1,0}, {0,1,0}}
    }),
    Z(new Color(255, 0, 0), new int[][][] { // Red
        {{1,1,0}, {0,1,1}, {0,0,0}},
        {{0,0,1}, {0,1,1}, {0,1,0}},
        {{0,0,0}, {1,1,0}, {0,1,1}},
        {{0,1,0}, {1,1,0}, {1,0,0}}
    });

    private final Color color;
    private final int[][][] rotations;

    Shape(Color color, int[][][] rotations) {
        this.color = color;
        // O shape optimization: if only 1 rotation provided, duplicate it 4 times
        if (rotations.length == 1) {
            this.rotations = new int[][][] { rotations[0], rotations[0], rotations[0], rotations[0] };
        } else {
            this.rotations = rotations;
        }
    }

    public Color getColor() {
        return color;
    }

    public int[][] getMatrix(int rotationIndex) {
        return rotations[rotationIndex % 4];
    }
    
    public int getSize() {
        return rotations[0].length;
    }

    public static Shape getRandomShape() {
        return values()[(int) (Math.random() * values().length)];
    }
}
