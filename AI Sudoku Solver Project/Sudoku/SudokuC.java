package SudokuP1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SudokuC {

	private static int [][] getData(Scanner scanner) {
		final int rows = 9;
		final int cols = 9;
		int a[][] = new int[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				a[row][col] = scanner.nextInt();
			}
		}
		return a;
	}
	
	private static int [][] getData(String filename) throws FileNotFoundException {
		return getData(new Scanner(new File(filename)));
	}
	
	private static int [][] getData() throws FileNotFoundException { 
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter a file name Professor Thede: ");
		return getData(s.nextLine()); 
	}
	
	private static void showData(int [][] a) {
		final int rows = a.length;
		final int cols = a[0].length;
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				System.out.print("  " + a[row][col]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
    public static void main(String[] args) throws FileNotFoundException {
      
    	int [][] matrix = getData();
		showData(matrix);
        
        if(!isValid(matrix)){
        	System.out.println("Invalid puzzle");
        }
        
        else if(search(matrix)){
        	System.out.println("The solution is: ");
        	printleMatrix(matrix);
        }
        
        else{
        	System.out.println("No Solution");
        }
        
    }
    
    public static int[][] readPuzzle(){
    	Scanner s = new Scanner(System.in);
    	System.out.println("Enter the file name of the Puzzle: ");
    	int[][] matrix = new int[9][9];
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			matrix[i][j] = s.nextInt();
    		}
    	}
    	return matrix;
    }
    
    
    public static int[][] getFreeCellList(int[][] matrix){
    	//Determine the number of free cells
    	int numberOfFreeCells = 0;
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(matrix[i][j] == 0){
    				numberOfFreeCells++;
    			}
    		}
    	}
    	
    	//Storing free cell positions
    	int[][] freeCellList = new int[numberOfFreeCells][2];
    	int count = 0;
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(matrix[i][j] == 0){
    				freeCellList[count][0] = i;
    				freeCellList[count++][1] = j;
    			}
    		}
    	}
    	
    	return freeCellList;
    }
    
    //Print Values in the Matrix
    public static void printleMatrix(int[][] matrix){
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			System.out.print(matrix[i][j] + "  ");
    		}
    		System.out.println();
    	}
    }
    
    //Search for solution
    public static boolean search(int[][] matrix){
    	int[][] freeCellList = getFreeCellList(matrix);
    	if(freeCellList.length == 0){
    		return true; //No Free Cells
    	}
    	
    	int a = 0; //Start from the first free cell
    	while(true){
    		int i = freeCellList[a][0];
    		int j = freeCellList[a][1];
    		if(matrix[i][j] == 0){
    			matrix[i][j] = 1; //Fill first free cell with the number: 1
    		}
    		
    		if(isValid(i, j, matrix)){
    			if(a + 1 == freeCellList.length){
    				return true;
    			}
    			else{
    				a++;
    			}
    		}
    		
    		else if(matrix[i][j] < 9){
    			matrix[i][j] = matrix[i][j] + 1;
    		}
    		
    		else{ //backtrack
    			while(matrix[i][j] == 9){
    				if(a == 0){
    					return false;
    				}
    				matrix[i][j] = 0; //Reset to free cell
    				a--;
    				i = freeCellList[a][0];
    				j = freeCellList[a][1];
    			}
    			
    			//Fill in the Cell with the next possible value
    			matrix[i][j] = matrix[i][j] + 1;
    
    		}	
    	}
    }
    
    
    //Check if the matrix is valid or not
    public static boolean isValid(int i, int j, int[][] matrix){
    	for(int c = 0; c < 9; c++){
    		if( c != j && matrix[i][c] == matrix[i][j]){
    			return false;
    		}
    	}
    	
    	for(int r = 0; r < 9; r++){
    		if(r != i && matrix[r][j] == matrix[i][j]){
    			return false;
    		}
    	}
    	
    	//Check if matrix is valid in the 3x3 box
    	for(int r = (i / 3) * 3; r < (i / 3) * 3 + 3; r++){
    		for(int c = (j / 3) * 3; c < (j / 3) * 3 + 3; c++){
    			if(r != i && c != j && matrix[r][c] == matrix[i][j]){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static boolean isValid(int[][] matrix){
    	for(int i = 0; i < 9; i++){
    		for(int j = 0; j < 9; j++){
    			if(matrix[i][j] < 0 || matrix[i][j] > 9 || matrix[i][j] != 0 && !isValid(i, j, matrix)){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    
}



