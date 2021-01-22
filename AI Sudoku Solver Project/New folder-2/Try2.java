import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Try2 {
	
	
	//Trying to do something genetic. Pls check if I was on the right track
	
	/*
	int genSize = 10;
	int numGen = 100;
	int timeoutMin = 5;
	
	public Try2(int GenerationSize, int NumGenerations, int TimeoutMin){
		GenerationSize = genSize;
		NumGenerations = numGen;
		TimeoutMin = timeoutMin;
	}
	
	ArrayList<Integer> coef = new ArrayList<Integer>();
	public int fitness(){
		int fitness = 0;
		for(int i = 0; i < s; i++){
			for(int j = i; j < s; j++){
				if(j == 0){
					fitness = matrix[i][j];
				}
				else{
					fitness += coef.get(i) * coef.get(j) * matrix[i][j];
				}
			}
		}
		return fitness;
	}
	
	public String binString(int decimal){
		
        int result = 0;
        int multiplier = 1;

        while(decimal > 0){
              int residue = decimal % 2;
              decimal     = decimal / 2;
              result      = result + residue * multiplier;
              multiplier  = multiplier * 10;
            }
            System.out.println ("binary....." + result);
        return result.toString();
    }
	
	public String convertToBinary(int[] arr){
		ArrayList<Integer> variables = new ArrayList<Integer>();
		for(int i = 1; i < arr.length; i++){
			variables.add(arr[i]);
		}
		String s = "";
		
		for(int i = 0; i < variables.size(); i++){
			s.concat(binString(variables.get(i)));
			
	}
		return s;
	
}
	*/
	
	
	static int s;
	static int[][] matrix = null;
    public static int[][] create2DIntMatrixFromFile(String filename) throws Exception {
        
    	//Read the file
        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        
        String line;
        int row = 0;
        int size = 0;
        
        buffer.readLine();
        buffer.readLine();
        while ((line = buffer.readLine()) != null) {
        	
            String[] vals = line.trim().split("\\s+");

            if (matrix == null) {
                size = vals.length;
                matrix = new int[size][size];
                s = size;
            }

            for (int col = 0; col < size; col++) {
                matrix[row][col] = Integer.parseInt(vals[col]);
            }

            row++;
        }
        
        return matrix;
    }

    //Method to Print out the File in 2D Array Format
    public static void printMatrix(int[][] matrix) {
        String str = "";
        int size = matrix.length;

        if (matrix != null) {
            for (int row = 0; row < size; row++) {
                str += " ";
                for (int col = 0; col < size; col++) {
                    str += String.format("%2d",  matrix[row][col]);
                    if (col < size - 1) {
                        str += " | ";
                    }
                }
                if (row < size - 1) {
                    str += "\n";
                    for (int col = 0; col < size; col++) {
                        for (int i = 0; i < 4; i++) {
                            str += "-";
                        }
                        if (col < size - 1) {
                            str += "+";
                        }
                    }
                    str += "\n";
                } else {
                    str += "\n";
                }
            }
        }

        System.out.println(str);
    }
    
    
    //Method that basically does everything that is needed
    public static void multiply(){
    	
    	int count = 0;
    	
    	
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	Random r = new Random();
   
    	for(int i = 1; i < s; i++){
    		arr.add(r.nextInt(256));
    	}
    	arr.add(0, 1);
   
    	// IF ALL NUMBERS IN THE GRID ARE POSITIVE, SET VARIABLE VALUES TO 255
    	if(isPositive()){
    		System.out.println("All Positive");
    		for(int i = 0; i < s; i++){
    			for(int j = i; j < s; j++){
    				for(int p = 1; p < arr.size(); p++){
    					arr.set(p, 255);
    				}
    				count = count + arr.get(i)* matrix[i][j] * arr.get(j);
    			}
    		}
    	}
    	//IF ALL NUMBERS IN THE GRID ARE NEGATIVE, SET VARIABLE VALUES TO 0
    	else if(allNegative()){
    		System.out.println("All Negative");
    		for(int i = 0; i < s; i++){
    			for(int j = i; j < s; j++){
    				for(int n = 1; n < arr.size(); n++){
    					arr.set(n, 0);
    				}
    				count = count + arr.get(i)* matrix[i][j] * arr.get(j);
    			}
    		}
    	}
    	//FOR NEGATIVE NUMBERS OR 0, SET VARIABLE VALUE TO 0
    	//FOR POSITIVE NUMBERS > 0, SET VARIABLE VALUE TO 255
    	else{
    		System.out.println("this shit");
    		for(int i = 0; i < s; i++){
    			for(int j = i; j < s; j++){
    				if(matrix[i][j] < 0){
    					if(matrix[j][i] < 0){
    						arr.set(i, 0);
    						arr.set(j, 0);
    					}
    				}
    				if(matrix[i][j] <= 0 && matrix[j][i] <=0){
    						arr.set(0, 1);
    				}
    				else if(matrix[i][j] > 0){
    						if(matrix[j][i] > 0){
    						arr.set(i, 255);
    						arr.set(j, 255);
    			}
    	   }
    				count = count + arr.get(i)* matrix[i][j] * arr.get(j);
       }
     }
   }
    	
    	for(int q = 0; q < s; q++){	
			System.out.println("The value of x" + q + " is " + arr.get(q));
		}
    	System.out.println(count);
    	
    }
    
    public static boolean isPositive(){
		
    	for(int i = 0; i < s; i++){
    		for(int j = 0; j < s; j++){
    			if(matrix[i][j] < 0 && matrix[j][i] < 0){
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }
     
    public static boolean allNegative(){
    	for(int i = 0; i < s; i++){
    		for(int j = 0; j < s; j++){
    			if(matrix[i][j] > 0 && matrix[j][i] > 0){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        int[][] matrix = null;
        System.out.println("Please enter a file name: ");
        Scanner s = new Scanner(System.in);
        
        try {
            matrix = create2DIntMatrixFromFile(s.nextLine());
        } catch (Exception e) {
        	System.out.println("File does not exist");
            e.printStackTrace();
        }

        printMatrix(matrix);
        multiply();
    }
}