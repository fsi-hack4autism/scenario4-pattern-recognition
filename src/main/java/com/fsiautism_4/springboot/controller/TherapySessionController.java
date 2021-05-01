package com.fsiautism_4.springboot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsiautism_4.springboot.dao.AnalyzedDataDao;
import com.fsiautism_4.springboot.dao.SessionsDao;
import com.fsiautism_4.springboot.model.AnalyzedData;
import com.fsiautism_4.springboot.model.Login;
import com.fsiautism_4.springboot.model.Sessions;
import com.fsiautism_4.springboot.model.Token;
import com.google.gson.Gson;

@RestController
public class TherapySessionController {

	@Autowired
	private SessionsDao sessionsDao;

	@Autowired
	private AnalyzedDataDao analyzedDataDao;

	private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

	private Token token;
	Gson gson = new Gson();

	private Token getToken(String subscription, Login login) {
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("Ocp-Apim-Subscription-Key", subscription);

		Map<String, Object> map = new HashMap();
		map.put("email", login.getEmail());
		map.put("password", login.getPassword());

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
		ResponseEntity<Token> token = template.postForEntity("https://linedanceaigateway.azure-api.net/identity/Login",
				request, Token.class);

		this.token = token.getBody();
		return token.getBody();
	}

	@PostMapping(path = "/connectToLineDanceAI", consumes = "application/json", produces = "application/json")
	public Token connectToLineDanceAI(
			@RequestHeader(name = "Ocp-Apim-Subscription-Key", required = true) String subscription,
			@RequestBody Login login) {

		return getToken(subscription, login);
	}

	@RequestMapping("/analyzeSegmentsAndSave")
	public String analyzeSegments(
			@RequestHeader(name = "Ocp-Apim-Subscription-Key", required = true) String subscription,
			@RequestHeader(name = "Authorization", required = true) String authorizationToken,
			@RequestParam String MainSequenceId, @RequestParam String BenchmarkSequenceId) throws Exception {

		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		headers.add("Ocp-Apim-Subscription-Key", subscription);

		headers.set("Authorization", authorizationToken);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> response = template.exchange(
				"https://linedanceaigateway.azure-api.net/analysis/Analyze?" + "MainSequenceId=" + MainSequenceId + "&"
						+ "BenchmarkSequenceId=" + BenchmarkSequenceId + "&" + "",
				HttpMethod.GET, request, String.class);
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);
		AnalyzedData analyzedData = new AnalyzedData();
		analyzedData.setOverallSimilarity(jsonNode.get("result_Info").get("similarity").asDouble());
		analyzedData.setS1(jsonNode.get("result_Info").get("s1").asDouble());
		analyzedData.setS2(jsonNode.get("result_Info").get("s2").asDouble());
		Iterator<JsonNode> nodes = jsonNode.get("result_Info").get("benchmarkSegmentsIndecies").elements();
		int benchmarkCurveSegmentStartIndex = 0;
		int benchmarkCurveSegmentEndIndex = 0;
		//choose the first segment
		if (nodes.hasNext()) {
			JsonNode childNode = nodes.next();
			benchmarkCurveSegmentStartIndex = childNode.get("startedAt").asInt();
			benchmarkCurveSegmentEndIndex = childNode.get("endedAt").asInt();
		}
		nodes = jsonNode.get("result_Info").get("mainSegmentsIndecies").elements();
		int mainCurveSegmentStartIndex = 0;
		int mainCurveSegmentEndIndex = 0;
		//choose the first segment
		if (nodes.hasNext()) {
			JsonNode childNode = nodes.next();
			mainCurveSegmentStartIndex = childNode.get("startedAt").asInt();
			mainCurveSegmentEndIndex = childNode.get("endedAt").asInt();
		}

		String url = "https://linedanceaigateway.azure-api.net/analysis/AnalyzeSegment";
		HttpEntity<String> segmentRequest = new HttpEntity<String>(headers);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("MainSequenceId", MainSequenceId).queryParam("BenchmarkSequenceId", BenchmarkSequenceId)
				.queryParam("BenchmarkSeqFromIndex", benchmarkCurveSegmentStartIndex)
				.queryParam("BenchmarkSeqToIndex", benchmarkCurveSegmentEndIndex)
				.queryParam("MainSeqFromIndex", mainCurveSegmentStartIndex)
				.queryParam("MainSeqToIndex", mainCurveSegmentEndIndex);

		ResponseEntity<String> newResponse = template.exchange(uriBuilder.toUriString(), HttpMethod.GET, segmentRequest,
				String.class);
		json = newResponse.getBody();
		//Persist the results into cosmosDB
		jsonNode = objectMapper.readTree(json);
		objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		String benchArr = jsonNode.get("result_Info").get("segmentBenchmarkPCAout").toString();
		double[] benchmarkCurve = objectMapper.readValue(benchArr, double[].class);
		analyzedData.setBmCurve(benchmarkCurve);
		String testArr = jsonNode.get("result_Info").get("segmentInSequencePCAout").toString();
		double[] testCurve = objectMapper.readValue(testArr, double[].class);
		analyzedData.setTestCurve(testCurve);
		analyzedData.setDescription("end to end");
		analyzedData.setBmCurveName(BenchmarkSequenceId);
		analyzedData.setTestCurveName(MainSequenceId);
		String result = saveAnalyzedData(analyzedData);
		return result;
	}

	@PostMapping(path = "/saveAnalyzedData", consumes = "application/json", produces = "application/json")
	public String saveAnalyzedData(@RequestBody AnalyzedData data) throws Exception {

		String message = analyzedDataDao.saveData(data);
		return message;
	}

	@PostMapping(path = "/queryData", consumes = "application/json", produces = "application/json")
	public List<AnalyzedData> queryData(@RequestBody AnalyzedData data) throws Exception {

		List<AnalyzedData> dataList = analyzedDataDao.queryData(data.getBmCurveName(), data.getTestCurveName());
		return dataList;
	}

	@RequestMapping("/insertSession")
	public String insertSessionData(@RequestParam int patientId, @RequestParam String assetId,
			@RequestParam String blobUri, @RequestParam String sessionDate)
			throws ParseException, IOException, SQLException {

		Sessions oneSession = new Sessions();
		oneSession.setPatientId(patientId);
		oneSession.setAssetId(assetId);
		oneSession.setBlobUri(blobUri);
		oneSession.setSessionDate(format.parse(sessionDate));
		return sessionsDao.insertSessionsData(oneSession);
	}

}