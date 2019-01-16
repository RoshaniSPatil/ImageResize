package com.imgeresize.services;

import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 * @author Roshani
 *
 */
@Service
public class ImageHandlerImpl implements ImageHandler{
  
  final Logger logger = LoggerFactory.getLogger(ImageHandlerImpl.class);

	
@Autowired
ResizeImage resizeImage;

	/* (non-Javadoc)
   * @see com.imgeresize.services.ImageHandler#findImage(java.lang.String, java.lang.String)
   */
	@Override
  public File findImage(String imageName, String imageType)  {
			File availableImage;
			try {
				availableImage = ResourceUtils.getFile("classpath:static/"+imageName+"_"+imageType+".jpg");
				logger.info("Fetching Existing Image from storage");
				return availableImage;
			} catch (FileNotFoundException e) {
			    logger.info("Image is optimized");
				return resizeImage.getResizedFile(imageName, imageType);
			  
			}
	}
	
}
