import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;

public class BattleshipShip
{
    private int size;
    private List<BattleshipCell> positions;
    private boolean isSunk;

    public BattleshipShip(int size){
        this.size = size;
        positions = new ArrayList<>();
        isSunk = false;
    }

    public void addPosition(BattleshipCell cell){
        positions.add(cell);
        cell.setOccupied(true);
    }

    public boolean checkIfSunk(){
        for(BattleshipCell cell : positions){
            if(!cell.isClicked()){
                return false;
            }
        }
        isSunk = true;
        return true;
    }

    public int getSize(){
        return size;
    }

    public List<BattleshipCell> getPositions(){
        return positions;
    }

    public boolean isSunk(){
        return isSunk;
    }


}
