package app.wooportal.server.test.units.core.setup.entities.image;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.ArrayPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QTestImageEntity is a Querydsl query type for TestImageEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestImageEntity extends EntityPathBase<TestImageEntity> {

  private static final long serialVersionUID = 438188720L;
  
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QTestImageEntity testImageEntity = new QTestImageEntity("testImageEntity");

  public final app.wooportal.server.core.image.QImageEntity _super = new app.wooportal.server.core.image.QImageEntity(this);

  //inherited
  public final DateTimePath<java.time.OffsetDateTime> created = _super.created;
  
  public final app.wooportal.server.test.units.core.setup.entities.base.QTestEntity parent;

  //inherited
  public final StringPath id = _super.id;

  //inherited
  public final DateTimePath<java.time.OffsetDateTime> modified = _super.modified;

  //inherited
  public final ArrayPath<byte[], Byte> image = _super.image;

  //inherited
  public final StringPath mimeType = _super.mimeType;

  public QTestImageEntity(String variable) {
    this(TestImageEntity.class, forVariable(variable), INITS);
  }

  public QTestImageEntity(Path<? extends TestImageEntity> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }
  
  public QTestImageEntity(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }
  
  public QTestImageEntity(PathMetadata metadata, PathInits inits) {
    this(TestImageEntity.class, metadata, inits);
  }
  
  public QTestImageEntity(Class<? extends TestImageEntity> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.parent = inits.isInitialized("parent") ? new app.wooportal.server.test.units.core.setup.entities.base.QTestEntity(forProperty("parent"), inits.get("parent")) : null;
  }

}

