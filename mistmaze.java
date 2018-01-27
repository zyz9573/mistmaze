/*
author : Panjoy_Zhang
Create Date : 01-24-2018
Finale edit : 01-25-2018
*/
import java.lang.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;  

class myPanel extends JPanel{
	private boolean wall;
	public myPanel(boolean wall){
		this.wall=wall;
	}
	public boolean getWall(){return wall;}
	public void setWall(boolean a){wall = a;}
}
public class mistmaze extends JFrame implements KeyListener{
	final private int num;
	final private int width;
	final private int height;
	//private JButton direction;
	private myPanel[] cell;
	private int location=0;
	private int endpoint =-1;
	private int count=0;
	public mistmaze(int num, int size){
		super("MistMaze");

		this.num=num;
		this.width=size;
		this.height=size;
		this.cell = new myPanel[num*num];
		for(int i=0;i<num*num;i++){
			(this.cell)[i] = new myPanel(false);
		}
		this.addKeyListener(this);
	}
	public static void main(String args[]){
		int num=21;
		int size=num*50;
		mistmaze game = new mistmaze(num,size);
		game.inigame();
		game.setSize(size,size);
		game.setDefaultCloseOperation(3);
		game.setVisible(true);
	}
	public void inigame(){
		//cell[num*num-1].setVisible(false);
		//cell[num*num-2].setVisible(false);
		//cell[num*num-num].setVisible(false);
		location=0;
		count=0;
		endpoint=-1;
		mazepath mp = new mazepath(num);
		mp.iniMaze();
		mp.generateMaze();
		boolean[] mazePath = mp.getMaze();
		
		for(int i=0;i<num*num;i++){
			if(mazePath[i]){
				cell[i].setWall(false);
				cell[i].setBackground(Color.WHITE);
			}
			else{
				cell[i].setWall(true);
				cell[i].setBackground(Color.BLACK);				
			}
			cell[i].setPreferredSize(new Dimension(50,50));
			cell[i].setBorder(new EtchedBorder());
			cell[i].setVisible(false);//set visible
			//cell[i].setBackground(Color.BLACK);
			//cell[i].addKeyListener(this);
		}


		cell[0].setBackground(Color.GREEN);
		cell[0].setVisible(true);
		cell[1].setVisible(true);
		cell[num].setVisible(true);
		System.out.println("set endpoint start");
		if(num%2==0){
			cell[num*num-2].setBackground(Color.RED);
			cell[num*num-2].setVisible(true);
			endpoint=num*num-2;

		}
		else{
			cell[num*num-1].setBackground(Color.RED);
			cell[num*num-1].setVisible(true);
			endpoint=num*num-1;
		}
		System.out.println("set endpoint end");

		Container c = getContentPane();
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(num,num));
		for(int i=0;i<num*num;i++){
			gamePanel.add(cell[i]);
		}
		c.add(gamePanel);

		c.setVisible(true);	
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		allinvisible();
		count++;
		if(key==KeyEvent.VK_UP){go_up();}
		else if(key==KeyEvent.VK_DOWN){go_down();}
		else if(key==KeyEvent.VK_LEFT){go_left();}
		else if(key==KeyEvent.VK_RIGHT){go_right();}
		else{
			if(location%num!=0){
				cell[location-1].setVisible(true);
			}
			if((location+1)%num!=0){
				cell[location+1].setVisible(true);
			}
			if(location>=num){
				cell[location-num].setVisible(true);
			}
			if(location<num*num-num){
				cell[location+num].setVisible(true);
			}
			cell[location].setBackground(Color.GREEN);
			cell[location].setVisible(true);
		}
		if(location==endpoint){
			for(int i=0;i<num*num;i++){
				cell[i].setVisible(true);
			}
			int i = JOptionPane.showConfirmDialog(null, "Success, you make it with "+count+" steps, do you want to restart", "restart",JOptionPane.YES_NO_OPTION);
			//System.out.println(i);
			if(i==0){
				inigame();
				cell[num*num-2+1*(num%2)].setVisible(true);
			}
			else{
				System.exit(0);
			}		
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	private void allinvisible(){
		if(location%num!=0){
			cell[location-1].setVisible(false);
		}
		if((location+1)%num!=0){
			cell[location+1].setVisible(false);
		}
		if(location>=num){
			cell[location-num].setVisible(false);
		}
		if(location<num*num-num){
			cell[location+num].setVisible(false);
		}
		cell[location].setBackground(Color.WHITE);
		cell[location].setVisible(false);
	}
	private void go_up(){
		if( location>=num && !cell[location-num].getWall()){
			location=location-num;
		}					
		if(location%num!=0){
			cell[location-1].setVisible(true);
		}
		if((location+1)%num!=0){
			cell[location+1].setVisible(true);
		}
		if(location>=num){
			cell[location-num].setVisible(true);
		}
		if(location<num*num-num){
			cell[location+num].setVisible(true);
		}
		cell[location].setBackground(Color.GREEN);
		cell[location].setVisible(true);
	}
	private void go_down(){
		if( location<num*num-num && !cell[location+num].getWall()){
			location=location+num;
		}
		if(location%num!=0){
			cell[location-1].setVisible(true);
		}
		if((location+1)%num!=0){
			cell[location+1].setVisible(true);
		}
		if(location>=num){
			cell[location-num].setVisible(true);
		}
		if(location<num*num-num){
			cell[location+num].setVisible(true);
		}
		cell[location].setBackground(Color.GREEN);
		cell[location].setVisible(true);
	}
	private void go_left(){
		if(location%num!=0 && !cell[location-1].getWall()){
			location--;
		}
		if(location%num!=0){
			cell[location-1].setVisible(true);
		}
		if((location+1)%num!=0){
			cell[location+1].setVisible(true);
		}
		if(location>=num){
			cell[location-num].setVisible(true);
		}
		if(location<num*num-num){
			cell[location+num].setVisible(true);
		}
		cell[location].setBackground(Color.GREEN);
		cell[location].setVisible(true);
	}
	private void go_right(){
		if((location+1)%num!=0 && !cell[location+1].getWall()){
			location++;
		}
		if(location%num!=0){
			cell[location-1].setVisible(true);
		}
		if((location+1)%num!=0){
			cell[location+1].setVisible(true);
		}
		if(location>=num){
			cell[location-num].setVisible(true);
		}
		if(location<num*num-num){
			cell[location+num].setVisible(true);
		}
		cell[location].setBackground(Color.GREEN);
		cell[location].setVisible(true);
	}
}