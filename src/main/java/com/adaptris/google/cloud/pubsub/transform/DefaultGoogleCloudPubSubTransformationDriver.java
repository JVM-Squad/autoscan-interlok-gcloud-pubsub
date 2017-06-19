package com.adaptris.google.cloud.pubsub.transform;

import com.adaptris.core.AdaptrisMessage;
import com.adaptris.core.ServiceException;
import com.adaptris.core.metadata.MetadataFilter;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("default-google-cloud-pubsub-tranform-driver")
public class DefaultGoogleCloudPubSubTransformationDriver implements TransformationDriver {

  @Override
  public void transform(AdaptrisMessage input, TransformationDirection direction, MetadataFilter metadataFilter) throws ServiceException {
    direction.transform(input, metadataFilter);
  }
}
