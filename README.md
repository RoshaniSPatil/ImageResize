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

Available imageNmaes:image1,image3,image5:
Available imageTypes:Original,Optimized:

examples: 
http://localhost:8080/resize/images/image1?imagetype=Original    
http://localhost:8080/resize/images/image1?imagetype=Optimized    

Please Note: File System/Project has to be refreshed to get Stored Optimized Image. And/Or Re-Run the project.

## Architechture
Depending on the image type in the request the ImageHandler fetches the image from the file system else ResizeImage resizes 
the image. 


