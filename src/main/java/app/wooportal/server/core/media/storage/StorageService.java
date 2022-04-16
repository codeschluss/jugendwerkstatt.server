package app.wooportal.server.core.media.storage;

import java.io.IOException;

public interface StorageService {
  
  public void delete(String id, String formatType);
  
  public byte[] read(String id, String formatType) throws IOException;

  public void store(String id, String formatType, byte[] data) throws IOException;
  
}
