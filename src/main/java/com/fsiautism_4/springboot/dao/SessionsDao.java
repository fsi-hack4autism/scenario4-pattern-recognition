package com.fsiautism_4.springboot.dao;

import org.springframework.stereotype.Repository;

import com.fsiautism_4.springboot.model.Sessions;

@Repository
public class SessionsDao {

//	@Autowired
//	@Qualifier("getJdbcTemplate")
//	private JdbcTemplate jdbcTemplate;

	public String insertSessionsData(Sessions oneSession) {

//		try {
//
//			int count = jdbcTemplate.update("insert into sessions_data values(?, ?, ?, ?)", oneSession.getPatientId(),
//					oneSession.getAssetId(), oneSession.getBlobUri(), oneSession.getSessionDate());
//
//			return "inserted " + count + " record into sessions table";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "";
	}

}
