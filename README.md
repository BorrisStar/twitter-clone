# twitter-clone

File uploading:
https://spring.io/guides/gs/uploading-files/

https://www.baeldung.com/spring-file-upload

MultipartFile:
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html


For Windows in application.yml
upload.path: e:/dir1/dir2/twitter-clone

- in MvcConfig.java
.addResourceLocations( "file:///" + uploadPath + "/" );   ( 3 /// after "file:")
