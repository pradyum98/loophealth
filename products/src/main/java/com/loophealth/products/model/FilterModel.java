package com.loophealth.products.model;

import java.util.ArrayList;

public class FilterModel {
	private ArrayList<String> brand;
	private ArrayList<String> gender;
	private ArrayList<String> primaryColour;
	private ArrayList<String> category;
	private ArrayList<String> season;
	private ArrayList<String> year;
	public ArrayList<String> getBrand() {
		return brand;
	}
	public void setBrand(ArrayList<String> brand) {
		this.brand = brand;
	}
	public ArrayList<String> getGender() {
		return gender;
	}
	public void setGender(ArrayList<String> gender) {
		this.gender = gender;
	}
	public ArrayList<String> getPrimaryColour() {
		return primaryColour;
	}
	public void setPrimaryColour(ArrayList<String> primaryColour) {
		this.primaryColour = primaryColour;
	}
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	public ArrayList<String> getSeason() {
		return season;
	}
	public void setSeason(ArrayList<String> season) {
		this.season = season;
	}
	public ArrayList<String> getYear() {
		return year;
	}
	public void setYear(ArrayList<String> year) {
		this.year = year;
	}
}
