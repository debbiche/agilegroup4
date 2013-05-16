package com.agilegroup4.model;

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
	}

	/*
	 * Creates a new user
	 * @param id The user id
	 * @param reputation The reputation
	 * @param creation_date The user creation date
	 * @param display_name The user display name
	 * @param email_hash The user email
	 * @param last_access_date The user last access date
	 * @param website_url The user website url
	 * @param location The user location
	 * @param age The user age
	 * @param about_me The user about text
	 * @param views The user views
	 * @param up_votes The user positive votes
	 * @param down_votes The user negative votes.
	 */
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

	/*
	 * Gets the id for this user.
	 * @returns the user id.
	 */
	public int getId() {
		return id;
	}

	/*
	 * Sets the id for this user.
	 * @param id the user id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * Gets the reputation for this user.
	 * @returns the user reputation.
	 */
	public int getReputation() {
		return reputation;
	}

	/*
	 * Sets the reputation for this user.
	 * @param reputation the user reputation.
	 */
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	/*
	 * Gets the creation date for this user.
	 * @returns the user creation date.
	 */
	public String getCreation_date() {
		return creation_date;
	}

	/*
	 * Sets the creation date for this user.
	 * @param creation_date the user creation date.
	 */
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	/*
	 * Gets the email for this user.
	 * @returns the user email.
	 */
	public String getEmail_hash() {
		return email_hash;
	}

	/*
	 * Sets the email for this user.
	 * @param email_hash the user email.
	 */
	public void setEmail_hash(String email_hash) {
		this.email_hash = email_hash;
	}

	/*
	 * Gets the last access date for this user.
	 * @returns the user last access date.
	 */
	public String getLast_access_date() {
		return last_access_date;
	}

	/*
	 * Sets the last access date for this user.
	 * @param last_access_date the user last access date.
	 */
	public void setLast_access_date(String last_access_date) {
		this.last_access_date = last_access_date;
	}

	/*
	 * Gets the website url for this user.
	 * @returns the user website url.
	 */
	public String getWebsite_url() {
		return website_url;
	}

	/*
	 * Sets the website url for this user.
	 * @param website_url the user website url.
	 */
	public void setWebsite_url(String website_url) {
		this.website_url = website_url;
	}

	/*
	 * Gets the location for this user.
	 * @returns the user location.
	 */
	public String getLocation() {
		return location;
	}

	/*
	 * Sets the location for this user.
	 * @param location the user location.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/*
	 * Gets the age for this user.
	 * @returns the user age.
	 */
	public int getAge() {
		return age;
	}

	/*
	 * Sets the age for this user.
	 * @param age the user age.
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * Gets the about for this user.
	 * @returns the user about.
	 */
	public String getAbout_me() {
		return about_me;
	}

	/*
	 * Sets the about for this user.
	 * @param about_me the about for this user.
	 */
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}

	/*
	 * Gets the views for this user.
	 * @returns the user views.
	 */
	public int getViews() {
		return views;
	}

	/*
	 * Sets the view count for this user.
	 * @param views the view count for user.
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/*
	 * Gets the positive votes for this user.
	 * @returns the user positive votes.
	 */
	public int getUp_votes() {
		return up_votes;
	}

	/*
	 * Sets the positive votes  for this user.
	 * @param up_votes the user positive votes .
	 */
	public void setUp_votes(int up_votes) {
		this.up_votes = up_votes;
	}

	/*
	 * Gets the negative votes for this user.
	 * @returns the user negative votes.
	 */
	public int getDown_votes() {
		return down_votes;
	}

	/*
	 * Sets the negative votes  for this user.
	 * @param down_votes the user negative votes .
	 */
	public void setDown_votes(int down_votes) {
		this.down_votes = down_votes;
	}
	
	/*
	 * Gets the display name for this user.
	 * @returns the user display name.
	 */
	public String getDisplay_name() {
		return display_name;
	}
	
	/*
	 * Gets the display name for this user.
	 * @returns the user display name.
	 */
	public String getFriendlyDisplayName() {
		return display_name + " (" + id + ")";
	}

	/*
	 * Sets the display name for this user.
	 * @param display_name the user display name.
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

}
