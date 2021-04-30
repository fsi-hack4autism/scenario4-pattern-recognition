package com.fsiautism_4.springboot.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fsiautism_4.springboot.dao.AnalyzedDataDao;
import com.fsiautism_4.springboot.dao.SessionsDao;
import com.fsiautism_4.springboot.model.AnalyzedData;
import com.fsiautism_4.springboot.model.Login;
import com.fsiautism_4.springboot.model.Sessions;
import com.fsiautism_4.springboot.model.Token;
 

@RestController
public class TherapySessionController { 
	
	@Autowired
	private SessionsDao sessionsDao;
	
	
	@Autowired
	private AnalyzedDataDao analyzedDataDao;
	
	private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
	
	 @PostMapping(path= "/connectToLineDanceAI", consumes = "application/json", produces = "application/json")
	public Token connectToLineDanceAI( @RequestHeader(name = "Ocp-Apim-Subscription-Key", required = true) String subscription,
	        @RequestBody Login login) {
		
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Ocp-Apim-Subscription-Key", subscription);

		Map<String, Object> map = new HashMap() ;
		map.put("email", login.getEmail());
		map.put("password",login.getPassword());
		
		
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
		ResponseEntity<Token> token = template.postForEntity("https://linedanceaigateway.azure-api.net/identity/Login", request, Token.class);
 
		
		return token.getBody();
	}
	
	 @PostMapping(path= "/saveAnalyzedData", consumes = "application/json", produces = "application/json")
		public String saveAnalyzedData(@RequestBody AnalyzedData data
				) throws Exception {
			
			 
		 String message =  analyzedDataDao.saveData(data);
		 return message;
		}
 
	 @PostMapping(path= "/queryData", consumes = "application/json", produces = "application/json")
		public List<AnalyzedData> queryData(@RequestBody AnalyzedData data
				) throws Exception {
			
			 
		 List<AnalyzedData> dataList =  analyzedDataDao.queryData(data.getBmCurveName(), data.getTestCurveName());
		 return dataList;
		}
	
	@RequestMapping("/insertSession")
	public String insertSessionData(@RequestParam int patientId, @RequestParam  String assetId,
			@RequestParam String blobUri, @RequestParam String sessionDate
			) throws ParseException, IOException, SQLException {
		
		Sessions oneSession = new Sessions();
		oneSession.setPatientId(patientId);
		oneSession.setAssetId(assetId);
		oneSession.setBlobUri(blobUri);
		oneSession.setSessionDate( format.parse(sessionDate));
		return sessionsDao.insertSessionsData(oneSession);
	}

}