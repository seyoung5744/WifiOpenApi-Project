package com.dto;

import java.time.LocalDateTime;

public class HistoryDto {
	private Integer id;
	private Double lnt; // x좌표 
	private Double lat; // y좌표
	private String refer_dt;
	
	
	public HistoryDto(Integer id, Double lnt, Double lat, String refer_dt) {
		this.id = id;
		this.lnt = lnt;
		this.lat = lat;
		this.refer_dt = refer_dt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLnt() {
		return lnt;
	}
	public void setLnt(Double lnt) {
		this.lnt = lnt;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getRefer_dt() {
		return refer_dt;
	}
	public void setRefer_dt(String refer_dt) {
		this.refer_dt = refer_dt;
	}
	
	
}
