
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

    private int oldHeuristic(){//Manhattan Distance times the value of the tile
        int ans = 0, val;
        for (int i = 0; i < tileHeight; i++) {
            for (int j = 0; j < tileWidth; j++) {
                val = tile[i][j];
                ans+= val*(Math.abs(j-((val-1)%tileWidth)) + Math.abs(i-((val-1)/tileHeight)));
            }
        }
        return ans;
    }

    private int newHeuristic(){//if 2 are in the same row or column but in different order, it counts the swap
        int ans = 0;
        //check the whole tilePuzzle
        for (int i=0; i<tile.length; i++){
            for (int j=0; j<tile.length; j++){
                if ((i == ((tile[i][j]-1)/tileWidth)) && !(j == ((tile[i][j]-1)%tileHeight)))//if a tile is in its row but not in its column
                    ans += 2* getNextLowerInRow(i, j);
                else if(!(i == ((tile[i][j]-1)/tileWidth)) && (j == ((tile[i][j]-1)%tileHeight)))//if a tile is in its column but not in its row
                    ans += 2* getNextLowerInCol(i, j);
            }
        }
        return ans;
    }

    private int getNextLowerInCol(int i, int j) {
        for(int k=i; k<tileHeight; k++){
            if(tile[i][j]>tile[k][j] && (j==((tile[k][j]-1)%tileHeight)))
                return tile[k][j];
        }
        return 0;
    }

    private int getNextLowerInRow(int i, int j) {
        for(int k = j; k<tileWidth; k++){
            if(tile[i][j]>tile[i][k] && (i==((tile[i][k]-1)/tileWidth)))
                return tile[i][k];
        }
        return 0;
    }
}
