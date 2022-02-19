import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int row, col;
        
        System.out.print("Num. of row: ");
        row = input.nextInt();                                 // take number of raws as an input
        System.out.print("Num. of column: ");
        col = input.nextInt();                                 // take number of columns as an input
        
        int size = row * col;                                  // Total size of matrices
        int numOfMine = size / 4;                              // Number of mines
        
        String[][] displayMatrix = new String[row][col];       // Define a matrix to display on the screen
        int[][] backEndMatrix = new int[row][col];             // Define a matrix to store number of mines around the location
       
      
      // PRINT THE INITIAL STATE OF THE GAME
      
      
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                displayMatrix[i][j] = "_";
                backEndMatrix[i][j] = 0;
                System.out.print(displayMatrix[i][j] + " ");
            }
            System.out.println("");
        }
        
        System.out.println(""); 
      
      
      // LOCATIONS OF MINES
      
        
        int[] locRandRow = new int[numOfMine];                   // Define a array to store row indices of mines                        
        int[] locRandCol = new int[numOfMine];                   // Define a array to store column indices of mines
        int locRow, locCol;
        
        int count = 0;
        while (count < numOfMine) {
            locRow = (int)(Math.random() * row);
            locCol = (int)(Math.random() * col);
            
            boolean isNotRepeated = true;
            for (int i=0; i<count; i++) {
                if (locRow == locRandRow[i] && locCol == locRandCol[i]){       // If same location is randomly determined, select randomly again
                    isNotRepeated = false;
                    break;
                }
            }
            
            if (isNotRepeated) {
                locRandRow[count] = locRow;
                locRandCol[count] = locCol;
                backEndMatrix[locRow][locCol] = -1;               // Deploy mines as -1 in the backEndMatrix 
                count +=1;
            }
        }
      
      
      // DEFINE THE NECESSARY VARIABLES TO TRAVEL THROUGH "BACKENDMATRIX"
        
        int mineRow, mineCol;
        int left, right, up, down;
        boolean leftExist, rightExist, upExist, downExist;
        
        for (int i=0; i<numOfMine; i++) {
            mineRow = locRandRow[i];
            mineCol = locRandCol[i];
            
            left = mineCol - 1;                // Near-left location of a mine
            right = mineCol + 1;               // Near-right location of a mine
            up = mineRow - 1;                  // Near-up location of a mine 
            down = mineRow + 1;                // Near-down location of a mine
          
          
        // DETERMINE THE CASES OF MINE EXISTENCE
            
            if (left<0 || col <= left) {  
                leftExist = false;  
            } else {    
                leftExist = true;   
            }   
            if (right<0 || col <= right) {
                rightExist = false;
            } else {    
                rightExist = true;  
            }   
            if (up<0 || row <= up) {  
                upExist = false;    
            } else {    
                upExist = true; 
            }   
            if (down<0 || row <= down) {
                downExist = false;  
            } else {    
                downExist = true;   
            }
                
          
         // NUMBER OF MINES AROUND THE LOCATIONS NOT HAVING MINES
          
            
            if (leftExist == true) {
                if (backEndMatrix[mineRow][left] != -1) {
                    backEndMatrix[mineRow][left] +=1;
                }
            }
            if (rightExist == true) {
                if (backEndMatrix[mineRow][right] != -1) {
                    backEndMatrix[mineRow][right] +=1;
                }
            }
            if (upExist == true) {
                if (backEndMatrix[up][mineCol] != -1) {
                    backEndMatrix[up][mineCol] +=1;
                }
            }
            if (downExist == true) {
                if (backEndMatrix[down][mineCol] != -1) {
                    backEndMatrix[down][mineCol] +=1;
                }
            }
            
            if (leftExist == true && upExist == true) {
                if (backEndMatrix[up][left] != -1) {
                    backEndMatrix[up][left] +=1;
                }
            }
            if (rightExist == true && upExist == true) {
                if (backEndMatrix[up][right] != -1) {
                    backEndMatrix[up][right] +=1;
                }
            }
            if (leftExist == true && downExist == true) {
                if (backEndMatrix[down][left] != -1) {
                    backEndMatrix[down][left] +=1;
                }
            }
            if (rightExist == true && downExist == true) {
                if (backEndMatrix[down][right] != -1) {
                    backEndMatrix[down][right] +=1;
                }
            }
        }  
        
      
      // PRINT THE MOST RECENT STATE OF THE GAME 
      
      
        int inputRow, inputCol;
        int turn = 1;
        
        while (true) {
            System.out.print("Please, enter raw index: ");
            inputRow = input.nextInt();
            System.out.print("Please, enter raw index: ");
            inputCol = input.nextInt();
            
            if (backEndMatrix[inputRow][inputCol] == -1) {
                System.out.println("GAME OVER!");
                break;
            } else {
                displayMatrix[inputRow][inputCol] = Integer.toString(backEndMatrix[inputRow][inputCol]);
                for (int i=0; i<row; i++) {
                    for (int j=0; j<col; j++) {
                        System.out.print(displayMatrix[i][j] + " ");
                    }
                    System.out.println("");
                }
                
                if (turn == (size - numOfMine)){
                    System.out.println("YOU WIN!!!");
                    break;
                }
                
                turn +=1;
            }
        }
 
    }
}
