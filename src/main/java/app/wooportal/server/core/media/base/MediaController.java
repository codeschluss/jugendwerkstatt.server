package app.wooportal.server.core.media.base;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;

@RestController
public class MediaController {

  private final MediaService service;

  public MediaController(MediaService service) {
    this.service = service;
  }

  @GetMapping(value = "/api/media/{id}")
  @ApprovedAndVerifiedPermission
  public ResponseEntity<byte[]> getMedia(@PathVariable String id) throws IOException {
    return service.getMedia(id);
  }
  
  @GetMapping(value = "/api/media/download/{id}")
  @ApprovedAndVerifiedPermission
  public ResponseEntity<byte[]> download(@PathVariable String id) throws IOException {
    return service.download(id);
  }
  
  @PostMapping(value = "/api/media/pdf")
  @ApprovedAndVerifiedPermission
  public ResponseEntity<byte[]> generatePdf(@RequestBody MediaHtmlDto content) throws IOException {
    return service.generatePdf(content);
  }
}
