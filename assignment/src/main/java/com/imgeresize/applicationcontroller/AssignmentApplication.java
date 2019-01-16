package com.imgeresize.applicationcontroller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.imgeresize.services.ImageHandler;

/**
 * @author Roshani
 *
 */
@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.imgeresize"})
public class AssignmentApplication {

  final Logger logger = LoggerFactory.getLogger(AssignmentApplication.class);
  
  @Autowired
  ImageHandler imageHandler;

  public static void main(String[] args) {
    SpringApplication.run(AssignmentApplication.class, args);
  }

  /**
   * @param imageName
   * @param imageType
   * @return
   */
  @RequestMapping("/resize/image/{image-name}")
  public ResponseEntity<?> resizeImage(@PathVariable("image-name") String imageName,
      @RequestParam("imagetype") String imageType) {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);


    try {
      if (imageType.equals("Original") || imageType.equals("Optimized")) {
        InputStream inputStream = new FileInputStream(imageHandler.findImage(imageName, imageType));
        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);
      } else
        headers.setContentType(MediaType.TEXT_HTML);
      logger.info("The requested predefined image type does not exist.");
      return new ResponseEntity<>("The requested predefined\n" + "image type does not exist.",
          headers, HttpStatus.NOT_FOUND);

    } catch (FileNotFoundException e) {
      headers.setContentType(MediaType.TEXT_HTML);
      logger.info("The requested source image does not exist");
      return new ResponseEntity<>("The requested source image does not exist", headers,
          HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      headers.setContentType(MediaType.TEXT_HTML);
      logger.error("The source url is down, or responds with an error code");
      return new ResponseEntity<>("The source url is down", headers,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }


  }

}
