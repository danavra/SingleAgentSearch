
public class TilePuzzleHeuristic implements IHeuristic {


    @Override
    public double getHeuristic(IProblemState problemState) {
        int ans = 0;
        if (problemState instanceof TilePuzzleState) {
            int tileWidth, tileHeight, val;
            int[][] tile = ((TilePuzzleState) problemState)._tilePuzzle;
            tileWidth = tile[0].length;
            tileHeight = tile.length;
            for (int i = 0; i < tileHeight; i++) {
                for (int j = 0; j < tileWidth; j++) {
                    val = tile[i][j];
                    ans+= val*(Math.abs(j-((val-1)%tileWidth)) + Math.abs(i-((val-1)/tileHeight)));
                }
            }
        }
        return ans;
    }
}
