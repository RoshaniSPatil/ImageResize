package com.imgeresize.services;

import java.io.File;


/**
 * @author Roshani
 *
 *
 */
public interface ImageHandler {

  /**
   * @param imageName
   * @param imageType
   * @return
   */
  File findImage(String imageName, String imageType);

}
