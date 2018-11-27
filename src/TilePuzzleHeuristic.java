
public class TilePuzzleHeuristic implements IHeuristic {
    private int[][] tile;
    private int tileWidth, tileHeight;


    @Override
    public double getHeuristic(IProblemState problemState) {
        if (problemState instanceof TilePuzzleState) {
            tile = ((TilePuzzleState) problemState)._tilePuzzle;
            tileWidth = tile[0].length;
            tileHeight = tile.length;
            return oldHeuristic()+newHeuristic();
        }
        return 0;
    }

    private int oldHeuristic(){
        int ans = 0, val;
        for (int i = 0; i < tileHeight; i++) {
            for (int j = 0; j < tileWidth; j++) {
                val = tile[i][j];
                ans+= val*(Math.abs(j-((val-1)%tileWidth)) + Math.abs(i-((val-1)/tileHeight)));
            }
        }
        return ans;
    }

    private int newHeuristic(){
        int ans = 0;
        for (int i=0; i<tile.length; i++){
            for (int j=0; j<tile.length; j++){
                if (isInRow(i, j) && !isInColumn(i, j)){
                    ans += getLowerInRow(i, j);
                }
                else if (!isInRow(i, j) && isInColumn(i, j)){
                    ans += getLowerInCol(i, j);
                }
            }
        }
        return ans;
    }

    private int getLowerInCol(int i, int j) {
        for(int k=i; k<tileHeight; k++){
            if(tile[i][j]>tile[k][j]&&isInColumn(k, j))
                return tile[k][j]*2;
        }
        return 0;
    }

    private int getLowerInRow(int i, int j) {
        for(int k = j; k<tileWidth; k++){
            if(tile[i][j]>tile[i][k]&&isInRow(i, k))
                return tile[i][k]*2;
        }
        return 0;
    }

    private boolean isInColumn(int i, int j) {
        return j == ((tile[i][j]-1)%tileHeight);
    }

    private boolean isInRow(int i, int j) {
        return i == ((tile[i][j]-1)/tileWidth);
    }
}
