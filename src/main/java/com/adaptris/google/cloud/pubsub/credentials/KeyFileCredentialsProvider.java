package com.adaptris.google.cloud.pubsub.credentials;

import com.adaptris.core.CoreException;
import com.adaptris.core.fs.FsHelper;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

@XStreamAlias("key-file-credentials-provider")
public class KeyFileCredentialsProvider extends ScopedCredentialsProvider {

  @NotNull
  @Valid
  private String jsonKeyFile;

  @Override
  void validateArguments() throws CoreException {
    if (StringUtils.isEmpty(getJsonKeyFile())){
      throw new CoreException("Json Key File is invalid");
    }
    super.validateArguments();
  }

  @Override
  com.google.api.gax.core.CredentialsProvider createCredentialsProvider() throws CoreException{
    try {
      URL url = FsHelper.createUrlFromString(getJsonKeyFile(), true);
      File jsonKey = FsHelper.createFileReference(url);
      return FixedCredentialsProvider.create(GoogleCredentials.fromStream(new FileInputStream(jsonKey)).createScoped(getScopes()));
    } catch (Exception e) {
      throw new CoreException(e);
    }
  }

  public String getJsonKeyFile() {
    return jsonKeyFile;
  }

  public void setJsonKeyFile(String jsonKeyFile) {
    this.jsonKeyFile = jsonKeyFile;
  }

}
