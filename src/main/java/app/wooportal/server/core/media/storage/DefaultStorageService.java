package app.wooportal.server.core.media.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.config.GeneralConfiguration;

@Service
public class DefaultStorageService implements StorageService {
  
  private final StorageConfiguration storageConfig;
  
  public DefaultStorageService(
      GeneralConfiguration generalConfig,
      StorageConfiguration storageConfig) {
    this.storageConfig = storageConfig;
  }
  
  @Override
  public void delete(String id, String formatType) {
    createFile(id, formatType).delete();
  }
  
  @Override
  public byte[] read(String id, String formatType) throws IOException {
    return Files.readAllBytes(createFile(id, formatType).toPath());
  }

  @Override
  public void store(String id, String formatType, byte[] data) throws IOException {
    Files.createDirectories(Paths.get(storageConfig.getLocation()));
    Files.write(createFile(id, formatType).toPath(), data);
  }
  
  private File createFile(String id, String formatType) {
    var base = storageConfig.getLocation().endsWith("/")
        ? storageConfig.getLocation()
        : storageConfig.getLocation() + "/";
    return new File(base + id +  "." + formatType);
  }

}
