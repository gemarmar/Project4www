import java.io.*;
import java.util.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class image extends HttpServlet {

	 	
		// array of supported extensions (use a List if you prefer)
			static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp","jpg"};
			
		// filter to identify images based on their extensions
			static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

				@Override
				public boolean accept(final File dir, final String name) {
				
					for (final String ext : EXTENSIONS) {
                
						if (name.endsWith("." + ext)) {
						
							return (true);
						}
					}
            
					return (false);
				}
			};
			
			static final File dir = new File("/usr/share/apache-tomcat-7.0.32/webapps/test/data");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			int k=0;
			String[] jspname;
			String[] categ;
			jspname = new String[dir.listFiles().length/2];

			categ = new String[dir.listFiles().length/2];

			if (dir.isDirectory()) {
			
				for (final File f : dir.listFiles(IMAGE_FILTER)) {
					
					BufferedImage img = null;

					try {
                    
						img = ImageIO.read(f);

						jspname[k] = f.getName();
                            getServletContext().setAttribute("Image "+k,jspname[k]);                 
						k++;

					} catch (final IOException e) {
                    // handle errors here
					}
				}
			}
		
			try{		

				File fXmlFile; 
				DocumentBuilderFactory dbFactory; 
				DocumentBuilder dBuilder; 
				Document doc; 
 
				for(int j=0; j<k; j++){

					fXmlFile= new File("/usr/share/apache-tomcat-7.0.32/webapps/test/data/"+jspname[j].substring(0,jspname[j].length()-4)+".xml");
					dbFactory= DocumentBuilderFactory.newInstance();
					dBuilder =  dbFactory.newDocumentBuilder();
					doc = dBuilder.parse(fXmlFile);
					doc.getDocumentElement().normalize();
					NodeList u = doc.getElementsByTagName("IMAGE");
					Node m = u.item(0);
					Element element = (Element) m;
					NodeList nodelist;
					Element element1;
					NodeList fstNm;
					nodelist = element.getElementsByTagName("CATEGORY");
					element1 = (Element) nodelist.item(0);
					fstNm = element1.getChildNodes();
					categ[j]=(fstNm.item(0)).getNodeValue();
                    getServletContext().setAttribute("Image "+j,jspname[j]);
				}

			}catch (Exception ex){
       
				System.out.println(ex);
			}
       
			request.setAttribute("attribute", jspname);
			request.setAttribute("categ", categ);
			Object[] obj = new Object[5];
			obj[0] = NumofUsers.getUsers();
			obj[1] = NumofImages.getNum();
			obj[2] = LastUser.getuser();
			
			request.setAttribute("obj", obj);
			
		
			try {   
            
				request.getRequestDispatcher("/upload.jsp").forward(request, response);
			}
			catch (ServletException ex) {}
			catch (IOException ex) {}
   
        
	} 

}
		
		
















