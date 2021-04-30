package com.fsiautism_4.springboot.model;

public class AnalyzedData {
	private String id;
	private String bmCurveName;
	private String testCurveName;
	private double overallSimilarity;
	private double s1;
	private double s2;
	private double[] bmCurve;
	private double[] testCurve;
	private String description;
	public double getOverallSimilarity() {
		return overallSimilarity;
	}
	public void setOverallSimilarity(double overallSimilarity) {
		this.overallSimilarity = overallSimilarity;
	}
	public double getS1() {
		return s1;
	}
	public void setS1(double s1) {
		this.s1 = s1;
	}
	public double getS2() {
		return s2;
	}
	public void setS2(double s2) {
		this.s2 = s2;
	}
	public double[] getBmCurve() {
		return bmCurve;
	}
	public void setBmCurve(double[] bmCurve) {
		this.bmCurve = bmCurve;
	}
	public double[] getTestCurve() {
		return testCurve;
	}
	public void setTestCurve(double[] testCurve) {
		this.testCurve = testCurve;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBmCurveName() {
		return bmCurveName;
	}
	public void setBmCurveName(String bmCurveName) {
		this.bmCurveName = bmCurveName;
	}
	public String getTestCurveName() {
		return testCurveName;
	}
	public void setTestCurveName(String testCurveName) {
		this.testCurveName = testCurveName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
