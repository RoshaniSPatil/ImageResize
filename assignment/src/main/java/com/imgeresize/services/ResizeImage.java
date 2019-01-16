package com.imgeresize.services;

import java.io.File;


public interface ResizeImage {

  /**
   * @param imageName
   * @param imageType
   * @return
   */
  File getResizedFile(String imageName, String imageType);

}
