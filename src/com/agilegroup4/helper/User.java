package com.agilegroup4.helper;

/* This class represents a user 
 * with the attributes being equal to the entries in the database */
public class User {

	private int id;
	private int reputation;
	private String creation_date;
	private String display_name;
	private String email_hash;
	private String last_access_date;
	private String website_url;
	private String location;
	private int age;
	private String about_me;
	private int views;
	private int up_votes;
	private int down_votes;
	
	public User(){
		// -.-
	}

	public User(int id, int reputation, String creation_date,
			String display_name, String email_hash, String last_access_date,
			String website_url, String location, int age, String about_me,
			int views, int up_votes, int down_votes) {
		super();
		this.id = id;
		this.reputation = reputation;
		this.creation_date = creation_date;
		this.display_name = display_name;
		this.email_hash = email_hash;
		this.last_access_date = last_access_date;
		this.website_url = website_url;
		this.location = location;
		this.age = age;
		this.about_me = about_me;
		this.views = views;
		this.up_votes = up_votes;
		this.down_votes = down_votes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getEmail_hash() {
		return email_hash;
	}

	public void setEmail_hash(String email_hash) {
		this.email_hash = email_hash;
	}

	public String getLast_access_date() {
		return last_access_date;
	}

	public void setLast_access_date(String last_access_date) {
		this.last_access_date = last_access_date;
	}

	public String getWebsite_url() {
		return website_url;
	}

	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAbout_me() {
		return about_me;
	}

	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getUp_votes() {
		return up_votes;
	}

	public void setUp_votes(int up_votes) {
		this.up_votes = up_votes;
	}

	public int getDown_votes() {
		return down_votes;
	}

	public void setDown_votes(int down_votes) {
		this.down_votes = down_votes;
	}
	
	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

}
