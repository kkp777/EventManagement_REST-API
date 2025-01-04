package in.kkpit.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Event_Tbl")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
	private Long id;
	private String name;
	private String description;
	
	@Column(name="entry_Fee")
	private double fee;
	
	@JsonFormat(pattern="yyyy-MM-DD")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-DD")
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
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

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, String name, String description, double fee, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.fee = fee;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", fee=" + fee + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
}
