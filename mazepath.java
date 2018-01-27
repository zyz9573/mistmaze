/*
author : Panjoy_Zhang
Create Date : 01-25-2018
Final edit : 01-25-2018
*/
import java.lang.*;
import java.util.*;

public class mazepath{
	private int[] maze;
	private boolean[] result;
	private int size;
	public mazepath(int size){
		this.size=size;
		maze= new int[size*size];
		result = new boolean[size*size];
	}

	public static void main(String args[]){
		mazepath mp = new mazepath(21);
		mp.iniMaze();
		mp.generateMaze();
		mp.getMaze();
		mp.printMaze();
	}

	public boolean[] getMaze(){
		for(int i=0;i<size*size;i++){
			if(maze[i]==2){
				result[i]=true;
			}
		}
		return result;
	}
	public void iniMaze(){
		for(int i=0;i<size;i+=2)
			for(int j=0;j<size;j+=2){
				maze[i*size+j]=1;
			}
	}
	public void generateMaze(){
		maze[0]=2;
		maze[1]=3;
		maze[size]=3;
		Random rand = new Random();
		ArrayList<Integer> choice = new ArrayList<Integer>();
		choice.add(1);
		choice.add(size);
		while(!choice.isEmpty()){
			int temp = rand.nextInt(choice.size());
			int loc = choice.remove(temp);
			int nextStep = nextLoc(loc);
			if(maze[nextStep]==2){maze[loc]=0;continue ;}
			maze[loc]=2;
			maze[nextStep]=2;
			if(isInMaze(nextStep)){
				addChoice(choice,nextStep);
			}
		}
	}
	private int nextLoc(int location){
		if(isInMaze(location-size) && maze[location-size]==2){
			return location+size;
		}
		if(isInMaze(location+size) && maze[location+size]==2){
			return location-size;
		}
		if(isInMaze(location-1) && maze[location-1]==2){
			return location+1;
		}
		if(isInMaze(location+1) && maze[location+1]==2){
			return location-1;
		}
		return -1;
	}
	private void addChoice(ArrayList<Integer> choice, int location){
		if(isInMaze(location)){
			if(isInMaze(location-size-size) && maze[location-size-size]==1){
				maze[location-size]=3;
				choice.add(location-size);
			}
			if(isInMaze(location+size+size) && maze[location+size+size]==1){
				maze[location+size]=3;
				choice.add(location+size);
			}
			if(isInMaze(location-2) && maze[location-2]==1){
				maze[location-1]=3;
				choice.add(location-1);
			}
			if(isInMaze(location+2) && maze[location+2]==1){
				maze[location+1]=3;
				choice.add(location+1);
			}
		}
	}
	public void printMaze(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(result[i*size+j]+" ");
			}
			System.out.println(" ");			
		}
		
	}
	private boolean isInMaze(int location){
		if(location>=0 && location<size*size){
			return true;
		}
		return false;
	}
}