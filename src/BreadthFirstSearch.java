import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch  extends ASearch
{
	// Define lists here ...
	private Queue<ASearchNode> openList, closeList;


	@Override
	public String getSolverName()
	{
		return "BFS";
	}

	@Override
	public ASearchNode createSearchRoot(IProblemState problemState){
		ASearchNode newNode = new BlindSearchNode(problemState);
		return newNode;
	}

	@Override
	public void initLists(){
		openList = new LinkedList<>();
		closeList = new LinkedList<>();
	}

	@Override
	public ASearchNode getOpen(ASearchNode node){
		for (ASearchNode open: openList){
			if ((open.equals(node))) {
				return open;
			}
		}
		return null;
	}

	@Override
	public boolean isOpen(ASearchNode node){
		return openList.contains(node);
	}

	@Override
	public boolean isClosed(ASearchNode node){
		return closeList.contains(node);
	}

	@Override
	public void addToOpen(ASearchNode node){
		if(!openList.add(node)){
			openList.remove(node);
			openList.add(node);
		}
	}

	@Override
	public void addToClosed(ASearchNode node){
		closeList.add(node);
	}

	@Override
	public int openSize(){
		return openList.size();
	}

	@Override
	public ASearchNode getBest(){
		return openList.poll();
	}



}
