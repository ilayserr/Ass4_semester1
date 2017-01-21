/*
Assignment number : 4.4
File Name : BlurToNoEdges.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class BlurToNoEdges {
	
	/**
	 * the function prints T times the edge ditaction version of the image in 
	 * a likewise animiation...
	 * uses the read , edges , show , blur functions from ImageEditing class. 
	 * 
	 * @param args - gets 2 args - name  of the image , number of wanted runs.
	 */
	
	public static void main(String[] args) {
    	
    	String name = args[0];
    	int T = Integer.parseInt(args[1]);

    	// setting up the pic in 3 * arrays.
    	int[][][] pic = ImageEditing.read(name);

    	// making the pic "feature ditection" - T times
    	for (int i = 0; i < T; i++) {
    		pic = ImageEditing.edges(pic);
    		ImageEditing.show(pic); 
    		pic = ImageEditing.blur(pic);
    	}
    	
    }

}
