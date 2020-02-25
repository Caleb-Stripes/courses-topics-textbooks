package courses;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private TopicRepository topicRepo;
	
	@Resource
	private CourseRepository courseRepo;

	@Test
	public void shouldSaveAndLoadTopic() {
		Topic topic = topicRepo.save(new Topic("topic"));
		long topicId = topic.getId();

		entityManager.flush(); // forces jpa to hit the database when we try to find it
		entityManager.clear();

		// topicRepo. will show us all the methods that we get from extending the
		// CrudRepository Yay!
		// so many new toys
		Optional<Topic> result = topicRepo.findById(topicId);
		result.get();
		assertThat(topic.getName(), is("topic"));
	}
	@Test
	public void shouldGenerateTopicId() {
		Topic topic = topicRepo.save(new Topic("topic"));
		long topicId = topic.getId();

		entityManager.flush();
		entityManager.clear();
		
		assertThat(topicId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadCourse() {
		Course course = new Course("course name", "description");
		course = courseRepo.save(course);
		long courseId = course.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Course> result = courseRepo.findById(courseId);
		result.get();
		assertThat(course.getName(), is("course name"));
	}
	
	@Test
	public void shouldEstablishCourseToTopicsRelationship() {
		//topic is not the owner so we must create these first
		Topic java = topicRepo.save(new Topic("Java"));
		Topic ruby = topicRepo.save(new Topic("Ruby"));
		
		Course course = new Course("OO Languages", "description", java, ruby);
		course = courseRepo.save(course);
		long courseId = course.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Course> result = courseRepo.findById(courseId);
		course = result.get();
		
		assertThat(course.getTopics(), containsInAnyOrder(java, ruby));
		}
	
	@Test
	public void shouldFindCoursesForTopic() {
		Topic java = topicRepo.save(new Topic("Java"));
		
		Course ooLanguages = courseRepo.save(new Course("OO Languages", "Description", java));
		Course advancedJava = courseRepo.save(new Course("Advanced Java", "Description", java));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Course> coursesForTopic = courseRepo.findByTopicsContains(java);
		
		assertThat(coursesForTopic, containsInAnyOrder(ooLanguages, advancedJava));	
	}
	
	

	
	
}
