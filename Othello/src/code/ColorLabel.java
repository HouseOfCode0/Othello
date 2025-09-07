package code;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ColorLabel extends JButton
{
	Color drawColor; 
	Color borderColor;
	int borderSize;
	int state;
   
	
	
	public ColorLabel( int width, int height, Color color, int borderWidth,Color borderCol)
	{
		borderSize = borderWidth;
		drawColor = color;
		borderColor = borderCol;
		setMinimumSize(new Dimension(width, height) );
		setPreferredSize(new Dimension(width, height) );
	}
	
	
	protected void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
		if ( borderColor != null )
		{
			g.setColor(borderColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if ( drawColor != null )
		{
			g.setColor(drawColor);
			g.fillRect( borderSize, borderSize,getWidth()-borderSize*2,getHeight()-borderSize*2 );
		}
		
		if(state == 1)
		{
			g.setColor(Color.white);
			g.fillOval(1,1,40,35);
			g.setColor(Color.black);
			g.drawOval(1,1,40,35);
		}
		if(state == 2)
		{
			g.setColor(Color.black);
			g.fillOval(1,1,40,35);
			g.setColor(Color.white);
			g.drawOval(1,1,40,35);
		}
	
	}
	
	
	
	
	
	
	

}

