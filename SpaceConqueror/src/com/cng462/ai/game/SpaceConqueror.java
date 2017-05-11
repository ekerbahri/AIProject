package com.cng462.ai.game;

import java.util.*;

import com.cng462.ai.game.Environment;
import com.cng462.ai.game.Tiles;

public class SpaceConqueror {
	static int scream=0;
	static int score=0;
	static int complete=0;

	static boolean check(Tiles t)
	{
	int temp=t.sense();
	if(temp==1 || temp==2)
	return false;

	return true;
	}
	public static void main(String args[])
	{
	Scanner scr=new Scanner(System.in);
	Environment e=new Environment();
	String w[][]=new String[5][5];
	e.accept(w);
	System.out.println("\n\nFinding the solution...");

	Tiles t[]=new Tiles[17];
	int c=1;
	out:for(int i=1;i<5;++i)
	{
	for(int j=1;j<5;++j)
	{
	if(c>16)
	break out; // If c is greater than 16 break the loop
	t[c]=new Tiles(w[i][j],c); // Create the environment that the agent knows
	++c;
	}
	}

	t[13].safe=1;		// Assign necessary flags
	t[13].visited=1;

	int pos=13;
	int condition;
	int limit=0;
	String temp1,temp2;
	do
	{
	++limit;
	condition=-1;

	if(t[pos].env.contains("~*~"))	// If space contains reward return reward found
	{
	complete=1;
	System.out.println("Reward Found!!");
	break;
	}

	if(t[pos].br!=1 && t[pos].r!=1 && t[pos+1].doubt_purple<1 && t[pos+1].doubt_red<1 && t[pos+1].purple!=1 && t[pos+1].red!=1 && !(t[pos].back.contains("r") && (t[pos].l!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))	// Check the conditions we have defined in the sense method of Tiles
	{
	////////////

	temp1="l";
	///////////
	t[pos].r=1;
	++pos;
	System.out.println("\nfront pos="+pos);	// If moved right print the move to the user
	++score;
	//t[pos].visited=1;
	////////////////
	t[pos].back+=temp1;
	////////////////
	condition=t[pos].sense();
	if(condition==3)
	{complete=1;break;}
	else
	if(condition==1 && t[pos].visited==0)	// Condition = 1 meaning having purple light
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)	//Check the adjacent cells and the current cell of the agent
	t[pos+1].doubt_purple+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 && t[pos-4].safe!=1)
	t[pos-4].doubt_purple+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1 )
	t[pos-1].doubt_purple+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 && t[pos+4].safe!=1)
	t[pos+4].doubt_purple+=1;

