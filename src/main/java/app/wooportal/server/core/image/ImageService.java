package app.wooportal.server.core.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.error.ErrorService;

@Service
public class ImageService {

  private final ImageConfiguration config;
  
  private final ErrorService errorService;

  public ImageService(
      ImageConfiguration config,
      ErrorService errorService) {
    this.config = config;
    
    this.errorService = errorService;
  }
 
  public byte[] resize(ImageEntity entity) {
    if (entity != null && entity.getImageData() != null && !entity.getImageData().isBlank()) {
      byte[] image = Base64.getDecoder().decode(entity.getImageData());
      String formatType = extractFormatFromMimeType(entity.getMimeType());
      ByteArrayInputStream inputStream = new ByteArrayInputStream(image);
      BufferedImage imageBuff;
      try {
        imageBuff = ImageIO.read(inputStream);
        return needsResize(imageBuff)
            ? resize(imageBuff, formatType)
            : image;
      } catch (IOException e) {
        errorService.sendErrorMail(e);
      }
    }
    return null;
  }
  
  private String extractFormatFromMimeType(String mimeType) {
    String[] parts = mimeType.split("/");
    return parts.length > 1 ? parts[1] : parts[0];
  }

  public byte[] resizeUrlImage(URL url, String formatType) {
    if (url == null) {
      throw new IllegalArgumentException("Image url must not be null");
    }
    try {
      BufferedImage imageBuff = ImageIO.read(url);
      if (imageBuff != null) {
        formatType = formatType != null && !formatType.isBlank() ? formatType : extractFormatFromUrl(url.getPath());
        return needsResize(imageBuff)
            ? resize(imageBuff, formatType)
            : convertToByte(imageBuff, formatType);
      }
    } catch (IOException e) {
      errorService.sendErrorMail(e);
    }
    return null;
  }
  
  public String extractFormatFromUrl(String imageUrl) {
    String[] splitUrl = imageUrl.split("\\.");
    return splitUrl[splitUrl.length - 1];
  }
  
  private boolean needsResize(BufferedImage imageBuff) {
    return imageBuff.getHeight() >= config.getMaxHeight()
        || imageBuff.getWidth() >= config.getMaxWidth();
  }
  
  private byte[] resize(BufferedImage imageBuff, String formatType) 
      throws IOException {
    BufferedImage resized = Scalr.resize(
        imageBuff, Method.ULTRA_QUALITY, config.getMaxWidth(), config.getMaxWidth());
    
    return convertToByte(resized, formatType);
  }
  
  private byte[] convertToByte(BufferedImage image, String mimeType) 
      throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(image, mimeType, outputStream);
    return outputStream.toByteArray();
  }
}
