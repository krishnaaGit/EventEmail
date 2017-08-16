package mum.ea.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private Facility facility;
	private User eventOrganizer;
	private int noOfPeople;
	private boolean status;

	private List<Category> categories = new ArrayList<>();

	public Event() {
		this.name = "First Event";
		this.startDate = new Date();
		this.endDate = new Date();
	}

	public Event(String name, Date startDate, Date endDate, Facility facility, int noOfPeople, boolean status,
			List<Category> categories) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.facility = facility;
		this.noOfPeople = noOfPeople;
		this.status = status;
		this.categories = categories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public int getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(int noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public User getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(User eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}
}