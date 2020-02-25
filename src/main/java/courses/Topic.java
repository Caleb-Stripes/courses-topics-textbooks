package courses;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity //this annotation tells jpa that this is our entity table
public class Topic {

	// these next three lines tell JPA that the id for the object is the index
	// it will generate a value for it and the index is not needed in the
	// constructor
	// it magically does all that work for us
	@Id
	@GeneratedValue
	private long id;

	private String name;

	//Because the relationship here is that courses will contain topics but topics will not contain courses
	//courses is the owner of the relationship. So both classes require the @ManyToMany annotation, while
	//Topic requires also the mapped by element. Highlighting and hovering can provide a lot of info.
	@ManyToMany(mappedBy = "topics")
	private Collection<Course> courses;
	

	public Collection<Course> getCourses() {
		return courses;
	}

	// default no args constructor required by jpa
	// This is an empty constructor required by jpa
	public Topic() {

	}

	public Topic(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
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
		Topic other = (Topic) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
