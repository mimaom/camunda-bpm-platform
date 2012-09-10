package com.camunda.fox.cycle.api.connector;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.camunda.fox.cycle.api.connector.ConnectorNode.ConnectorNodeType;
import com.camunda.fox.cycle.entity.ConnectorConfiguration;


public abstract class Connector {
  
  public enum ConnectorContentType  {
    DEFAULT,
    PNG
  }
  
  private ConnectorConfiguration configuration;
  
  public abstract List<ConnectorNode> getChildren(ConnectorNode parent);
  
  public abstract ConnectorNode getRoot();
  
  public abstract ConnectorNode getNode(String id);
  
  public InputStream getContent(ConnectorNode node) {
    return getContent(node, ConnectorContentType.DEFAULT);
  }
  
  public List<ConnectorContentType> getSupportedTypes() {
    ConnectorContentType[] types = {ConnectorContentType.DEFAULT};
    return Arrays.asList(types);
  }
  
  /**
   * Method to check if a specific content type is currently available in the connector,
   * default assumption is that all supported content types are always available
   * 
   * @param node to check content availabilty for
   * @param type content type
   * @return true if content type is currently available for this node, false otherwise
   */
  public boolean isContentAvailable(ConnectorNode node, ConnectorContentType type) {
    return true;
  }
  
  public abstract InputStream getContent(ConnectorNode node, ConnectorContentType type); 
  
  public abstract ConnectorNode createNode(String id, String label, ConnectorNodeType type);
  
  public abstract void deleteNode(String id);
  
  public abstract void updateContent(ConnectorNode node, InputStream newContent) throws Exception;
  
  public void login(String userName, String password) {
  }
  
  public void dispose() {
  }
  
  public boolean needsLogin() {
    return false;
  }

  public ConnectorConfiguration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(ConnectorConfiguration configuration) {
    this.configuration = configuration;
  }
  
  public void init(ConnectorConfiguration config) {
  }
  
  public Long getId() {
    return getConfiguration().getId();
  }
  
  public String getMimeType (ConnectorContentType type) {
    switch (type) {
    case PNG:
      return "image/png";
    default:
      return "application/xml";
    }
  }
  
}
