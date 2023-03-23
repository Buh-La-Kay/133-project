package com.mycompany.a1;

import java.util.ArrayList;

public class GameCollection implements ICollection{
	private ArrayList <GameObject> gameList;
	
	public GameCollection() {
		gameList=new ArrayList<GameObject>();
	}
	
	public void add(GameObject newGameObject) {
		gameList.add(newGameObject);
	}
	
	public void clear() {
		gameList.clear();
	}
	
	public IIterator getIterator() {
		return new GameWorldIterator();
	}
	
	
	private class GameWorldIterator implements IIterator{
		private int currElementIndex;
		
		public GameWorldIterator() {
			currElementIndex=-1;
		}
		
		public boolean hasNext() {
			if (gameList.size()<=0) {
				return false;
			}
			if (currElementIndex==gameList.size()-1) {
				return false;
			}
			return true;
		}
		
		public GameObject getNext() {
			currElementIndex++;
			return (gameList.get(currElementIndex));
		}
	}
}
