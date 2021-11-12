import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DrawGrid {
    private JFrame frame;

    public DrawGrid() {
        frame = new JFrame("DrawGrid");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new MultiDraw(frame.getSize()));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... argv) {
        
    	new DrawGrid();
    }

    public static class MultiDraw extends JPanel  implements MouseListener {
        int startX = 10;
        int startY = 10;
        int cellWidth = 40;
        //when turn is even, it's red's turn
        int turn = 2;
        int rows = 6;
        int cols = 7;
        
        Color[][] grid = new Color[rows][cols];

        public MultiDraw(Dimension dimension) {
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            //initialize array
            int x = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                   grid[row][col] = new Color(240, 240, 240);
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
            g2.setColor(new Color(176,208,255));
            g2.fillRect(0,0,d.width,d.height);
            startX = 0;
            startY = 0;

            //draw grid
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                  g2.setColor(grid[row][col]);
                  g2.fillOval(startX, startY, cellWidth, cellWidth);
                  startX = startX + cellWidth; //can more space to make it look more
                  								//aesthetically pleasing
                }
                //for each x, y
                startX = 0;
                startY += cellWidth;
               
            }
            g2.setColor(new Color(255, 255, 255));
            if(turn%2==0) { //if the turn is even
            	g.setFont(new Font("Calabri",Font.BOLD,24));;
            	g2.drawString("Player 1's Turn", 400, 20);
            }else {
           
            	g2.drawString("Player 2's Turn", 400, 20);
            }
        }

        public void mousePressed(MouseEvent e) {
        	
            int x = e.getX();
            int y = e.getY();
            
            
           // System.out.println(x + " " + y);
            int xSpot = x/cellWidth;
            int ySpot = y/cellWidth;
            ySpot = testForOpenSpot(xSpot);	
            	if(ySpot<0) {
            		System.out.println("Try another position");
            	}else {
            		
            	
            	
            //rotate between red and yellow's turn
            if(turn%2==0) {
            	grid[ySpot][xSpot]= new Color(243, 190, 188);
            }else {
            	grid[ySpot][xSpot]= new Color(255, 249, 196);
            }
            turn++;
            }
            repaint();
        }

        public int testForOpenSpot(int xSpot) {
        	int ySpot = rows-1;
        	while(!(grid[ySpot][xSpot].equals(new Color(240, 240, 240))|| ySpot<0)) {
        		ySpot--; //move down the row
        	}
        	return ySpot;
        }
        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseClicked(MouseEvent e) {

        }
    }
}