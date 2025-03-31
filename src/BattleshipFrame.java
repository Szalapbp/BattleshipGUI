import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipFrame  extends JFrame
{
    private JButton[][] gridBtns;
    private JLabel missCounterLbl, strikeCounterLbl, totalMissCounterLbl, totalHitCounterLbl;
    private JButton playAgainBtn, quitBtn;
    private BattleshipManager battleshipManager;

    public BattleshipFrame(){
        battleshipManager = new BattleshipManager();

        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel gameGrid = new JPanel();
        gameGrid.setLayout(new GridLayout(10, 10));
        gridBtns = new JButton[10][10];
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                JButton button = new JButton("~");
                button.setActionCommand(row + "," + col);
                button.addActionListener(new ButtonClickListener());
                gridBtns[row][col] = button;
                gameGrid.add(button);
            }
        }

        add(gameGrid, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new GridLayout(2,2));
        missCounterLbl = new JLabel("Miss Counter: 0");
        strikeCounterLbl = new JLabel("Strike Counter: 0");
        totalMissCounterLbl = new JLabel("Total Miss Counter: 0");
        totalHitCounterLbl = new JLabel("Total Hits Counter: 0");
        statusPanel.add(missCounterLbl);
        statusPanel.add(strikeCounterLbl);
        statusPanel.add(totalMissCounterLbl);
        statusPanel.add(totalHitCounterLbl);
        add(statusPanel, BorderLayout.NORTH);

        JPanel controlPanel = new JPanel();
        playAgainBtn = new JButton("Play Again");
        quitBtn = new JButton("Quit");

        playAgainBtn.addActionListener(e -> restartGame());
        quitBtn.addActionListener(e -> quitGame());

        controlPanel.add(playAgainBtn);
        controlPanel.add(quitBtn);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] coordinates = e.getActionCommand().split(",");
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);

            battleshipManager.handleCellClick(row, col);

            updateUI(row, col);
        }
    }

    private void updateUI(int row, int col){
        BattleshipCell cell = battleshipManager.getBoard().getCell(row, col);

        if(cell.getStatus().equals("HIT")){
            gridBtns[row][col].setText("X");
            gridBtns[row][col].setBackground(Color.RED);

        }
        else if(cell.getStatus().equals("Miss")){
            gridBtns[row][col].setText("M");
            gridBtns[row][col].setBackground(Color.YELLOW);
        }

        gridBtns[row][col].setEnabled(false);

        missCounterLbl.setText("Miss Counter: " + battleshipManager.getMissCounter());
        strikeCounterLbl.setText("Strike Counter: " + battleshipManager.getStrikeCounter());
        totalMissCounterLbl.setText("Total Miss Counter: " + battleshipManager.getTotalMissCounter());
        totalHitCounterLbl.setText("Total Hit Counter: " + battleshipManager.getTotalHitCounter());
    }

    private void restartGame(){
        boolean confirm = BattleshipMessages.showConfirmationDialog("Restart Game", "Are you sure you'd like to Restart");
        if(confirm){
            battleshipManager.resetGame();
            resetUI();
        }

    }
    private void quitGame(){
        boolean confirm = BattleshipMessages.showConfirmationDialog("Quit Game", "Are you sure you'd like to Quit");
        if(confirm){
            System.exit(0);
        }
    }

    private void resetUI() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                gridBtns[row][col].setText("~");
                gridBtns[row][col].setBackground(null);
                gridBtns[row][col].setEnabled(true);
            }
        }
    }

}
