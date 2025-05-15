import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TicTacToe {
    // Colors
    private final Color BACKGROUND_COLOR = new Color(40, 44, 52);
    private final Color BUTTON_COLOR = new Color(54, 60, 73);
    private final Color HOVER_COLOR = new Color(66, 72, 85);
    private final Color TEXT_COLOR = new Color(220, 223, 228);
    private final Color WINNER_COLOR = new Color(152, 195, 121);
    private final Color TIE_COLOR = new Color(229, 192, 123);
    
    int boardWidth = 600;
    int boardHeight = 700;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JButton resetButton = new JButton("New Game");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(BACKGROUND_COLOR);

        // Setup text label
        textLabel.setBackground(BUTTON_COLOR);
        textLabel.setForeground(TEXT_COLOR);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        textLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // Setup text panel
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        textPanel.setBackground(BACKGROUND_COLOR);
        frame.add(textPanel, BorderLayout.NORTH);

        // Setup board panel
        boardPanel.setLayout(new GridLayout(3, 3, 10, 10));
        boardPanel.setBackground(BACKGROUND_COLOR);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        frame.add(boardPanel, BorderLayout.CENTER);

        // Setup control panel
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(BACKGROUND_COLOR);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        // Setup reset button
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resetButton.setForeground(TEXT_COLOR);
        resetButton.setBackground(BUTTON_COLOR);
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(HOVER_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 20, 5, 20)
        ));
        resetButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                resetButton.setBackground(HOVER_COLOR);
            }
            public void mouseExited(MouseEvent e) {
                resetButton.setBackground(BUTTON_COLOR);
            }
        });
        resetButton.addActionListener(e -> resetGame());
        controlPanel.add(resetButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Setup game board
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(BUTTON_COLOR);
                tile.setForeground(TEXT_COLOR);
                tile.setFont(new Font("Segoe UI", Font.BOLD, 80));
                tile.setFocusPainted(false);
                tile.setBorder(new LineBorder(HOVER_COLOR, 2));

                tile.addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        if (!gameOver && ((JButton)e.getSource()).getText().isEmpty()) {
                            ((JButton)e.getSource()).setBackground(HOVER_COLOR);
                        }
                    }
                    public void mouseExited(MouseEvent e) {
                        if (!gameOver && ((JButton)e.getSource()).getText().isEmpty()) {
                            ((JButton)e.getSource()).setBackground(BUTTON_COLOR);
                        }
                    }
                });

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
        
        // Set initial turn text
        textLabel.setText(currentPlayer + "'s turn");
    }
    
    void resetGame() {
        gameOver = false;
        turns = 0;
        currentPlayer = playerX;
        textLabel.setText(currentPlayer + "'s turn");
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(BUTTON_COLOR);
                board[r][c].setForeground(TEXT_COLOR);
                board[r][c].setBorder(new LineBorder(HOVER_COLOR, 2));
            }
        }
    }

    void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().isEmpty()) continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        //vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().isEmpty()) continue;
            
            if (board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonally
        if (board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText() &&
            !board[0][0].getText().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //anti-diagonally
        if (board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText() &&
            !board[0][2].getText().isEmpty()) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(WINNER_COLOR);
        tile.setBackground(BUTTON_COLOR);
        tile.setBorder(new LineBorder(WINNER_COLOR, 2));
        textLabel.setText(currentPlayer + " wins!");
    }

    void setTie(JButton tile) {
        tile.setForeground(TIE_COLOR);
        tile.setBackground(BUTTON_COLOR);
        tile.setBorder(new LineBorder(TIE_COLOR, 2));
        textLabel.setText("It's a tie!");
    }
}
