package com.imgeresize.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Roshani
 *
 */
@Service
@Configuration
public class ResizeImageImpl implements ResizeImage {
  
  final Logger logger = LoggerFactory.getLogger(ResizeImageImpl.class);
  
  @Value("${optimized.height}")
  private int height;
  @Value("${optimized.width}")
  private int width;
  @Value("${image.format}")
  private String imageFormat;
  @Value("${image.location}")
  private String imageLocation;
  @Value("${image.OriginalPostfix}")
  private String originalPostfix;


  /* (non-Javadoc)
   * @see com.imgeresize.services.ResizeImage#getResizedFile(java.lang.String, java.lang.String)
   */
  @Override
  public File getResizedFile(String imageName, String imageType) {

    File resizedImageFile =
        new File(imageLocation + imageName + "_" + imageType +"."+imageFormat);
    File originalImageFile =
        new File(imageLocation + imageName + "_" + originalPostfix+"."+imageFormat);

    try {

      BufferedImage originalImage = ImageIO.read(originalImageFile);

      int type =
          originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

      BufferedImage resizeImageJpg = resizeImage(originalImage, type, height, width);

      ImageIO.write(resizeImageJpg,imageFormat, resizedImageFile);

    } catch (IOException e) {
      logger.warn(
          "There is a problem writing the new (resized) image to the storage . " + e.getMessage());
    }
    return resizedImageFile;

  }

  /**
   * @param originalImage
   * @param type
   * @param IMG_WIDTH
   * @param IMG_HEIGHT
   * @return
   */
  private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH,
      int IMG_HEIGHT) {
    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    g.dispose();

    return resizedImage;
  }

}
