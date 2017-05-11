package com.cng462.ai.game;

import java.util.*;

public class Environment {
	Scanner scr=new Scanner(System.in);
	//char w[][]=new char[5][5];
	int np;     //number of purples
	int rp,yp; // red position   yellow position
	int pos[]; // position of purples
	int pl_pos[]=new int[20];
	int rl_pos[]=new int[20];
	void accept(String space[][])
	{
	for(int i=0;i<20;++i) // Initialize all to -1
	{
	pl_pos[i]=-1;
	rl_pos[i]=-1;
	}

	for(int i=0;i<5;++i) // Assign empty values for each space 
	for(int j=0;j<5;++j)
	space[i][j]="";

	int count=1;
	System.out.println("\n\n********* Space Conqueror *********\n-by Doðanay Demirten & Bahri Eker.\n");

	System.out.println("The positions are as follows.");
	for(int i=1;i<=4;++i)
	{
	System.out.println("\n-----------------------------------------------------------------");
	System.out.print("|\t");
	for(int j=1;j<=4;++j)				// To display the initial form of the grid
	System.out.print((count++)+"\t|\t");
	}
	System.out.println("\n-----------------------------------------------------------------");
	System.out.println("\nAgent start position: 13");
	space[4][1]="A";
	System.out.println("\nEnter the number of purple enemies.");
	np=scr.nextInt(); // Take number of purple enemies
	pos=new int[np];
	System.out.println("Positions of purple, yellow and red enemies should not overlap.");
	System.out.println("Enter the position of purple enemies.");
	for(int i=0;i<np;++i)
	{
	pos[i]=scr.nextInt(); // Get the positions of purple enemies
	show_sense(pos[i],1,space);
	}
	System.out.println("Enter the position of red enemy.");
	rp=scr.nextInt(); // Get the position of red enemy
	show_sense(rp,2,space);

	System.out.println("Enter the position of the reward(yellow).");
	yp=scr.nextInt(); // Get the position of reward
 
	insert(space);
	}

	void insert(String space[][])
	{
	int temp=0;
	int count=0;
	int flag1=0,flag2=0;
	for(int i=0;i<np;++i)
	{
	temp=pos[i];
	count=0;
	for(int j=1;j<=4;++j)
	{
	for(int k=1;k<=4;++k)
	{
	++count;
	if(count==temp)
	space[j][k]+="M"; // Assign secondary (purple) enemies  to the related space
	else
	if(count==yp && flag1==0)
	{
	space[j][k]+="~*~"; // Assign reward to the related space
	flag1=1;
	}
	else
	if(count==rp && flag2==0)
	{
	space[j][k]+="M(*)"; // Assign major (red) enemy to the related space
	flag2=1;
	}
	}
	}
	}

	display(space); // Display the grid 
	}

	void show_sense(int a,int b,String space[][])
	{
	int t1,t2,t3,t4;
	t1=a-1;
	t2=a+1;
	t3=a+4;
	t4=a-4;

	if(a==5 || a==9)
	t1=0;
	if(a==8 || a==12)
	t2=0;
	if(a==4)
	t2=0;
	if(a==13)
	t1=0;

	if(t3>16)
	t3=0;
	if(t4<0)
	t4=0;

	//int temp[]=new int[4];

	if(b==1)
	{pl_pos[0]=t1;pl_pos[1]=t2;pl_pos[2]=t3;pl_pos[3]=t4;}
	else
	if(b==2)
	{rl_pos[0]=t1;rl_pos[1]=t2;rl_pos[2]=t3;rl_pos[3]=t4;}

	int temp1,count;

	for(int i=0;i<4;++i)
	{
	if(b==1)
	temp1=pl_pos[i];
	else
	temp1=rl_pos[i];
	count=0;
	for(int j=1;j<=4;++j)
	{
	for(int k=1;k<=4;++k)
	{
	++count;
	if(count==temp1 && b==1 && !space[j][k].contains("PL"))	// Assign purple light to the related space
	{
	space[j][k]+="PL";
	}
	else
	if(count==temp1 && b==2 && !space[j][k].contains("RL")) // Assign red light to the related space
		space[j][k]+="RL";
	}
	}
	}
	 
	//display(w);
	}
	void display(String space[][])// Display grid method
	{
	System.out.println("\nThe environment for problem is as follows.\n");
	for(int i=1;i<=4;++i)
	{
	System.out.println("\n-----------------------------------------------------------------");
	System.out.print("|\t");
	for(int j=1;j<=4;++j)
	System.out.print(space[i][j]+"\t|\t");
	}
	System.out.println("\n-----------------------------------------------------------------");
	}

}
