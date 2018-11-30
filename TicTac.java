import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/** Class to implement the classic game of Tic Tac Toe.
 *  Two players alternate taking turns clearing a section of dots on the game board.
 *  The player who clears the last dot loses.
 *  @param width           Width of the game board.
 *  @param height          Height of the game board.
 *  @param cells           2D array of indicators of the boards current status.
 *  @param score           2D int array that keeps tallies on which player marked the board.
 *  @param buttons         2D array of JButtons used to display that state of the cells and to take action events. 
 *  @param BlankSpace      The white square icon for a unmarked cell on the game board.
 *  @param playerIcon      the Icon that the player uses to mark the board of the game. (X's or O's)  
 *  @param player          The current player number, either 1 or 2.
 *  @param playerOneScore  The current score for Player 1
 *  @param playerTwoScore  The current score for Player 2
 *  @author defoulser@ncat.edu Used game of Nim code from lab as a starter template model for code.
 *  @author gksidburycrawford@aggies.ncat.edu
**/
 
public class TicTac extends JFrame implements ActionListener {
  private int width;
  private int height;
  private int playerOneScore;
  private int playerTwoScore;
  private boolean[][] cells;
  private int[][] score;
  private JButton[][] buttons;
  private ImageIcon BlankSpace;
  private ImageIcon playerIcon;
  private int player;
  
