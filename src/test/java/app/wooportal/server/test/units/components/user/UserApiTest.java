package app.wooportal.server.test.units.components.user;

import static app.wooportal.server.test.units.services.ObjectFactory.newExp;
import static app.wooportal.server.test.units.services.ObjectFactory.newFilter;
import static app.wooportal.server.test.units.services.ObjectFactory.newInstance;
import static app.wooportal.server.test.units.services.ObjectFactory.newQuery;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.security.components.user.UserEntity;

@ActiveProfiles("test")
@SpringBootTest
public class UserApiTest {

  @Autowired
  private UserSetup setup;

  private List<UserEntity> data = List.of(
      newInstance(UserEntity.class, Map.of("id", "1")),
      newInstance(UserEntity.class, Map.of("id", "2")));

  @BeforeEach
  public void init() {
    setup.init(data, null, null, null, null);
  }

  @Test
  public void readOneOk() {
    var test = newInstance(UserEntity.class, Map.of("id", "1"));
    
    var result = setup.getApi().readOne(test);
    
    assertThat(result).isPresent();
  }
  
  @Test
  public void readOneBadParam() {
    var result = catchThrowable(() -> setup.getApi().readOne(null));
    
    assertThat(result).isInstanceOf(BadParamsException.class);
  }
  
  @Test
  public void readOneNotExist() {
    var test = newInstance(UserEntity.class, Map.of("id", "x"));
    
    var result = setup.getApi().readOne(test);
    
    assertThat(result).isEmpty();
  }
  
  @Test
  public void readAllWithParams() {
    var test = newFilter(Map.of(
            "expression", newExp(Map.of(
                "entity", newQuery(Map.of(
                    "path", "id",
                    "value", "1"))))));
    
    var result = setup.getApi().readAll(test);
    
    assertThat(result.getList()).isNotEmpty();
  }
  
  @Test
  public void readAllWithoutParams() {    
    var result = setup.getApi().readAll(null);
    
    assertThat(result.getList()).isNotEmpty();
  }
  
  @Test
  public void saveOneOk() {
    var test = newInstance(UserEntity.class, Map.of(
        "email", "test@example.com",
        "password", "test",
        "fullname", "test test"
        ));
    var result = setup.getApi().saveOne(test);
    
    assertThat(result).matches(e -> e.getFullname().equals(test.getFullname())
        && e.getEmail().equals(test.getEmail())
        && e.getVerifications() != null
    );
  }

}
