package com.tetris.gui;

import com.tetris.game.Game;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * The main window frame containing the game panels.
 */
public class GameFrame extends JFrame {
    private final GamePanel gamePanel;
    private final SidePanel sidePanel;

    public GameFrame(Game game) {
        setTitle("Java Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        gamePanel = new GamePanel(game);
        sidePanel = new SidePanel(game);

        add(gamePanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null); // Center on screen
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }
}
