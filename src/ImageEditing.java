/*
Assignment number : 4.1
File Name : ImageEditing.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class ImageEditing {

	/**
	 * the function reads the image and order it in 3 * arrays.
	 * 
	 * @param filename  - the name of the image
	 * @return the image represented as 3 * arrays.
	 */
	public static int[][][] read(String filename) {

		// reads all the initially data and what's not important leaves out.
		StdIn.setInput(filename);
		StdIn.readString();
		int column = StdIn.readInt();
		int row = StdIn.readInt();
		StdIn.readInt();

		// setting up the data in 3 * arrays.
		int[][][] pic = new int [row][column][3];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				pic[i][j][0] = StdIn.readInt();
				pic[i][j][1] = StdIn.readInt();
				pic[i][j][2] = StdIn.readInt();
			}
		}
		return pic;
	}
// ----------------------------------------------------------------------------
	/**
	 *  the given "show" function
	 * 
	 * @param pic - the image as 3 * arrays
	 */
	
	public static void show(int[][][] pic) {
		StdDraw.setCanvasSize(pic[0].length, pic.length);
		int height = pic.length;
		int width = pic[0].length;
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.show(30);
		for (int i = 0; i < height; i++){
		    for (int j = 0; j < width; j++){
				StdDraw.setPenColor(pic[i][j][0], pic[i][j][1], pic[i][j][2] );
				StdDraw.filledRectangle(j + 0.5, height - i - 0.5, 0.5, 0.5);
		    }
		}
		StdDraw.show();
	}	
// ----------------------------------------------------------------------------

	/**
	 * the function flip the image horizontally.
	 * 
	 * @param source -  the image as 3 * arrays
	 * @return the image flipped horizontally
	 */
	public static int[][][] flipHorizontally(int[][][] source) {

		// setting up the number of rows and columns from the arrays.
		int height = source.length;
		int width = source[0].length;
		int[][][] horizontal = new int[height][width][3];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < 3; k++) {
					horizontal[i][j][k] = source[i][width - j - 1][k];
				}
			}
		}
		return horizontal;
	}
// ----------------------------------------------------------------------------
	/**
	 * the function flip the image vertically.
	 * 
	 * @param source -  the image as 3 * arrays
	 * @return the image flipped vertically
	 */

	public static int[][][] flipVertically(int[][][] source) {
		
		// setting up the number of rows and columns from the arrays.
		int height = source.length;
		int width = source[0].length;
		int[][][] vertical = new int[height][width][3];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < 3; k++) {
					vertical[i][j][k] = source[height - i - 1][j][k];
				}
			}
		}
		return vertical;
	}
// ----------------------------------------------------------------------------

	/**
	 * getting a colorful pixel and makes it greyish.
	 * 
	 * @param pixel - a pixel from the image
	 * @return the same pixel - transfered to grey elements.
	 */
	
	public static int[] luminance(int[] pixel) {
		int r = pixel[0];
		int g = pixel[1];
		int b = pixel[2];
		for (int i = 0; i < 3; i++) {
			pixel[i] = (int) (0.299 * r + 0.587 * g + 0.114 * b);	
		}
		return pixel;
	}
