package app.wooportal.server.test.units.services;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.context.ApiContextAdapter;
import graphql.language.Field;

public class TestApiContextAdapter implements ApiContextAdapter {

  @Override
  public JsonNode getSingleSaveContext() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public JsonNode getMultiSaveContext() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Field> getSingleReadContext() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Field> getMultiReadContext() {
    // TODO Auto-generated method stub
    return null;
  }

}
