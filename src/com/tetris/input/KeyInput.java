package com.tetris.input;

import com.tetris.game.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles keyboard input for controlling the game.
 */
public class KeyInput extends KeyAdapter {
    private final Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER) {
            if (!game.isPlaying() || game.isGameOver()) {
                game.start();
            }
            return;
        }

        if (key == KeyEvent.VK_P) {
            game.pause();
            return;
        }

        if (game.isPlaying() && !game.isPaused() && !game.isGameOver()) {
            switch (key) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    game.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    game.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    game.moveDown();
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                case KeyEvent.VK_SPACE:
                    game.rotate();
                    break;
            }
        }
    }
}