// ----------------------------------------------------------------------------

	/**
	 * the function makes a colorful image -grey (using the luminance function)
	 * 
	 * @param source - the image as 3 * arrays
	 * @return the grey version of the image.
	 */
	
	public static int[][][] greyScale(int[][][] source) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				source[i][j] = luminance(source[i][j]);
			}
		}
		return source;
	}
	

	// ----------------------------------------------------------------------------
	
	/**
	 * the function makes a picture blur by connecting adjacent pixels. 
	 * 
	 * @param source -  the image as 3 * arrays
	 * @return the blur version of the image.
	 */
	
	public static int[][][] blur (int[][][] source) {
		int height = source.length;
		int width = source[0].length;
		int N = 0;

		// setting up a new 3 * arrays the there is 2 more rows and columns.
		int [][][] data = new int [height + 2][width + 2][3];

		// transfering the source matrix to the data matrix whle there is
		// a padding layer of '0'.
		for(int i = 1; i < height + 1; i++) {
			for (int j = 1; j < width + 1; j++) {
				for (int k = 0; k < 3; k++) {
					data[i][j][k] = source[i - 1][j - 1][k];	
				}
			}
		}

		// setting up the new blur version of the pic as 3 * arrays.
		for (int i = 1; i < height + 1; i++) {
			for (int j = 1; j < width + 1; j++) {
				for (int k = 0; k < 3; k++) {

					// calling the adjacent function to get the number of 
					// adjacent numbers.
					N = Adjacent( height - 1, width - 1 , i - 1 , j - 1);

					// making each pixel in "source" - blur.
					// connecting the adjacent pixels and divide it by N.
					source[i - 1][j - 1][k] = ((int)((data[i][j][k] 
					+ data[i][j + 1][k] + data[i][j - 1][k]
					+ data[i - 1][j][k] + data[i - 1][j - 1][k]
					+ data[i - 1][j + 1][k] + data[i + 1][j - 1][k] 
					+ data[i + 1][j + 1][k] + data[i + 1][j][k]) / N));
				}
			}
		}
		return source;
    }
	// ----------------------------------------------------------------------------
	
	/**
	 * a function that checks if a number is on both edges of the matrix,
	 * one edge or in the middle of the matrix and return number of adjacents.
	 * 
	 * @param height - the rows number of the image
	 * @param width - the columns number of the image.
	 * @param i - the row index
	 * @param j - the column index
	 * @return the numer of dividers - considering the index location.
	 */

	public static int Adjacent (int height, int width , int i, int j) {
	   	int N = 0;
	   	if ((j > 0) && (j < width) && (i > 0) && (i < height)) {
	   		N = 9;
	   	} else if (((j == 0) || (j == width)) && ((i == 0) || (i == height))) {
    		N = 4;
	   	} else N = 6;
	    return N;
	}
	    
	 // ----------------------------------------------------------------------------
	
	/**
	 * the function make a 3 * arrays to 2 * arrays (from 3 equal numbers to 1).
	 * then it uses the formula given and setting the edges to zero
	 * 
	 * @param image -  the image as 3 * arrays
	 * @return a 2 * arrays version with the new formula and '0' edges.
	 */
	
    public static int[][] calculateGradient (int[][][] image) {

	   	int height = image.length;
		int width = image[0].length;

		// inserting the 3 * arrays (image) into a new 2 * arrays.
		int [][] pic = new int [height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				pic[i][j] = image[i][j][1];
			}
		}

		// changing each pixel with the formula given.
		for (int i = 0; i < height; i++) {
			for (int j = 1; j < width - 1; j++) {
				pic[i][j] = (image[i][j + 1][1] - image[i][j - 1][1]);
			}
		}

		// setting the edges to '0'. 
		for (int i = 0; i < height; i++) {
			pic[i][0] = 0;
			pic[i][width - 1] = 0;
		}
		return pic;

    }
	// ----------------------------------------------------------------------------
    
    /**
     * getting the 2 * arrays and uses the formula given 
     * (changes the 2 * arrays image)
     * 
     * @param gradient  - a 2 * arrays representing the image. (after changes)
     */
    
    public static void normalize (int[][] gradient) {

		int height = gradient.length;
		int width = gradient[0].length;
		int max = 0;
		int min = 255;

		// finding the max and min values in the arrays.
		for (int i = 0; i < height; i++) {
			for (int j = 1; j < width - 1; j++) {
				max = Math.max(max, gradient[i][j]);
				min = Math.min(min, gradient[i][j]);
			}
		}

		// calculating the gardient value with the formula given.
		for (int i = 0; i < height; i++) {
			for (int j = 1; j < width - 1; j++) {
				gradient[i][j] = (int)((gradient[i][j] - min) 
										* 255 / (max - min));
			}
		}
		
	}
	// ----------------------------------------------------------------------------
    
    /**
     * uses the gradient, greyscale and the normalize function to print the 
     * image with edge dictation.
     * 
     * @param image -  the image as 3 * arrays
     * @return the modified image.
     */
    
	public static int[][][] edges (int[][][] image) {

    	int height = image.length;
		int width = image[0].length;

		// making a new 2 * arrays.
	    int[][] pic = new int [height][width];

	   	// making the image greyscale...
    	image = ImageEditing.greyScale(image);

	   	// making the new gradient image and inserting it to "pic"
	   	pic = calculateGradient(image);
		
		//calling the normalize function and returning the pic arrays.
	   	normalize(pic);

	   	// setting up the "image" arrays with the modifed "pic" arrays
		for (int i = 0; i < height ; i++) {
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < 3; k++) {
					image [i][j][k] = pic[i][j];
				}
			}
		}
		return image;
	}

}


