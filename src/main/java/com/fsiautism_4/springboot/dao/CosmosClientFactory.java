package com.fsiautism_4.springboot.dao;



import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;

public class CosmosClientFactory {
    private static final String HOST = "https://hackathon4.documents.azure.com:443/";
    private static final String MASTER_KEY = "yVbsVd9PbY3ThulybqalOXZzmhwoJDOTzHdybos6yVzy6jg0cYc2ZLqOtJuqo9hcCZchoWFsYqMP9PcpgHobug==";

    private static CosmosClient cosmosClient = new CosmosClientBuilder()
            .endpoint(HOST)
            .key(MASTER_KEY)
            .consistencyLevel(ConsistencyLevel.EVENTUAL)
            .buildClient();

    public static CosmosClient getCosmosClient() {
        return cosmosClient;
    }

}