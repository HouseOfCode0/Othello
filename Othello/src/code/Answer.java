package code;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Answer  

{
	JFrame guiframeWhite = new JFrame();
	JFrame guiframeBlack = new JFrame();
	ArrayList<ArrayList<ColorLabel>> arrayLabels2d = new ArrayList<ArrayList<ColorLabel>>();
	ArrayList<ArrayList<ColorLabel>> arrayLabelsblack = new ArrayList<ArrayList<ColorLabel>>();
	
	
	JButton button1;
	JButton button2;
	
	JLabel white;
	JLabel black;
	JPanel panelWhiteGrid;
	
	
	
	
	int play = 1; 
	
	
	Color blackColor =  new Color(0,0,0);
	Color green = new Color(0,255,0);	
	
	
	
	public void White() 
	{
		guiframeWhite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiframeWhite.setTitle("Reversi - white player");
		guiframeWhite.setLocationRelativeTo(null);
		guiframeWhite.pack();
		guiframeWhite.setVisible(true);
		guiframeWhite.setSize(370, 400);
		
		//Add Button to the Bottom of Panel1.
		button1 = new JButton("Greedy AI (Play White");
		button1.addActionListener(this.new buttonPressed1());
		
		
		white = new JLabel();
		white.setText("White Player - Click Place to Put a Piece");
		
		panelWhiteGrid = new JPanel();
		panelWhiteGrid.setLayout(new GridLayout(8,8));
		
				
		for(int i = 0; i <8 ; i++)
		{
			arrayLabels2d.add(new ArrayList<ColorLabel>());
			
			for(int j = 0; j< 8 ;j++)	
			{
				ColorLabel colorlabel = new ColorLabel(5,5,green,1 ,blackColor);
				if((8*i)+7-j == 28 || (8*i)+7-j== 35)
				{
					colorlabel.state = 1;
					colorlabel.addMouseListener(new mouseHandler());
					arrayLabels2d.get(i).add(colorlabel);
					panelWhiteGrid.add(colorlabel);
					continue;
				}
							
				if((8*i)+7-j == 27 || (8*i)+7-j== 36 )
				{
					
					colorlabel.state = 2;
					colorlabel.addMouseListener(new mouseHandler());
					arrayLabels2d.get(i).add(colorlabel);
					panelWhiteGrid.add(colorlabel);
					continue;
				}	
				
				colorlabel.state = -1;
				colorlabel.addMouseListener(new mouseHandler());
				arrayLabels2d.get(i).add(colorlabel);
				panelWhiteGrid.add(arrayLabels2d.get(i).get(j));	
			}				
		}

		//panelWhite.add(panelWhiteGrid,BorderLayout.CENTER );	
		guiframeWhite.getContentPane().add(panelWhiteGrid);
		guiframeWhite.getContentPane().add(button1,BorderLayout.SOUTH);
		guiframeWhite.add(white,BorderLayout.NORTH);	

	}
	public void Black()
	{
		guiframeBlack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiframeBlack.setTitle("Reversi - Black player");
		guiframeBlack.setLocationRelativeTo(null);
		guiframeBlack.pack();
		guiframeBlack.setVisible(true);
		guiframeBlack.setSize(370, 400);
		
		
		JPanel panelBlackGrid = new JPanel();
		panelBlackGrid.setLayout(new GridLayout(8,8));
		
		
		button2 = new JButton("Greedy AI (Play Black)");
		button2.addActionListener(this.new buttonPressed2());
		black = new JLabel();
		black.setText("Black Player - not your turn");
		
		for(int i = 0; i <8 ; i++)
		{
			arrayLabelsblack.add(new ArrayList<ColorLabel>());
			
			for(int j = 0; j< 8 ;j++)
			{
				ColorLabel colorlabel = new ColorLabel(5,5,green,1 ,blackColor);
				if((8*i)+7-j == 28 || (8*i)+7-j== 35)
				{
					colorlabel.state = 1;
					colorlabel.addMouseListener(new mouseHandler());
					arrayLabelsblack.get(i).add(colorlabel);
					panelBlackGrid.add(colorlabel);
					continue;
				}
							
				if((8*i)+7-j == 27 || (8*i)+7-j== 36 )
				{
					
					colorlabel.state = 2;
					colorlabel.addMouseListener(new mouseHandler());
					arrayLabelsblack.get(i).add(colorlabel);
					panelBlackGrid.add(colorlabel);
					continue;
				}	
				
				colorlabel.state = -1;
				colorlabel.addMouseListener(new mouseHandler());
				arrayLabelsblack.get(i).add(colorlabel);
				panelBlackGrid.add(arrayLabelsblack.get(i).get(j));
				
			}
			
		}
	
		guiframeBlack.getContentPane().add(panelBlackGrid);
		guiframeBlack.getContentPane().add(button2,BorderLayout.SOUTH);
		guiframeBlack.add(black,BorderLayout.NORTH);
		
	}

	public static void main(String[] args) {
		
		
		Answer board = new Answer();
		
		board.White();
		board.Black();
		
			
	}
	
	
	public class buttonPressed1 implements ActionListener
	{
		int ki = 0;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(play == 1)
			{
				int[][] array = new int[8][8];
				int kwhite = checkEnd(array,arrayLabels2d);
				int kblack = checkEnd(array,arrayLabelsblack);
				String key = empty(arrayLabels2d);
				if((kwhite == 0 && kblack == 0)|| (key =="notempty"))
				{
					winner(arrayLabels2d);
					ki++;
				}
				else if(kwhite == 0 && kblack != 0 )
				{
					play = 2;
					white.setText("White Player - not your turn");
					black.setText("Black Player - Click Place to Put a Piece");
				}
				
			}
			
			if(play == 1 && ki == 0 )
			{
				int[][] whitearray = new int[8][8];
				
				arrayCreate(whitearray,arrayLabels2d);
				int maxValue;
				maxValue = max(whitearray,arrayLabels2d);
		        
		        int ea = 0;
		        for (int i = 0; i < whitearray.length; i++)
		        {
		            for (int j = 0; j < whitearray.length; j++) 
		            {
		            	if(maxValue == whitearray[i][j] && arrayLabels2d.get(i).get(j).state == -1)
		            	{
		            		play(i,j,arrayLabels2d.get(i).get(j) ,arrayLabels2d);
		            		arrayLabels2d.get(i).get(j).state = play;
		            	    ea++;
		            		break;
		            	}
		            	
		            }
		            if(ea != 0)
		            	break;
		            
		        }
		        play = 2;
		        guiframeWhite.repaint();
		        guiframeBlack.repaint();
				update_board(arrayLabels2d,arrayLabelsblack);
				white.setText("White Player - not your turn");
				black.setText("Black Player - Click Place to Put a Piece");
				
			}
			
		}
		
	}
	public class buttonPressed2 implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int ki = 0;
			if(play == 2)
			{
				int[][] array = new int[8][8];
				int kwhite = checkEnd(array,arrayLabels2d);
				int kblack = checkEnd(array,arrayLabelsblack);
				String key = empty(arrayLabelsblack);
				if((kwhite == 0 && kblack == 0) || (key == "notempty"))
				{
					winner(arrayLabels2d);
					ki++;
				}
				else if(kwhite != 0 && kblack == 0)
				{
					play = 1;
					black.setText("Black Player - not your turn");
					white.setText("White Player - Click Place to Put a Piece");
				}
				
			}
			if(play == 2 && ki == 0)
			{
				int[][] whitearray = new int[8][8];
				arrayCreate(whitearray,arrayLabelsblack);
				int maxValue;
				maxValue = max(whitearray,arrayLabelsblack);
		       
		        int ea = 0;
		        for (int i = 0; i < whitearray.length; i++)
		        {
		            for (int j = 0; j < whitearray.length; j++) 
		            {
		            	if(maxValue == whitearray[i][j] && arrayLabelsblack.get(i).get(j).state == -1)
		            	{
		            		play(i,j,arrayLabelsblack.get(i).get(j) ,arrayLabelsblack);
		            		arrayLabelsblack.get(i).get(j).state = play;
		            	    ea++;
		            		break;
		            	}
		            	
		            }
		            if(ea != 0)
		            	break;
		            
		        }
		        
		        play = 1;
		        guiframeWhite.repaint();
		        guiframeBlack.repaint();
		        update_board(arrayLabelsblack,arrayLabels2d);
				black.setText("Black Player - not your turn");
				white.setText("White Player - Click Place to Put a Piece");
					
			}
			
			
		}
	}
	
	public class mouseHandler  extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			super.mouseClicked(e);
			e.getSource();
			ColorLabel a;
			if(play == 1)
			{
				int[][] array = new int[8][8];
				int kwhite = checkEnd(array,arrayLabels2d);
				int kblack = checkEnd(array,arrayLabelsblack);
				String key = empty(arrayLabels2d);
				if((kwhite == 0 && kblack == 0) || (key == "notempty"))
				{
					winner(arrayLabels2d);
				}
				else if(kwhite == 0 && kblack != 0 )
				{
					play = 2;
					white.setText("White Player - not your turn");
					black.setText("Black Player - Click Place to Put a Piece");
				}
				
			}
			
			if(play == 2)
			{
				int[][] array = new int[8][8];
				int kwhite = checkEnd(array,arrayLabels2d);
				int kblack = checkEnd(array,arrayLabelsblack);
				String key = empty(arrayLabelsblack);
				if((kwhite == 0 && kblack == 0)|| (key == "notempty"))
				{
					winner(arrayLabels2d);
					
				}
				else if(kwhite != 0 && kblack == 0)
				{
					play = 1;
					black.setText("Black Player - not your turn");
					white.setText("White Player - Click Place to Put a Piece");
				}
				
			}
			
			if(play == 1)
			{
				for(int i = 0; i<8;i++)
				{
					for(int j = 0; j<8;j++)
					{
						if(e.getSource() == arrayLabels2d.get(i).get(j))
						{
							int ea = 0;
							a = arrayLabels2d.get(i).get(j);
							if(a.state == -1)
							{
								ea = play(i,j,a,arrayLabels2d);
							}
							else
							{
								continue;
							}
								
							
							
							guiframeBlack.repaint();
							if(ea != 0)
							{
								a.state = play;
								update_board(arrayLabels2d,arrayLabelsblack);
								white.setText("White Player - not your turn");
							    black.setText("Black Player - Click Place to Put a Piece");
								play = 2;
							}
								
						}
					}
				}
			}
			
			else if(play == 2)
			{
				for(int i = 0; i<8;i++)
				{
					for(int j = 0;j<8;j++)
					{
						if(e.getSource() == arrayLabelsblack.get(i).get(j))
						{
							a = arrayLabelsblack.get(i).get(j);
							int ea = 0;
							if(a.state == -1 )
							{
								ea = play(i,j,a,arrayLabelsblack);
							}
							else
							{
								continue;
							}
							
							guiframeBlack.repaint();
							
							if(ea != 0)
							{
								a.state = play;
								update_board(arrayLabelsblack,arrayLabels2d);
								black.setText("Black Player - not your turn");
								white.setText("White Player - Click Place to Put a Piece");
								play = 1;
							}		
						}
					}
				}		
			}
		}	
	}
	
	public void left(int i, int j, ColorLabel a, ArrayList<ArrayList<ColorLabel>> list)
	{	
		j--;
		ColorLabel b = list.get(i).get(j);
		while(b.state != play)
		{
			
			b.state = play;
			j--;
			b = list.get(i).get(j);
			if(b.state == -1)
			{
				return ;
			}
				
		}
		guiframeWhite.repaint();
	}
	
	public void down(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{	
		i++;
		ColorLabel b = list.get(i).get(j);
				//while not edge and 
		while( b.state != play)
		{
			b.state = play;
			i++;
			b = list.get(i).get(j);
			if(b.state == -1)
			{
				return;
			}		
		}
		guiframeWhite.repaint();
	}
	//captures all pieces up
	public void up(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{			
		i--;
		ColorLabel b = list.get(i).get(j);
				//while not edge and 
		while(b.state != play)
		{
			b.state = play;
			i--;
			b = list.get(i).get(j);
			if(b.state == -1)
			{
				return;
			}		
		}
		guiframeWhite.repaint();
	}
	public void right(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		j++;
		ColorLabel b = list.get(i).get(j);
 		while( b.state != play)
		{
			b.state = play;
			j++;
			b = list.get(i).get(j);
			if(b.state == -1)
				return;
		}
		guiframeWhite.repaint();
	}
	
	public void upright(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		j++;
		i--;
		ColorLabel b = list.get(i).get(j);
 		while(j<=7 && b.state != play)
		{
			b.state = play;
			j++;
			i--;
			b = list.get(i).get(j);
			if(b.state == -1)
				return;
		}
		guiframeWhite.repaint();
	}
	public void upleft(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		i--;
		j--;
		ColorLabel b = list.get(i).get(j);
 		while(j<=7 && b.state != play)
		{
			b.state = play;
			j--;
			i--;
			b = list.get(i).get(j);
			if(b.state == -1)
				return;
		}
		guiframeWhite.repaint();
	}
	public void downleft(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		j--;
		i++;
		ColorLabel b = list.get(i).get(j);
 		while(j<=7 && b.state != play)
		{
			b.state = play;
			j--;
			i++;
			b = list.get(i).get(j);
			if(b.state == -1)
				return;
		}
		guiframeWhite.repaint();
	}
	public void downright(int i, int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		i++;
		j++;
		ColorLabel b = list.get(i).get(j);
 		while(j<=7 && b.state != play)
		{
			b.state = play;
			j++;
			i++;
			b = list.get(i).get(j);
			if(b.state == -1)
				return;
		}
		guiframeWhite.repaint();
	}
	
	
	
	public int pieces_left(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(j == 0)
		{
			return 0;
		}
			
		j--;
		ColorLabel b = list.get(i).get(j);
		int count = 0;
		while(j > 0 && b.state != play && b.state != -1)
		{	
			count++;
			j--;
			b = list.get(i).get(j);
			if(b.state == -1)
			{
				return 0;
			}	
		}
		if(j == 0 && list.get(i).get(j).state != play)
		{
			return 0;
		}
		
		return count;
	}
	public int pieces_right(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(j == 7)
		{
			return 0;
		}
		j++;
		ColorLabel b = list.get(i).get(j);
		int count = 0;
		while(j < 7 && b.state != play && b.state != -1)
		{	
			count++;
			j++;
			b = list.get(i).get(j);
			
			if(b.state == -1)
			{
				return 0;
			}
		}
	
		if(j == 7 && list.get(i).get(j).state != play)
		{
			return 0;
		}
		
		return count;
	}

	public int pieces_up(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(i == 0)
		{
			return 0;
		}
		i--;
		ColorLabel b = list.get(i).get(j);
		int count = 0;
		while(i> 0 && b.state != play && b.state != -1)
		{	
			count++;
			i--;
			b = list.get(i).get(j);
			if(b.state == -1 )
			{
				return 0;
			}	
		}
		if(list.get(i).get(j).state != play && i == 0)
		{
			return 0;
		}
		
		return count;
	}
	public int pieces_down(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(i == 7)
		{
			return 0;
		}
		i++;
		ColorLabel b = list.get(i).get(j);
		int count = 0;
		while(i<7 && b.state != play && b.state != -1)
		{	
			count++;
		    i++;
			b = list.get(i).get(j);
			if(b.state == -1)
			{
				return 0;
			}		
			
		}
		if(i == 7 &&  list.get(i).get(j).state != play)
		{
			return 0;
		}
		
		return count;
	}

	public int pieces_upright(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(i == 0 || j == 7)
		{
			return 0;
		}
		
		int count = 0;
		ColorLabel b = list.get(i-1).get(j+1);
 		while(i>= 0 && j<=7 && b.state != play && b.state != -1)
		{
 			
			j++;
			i--;
			count++;
			b = list.get(i).get(j);
			if(b.state == -1 || ((i == 0 || j == 7) && b.state != play))
				return 0;
		}
 		return count;
	}
	public int pieces_upleft(int i , int j, ColorLabel a, ArrayList<ArrayList<ColorLabel>> list)
	{
		if(j == 0 || i == 0)
		{
			return 0;
		}
		
		ColorLabel b = list.get(i-1).get(j-1);
		int count = 0;
 		while(j<=7 && b.state != play && b.state != -1)
		{
 			
			j--;
			i--;
			count++;
			b = list.get(i).get(j);
			if(b.state == -1 || ((j == 0 || i == 0) && b.state != play))
				return 0;
		}
 		return count;
		
	}
	public int pieces_downright(int i , int j, ColorLabel a, ArrayList<ArrayList<ColorLabel>> list)
	{
		if(i == 7 || j == 7)
		{
			return 0;
		}
		
		ColorLabel b = list.get(i+1).get(j+1);
		int count = 0;
 		while(j<=7 && i<= 7 && b.state != play && b.state != -1)
		{
 			
			j++;
			i++;
			count++;
			b = list.get(i).get(j);
			if(b.state == -1 || ((i == 7 || j == 7 ) && b.state != play))
				return 0;
		}
 		return count;
		
	}
	public int pieces_downleft(int i , int j, ColorLabel a,ArrayList<ArrayList<ColorLabel>> list)
	{
		if(j == 0 || i == 7)
		{
			return 0;
		}
		ColorLabel b = list.get(i+1).get(j-1);
		int count = 0;
 		while(i <= 7 && j>= 0 && b.state != play && b.state != -1)
		{
			j--;
			i++;
			count++;
			b = list.get(i).get(j);
			if(b.state == -1 || ((i == 7 || j == 0) && b.state != play))
				return 0;
		}
 		return count;
		
	}
    public void update_board(ArrayList<ArrayList<ColorLabel>> a, ArrayList<ArrayList<ColorLabel>> b)
    {
    	for(int i = 0; i<8;i++)
    	{
    		for(int j = 0;j<8;j++)
    		{
    			ColorLabel plays  = a.get(i).get(j);
    			ColorLabel update = b.get(7-i).get(7-j);
    			update.state = plays.state;
    			
    		}
    	}
    	
    }
    
    public int play(int i,int j,ColorLabel a, ArrayList<ArrayList<ColorLabel>> list)
    {
    	int ea = 0;

    	int oo = pieces_right(i,j,a,list);
		if(oo != 0)
		{
			right(i,j,a,list);
		    ea++;
		}
			
		
        oo = pieces_left(i,j,a,list);
		if( oo != 0) 
		{
			left(i,j,a,list);
		    ea++;
		}
		oo =0;
		
		oo = pieces_up(i,j,a,list);
		if(oo !=  0)
		{
			up(i,j,a,list);
		    ea++;
		}
		oo= 0;
			
		
		oo = pieces_down(i,j,a,list);
		if(oo != 0)
		{
			down(i,j,a,list);
		    ea++;
		}
		oo = 0;
			
		oo = pieces_upleft(i,j,a,list);
		if(oo != 0)
		{
			upleft(i,j,a,list);
		    ea++;
		}
		oo = 0;
			
		
		oo = pieces_upright(i,j,a,list);
		if(oo != 0)
		{
			upright(i,j,a,list);
		    ea++;
		}
		oo = 0;
										
		  
		
		oo = pieces_downleft(i,j,a,list);
		if(oo != 0)
		{
			downleft(i,j,a,list);
	        ea++;
		}
		oo = 0;
			
		
		oo = pieces_downright(i,j,a,list);
		if(oo != 0)
		{
			downright(i,j,a,list);
	        ea++;
		}
		oo = 0;
			
	        
	    return ea;
    	
    }
    public int checkMoves(int i,int j,ColorLabel a, ArrayList<ArrayList<ColorLabel>> list)
    {
    	int k = 0;
    	k += pieces_left(i,j,a,list);
    	k += pieces_right(i,j,a,list);
    	k += pieces_up(i,j,a,list);
    	k += pieces_down(i,j,a,list);
    	k += pieces_upright(i,j,a,list);
    	k += pieces_upleft(i,j,a,list);
    	k += pieces_downleft(i,j,a,list);
    	k += pieces_downright(i,j,a,list);
    	return k;
    }
    public void winner(ArrayList<ArrayList<ColorLabel>> list)
    {
    	int black = 0;
    	int white = 0;
    	String blacks = "Black Wins";
    	String whites = "White Wins";
    	for(int i = 0; i<8;i++)
    	{
    		for(int j = 0;j<8;j++)
    		{
    			if(list.get(i).get(j).state == 1)
    			{
    				white++;
    			}
    			else if(list.get(i).get(j).state == 2)
    			{
    				black++;
    			}
    		}
    	}
    	if(black > white)
    	{
    		JOptionPane.showMessageDialog(null,blacks + black);
    		reset();
    	}
    	else if (white>black)
    	{
    		JOptionPane.showMessageDialog(null,whites + white);
    		reset();
    	}
    	
    	else if (white == black)
    	{
    		JOptionPane.showMessageDialog(null,"Tie"+ white + black);
    		reset();
    	}
    }
    public void arrayCreate(int[][] whitearray,ArrayList<ArrayList<ColorLabel>> list )
    {
    	for(int  i = 0; i<8;i++)
		{
			for(int j = 0;j<8;j++)
			{
				int k = checkMoves(i,j,list.get(i).get(j),list);
				whitearray[i][j] = k;
				
			}
		}
    	
    }
    public int max(int[][] whitearray,ArrayList<ArrayList<ColorLabel>> list )
    {
    	int maxValue = whitearray[0][0];
        for (int i = 0; i < whitearray.length; i++)
        {
            for (int j = 0; j < whitearray.length; j++) 
            {
                if (whitearray[i][j] > maxValue && list.get(i).get(j).state == -1) 
                {
                    maxValue = whitearray[i][j];
                }
            }
        }
        
    	return maxValue;
    }
    public int checkEnd(int[][] whitearray,ArrayList<ArrayList<ColorLabel>> list)
    {
    	//create array
    	arrayCreate(whitearray,list);
    	int k = max(whitearray,list);
    	
    	return k;
    	
    }
    public String empty(ArrayList<ArrayList<ColorLabel>> list)
    {
    	for(int i = 0;i<8;i++)
    	{
    		for(int j = 0;j<8;j++)
    		{
    			if(list.get(i).get(j).state == -1)
    			{
    				return null;
    			}
    		}
    	}
    	//implies game over as didn't encouter any empty space 
    	return "notempty";
    }
    public void reset()
    {
    	for(int i = 0; i<8;i++)
    	{
    		for(int j = 0; j<8;j++)
    		{
    			if((8*i)+7-j == 28 || (8*i)+7-j== 35)
				{
    				arrayLabels2d.get(i).get(j).state = 1;
    				continue;
				}
							
				if((8*i)+7-j == 27 || (8*i)+7-j== 36 )
				{
					arrayLabels2d.get(i).get(j).state = 2;
					continue;
				}
    			arrayLabels2d.get(i).get(j).state = -1;
    		}
    	}
    	update_board(arrayLabels2d,arrayLabelsblack);
    	guiframeWhite.repaint();
    	guiframeBlack.repaint();
    	white.setText("White Player -Click Place to Put a Piece ");
		black.setText("Black Player - not your turn");
    	play = 1;
    }
}
