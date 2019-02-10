package net.community.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.community.domain.model.picture.IPictureRepository;
import net.community.domain.model.picture.Picture;
import net.community.domain.model.tag.ITagRepository;
import net.community.domain.model.tag.Tag;
import net.community.domain.model.user.IUserRepository;
import net.community.domain.model.user.User;
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
	
	@Autowired
	ITagRepository tags;

	    //////////////////////////////// DEBUT PARTIE IMAGE/////////////////////////
	
	
	 private final Logger logger = LoggerFactory.getLogger(RegularController.class);

	    @Autowired
	    IFileStorageService fileStorageService;
	
	    
	    @PostMapping("/{userId}/uploadFile")
	    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long userId) throws Exception {
	        
	    	ResponseEntity<?> result = null;
	    	
	    	String mimeType = file.getContentType();
	        String type = mimeType.split("/")[0];
	        if (!type.equalsIgnoreCase("image")) {
	    		
	    		result = new ResponseEntity<String>("The file is not an image", HttpStatus.CONFLICT);
	    		
	    		return result;
	    		
	    	} else {
	    		
	    		String fileName = userId+fileStorageService.storeFile(file);
		    	
		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/regular/downloadFile/")
		                .path(fileName)
		                .toUriString();
		        
		        UploadFileResponse thisUploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri,
		                file.getContentType(), file.getSize());
		        
		        Picture picture = new Picture(fileName,"comment here",file.getSize(),users.findById(userId).get());
		    	pictures.save(picture);
		               
//		        result = new ResponseEntity  <UploadFileResponse> (thisUploadFileResponse, HttpStatus.OK);
	    		
	    		if(users.findById(userId).isPresent()){
	    			User user = users.findById(userId).get();
	    			picture.setUser(user);
	    			pictures.save(picture);
	    			user.addPicture(picture);
	    			users.save(user);
	    			
	    			result = new ResponseEntity<Picture>(picture, HttpStatus.OK);
	    			
	    		} else {
	    			
	    			result = new ResponseEntity<String>("Cannot create picture because there is no user with id" + userId, HttpStatus.NOT_FOUND);
	    		}
	    		
	    		return result;
	    		}
	    	}
	    
	  
	    	
	    @GetMapping("/{userId}/getpictures")
	    public ResponseEntity<?> getPicturesByUser(@PathVariable Long userId){

	    	ResponseEntity<?> result = null;
	    	
	    	if (users.findById(userId).isPresent()) {
				result = new ResponseEntity<List<Picture>>(users.findById(userId).get().getPictures(), HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("No user with id " + userId, HttpStatus.NOT_FOUND);
			}
			return result;
	    	
	    }
	    
	    @DeleteMapping("/{idUser}/{idPicture}")
		public ResponseEntity<?> deleteTask(@PathVariable Long idUser, @PathVariable Long idPicture) {
			ResponseEntity<?> result = null;
			if (!users.findById(idUser).isPresent()) {

				result = new ResponseEntity<String>("No user with id " + idUser, HttpStatus.NO_CONTENT);

			} else if (pictures.findById(idPicture) == null) {
				result = new ResponseEntity<String>("No picture with id " + idPicture, HttpStatus.NO_CONTENT);
			}

			else {

				User user = users.findById(idUser).get();
				Picture picture = pictures.findById(idPicture).get();
				user.removePicture(picture);
				users.save(user);

				result = new ResponseEntity<String>("", HttpStatus.OK);
			}

			return result;

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
		
		@GetMapping("/allPictures")
		public List<Picture> getAllPictures() {
			return pictures.findAll();
		}
	    
	    ////////////////////////////////FIN PARTIE IMAGE/////////////////////////
		

		@GetMapping("/allUsers")
		public List<User> getAllUsers() {
			return users.findAll();
		}
		
		@GetMapping("/allTags")
		public List<Tag> getAllTags() {
			return tags.findAll();
		}
		
		@GetMapping("/allUsersByTag/") 
		public List<User> getAllUsersByTag (@RequestBody Tag tag){
			return users.findByTagsContains(tag);
		}
		
		@GetMapping("/allTagsByUser/") 
		public List<Tag> getAllTagsByUser (@RequestBody User user){
			return tags.findByUsersContains(user);
		}
		
		@GetMapping("/userName")
		public ResponseEntity<?> getUserByName(@RequestBody String userName) {

			ResponseEntity<?> result = null;
			if (users.findByName(userName) != null) {
				result = new ResponseEntity<User>(users.findByName(userName), HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("No user with name " + userName, HttpStatus.NO_CONTENT);
			}
			return result;
		}
		
		@GetMapping("/tagName")
		public ResponseEntity<?> getTagByName(@RequestBody String tagName) {

			ResponseEntity<?> result = null;
			if (tags.findByName(tagName) != null) {
				result = new ResponseEntity<Tag>(tags.findByName(tagName), HttpStatus.OK);
			} else {
				result = new ResponseEntity<String>("No tag with name " + tagName, HttpStatus.NO_CONTENT);
			}
			return result;
		}
		
		@PutMapping("/updateTag/{idUser}/tag")
		public ResponseEntity<?> updateTag(@RequestBody Tag tag, @PathVariable Long idUser) {

			ResponseEntity<?> result = null;
			if (!users.findById(idUser).isPresent()) {
				result = new ResponseEntity<String>("No user with id " + idUser, HttpStatus.NO_CONTENT);

			} else if (tags.findByName(tag.getName()) == null) {
				result = new ResponseEntity<String>("No tag with name " + tag.getName(), HttpStatus.NO_CONTENT);
			}

			else {
				User user = users.findById(idUser).get();
				tag.addUser(user);
				tags.save(tag);
				result = new ResponseEntity<String>("", HttpStatus.OK);
			}

			return result;
		}
		
		@DeleteMapping("/removeTag/{idUser}/{idTag}")
		public ResponseEntity<?> removeTag(@PathVariable Long idUser, @PathVariable Long idTag) {
			ResponseEntity<?> result = null;
			if (!users.findById(idUser).isPresent()) {

				result = new ResponseEntity<String>("No user with id " + idUser, HttpStatus.NO_CONTENT);

			} else if (tags.findById(idTag) == null) {
				result = new ResponseEntity<String>("No tag with id " + idTag, HttpStatus.NO_CONTENT);
			}

			else {

				User user = users.findById(idUser).get();
				Tag tag = tags.findById(idTag).get();
				user.removeTag(tag);
				users.save(user);

				result = new ResponseEntity<String>("", HttpStatus.OK);
			}

			return result;

		}
		
}
