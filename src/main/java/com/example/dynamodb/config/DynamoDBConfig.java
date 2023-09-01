package com.example.dynamodb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.dynamodb.repositories")

public class DynamoDBConfig {
	@Bean
	//public AmazonDynamoDB amazonDynamoDB(@Value("${aws.access-key}") String accessKey,
		//	@Value("${aws.secret-key}") String secretKey) 
	public AmazonDynamoDB amazonDynamoDB() 
	{
		String hardcodeAccess = "AKIAT6FG45EMCA53SGOY";
		String hardcodeSecret = "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";
		return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(hardcodeAccess, hardcodeSecret)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "https://dynamodb.us-east-1.amazonaws.com", // Change to your region
                        "us-east-1" // Change to your region
                ))
                .build();
	}
	public static AmazonDynamoDB amazonDynamoDB(String test) 
		{
			String hardcodeAccess = "AKIAT6FG45EMCA53SGOY";
			String hardcodeSecret = "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";
			return AmazonDynamoDBClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(hardcodeAccess, hardcodeSecret)))
	                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
	                        "https://dynamodb.us-east-1.amazonaws.com", // Change to your region
	                        "us-east-1" // Change to your region
	                ))
	                .build();
				
				//.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
        //        "https://dynamodb.us-east-1.amazonaws.com", // Change to your region
        //        "us-east-1" // Change to your region
        //))
        //.build();
		
		//return dynamoDB;
	}
}
