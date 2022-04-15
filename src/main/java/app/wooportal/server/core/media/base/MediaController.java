package app.wooportal.server.core.media.base;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {

  private final MediaService service;

  public MediaController(MediaService service) {
    this.service = service;
  }

  @GetMapping(value = "/media/{id}")
  public ResponseEntity<byte[]> getMedia(@PathVariable String id) throws IOException {
    return service.getMedia(id);
  }
}
