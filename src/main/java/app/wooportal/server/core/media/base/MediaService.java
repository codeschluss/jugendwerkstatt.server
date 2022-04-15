package app.wooportal.server.core.media.base;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.image.ImageService;
import app.wooportal.server.core.media.storage.StorageService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MediaService extends DataService<MediaEntity, MediaPredicateBuilder> {
  
  private final List<String> imageFormats = List.of("bmp", "gif", "png", "tiff", "tiff", "jpeg");
  
  private final ImageService imageService;
  
  private final StorageService storageService;

  public MediaService(
      DataRepository<MediaEntity> repo, 
      MediaPredicateBuilder predicate,
      ImageService imageService,
      StorageService storageService) {
    super(repo, predicate);
    
    this.imageService = imageService;
    this.storageService = storageService;
  }
  
  @Override
  public void postSave(MediaEntity entity, MediaEntity newEntity, JsonNode context) {
    if (newEntity.getBase64() != null && !newEntity.getBase64().isBlank()) {
      byte[] data = Base64.getDecoder().decode(newEntity.getBase64());
      var formatType = MediaHelper.extractFormatFromMimeType(newEntity.getMimeType());
      if (isImage(newEntity.getMimeType())) {
        data = imageService.resize(data, formatType);
      }
      
      if (entity.getId() != null && !entity.getId().isBlank()) {
        storageService.delete(entity.getId(), formatType);
      }
      try {
        storageService.store(entity.getId(), data, formatType);
      } catch (IOException e) {
        //TODO: handle errors
      }
    }
  }
  
  private boolean isImage(String mimeType) {
    return imageFormats.stream().anyMatch(format -> mimeType.toLowerCase().contains(mimeType));
  }

  public ResponseEntity<byte[]> getMedia(String id) throws IOException {
    var result = getById(id);
    
    if (result.isEmpty()) {
      throw new NotFoundException("media does not exist", id);
    }
    var formatType = MediaHelper.extractFormatFromMimeType(result.get().getMimeType());
    return ResponseEntity.ok().headers(createHeader(result.get().getName(), formatType))
      .contentType(MediaType.parseMediaType(result.get().getMimeType()))
      .body(storageService.read(id, formatType));
  }
  
  public HttpHeaders createHeader(String name, String formatType) {
    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name + "." + formatType);
    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    header.add("Pragma", "no-cache");
    header.add("Expires", "0");
    return header;
  }

}