  /**
   *  Constructor for the TicTac board.
   *  @param height is the number of rows in the board
   *  @param width is the number of columns in the board
  **/
  public TicTac(int height, int width) {
    this.width = width;
    this.height = height;
    cells = new boolean[height][width];
    buttons = new JButton[height][width];
    score = new int[height][width];
    playerOneScore = 1;
    playerTwoScore = 2;
    
    // TODO: Instantiate a new ImageIcon using the GreenCircle.png and assign to BlankSpace.
    // TODO: Instantiate a new ImageIcon using the GrayCircle.png and assign to clearedIcon.
    BlankSpace = new ImageIcon("WhiteSquare.jpg");
    playerIcon = new ImageIcon("XIcon.png");


    // Starting player is 1.
    player = 1;
        
    // Set up the GridBagLayout then add buttons to the pane.
    Container pane = getContentPane();
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        JButton button = new JButton();
        button.setIcon(BlankSpace);
        gbc.gridx = c;
        gbc.gridy = r;
        
        // TODO: Add the new button object to the content pane with gbc object to control GridBagLayout.
        pane.add(button, gbc);

        buttons[r][c] = button;
        
        // TODO: Add this TicTac object as the action listener for touches to the new button object.
        button.addActionListener(this);

      }
    }
    
    // Change all the cells to beginning of game style.
    startNewGame();

    pack();
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
 
  
  /**
   *  Check whether all cells are reset to true.
   *  @return Returns true if all are true
  **/ 
  private boolean isNewGame() {
    return cells[height - 1][width - 1] == true;
  }
  
  /** 
   *  Start a new game by resetting cells, showing the "new game" state.
  **/
  private void startNewGame() {
    resetCells();
    showGameState();
  }
  
  /**
   *  Show the game state in the title bar of the JFrame.
  **/
  private void showGameState() {
    boolean isNew = isNewGame();
    int whoWon = playerWon();
    if (isNew) {
      // Special message for new game
      setTitle("Tic Tac Toe: Player " + player + " goes first. Get 3 Marks in a Row first to win.");
    }
    if (whoWon == 1) {
      playerOneScore += 1;
      setTitle("Tic Tac Toe: Player 1 wins the game. Player 1: " + playerOneScore + ", Player 2: " + playerTwoScore + ".  Please click the top left button to play again.");
    } else if (whoWon == 2) {
      playerTwoScore += 1;
      setTitle("Tic Tac Toe: Player 2 wins the game. Player 1: " + playerOneScore + ", Player 2: " + playerTwoScore + ".   Please click the top left button to play again.");
    } else {
      setTitle("Tic Tac Toe: Player " + player + "'s turn now.  Click on a white space.");
    }
  }

  /** 
   *  Swap player from 1 to 2 or vice versa.
  **/
  private void swapPlayer() {
      player = 3 - player;
  }
  
  public int playerWon() {
    if ((score[0][0] == 1) && (score[0][1] == 1) && (score[0][2] == 1)) {
      return 1;
    } else if ((score[1][0] == 1) && (score[1][1] == 1) && (score[1][2] == 1)) {
      return 1;
    } else if ((score[2][0] == 1) && (score[2][1] == 1) && (score[2][2] == 1)) {
      return 1;
    } else if ((score[0][0] == 1) && (score[1][0] == 1) && (score[2][0] == 1)) {
      return 1;
    } else if ((score[0][1] == 1) && (score[2][1] == 1) && (score[2][1] == 1)) {
      return 1;
    } else if ((score[0][2] == 1) && (score[1][2] == 1) && (score[2][2] == 1)) {
      return 1;
    } else if ((score[0][0] == 1) && (score[1][1] == 1) && (score[2][2] == 1)) {
      return 1;
    } else if ((score[0][2] == 1) && (score[1][1] == 1) && (score[2][0] == 1)) {
      return 1;
    } else {
      if ((score[0][0] == 2) && (score[0][1] == 2) && (score[0][2] == 2)) {
      return 2;
    } else if ((score[1][0] == 2) && (score[1][1] == 2) && (score[1][2] == 2)) {
      return 2;
    } else if ((score[2][0] == 2) && (score[2][1] == 2) && (score[2][2] == 2)) {
      return 2;
    } else if ((score[0][0] == 2) && (score[1][0] == 2) && (score[2][0] == 2)) {
      return 2;
    } else if ((score[0][1] == 2) && (score[2][1] == 2) && (score[2][1] == 2)) {
      return 2;
    } else if ((score[0][2] == 2) && (score[1][2] == 2) && (score[2][2] == 2)) {
      return 2;
    } else if ((score[0][0] == 2) && (score[1][1] == 2) && (score[2][2] == 2)) {
      return 2;
    } else if ((score[0][2] == 2) && (score[1][1] == 2) && (score[2][0] == 2)) {
      return 2;
    } else {
      return 0;
    }
  }
  }


      
  /**
   *  Set all the cells to true, as in available to play.
  **/
  private void resetCells() {
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        cells[r][c] = true;
        buttons[r][c].setIcon(BlankSpace);
        score[r][c] = 0;
      }
    }
  }
  
  /**
   * Changes the current player icon to either an X or a O
  **/
  private void changePlayerIcon() {
      if (player == 1) {
         playerIcon = new ImageIcon("OIcon.png");
      } 
      if (player == 2) {
         playerIcon = new ImageIcon("XIcon.png");
      }
  }
  
  /**
   * Marks the button with the player's Icon, (either X or O)
   * and makes them unlclickable until the game is reset, or over
   * @param row the x position of the button 
   * @param col the y position of the button
  **/
  private void markSpace(int row, int col) {
    cells[row][col]= false;
    buttons[row][col].setIcon(playerIcon);
    score[row][col] = player;
  }
  
  
  /**
   *  Find the (column, row) == (width, height) dimension of the button that was clicked.
   *  @param button: the clicked button.
   *  @return Returns the generated Dimension object (width, height) of the button's position, or null.
  **/
  private Dimension findButtonDimension(JButton button) {
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        if (buttons[r][c] == button) {
          return new Dimension(c, r);
        }
      }
    }
    return null;
  }

  /**
   *  Method to handle GUI events implementing ActionListener interface.
   *  @param event: the GUI event to handle.
  **/
  public void actionPerformed(ActionEvent event) {
    if (!(event.getSource() instanceof JButton)) {
      System.out.println("Event source not a JButton.");
      return;
    }
    // Find the row and column of the button generating the event.
    Dimension where = findButtonDimension((JButton)event.getSource());
    if (where == null) {
      return;
    }
    System.out.println("Clicked on row " + where.height + ", column " + where.width);
    if (cells[where.height][where.width]) {
         score[where.height][where.width] = player;
         markSpace(where.height, where.width);
         changePlayerIcon();
         swapPlayer();
         showGameState();
    } else {
      if (where.height == 0 && where.width == 0) {
        startNewGame();
      }
    }
  }
  
  /**
   *  Main program to play the game Tic Tac Toe.
   *  @param args: Optional pair of integers giving width and height of the game board.
  **/
  public static void main(String[] args) {
    int width = 3;
    int height = 3;
    if (args.length == 2) {
      width = Integer.parseInt(args[0]);
      height = Integer.parseInt(args[1]);
    } else {
      System.out.print("Usage: provide width and height command line args. Defaulting to ");
      System.out.println(width + "x" + height);
    }
    TicTac game = new TicTac(height, width);
  }
}
