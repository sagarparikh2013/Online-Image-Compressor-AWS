package Image_Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Image_Dao.CompressImageDao;

/**
 * Servlet implementation class compressImage
 */
public class compressImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "Uploaded_Images";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
	private static String image_path ="";
	private static String compressedFolder = "Compressed_Images";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public compressImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String uploadPath = getServletContext().getRealPath("")+UPLOAD_DIRECTORY;
		String server_path = getServletContext().getRealPath("");
		String dest_folder_path = server_path+File.separator+"Compressed_Images";
		response.setContentType("text/html");
		String c_factor_string = null;
		
		  File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
	        File compressedDir = new File(dest_folder_path);
	        if (!compressedDir.exists()) {
	        	compressedDir.mkdirs();
	        }
	 
		
		
		//-------------------------******************----------------------------
		
		
		if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            
            out.println("Error: Form must has enctype=multipart/form-data.");
            out.flush();
            return;
        }
 
        // --------------------------configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // ------------------creates the directory if it does not exist
        
      
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            int i=0;
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    
                	if(item.isFormField()){
                		c_factor_string = item.getString();
                		System.out.println("Compression factor is :" +c_factor_string);
                	}
                    if (!item.isFormField()) {
                    	// processes only fields that are not form fields
                        String fileName = item.getName();
                        image_path = fileName;

                       	image_path = uploadPath +"/"+ fileName;
                        
                        File storeFile = new File(image_path);
                        // saves the file on disk
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
          ex.printStackTrace();
        }//end of catch block
        
        //________________Got C_Factor_____&_____File Stored in server_________________________
        
        double c_factor = Double.parseDouble(c_factor_string);
        c_factor = c_factor*0.01;
		System.out.println("Float value of c_factor is :"+c_factor);
		CompressImageDao dao = new CompressImageDao();
		
		String compressedImageName = dao.compress_image(image_path, c_factor, dest_folder_path);
		String path = compressedFolder+File.separator+compressedImageName;
		HttpSession session = request.getSession();
		session.setAttribute("quality", c_factor_string);
		session.setAttribute("path", path);
		response.sendRedirect("display.jsp");
	}//end of doPost

}
