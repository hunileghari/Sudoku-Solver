package Proj6;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Driver 
{
	private static int WIDTH = 128;
	private static int HEIGHT = 128;
	private static int MAX_PIXEL = 255;
	
	private static int[][] orig_pixels;
	private static int[][] pixels;
	
	static int[][] aResult = new int[WIDTH][HEIGHT];
	static int[][] mResult = new int[WIDTH][HEIGHT];
	
	
	//=========================== A V E R A G E      F I L T E R ================================//

		public static int[][] average(int[][] pixels){
			
			int height = pixels.length;
			int width = pixels[0].length;
			
			//Apply Average Filter
	        for (int v = 1; v < height; v++) {
	            for (int u = 1; u < width; u++) {
	               
	                int sum = 0;
	                for (int j = -1; j <=1; j++) {
	                    for (int i = -1; i <= 1; i++) {
	                        if((u + (j) >= 0 && v + (i) >= 0 && u+(j) < width && v + (i) < height)){
	                        	int p = pixels[v+(i)][u+(j)];
	                        	sum = sum + p;
	                        }
	                    }
	                }

	                int q = (int) (sum /9);
	                aResult[v][u] = q;
	            }
	        }
	        return aResult;
		}
		
	//========================================== M E D I A N      F I L T E R ==========================================//
		
		public static int[][] meDian(int[][] pixels){
			
			ArrayList<Integer>  a = new ArrayList<>();
			int[][] temp = new int[3][3];
		    int height = pixels.length;
		    int width = pixels[0].length;
			
			for(int col = 0; col < pixels.length; col++){
				mResult[0][col] = pixels[0][col];
				mResult[width -1][col] = pixels[width - 1][col];
			}
			
			for (int row = 0 ; row < pixels.length ; row++) {
		        mResult[row][0] = pixels[0][row];
		        mResult[row][height - 1] = pixels[row][height - 1];
		    }
			
			for (int row = 1 ; row < height - 1 ; row++) {
		        for (int col = 1 ; col < width - 1 ; col++) {
		        	for(int i = -1; i <= 1; i++){
		        		for(int j = -1; j <= 1; j++){
		        			a.add(pixels[row+i][col+j]);
		        		}
		        	}
		        	Collections.sort(a);
            		mResult[row][col] = a.get(4);
            		a.clear();
		        }
		}
			return mResult;
	}
		 
		
	// =====================WRITE THE FILE FOR AVERAGE FILTER =====================//
	
	public static void writeFileForAverage( String file ) throws Exception{
		BufferedOutputStream writer = new BufferedOutputStream( new FileOutputStream( file ) );
		//BufferedWriter writer = new BufferedWriter( new FileWriter( file ) );
		//char ch;
		
		writer.write( 'P' );
		writer.write( '5' );
		writer.write( ' ' );
		writer.write( '1' );
		writer.write( '2' );
		writer.write( '8' );
		writer.write( ' ' );
		writer.write( '1' ); 
		writer.write( '2' );
		writer.write( '8' );
		writer.write( ' ' );
		writer.write( '2' );
		writer.write( '5' );
		writer.write( '5' );
		writer.write( ' ' );

		for ( int i = 0; i < WIDTH; i++ ){
			for ( int j = 0; j < HEIGHT; j++ ){
				writer.write( aResult[i][j] );	
				//if ( aResult[i][j] > 255 )
					//System.out.print( aResult[i][j] + " " );
			}
			System.out.println( );
		}
		
		writer.close();
	}
	
	//================================ WRITE THE FILE FOR MEAN FILTER =======================================//
	
	public static void writeFileForMedian( String file ) throws Exception{
		BufferedOutputStream writer = new BufferedOutputStream( new FileOutputStream( file ) );
		//BufferedWriter writer = new BufferedWriter( new FileWriter( file ) );
		//char ch;
		
		writer.write( 'P' );
		writer.write( '5' );
		writer.write( ' ' );
		writer.write( '1' );
		writer.write( '2' );
		writer.write( '8' );
		writer.write( ' ' );
		writer.write( '1' );
		writer.write( '2' );
		writer.write( '8' );
		writer.write( ' ' );
		writer.write( '2' );
		writer.write( '5' );
		writer.write( '5' );
		writer.write( ' ' );

		for ( int i = 0; i < WIDTH; i++ ){
			for ( int j = 0; j < HEIGHT; j++ ){
				writer.write( mResult[i][j] );	
				//if ( mResult[i][j] > 255 )
					//System.out.print( mResult[i][j] + " " );
			}
			System.out.println( );
		}
		
		writer.close();
	}
		// ===================== R E A D I N G     T H E    F I L E ===========================//
	public static void readFile( String file ) throws Exception
	{
		String format;
		int width, height, maxPixel;
		
		Scanner get = new Scanner( new FileReader( file ) );
		
		format = get.next();
		width = get.nextInt();
		height = get.nextInt();
		maxPixel = get.nextInt();
		
		if ( ( width != WIDTH ) || ( height != HEIGHT ) || ( maxPixel != MAX_PIXEL ) )
		{
			System.out.println( "Error in file format. Exiting..." );
			System.exit( 1 );
		}
			
		if ( format.equals("P2") )
		{
			for ( int i = 0; i < WIDTH; i++ )
			{
				for ( int j = 0; j < HEIGHT; j++ )
				{
					orig_pixels[i][j] = get.nextByte( );
				}
			}
		}
		
		if ( format.equals( "P5" ) )
		{
			get.close();
			
			DataInputStream input = new DataInputStream( new FileInputStream( file ) );

			for ( int i = 0; i < 15; i++ )
				input.readUnsignedByte();
			
			for ( int i = 0; i < WIDTH; i++ )
			{
				for ( int j = 0; j < HEIGHT; j++ )
				{
					orig_pixels[i][j] = input.readUnsignedByte();
				}
			}
			
			input.close();
		}
		
	}
	
	//================================= M A I N     F U N C T I O N ============================//
	
	public static void main( String[] args ) throws Exception
	{
		orig_pixels = new int[WIDTH][HEIGHT];
		pixels = new int[WIDTH][HEIGHT];
		
		Scanner in = new Scanner( System.in );
		
		System.out.println( "Enter a file name: ");
		String file = in.nextLine();
		in.close();
		
		readFile( file );
		
		for ( int i = 0; i < WIDTH; i++ ){
			for ( int j = 0; j < HEIGHT; j++ ){
				pixels[i][j] = orig_pixels[i][j];
			}
		}
		//Testing Methods
		
				meDian(pixels);
				average(pixels);
				writeFileForAverage( "testA.pgm" );
				writeFileForMedian("testM.pgm");
			}
	}