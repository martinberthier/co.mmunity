package net.community.domain.model.picture;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import net.community.domain.model.user.User;
import net.community.payload.UploadFileResponse;

@Entity
@Data
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String pictureComment;
	
	private Long size;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	@JoinColumn(name = "uploadFileresponse_id")
	private UploadFileResponse thisUploadFileResponse;

	protected Picture() {}

	public Picture(String name, String pictureComment, Long size, User user, UploadFileResponse thisUploadFileResponse) {
		super();
		this.name = name;
		this.pictureComment = pictureComment;
		this.size = size;
		this.user = user;
		this.thisUploadFileResponse = thisUploadFileResponse;
	}

	public boolean isJpg(MultipartFile multipartFile) throws IOException{
		
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

	
	
	
	
}
