package cs2410.assn4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.image.Image;


/**
 * Kvarfordt-Tanner-Assn4
 * Created on 9/30/2016
 *
 * Add a short description of the class here
 *
 * @author Tanner Kvarfordt
 * @version 1.0
 */
class ImageViewerCtrl {

    /**
     * integer for iterating through images
     */
    private static int curImage = 0;

    /**
     * Scanner to read in urls from images.data
     */
    private static Scanner fileIn = null;

    /**
     * Scanner to write new urls to images.data
     */
    private static PrintWriter fileOut = null;


    /**
     * an array of strings that holds all urls from the .data file
     */
    private static ArrayList<String> urls = new ArrayList<>();

    /**
     * default constructor
     */
    ImageViewerCtrl() {
        initScanners();
    }

    /**
     * a method to initialize all necessary Scanners
     */
    private void initScanners() {
        try {
            fileIn = new Scanner(new FileReader("data/images.data"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (fileIn.hasNext()) {
            urls.add(fileIn.next());
        }
    }

    /**
     * a method to access the next image when the "next" button is clicked
     *
     * @return the next image
     */
    static Image nextImage() {
        //if there are no images to display, show an image telling the user so
        if (urls.isEmpty()) {
            ImageViewerView.disableButtons();
            return new Image(ImageViewerView.noImage);
        }

        //re-enable buttons if they were disabled
        ImageViewerView.enableButtons();

        //access the appropriate image
        ++curImage;
        if (curImage >= urls.size()) {
            curImage = 0;
            return new Image(urls.get(curImage));
        }
        return new Image(urls.get(curImage));
    }

    /**
     * a method to access the prev image when the "prev" button is clicked
     *
     * @return the previous image
     */
    static Image prevImage() {
        if (curImage == 0) {
            curImage = urls.size() - 1;
            return new Image(urls.get(curImage));
        }

        --curImage;
        return new Image(urls.get(curImage));
    }

    /**
     * a method to add a new image to the viewer
     *
     * @return the added image
     */
    static Image addImage() {
        Optional<String> result = ImageViewerView.getNewUrl.showAndWait();
        if (result.isPresent()){

            //if the our urls is empty, set curImage to 0 for proper indexing and add a new image there
            if (urls.isEmpty()){
                urls.add(0, result.get());
                ImageViewerView.enableButtons();
                ImageViewerView.getNewUrl.getEditor().clear();
                curImage = 0;
                return new Image(urls.get(curImage));
            }
            //if urls is not empty, add the new image in the proper position
            ++curImage;
            urls.add(curImage, result.get());
        }
        ImageViewerView.getNewUrl.getEditor().clear();
        ImageViewerView.enableButtons();
        return new Image(urls.get(curImage));
    }

    /**
     * a method to delete the current image
     *
     * @return the image after the deleted one
     */
    static Image delImage() {
        urls.remove(curImage);
        --curImage;
        return nextImage();
    }

    /**
     * a method that creates the necessary changes
     * to the input file before closing the program
     */
    static void quit() {
        try {
            /*the second parameter sent for FileOutputStream determines whether or not
            the PrintWriter appends to the file or deletes all contents before printing*/
            fileOut = new PrintWriter(new FileOutputStream(new File("data/images.data"), false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!urls.isEmpty()) {
            for (String url : urls) {
                fileOut.println(url);
            }
        }
        fileOut.close();
        fileIn.close();
        System.exit(0);
    }
}
