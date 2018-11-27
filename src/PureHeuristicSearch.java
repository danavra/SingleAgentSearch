import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PureHeuristicSearch extends ASearch {
    // Define lists here ...
    private PriorityQueue<ASearchNode> openList;
    private ArrayList<ASearchNode> closeList;

    @Override
    public String getSolverName() {
        return "PHS";
    }

    @Override
    public ASearchNode createSearchRoot(IProblemState problemState) {
        ASearchNode newNode = new HeuristicSearchNode(problemState);
        return newNode;
    }

    @Override
    public void initLists() {
        openList = new PriorityQueue<>((ASearchNode o1, ASearchNode o2) -> (int) (o1.getH() - o2.getH()));
        closeList = new ArrayList<>();
    }

    @Override
    public ASearchNode getOpen(ASearchNode node) {
        Iterator<ASearchNode> it = openList.iterator();
        ASearchNode ans;
        while (it.hasNext()) {
            ans = it.next();
            if (ans.equals(node)) {
                return ans;
            }
        }
        return null;
    }

    @Override
    public boolean isOpen(ASearchNode node) {
        return openList.contains(node);
    }

    @Override
    public boolean isClosed(ASearchNode node) {
        return closeList.contains(node);
    }


    @Override
    public void addToOpen(ASearchNode node) {
        if (openList.contains(node))
            openList.remove(node);
        openList.add(node);
    }

    @Override
    public void addToClosed(ASearchNode node) {
        closeList.add(node);
    }

    @Override
    public int openSize() {
        return openList.size();
    }

    @Override
    public ASearchNode getBest() {
        return openList.poll();
    }

}