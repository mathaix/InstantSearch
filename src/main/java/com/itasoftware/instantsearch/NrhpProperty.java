package com.itasoftware.instantsearch;

import java.util.ArrayList;

public class NrhpProperty {
	
	private String propertyID;
	private String address;
	private String city;
	private ArrayList<String> names = new ArrayList<String>();
	private String state;
	
	public NrhpProperty(String Id) {
		this.propertyID = Id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Integer getId() {
		return Integer.parseInt(this.propertyID);
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public Iterable<String> getNames(){
		return names;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void addName(String name) {
		this.names.add(name);
	}
	
	@Override
    public int hashCode() {
        return Integer.parseInt(this.propertyID);
    }
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(propertyID);
		buffer.append("\n");
		for(String name: names) {
			buffer.append(name);
			buffer.append("\n");
		}
		buffer.append(address);
		buffer.append("\n");
		buffer.append(city);
		buffer.append("\n");
		buffer.append(state);
		return buffer.toString();
	}
}
