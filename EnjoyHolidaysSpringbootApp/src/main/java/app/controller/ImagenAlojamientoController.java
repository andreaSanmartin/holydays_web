package app.controller;

import app.http.HttpCode;
import app.http.HttpDescription;
import app.http.HttpListResponse;
import app.http.HttpSimpleResponse;
import app.model.ImagenAlojamiento;
import app.service.ImagenAlojamientoService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**@author Christian Mendieta*/

@CrossOrigin("http://localhost:4200")
@RequestMapping("/enjoy-holidays")
@RestController
public class ImagenAlojamientoController {
    
    @Autowired
    private ImagenAlojamientoService imgAlojamientoService;
    
    @GetMapping("/alojamientos/img/{alojamientoId}")
    public HttpListResponse<ImagenAlojamiento>getImgsAlojByAlojId(@PathVariable Long alojamientoId){
        return imgAlojamientoService.getImgsAlojByAlojId(alojamientoId);
    }
    
    @PostMapping("/alojamientos/img/{alojamientoId}")
    public HttpSimpleResponse createImgAlojamiento(
            @PathVariable Long alojamientoId, 
            @RequestParam MultipartFile multipartFile){
        try{
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage == null)
                return new HttpSimpleResponse(HttpCode.INVALID_IMAGE_FORMAT, 
                        HttpDescription.INVALID_IMAGE_FORMAT);
            return imgAlojamientoService.subirImagen(alojamientoId, multipartFile);
        }catch(IOException e){
             return new HttpSimpleResponse(HttpCode.IO_EXCEPTION, 
                        HttpDescription.IO_EXCEPTION);
        }  
    }
    
    @DeleteMapping("/alojamientos/img/{id}")
    public HttpSimpleResponse deleteImgAlojamiento(@PathVariable Long id){
        try{
            return imgAlojamientoService.eliminarImagen(id);
        }catch(IOException e){
            return new HttpSimpleResponse(HttpCode.IO_EXCEPTION, 
                        HttpDescription.IO_EXCEPTION); 
        }
    }
}
