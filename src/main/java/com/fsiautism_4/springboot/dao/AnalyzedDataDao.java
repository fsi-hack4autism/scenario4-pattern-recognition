package com.fsiautism_4.springboot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.CosmosException;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.models.ThroughputProperties;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.fsiautism_4.springboot.model.AnalyzedData;

@Repository
public class AnalyzedDataDao {
	
	private final String databaseName = "data";
    private final String containerName = "analyzedData";
    private CosmosClient client =  CosmosClientFactory.getCosmosClient();
    protected static Logger logger = LoggerFactory.getLogger(AnalyzedDataDao.class.getSimpleName());
    private CosmosDatabase database;
    private CosmosContainer container;
    
    public AnalyzedDataDao() throws Exception {
    	createDatabaseIfNotExists();
		createContainerIfNotExists();
    }

	public String saveData(AnalyzedData data) throws Exception {
		
		
		 CosmosItemRequestOptions cosmosItemRequestOptions = new CosmosItemRequestOptions();
		 if (data.getId() == null) {
			 data.setId(UUID.randomUUID().toString());
		 }
		 CosmosItemResponse<AnalyzedData> item = container.upsertItem(data, new PartitionKey(data.getBmCurveName()), cosmosItemRequestOptions);
         logger.info("Created item with request charge of {} within duration {}",
                 item.getRequestCharge(), item.getDuration());
		 
         
         
         return "Success";
		 
	}
	
	public List<AnalyzedData> queryData(String bmCurveName, String testCurveName) {
		  // Set some common query options
		List<AnalyzedData> dataList = new ArrayList<>();
        CosmosQueryRequestOptions queryOptions = new CosmosQueryRequestOptions();
        //queryOptions.setEnableCrossPartitionQuery(true); //No longer necessary in SDK v4
        //  Set query metrics enabled to get metrics around query executions
        queryOptions.setQueryMetricsEnabled(true);
            try {
            	String sql =  "SELECT * FROM items WHERE items.bmCurveName ='" + bmCurveName + "' and items.testCurveName='" + testCurveName +  "'";
            	CosmosPagedIterable<AnalyzedData> dataIterable = container.queryItems(sql,
                       queryOptions, AnalyzedData.class);
            	dataIterable.iterableByPage(10).forEach(cosmosItemPropertiesFeedResponse -> {
                    logger.info("Got a page of query result with {} items(s) and request charge of {}",
                            cosmosItemPropertiesFeedResponse.getResults().size(), cosmosItemPropertiesFeedResponse.getRequestCharge());

                    dataList.addAll( cosmosItemPropertiesFeedResponse
                        .getResults()) ;
                });
            } catch (CosmosException e) {
                logger.error("Read Item failed with", e);
            }
             
         return dataList;
    }
	
	private void createDatabaseIfNotExists() throws Exception {
        logger.info("Create database {} if not exists.", databaseName);

        //  Create database if not exists
        //  <CreateDatabaseIfNotExists>
        CosmosDatabaseResponse cosmosDatabaseResponse = client.createDatabaseIfNotExists(databaseName);
        database = client.getDatabase(cosmosDatabaseResponse.getProperties().getId());
        //  </CreateDatabaseIfNotExists>

        logger.info("Checking database {} completed!\n", database.getId());
    }

    private void createContainerIfNotExists() throws Exception {
        logger.info("Create container {} if not exists.", containerName);

        //  Create container if not exists
        //  <CreateContainerIfNotExists>
        CosmosContainerProperties containerProperties =
            new CosmosContainerProperties(containerName, "/bmCurveName");

        //  Create container with 400 RU/s
        CosmosContainerResponse cosmosContainerResponse =
            database.createContainerIfNotExists(containerProperties, ThroughputProperties.createManualThroughput(400));
        container = database.getContainer(cosmosContainerResponse.getProperties().getId());
        //  </CreateContainerIfNotExists>

        logger.info("Checking container {} completed!\n", container.getId());
    }
}
