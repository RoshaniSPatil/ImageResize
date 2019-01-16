# ImageResize
Image Resize is a spring boot application which optimizes the original image if the optimized image not available in the file system.

## Overview
When the optimised image is requested through the URL ,The original image is fetched from the storage and optimised to predefined image size in the properties file.

## Getting Started

Download/Clone the project  
Run the command in the terminal   
mvn spring-boot:run  

Go to th brower and type url: http://localhost:8080/resize/images/{imageName}?imagetype={imageType}  

Change the imageNmaes,imagetypes with the below available lists.
available imageNmaes:image1,image2,image3,image4,image5.
available imageTypes:Original,Optimized

example: 1.http://localhost:8080/resize/images/image1?imagetype=Original
         2.http://localhost:8080/resize/images/image1?imagetype=Optimized

## Architechture
Depending on the image type in the request the ImageHandler fetches the image from the file system else ResizeImage resizes 
the image.  
Note: Project needs to be refreshed to have the resized image in the file system available.
