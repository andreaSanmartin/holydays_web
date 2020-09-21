package app.service;

import app.http.HttpCodeResponse;
import app.http.HttpDescriptionResponse;
import app.http.HttpListResponse;
import app.http.HttpSimpleResponse;
import app.model.Alojamiento;
import app.model.ImagenAlojamiento;
import app.repository.AlojamientoRepository;
import app.repository.ImagenAlojamientoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**@author Christian Mendieta*/

@Service
public class ImagenAlojamientoService {

    private Cloudinary cloudinary;
    private Map<String, String> cloudinaryValues = new HashMap<>();

    @Autowired
    private AlojamientoRepository alojamientoRepository;

    @Autowired
    private ImagenAlojamientoRepository imgAlojamientoRepository;

    public ImagenAlojamientoService() {
        /*Abre una conexion con el servidor de cloudinary para consumir imagenes*/
        cloudinaryValues.put("cloud_name", "dm7i4gtgv");
        cloudinaryValues.put("api_key", "914266419434648");
        cloudinaryValues.put("api_secret", "IF6OfZHwZwx5tIAIgjeNAWG1VSQ");
        cloudinary = new Cloudinary(cloudinaryValues);
    }
    
    public HttpListResponse<ImagenAlojamiento> getImgsAlojByAlojId(Long alojamientoId){
        if(alojamientoRepository.findById(alojamientoId).isPresent()){
            List<ImagenAlojamiento> listaImg = imgAlojamientoRepository.getImgsAlojByAlojId(alojamientoId);
            if(listaImg.isEmpty())
                return new HttpListResponse<>(HttpCodeResponse.RESOURCE_NOT_FOUND, 
                        HttpDescriptionResponse.RESOURCE_NOT_FOUND, listaImg);
            return new HttpListResponse<>(HttpCodeResponse.OK, HttpDescriptionResponse.OK, listaImg);
        }else  
            return new HttpListResponse<>(HttpCodeResponse.RESOURCE_NOT_FOUND, 
                        HttpDescriptionResponse.RESOURCE_NOT_FOUND, null);       
    }

    public HttpSimpleResponse subirImagen(Long alojamientoId, MultipartFile multipartFile) throws IOException {
        if (alojamientoRepository.findById(alojamientoId).isPresent()) {
            Alojamiento alojamiento = alojamientoRepository.findById(alojamientoId).get();
            File file = convertirImagen(multipartFile);
            Map datosImg = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            file.delete();
            return guardarDatosImgAlojamiento(datosImg, alojamiento);
        }else
            return new HttpSimpleResponse(HttpCodeResponse.RESOURCE_NOT_FOUND, 
                    HttpDescriptionResponse.RESOURCE_NOT_FOUND);
    }
    
    public HttpSimpleResponse eliminarImagen(Long id) throws IOException {
        if(imgAlojamientoRepository.findById(id).isPresent()){
            ImagenAlojamiento img = imgAlojamientoRepository.findById(id).get();
            cloudinary.uploader().destroy(img.getCloudinaryImgId(), ObjectUtils.emptyMap());
            imgAlojamientoRepository.deleteById(id);
            return new HttpSimpleResponse(HttpCodeResponse.OK, HttpDescriptionResponse.OK);
        }
        return new HttpSimpleResponse(HttpCodeResponse.RESOURCE_NOT_FOUND, 
                HttpDescriptionResponse.RESOURCE_NOT_FOUND);
    }
    
    
    private HttpSimpleResponse guardarDatosImgAlojamiento(Map datosImg, Alojamiento alojamiento){
        ImagenAlojamiento imgAlojamiento = new ImagenAlojamiento();
        imgAlojamiento.setNombre((String)datosImg.get("original_filename"));
        imgAlojamiento.setImagenUrl((String)datosImg.get("url"));
        imgAlojamiento.setCloudinaryImgId((String)datosImg.get("public_id"));
        imgAlojamiento.setAlojamiento(alojamiento);
        imgAlojamientoRepository.save(imgAlojamiento);
        return new HttpSimpleResponse(HttpCodeResponse.CREATED, HttpDescriptionResponse.CREATED);
    }


    private File convertirImagen(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}
