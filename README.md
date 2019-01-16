# ImageResize
Image Resize is a spring boot application which optimizes the original image if the optimized image not available in the file system.
## Overview
When the optimised image is requested through the URL ,The original image is fetched from the file system and optimised to predefined image size in the properties file.
## Getting Started
Download/Clone the project  
Run the command in the terminal   
mvn spring-boot:run.  
Go to th brower and type http://localhost:8080/resize/image/Image1?imagetype=Original(To get the original image)  
Change the imagetype=Optimized(To get the optimized image)
## Architechture
Depending on the image type in the request the ImageHandler fetches the image from the file system else ResizeImage resizes 
the image.  
Note: Project needs to be refreshed to have the resized image in the file system available.
