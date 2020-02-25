package courses;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String descripton;

	@ManyToMany
	private Collection<Topic> topics;
	
	public Course() {
		
	}
	
	public Course(String name, String description, Topic...topics) {
		this.name = name;
		this.descripton = description;
		this.topics = new HashSet<>(Arrays.asList(topics));

	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return descripton;
	}

	public long getId() {
		return id;
	}

	public Collection<Topic> getTopics() {
		return topics;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
