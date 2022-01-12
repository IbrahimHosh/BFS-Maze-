// Names:
// x
//Hosh0013
import java.util.Random;
public class MyMaze{
   Cell[][] maze;
    int startRow;
    int endRow;
    public MyMaze(int rows, int cols, int startRow1, int endRow1) { // constructs MyMaze objects and gives every index in maze a cell//
        maze = new Cell[rows][cols];
        startRow = startRow1;
        endRow = endRow1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = new Cell();
            }
        }
    }
    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int rows, int cols, int startRow1, int endRow1) {
        MyMaze maze1 = new MyMaze(rows,cols,startRow1,endRow1);
        Stack1Gen<int []>stack = new Stack1Gen();
        stack.push(new int [] {startRow1,0});                             // creates an integer array stack and pushes initial point to the stack
        maze1.maze[startRow1][0].setVisited(true);
        while(!stack.isEmpty()){                                         // will keep on looping until queue is empty//
            int [] current = stack.top();
            int currentRow = current[0];                                       // keeps track of current row and column
            int currentCol = current[1];
            int rnd = getRandom(new int[] {1,2,3,4}); // pick a random number 1-4//
            boolean x = true;
            while(x == true) {
                int count = 0;
                if(currentRow + 1 < maze1.maze.length && maze1.maze[currentRow+1][currentCol].getVisited() == false){  //checks if neighbor below is in bounds and if neighbor below has been visited//
                    count++;
                }
                if(currentRow-1 >= 0 && maze1.maze[currentRow-1][currentCol].getVisited() == false){   //checks if neighbor above is in bounds and if neighbor above has been visited//
                    count++;
                }
                if(currentCol+1 < maze1.maze[0].length && maze1.maze[currentRow][currentCol+1].getVisited() == false){ //checks if neighbor to the right is in bounds and if neighbor to thr right has been visited//
                    count++;
                }
                if(currentCol-1 >= 0 && maze1.maze[currentRow][currentCol-1].getVisited() == false){      //checks if neighbor to the left is in bounds and if neighbor to the left has been visited//
                    count++;
                }
                if (rnd == 1 && currentRow - 1 >= 0 && maze1.maze[currentRow - 1][currentCol].getVisited() == false) { // if rnd == 1 select the neighbor above and if it is inbounds and if it has been visited//
                    stack.push(new int[]{currentRow - 1, currentCol});
                    maze1.maze[currentRow - 1][currentCol].setVisited(true);
                    maze1.maze[currentRow - 1][currentCol].setBottom(false);
                    x = false;
                }
                if(rnd == 2 && currentRow + 1 < maze1.maze.length && maze1.maze[currentRow+1][currentCol].getVisited() == false){ // if rnd == 2 select the neighbor below and if it is inbounds and if it has been visited//
                    stack.push(new int[] {currentRow+1,currentCol});
                    maze1.maze[currentRow + 1][currentCol].setVisited(true);
                    maze1.maze[currentRow][currentCol].setBottom(false);
                    x = false;
                }
                if (rnd == 3 && currentCol + 1 < maze1.maze[0].length && maze1.maze[currentRow][currentCol+1].getVisited() == false){ // if rnd == 3 select the right neighbor and if it is inbounds and if it has been visited//
                    stack.push(new int[] {currentRow,currentCol+1});
                    maze1.maze[currentRow][currentCol+1].setVisited(true);
                    maze1.maze[currentRow][currentCol].setRight(false);
                    x = false;
                }
                if(rnd == 4 && currentCol - 1 >= 0 && maze1.maze[currentRow][currentCol-1].getVisited() == false){ // if rnd == 4 select the left neighbor and if it is inbounds and if it has been visited//
                    stack.push(new int[] {currentRow,currentCol-1});
                    maze1.maze[currentRow][currentCol-1].setVisited(true);
                    maze1.maze[currentRow][currentCol-1].setRight(false);
                    x = false;
                }
                if(count ==  0){ // if all neighbors have been visited pop stack//
                    stack.pop();
                    x = false;
                }
                else{                                              // if none of the if statements apply select another random int.
                    rnd = getRandom(new int[] {1,2,3,4});
                }
            }
        }
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols;j++){                          // sets every cell's visited to false//
                maze1.maze[i][j].setVisited(false);
            }
        }
        return maze1;
    }
    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        String printed_maze = "|";
        for(int a = 0; a < maze[0].length; a++){     //creates very top row |---|
            printed_maze = printed_maze + "---|";
        }
        for(int i = 0; i < maze.length ; i++ ){
            String top = "";                                                  // Top and bottom are reset every new row loop
            String bottom = "|";
            for(int j = 0; j< maze[0].length;j++){
                if(j == 0 && i != startRow){             // adds | to the beginning of each row
                    top = top + "|";
                }
                if(i == startRow && j == 0 ){          // doesn't add | if at start row
                    top = top + " ";
                }
                if(maze[i][j].getVisited() == true){    // if cell has been visited add *
                    top = top + " *";
                }
                if(maze[i][j].getVisited() == false){  // if cell hasn't been visited add space
                    top = top + "  ";
                }
                if(maze[i][j].getRight() == true){                     // if there is a right border add wall but if indices at endpoint don't add wall
                    if(i == endRow && j == maze[0].length - 1){
                        top = top + "   ";
                    }
                    else{
                        top = top + " |";
                    }
                }
                if(maze[i][j].getRight() == false){ // no right border add space
                    top = top + "  ";
                }
                if(maze[i][j].getBottom() == true){ // if there is a bottom border add it to bottom string
                    bottom = bottom + "---|";
                }
                if(maze[i][j].getBottom() == false){ // if there is no bottom border add spaces
                    bottom = bottom + "   |";
                }
            }
            printed_maze = printed_maze + "\n" + top + "\n" + bottom; // adds top string and bottom string to printed maze string
        }
        System.out.print(printed_maze);
    }
    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q2Gen<int[]> queue = new Q2Gen();            //creates queue and adds start row
        queue.add(new int[] {startRow,0});
        while(queue.length() != 0){
            int [] current = queue.remove();                       // removes from queue and keeps track of current row and current column
            int currentRow = current[0];
            int currentCol = current[1];
            maze[currentRow][currentCol].setVisited(true);
            if(currentRow == endRow && currentCol == maze[0].length-1){           // if at end row and end column stop looping
                break;
            }
            if(currentRow - 1 >= 0 && maze[currentRow-1][currentCol].getVisited() == false && maze[currentRow-1][currentCol].getBottom()== false){              // if inbounds and not been visited and there is no bottom wall add to queue
                queue.add(new int[] {currentRow-1,currentCol});
            }
            if(currentCol - 1 >= 0 && maze[currentRow][currentCol-1].getVisited() == false && maze[currentRow][currentCol-1].getRight() == false){                  // if inbounds and not been visited and there is no right wall add to queue
                queue.add(new int[] {currentRow,currentCol-1});
            }
            if (currentRow + 1 < maze.length && maze[currentRow+1][currentCol].getVisited() == false && maze[currentRow][currentCol].getBottom()== false){       // if inbounds and not been visited and there is no bottom wall add to queue
                queue.add(new int[] {currentRow +1,currentCol});
            }
            if (currentCol + 1 < maze[0].length && maze[currentRow][currentCol+1].getVisited() == false && maze[currentRow][currentCol].getRight() == false){          // if inbounds and not been visited and there is no right wall add to queue
                queue.add(new int[] {currentRow,currentCol +1});
            }
        }
        printMaze();
    }

    public static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);              // function to get random int
        return array[rnd];
    }
    public static void main(String[] args){
        MyMaze maze2 = makeMaze(5,20,0,4);
        maze2.solveMaze();
    }
}
// Hosh0013