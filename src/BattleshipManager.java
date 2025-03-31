import javafx.scene.control.Cell;

public class BattleshipManager
{
    private BattleshipBoard board;
    private int missCounter;
    private int strikeCounter;
    private int totalMissCounter;
    private int totalHitCounter;

    public BattleshipManager(){
        board = new BattleshipBoard();
        resetCounters();

    }

    public void resetCounters(){
        missCounter = 0;
        strikeCounter = 0;
        totalMissCounter = 0;
        totalHitCounter = 0;
    }

    public void handleCellClick(int row, int col){
        BattleshipCell cell = board.getCell(row, col);

        if (cell.isClicked()){
            return;
        }
        if(cell.isOccupied()){
            cell.markAsHit();
            totalHitCounter++;
            missCounter = 0;
            checkShipSunk(row, col);
        }
        else{
            cell.markAsMiss();
            missCounter++;
            totalMissCounter++;
            checkStrike();
        }
        checkGameOver();
    }

    private void checkShipSunk(int row, int col){
        for(BattleshipShip ship : board.getShips()){
            if(ship.getPositions().contains(board.getCell(row, col))){
                if(ship.checkIfSunk()){
                    displayPopup("Ship sunk!", "You sunk an enemy ship!");
                }
                break;
            }
        }
    }

    private void checkStrike() {
        if (missCounter >= 5) {
            strikeCounter++;
            missCounter = 0;

            if (strikeCounter >= 3) {
                boolean playAgain = BattleshipMessages.showConfirmationDialog
                        ("Game Over, You Lost", "Would you like to play again?");

                if (playAgain)
                {
                    resetGame();
                }
                else
                {
                    System.exit(0);
                }
            }
        }
    }


    private void checkGameOver(){
        boolean allShipsSunk = board.getShips().stream().allMatch(BattleshipShip::isSunk);
        if(allShipsSunk){
            boolean playAgain = BattleshipMessages.showConfirmationDialog
                    ("Game Over", "You Won! Play Again?");

            if (playAgain)
            {
                resetGame();
            }
            else
            {
                System.exit(0);
            }
        }
    }

    private void displayPopup(String title, String message){
        BattleshipMessages.showMessageDialog(title, message);
    }

    public void resetGame(){
        board = new BattleshipBoard();
        resetCounters();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                BattleshipCell cell = board.getCell(row, col);
                cell.resetClicked();
                cell.setOccupied(false);
                cell.setStatus("BLANK");
            }
        }
    }


    public int getMissCounter(){
        return missCounter;
    }

    public int getStrikeCounter(){
        return strikeCounter;
    }

    public int getTotalMissCounter(){
        return totalMissCounter;
    }

    public int getTotalHitCounter(){
        return totalHitCounter;
    }
    public BattleshipBoard getBoard() {
        return board;
    }
}
