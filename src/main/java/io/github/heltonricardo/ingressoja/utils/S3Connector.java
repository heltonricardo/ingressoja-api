package io.github.heltonricardo.ingressoja.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

public class S3Connector {

  private static final String bucketName = "ingresso-ja";

  private static final String baseUrl =
      "https://ingresso-ja.s3.us-east-2.amazonaws.com/";

  private static final AWSCredentials credentials = new BasicAWSCredentials(
      "AKIAQ3HRRU3JBMMEWI4I",
      "mnmV0aKMxHY883bzeV6FH4GsEDFsCan8trI1wWDt"
  );

  private static final AmazonS3 s3client = AmazonS3ClientBuilder
      .standard()
      .withCredentials(new AWSStaticCredentialsProvider(credentials))
      .withRegion(Regions.US_EAST_2)
      .build();

  public static String upload(MultipartFile file, String nomeArquivo) {

    try {
      s3client.putObject(
          new PutObjectRequest(
              bucketName,
              nomeArquivo,
              file.getInputStream(),
              new ObjectMetadata())
      );
    } catch (Exception e) {
      return null;
    }

    return baseUrl + nomeArquivo;
  }
}
