package com.tetris.main;

import com.tetris.game.Game;
import com.tetris.gui.GameFrame;
import com.tetris.input.KeyInput;

import javax.swing.SwingUtilities;

/**
 * The main entry point for the Tetris game.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            GameFrame frame = new GameFrame(game);
            
            // Add input listener
            frame.getGamePanel().addKeyListener(new KeyInput(game));
            frame.setVisible(true);
            
            // Start the game loop in a separate thread
            startGameLoop(game, frame);
        });
    }
    
    private static void startGameLoop(Game game, GameFrame frame) {
        Thread loop = new Thread(() -> {
            while (true) {
                try {
                    if (game.isPlaying() && !game.isPaused() && !game.isGameOver()) {
                        game.update();
                    }
                    
                    // Repaint GUI
                    frame.getGamePanel().repaint();
                    frame.getSidePanel().repaint();
                    
                    // Sleep based on game level speed
                    Thread.sleep(game.getDelay());
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
        loop.setDaemon(true);
        loop.start();
    }
}
