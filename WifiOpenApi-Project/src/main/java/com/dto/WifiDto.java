package com.dto;

public class WifiDto {
	private String mgr_no; // 관리번호
	private String wrdofc; // 자치구
	private String wifi_name; // 와이파이명
	private String road_adres; // 도로명주소
	private String detail_adres; // 상세주소
	private String install_floor; // 설치위치(층)
	private String install_org; // 설치기관
	private String install_type; // 설치유형
	private String service_type; // 서비스구분
	private String net_type; // 망종류
	private String install_year; // 설치년도
	private String inout_door; // 실내외구분
	private String access_env; // wifi 접속 환경
	private Double lnt; // x좌표 
	private Double lat; // y좌표
	private String work_dt; // 작업일자
	private Double distance;
	
	
	public WifiDto(Double distance, String mgr_no, String wrdofc, String wifi_name, String road_adres, String detail_adres,
			String install_floor, String install_org, String install_type, String service_type, String net_type,
			String install_year, String inout_door, String access_env, Double lnt, Double lat, String work_dt) {
		this.distance = distance;
		this.mgr_no = mgr_no;
		this.wrdofc = wrdofc;
		this.wifi_name = wifi_name;
		this.road_adres = road_adres;
		this.detail_adres = detail_adres;
		this.install_floor = install_floor;
		this.install_org = install_org;
		this.install_type = install_type;
		this.service_type = service_type;
		this.net_type = net_type;
		this.install_year = install_year;
		this.inout_door = inout_door;
		this.access_env = access_env;
		this.lnt = lnt;
		this.lat = lat;
		this.work_dt = work_dt;
	}
	
	public Double getDistance() {
		return distance ;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getMgr_no() {
		return mgr_no;
	}
	public void setMgr_no(String mgr_no) {
		this.mgr_no = mgr_no;
	}
	public String getWrdofc() {
		return wrdofc;
	}
	public void setWrdofc(String wrdofc) {
		this.wrdofc = wrdofc;
	}
	public String getWifi_name() {
		return wifi_name;
	}
	public void setWifi_name(String wifi_name) {
		this.wifi_name = wifi_name;
	}
	public String getRoad_adres() {
		return road_adres;
	}
	public void setRoad_adres(String road_adres) {
		this.road_adres = road_adres;
	}
	public String getDetail_adres() {
		return detail_adres;
	}
	public void setDetail_adres(String detail_adres) {
		this.detail_adres = detail_adres;
	}
	public String getInstall_floor() {
		return install_floor;
	}
	public void setInstall_floor(String install_floor) {
		this.install_floor = install_floor;
	}
	public String getInstall_org() {
		return install_org;
	}
	public void setInstall_org(String install_org) {
		this.install_org = install_org;
	}
	public String getInstall_type() {
		return install_type;
	}
	public void setInstall_type(String install_type) {
		this.install_type = install_type;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getNet_type() {
		return net_type;
	}
	public void setNet_type(String net_type) {
		this.net_type = net_type;
	}
	public String getInstall_year() {
		return install_year;
	}
	public void setInstall_year(String install_year) {
		this.install_year = install_year;
	}
	public String getInout_door() {
		return inout_door;
	}
	public void setInout_door(String inout_door) {
		this.inout_door = inout_door;
	}
	public String getAccess_env() {
		return access_env;
	}
	public void setAccess_env(String access_env) {
		this.access_env = access_env;
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
	public String getWork_dt() {
		return work_dt;
	}
	public void setWork_dt(String work_dt) {
		this.work_dt = work_dt;
	}
	
	
	
	
	
}
