package courses;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

	@InjectMocks
	private CourseController underTest;
	
	@Mock
	private Course course;
	
	@Mock
	private Course course2;
	
	@Mock
	private Topic topic;
	
	@Mock
	private Topic topic2;
	
	@Mock
	private Textbook textbook;
	
	@Mock
	private Textbook textbook2;
	
	@Mock
	private CourseRepository courseRepo;
	
	@Mock
	private TopicRepository topicRepo;
	
	@Mock
	private TextbookRepository textbookRepo;
	
	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCourseToModel() throws CourseNotFoundException {
		long arbitraryCourseId = 1;
		when(courseRepo.findById(arbitraryCourseId)).thenReturn(Optional.of(course));
		
		underTest.findOneCourse(arbitraryCourseId, model);
		verify(model).addAttribute("courses", course);
	}
	
	@Test
	public void shouldAddAllCoursesToModel() {
		Collection<Course> allCourses = Arrays.asList(course, course2);
		when(courseRepo.findAll()).thenReturn(allCourses);
		
		underTest.findAllCourses(model);
		verify(model).addAttribute("courses", allCourses);
		
	}
	
	@Test
	public void shouldAddSingleTopicToModel() throws TopicNotFoundException {
		long arbitraryTopicId = 1;
		when(topicRepo.findById(arbitraryTopicId)).thenReturn(Optional.of(topic));
		
		underTest.findOneTopic(arbitraryTopicId, model);
		verify(model).addAttribute("topics", topic);
	}
	
	@Test
	public void shouldAddAllTopicsToModel() {
		Collection<Topic> allTopics = Arrays.asList(topic, topic2);
		when(topicRepo.findAll()).thenReturn(allTopics);
		
		underTest.findAllTopics(model);
		verify(model).addAttribute("topics", allTopics);
	}
	
	@Test
	public void shouldAddSingleTextbookToModel() throws TextbookNotFoundException {
		long arbitraryTextbookId = 1;
		when(textbookRepo.findById(arbitraryTextbookId)).thenReturn(Optional.of(textbook));
		
		underTest.findOneTextbook(arbitraryTextbookId, model);
		verify(model).addAttribute("textbooks", textbook);
	}
	
	@Test
	public void shouldAddAllTextbooksToModel() {
		Collection<Textbook> allTextbooks = Arrays.asList(textbook, textbook2);
		when(textbookRepo.findAll()).thenReturn(allTextbooks);
		
		underTest.findAllTextbooks(model);
		verify(model).addAttribute("textbooks", allTextbooks);
	}
	
	
	
	
	
	
}
