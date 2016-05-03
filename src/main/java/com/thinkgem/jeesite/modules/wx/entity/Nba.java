package com.thinkgem.jeesite.modules.wx.entity;

import java.io.Serializable;

public class Nba implements Serializable {
	private String link1text;//视频集锦
	private String link1url;
	private String link2text;//数据统计
	private String link2url;
	private String m_time;
	private String player1;
	private String player1logo;
	private String player2;
	private String score;
	private String status;
	private String time;
	
	public String getLink1text() {
		return link1text;
	}
	public void setLink1text(String link1text) {
		this.link1text = link1text;
	}
	public String getLink1url() {
		return link1url;
	}
	public void setLink1url(String link1url) {
		this.link1url = link1url;
	}
	public String getLink2text() {
		return link2text;
	}
	public void setLink2text(String link2text) {
		this.link2text = link2text;
	}
	public String getLink2url() {
		return link2url;
	}
	public void setLink2url(String link2url) {
		this.link2url = link2url;
	}
	public String getM_time() {
		return m_time;
	}
	public void setM_time(String m_time) {
		this.m_time = m_time;
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlayer1logo() {
		return player1logo;
	}
	public void setPlayer1logo(String player1logo) {
		this.player1logo = player1logo;
	}
	@Override
	public String toString() {
		return "Nba [link1text=" + link1text + ", link1url=" + link1url + ", link2text=" + link2text + ", link2url="
				+ link2url + ", m_time=" + m_time + ", player1=" + player1 + ", player1logo=" + player1logo
				+ ", player2=" + player2 + ", score=" + score + ", status=" + status + ", time=" + time + "]";
	}
   
   
}
