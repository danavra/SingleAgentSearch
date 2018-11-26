
public class TilePuzzleHeuristic implements IHeuristic {

    @Override
    public double getHeuristic(IProblemState problemState) {
        double ans = 0;
        if (problemState instanceof TilePuzzleState) {
            int h, optCol, optRow;
            int[][] tile = ((TilePuzzleState) problemState)._tilePuzzle;
            for (int i = 0; i < tile.length; i++) {
                for (int j = 0; j < tile[i].length; j++) {
                    optCol = (tile[i][j] + 2) % tile[i].length;
                    optRow = (tile[i][j] - 1) % tile.length;
                    h = (Math.abs(j - optCol) + Math.abs(i - optRow)) * tile[i][j];
                    ans += h;
                }
            }
        }
        return ans;
    }

}
