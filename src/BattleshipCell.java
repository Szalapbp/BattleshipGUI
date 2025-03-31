public class BattleshipCell
{
    private int row;
    private int col;
    private boolean isOccupied;
    private boolean isClicked;
    private String status;

    public BattleshipCell(int row, int col){
        this.row = row;
        this.col = col;
        this.isOccupied = false;
        this.isClicked = false;
        this.status = "Blank";
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setOccupied(boolean occupied){
        this.isOccupied = occupied;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void resetClicked() {
        this.isClicked = false;
    }


    public boolean isClicked(){
        return isClicked;
    }

    public void markAsClicked(){
        this.isClicked = true;
    }

    public String getStatus(){
        return status;
    }

    public void markAsHit(){
        if (!isClicked){
            this.status = "HIT";
            markAsClicked();
        }
    }

    public void markAsMiss(){
        if (!isClicked){
            this.status = "MISS";
            markAsClicked();
        }
    }

}
