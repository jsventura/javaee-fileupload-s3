package pe.isil.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pe.isil.service.S3Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UploadFileServlet", urlPatterns = "/upload")
public class UploadFileServlet extends HttpServlet {

    S3Manager s3Manager = new S3Manager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multipart = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(req);

                for (FileItem item : multipart){
                    if(!item.isFormField()){
                        s3Manager.uploadFileToS3(item.getName(), item.getInputStream());
                    }
                }

                req.setAttribute("message", "cargó satisfactoriamente!");

            }catch (Exception e){
                req.setAttribute("message", "fallo la carga");
            }

        }else{
            req.setAttribute("message", "Lo siento solo soporta uploadfile");
        }

        req.getRequestDispatcher("/resultado.jsp").forward(req, resp);
    }
}
