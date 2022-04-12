package app.wooportal.server.core.image;

import java.io.Serial;
import java.util.Base64;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(columnDefinition = "MEDIUMBLOB", nullable = false)
  @JsonIgnore
  private byte[] image;

  @Transient
  @JsonSerialize
  @JsonDeserialize
  private String imageData;

  @Column(name = "mime_type", nullable = false)
  private String mimeType;

  public String getImageData() {
    if (imageData != null) {
      return imageData;
    }
    if (image != null) {
      return Base64.getEncoder().encodeToString(image);
    }
    return null;
  }

  public void setImage(byte[] image) {
    this.image = image;
    this.imageData = Base64.getEncoder().encodeToString(this.image);
  }

}
