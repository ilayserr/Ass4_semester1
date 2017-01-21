/*
Assignment number : 4.3
File Name : BlurringEditor.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class BlurringEditor {
	
	/**
	 * the function blurs the image T times.
	 * uses the read, blur , show function from ImageEdting class
	 * 
	 * @param args - gets 2 args - name  of the image , number of wanted runs.
	 */
	public static void main(String[] args) {

		// getting two arguments from the user
    	String name = args[0];
    	int T = Integer.parseInt(args[1]);

    	// setting up a new 3 * arrays representing the pic
    	int[][][] pic = ImageEditing.read(name);

    	// running T times on the pic with the blur function.
    	for (int i = 0; i < T; i++) {
    		ImageEditing.blur(pic);
    	}
    	ImageEditing.show(pic);
	}
	
}