/*
Assignment number : 4.2
File Name : BasicEditor.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class BasicEditor {
	
	/**
	 * the function makes one of three actions on a image.
	 * flip it vertically , flip it horizontally , make it grey.
	 * uses the read , flipvertically, fliphorizontally, greyscale, show
	 * functions from ImageEditing class.
	 * 
	 * @param args - gets the image name , and the code of the wanted action..
	 */
    
	public static void main(String[] args) {
    	
    	String name = args[0];
    	String type = args[1];
    	int[][][] pic = ImageEditing.read(name);	
    	if (type.equals("fv")) {
    		ImageEditing.show(ImageEditing.flipVertically(pic));
    	} else if (type.equals("fh")) {
    		ImageEditing.show(ImageEditing.flipHorizontally(pic));
    	} else if (type.equals("gr")) {
    		ImageEditing.show(ImageEditing.greyScale(pic));
    	} else System.out.println("illegal input");
    }
}