	t[pos].safe=1;	// If conditions are matched mark the space as safe
	}
	else
	if(condition==2 && t[pos].visited==0) //Check the adjacent cells and the current cell of the agent	// Condition = 2 meaning having red light
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_red+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_red+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_red+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_red+=1;

	t[pos].safe=1;
	}
	/*else
	if(condition==4)
	{
	score=score+100;
	t[pos].safe=1;
	}*/
	else
	if(condition==0)	// If there are no perception coming from the cell than mark it as safe directly
	t[pos].safe=1;


	t[pos].visited=1;
	}
	else
	if(t[pos].bl!=1 && t[pos].l!=1 && t[pos-1].doubt_purple<1 && t[pos-1].doubt_red<1 && t[pos-1].purple!=1 && t[pos-1].red!=1 && !(t[pos].back.contains("l") && (t[pos].r!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
	{
	////////////////////
	temp1="r";
	///////////////////
	t[pos].l=1;
	pos=pos-1;
	System.out.println("\nback pos= "+pos);// If moved left print the move to the user
	++score;
	//t[pos].visited=1;

	//////////////////////

	t[pos].back+=temp1;
	/////////////////////


	condition=t[pos].sense();
	if(condition==3)	// Condition = 3 meaning reward found
	{complete=1;break;}
	else
	if(condition==1 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_purple+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_purple+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_purple+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_purple+=1;


	t[pos].safe=1;
	}
	else
	if(condition==2 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_red+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_red+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_red+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_red+=1;

	t[pos].safe=1;
	}
	else
	if(condition==0)
	t[pos].safe=1;

	t[pos].visited=1;


	}
	else
	if(t[pos].bu!=1 && t[pos].u!=1 && (pos-4)>=1 &&  t[pos-4].doubt_purple<1 && t[pos-4].doubt_red<1 && t[pos-4].purple!=1 && t[pos-1].red!=1 && !(t[pos].back.contains("u") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].d!=1) && check(t[pos])  ))
	{
	/////////////////////

	temp1="d";
	/////////////////////
	t[pos].u=1;
	pos=pos-4;
	System.out.println("\nUp pos= "+pos);// If moved up print the move to the user
	++score;
	//t[pos].visited=1;

	///////////////////////
	t[pos].back+=temp1;
	/////////////////////
	condition=t[pos].sense();
	if(condition==3)
	{complete=1;break;}
	else
	if(condition==1 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_purple+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_purple+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_purple+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_purple+=1;


	t[pos].safe=1;
	}
	else
	if(condition==2 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_red+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_red+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_red+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_red+=1;

	t[pos].safe=1;
	}
	else
	if(condition==0)
	t[pos].safe=1;

	t[pos].visited=1;
	}
	else
	if(t[pos].bd!=1 && t[pos].d!=1 && (pos+4)<=16 &&  t[pos+4].doubt_purple<1 && t[pos+4].doubt_red<1 && t[pos+4].purple!=1 && t[pos+4].red!=1)
	{
	/////////////////
	temp1="u";
	////////////////
	t[pos].d=1;
	pos=pos+4;
	System.out.println("\ndown pos= "+pos);// If moved down print the move to the user
	++score;
	//t[pos].visited=1;

	//////////////////

	t[pos].back+=temp1;
	//////////////////
	condition=t[pos].sense();
	if(condition==3)
	{complete=1;break;}
	else
	if(condition==1 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_purple+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_purple+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_purple+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_purple+=1;

	t[pos].safe=1;
	}
	else
	if(condition==2 && t[pos].visited==0)
	{
	if(t[pos].br!=1 && t[pos+1].safe!=1)
	t[pos+1].doubt_red+=1;
	if(t[pos].bu!=1 && (pos-4)>=1 &&  t[pos-4].safe!=1)
	t[pos-4].doubt_red+=1;
	if(t[pos].bl!=1 && t[pos-1].safe!=1)
	t[pos-1].doubt_red+=1;
	if(t[pos].bd!=1 && (pos+4)<=16 &&  t[pos+4].safe!=1)
	t[pos+4].doubt_red+=1;

	t[pos].safe=1;
	}
	else
	if(condition==0)
	t[pos].safe=1;

	t[pos].visited=1;
	}
	else
	if(limit>50)
	{
	int temp3=pos;
	int flag_1=0,flag2=0,flag3=0,flag4=0;

	System.out.println("\nCurrently at position "+temp3+".\nThinking....");	// Print the user where the agent is after several moves

	//if(!(t[pos].back.contains("r") && (t[pos].l!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
	while(t[pos].visited==1 && t[pos].br!=1)
	{
	++pos;
	++score;
	}


	if(t[pos].purple==1 || t[pos].red==1 || (t[pos].br==1 && t[pos].visited==1 && t[pos].safe!=1))
	{
	//System.out.println("\nUnsuccessful at pos "+pos);
	pos=temp3;
	//System.out.println("\nBack at pos "+pos);
	flag_1=1;
	} 

	if(flag_1==0)
	t[pos].back+="l";

	//if(!(t[pos].back.contains("u") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].d!=1) && check(t[pos])  ))
	while(pos+4>=1 && t[pos].bu!=1 && t[pos].visited==1)
	{
	pos-=4;
	++score;
	}

	if(t[pos].purple==1 || t[pos].red==1 || (t[pos].bu==1 && t[pos].visited==1  && t[pos].safe!=1))
	{
	//System.out.println("\nUnsuccessful at pos "+pos);
	pos=temp3;
	//System.out.println("\nBack at pos "+pos);
	flag3=1;
	} 

	if(flag3==0)
	t[pos].back+="d";

	//if(!(t[pos].back.contains("l") && (t[pos].r!=1 || t[pos].u!=1 || t[pos].d!=1) && check(t[pos]) ))
	while(t[pos].visited==1 && t[pos].bl!=1)
	{
	--pos;
	++score;
	}

	if(t[pos].purple==1 || t[pos].red==1 || (t[pos].bl==1 && t[pos].visited==1 && t[pos].safe!=1))
	{
	//System.out.println("\nUnsuccessful at pos "+pos);
	pos=temp3;
	//System.out.println("\nBack at pos "+pos);
	flag2=1;
	} 

	if(flag2==0)
	t[pos].back+="r";



	//if(!(t[pos].back.contains("d") && (t[pos].l!=1 || t[pos].r!=1 || t[pos].u!=1) && check(t[pos])  ))
	while(pos+4<=16 && t[pos].bd!=1 && t[pos].visited==1)
	{
	pos+=4;
	++score;
	}

	if(t[pos].purple==1 || t[pos].red==1 || (t[pos].bd==1 && t[pos].visited==1 && t[pos].safe!=1))
	{
	//System.out.println("\nUnsuccessful at pos "+pos);
	pos=temp3;
	//System.out.println("\nBack at pos "+pos);
	flag4=1;
	} 
	

	if(flag4==0)
	t[pos].back+="u";

	t[pos].safe=1;
	t[pos].visited=1;
	System.out.println("reached at position "+pos);
	limit=0;
	}
	if(t[pos].env.contains("~*~") && scream!=1)
	{
	score+=100;
	scream=1;
	t[pos].safe=1;
	System.out.println("\n\nMajor Enemy killed >--0-->");	// After killing the red enemy
	t[pos].env.replace("M(*)"," ");
	for(int l=1;l<=16;++l)
	{
	t[l].doubt_red=0;
	t[l].env.replace("RL"," ");
	}
	}

	if(t[pos].env.contains("M"))
	{
	score+=50;
	t[pos].purple=1;
	System.out.println("\n\nTrapped by purple enemy in cell position "+pos+".");	// If the agent enters the same cell with a purple enemy
	}

	for(int k=1;k<=16;++k)
	{
	if(t[k].doubt_purple==1 && t[k].doubt_red==1)
	{
	t[k].doubt_purple=0;
	t[k].doubt_red=0;
	t[k].safe=1;
	}
	}

	for(int y=1;y<=16;++y)
	{
	if(t[y].doubt_red>1)
	{
	t[y].red=1;
	for(int h=1;h<=16;++h)
	{
	if(h!=y)
	{
	t[h].doubt_red=0;
	t[h].env.replace("RL"," ");
	}
	}

	}

	}

	///////////////////////////
	for(int y=1;y<=16;++y)
	{
	if(t[y].doubt_purple>1)
	{t[y].purple=1;
	
	}
	}
	///////////////////////////


	try{Thread.sleep(200);}catch(Exception p){}

	}
	while(complete==0);

	if(complete==1)
	{
	//score=score*2;
	//if(scream==1)
	//score-=100;

	score*=-1;
	score+=1000;
	}
	System.out.println("The score of the agent till he reaches the reward(yellow) is "+score+".\nNow he will return back following the best explored path.");

	}

}
