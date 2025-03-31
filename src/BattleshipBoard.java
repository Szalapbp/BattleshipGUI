import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleshipBoard
{
    private BattleshipCell[][] cells;
    private List<BattleshipShip> ships;

    public BattleshipBoard() {
        cells = new BattleshipCell[10][10];
        ships = new ArrayList<>();
        initializeBoard();
        placeShips();
    }

    private void initializeBoard() {
        for(int row = 0; row < 10; row++) {
            for(int col = 0; col < 10; col++) {
                cells[row][col] = new BattleshipCell(row, col);
            }
        }
    }

    private void placeShips(){
        int[] shipSizes = {5,4,3,3,2};
        Random random = new Random();

        for(int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(10);
                int col = random.nextInt(10);

                if (canPlaceShip(size, row, col, horizontal)) {
                    placeShip(size, row, col, horizontal);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int size, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (cells[row][col + i].isOccupied()) return false;
            }
        } else {
            if (row + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (cells[row + i][col].isOccupied()) return false;
            }
        }
        return true;
    }


    private void placeShip(int size, int row, int col, boolean horizontal)
    {
        BattleshipShip ship = new BattleshipShip(size);
        if (horizontal) {
            for(int i = 0; i < size; i++) {
                BattleshipCell cell = cells[row][col + i];
                cell.setOccupied(true);
                ship.addPosition(cell);
            }
        }
        else{
            for(int i = 0; i < size; i++) {
                BattleshipCell cell = cells[row + i][col];
                cell.setOccupied(true);
                ship.addPosition(cell);
            }
        }
        ships.add(ship);
    }

    public BattleshipCell getCell(int row, int col){
        return cells[row][col];
    }
    public List<BattleshipShip> getShips() {
        return ships;
    }

}
