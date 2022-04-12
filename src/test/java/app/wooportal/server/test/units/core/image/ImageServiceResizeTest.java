package app.wooportal.server.test.units.core.image;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.image.ImageConfiguration;
import app.wooportal.server.core.image.ImageService;
import app.wooportal.server.test.units.core.entities.image.TestImageEntity;
import app.wooportal.server.test.units.services.ImageReader;

public class ImageServiceResizeTest {
  
  private final static String basePath = "src/test/resources/pictures/";
  
  private static ImageService imageService;
  
  @BeforeAll
  public static void init() {
    var imageConfig = new ImageConfiguration();
    imageConfig.setMaxHeight(200);
    imageConfig.setMaxWidth(200);
    
    imageService = new ImageService(imageConfig, null);
  }
  
  @Test
  public void resizeOk() {
    var path = basePath + "test_232x232.jpg";
    var test = new TestImageEntity();
    test.setImage(ImageReader.readFile(path));
    test.setMimeType(ImageReader.getMimeType(path));
    
    var result = imageService.resize(test);
    
    assertThat(result).isNotEqualTo(test.getImageData());
  }
  
  @Test
  public void resizeNotNeededOk() {
    var path = basePath + "test_100x100.jpg";
    var test = new TestImageEntity();
    test.setImage(ImageReader.readFile(path));
    test.setMimeType(ImageReader.getMimeType(path));
    
    var result = imageService.resize(test);
    
    assertThat(result).isEqualTo(test.getImage());
  }
  
  @Test
  public void resizeNullEntity() {   
    var result = imageService.resize(null);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void resizeNullImage() {
    var test = new TestImageEntity();
    test.setMimeType("test");
    
    var result = imageService.resize(test);
    
    assertThat(result).isNull();
  }
  
  @Test
  public void resizeEmptyImage() {
    var test = new TestImageEntity();
    test.setImage(new byte[0]);
    test.setMimeType("test");
    
    var result = imageService.resize(test);
    
    assertThat(result).isNull();
  }
}
