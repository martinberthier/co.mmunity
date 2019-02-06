package net.community.controller;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.user.IUserRepository;
import net.community.payload.UploadFileResponse;
import net.community.service.IFileStorageService;

@CrossOrigin
@RestController
@RequestMapping("/regular")
public class RegularController {
	
	@Autowired
	IPictureRepository pictures;
	
	@Autowired
	IUserRepository users;

	    //////////////////////////////// DEBUT PARTIE IMAGE/////////////////////////
	
	
	 private final Logger logger = LoggerFactory.getLogger(RegularController.class);

	    @Autowired
	    IFileStorageService fileStorageService;
	    

	    @PostMapping("/uploadFile")
	    //@PathVariable Long userId, 
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
	        
	    	
	    	if(isJpgOrPng(file)!=true) {
	    		
	    		String fileName = fileStorageService.storeFile(file);
	    		
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/blabla/downloadFile/")
		                .path(fileName)
		                .toUriString();
	
		        return new UploadFileResponse(fileName, fileDownloadUri,
		                file.getContentType(), file.getSize());
	    		
	    	} else {
	    		
	    	
		    	String fileName = fileStorageService.storeFile(file);
	
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/regular/downloadFile/")
		                .path(fileName)
		                .toUriString();
	
		        return new UploadFileResponse(fileName, fileDownloadUri,
		                file.getContentType(), file.getSize());
	    	
	    	}
	    	
	    	
	    }
	    
//	    //la V1
//	    @PostMapping("/uploadFile")
//	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//	        String fileName = fileStorageService.storeFile(file);
//
//	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//	                .path("/regular/downloadFile/")
//	                .path(fileName)
//	                .toUriString();
//
//	        return new UploadFileResponse(fileName, fileDownloadUri,
//	                file.getContentType(), file.getSize());
//	    }

//	    @PostMapping("/uploadMultipleFiles")
//	    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//	        return Arrays.asList(files)
//	                .stream()
//	                .map(file -> uploadFile(file))
//	                .collect(Collectors.toList());
//	    }

	    
	    public boolean isJpgOrPng(MultipartFile multipartFile) throws IOException{
			
			//conversion multipartFile to file
			File file = new File(multipartFile.getOriginalFilename());
			file.createNewFile(); 
		    FileOutputStream fos = new FileOutputStream(file); 
		    fos.write(multipartFile.getBytes());
		    fos.close(); 
		    
		    
			DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
	        int fileSignature = input.readInt();
	            input.close();
	        if (fileSignature == 0xffd8ffe0) {
	            //File is jpeg
	            return true;
	        } else if(fileSignature == 0x89504E47){
	            //file is in PNG
	            return true;
			} else {
				return false;
			}
		}

		@GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	    
	    ////////////////////////////////FIN PARTIE IMAGE/////////////////////////
}
