package next.dao;

import static org.junit.Assert.*;

import java.util.List;

import next.model.Question;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

public class QuestionDaoTest {
	Question expected = new Question("자바지기", "title", "contents");
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		QuestionDao dut = new QuestionDao();
		dut.insert(expected);
		
		List<Question> questions = dut.findAll();
		assertTrue(questions.size() > 0);
	}
	
	@Test
	public void update() throws Exception {
		QuestionDao dut = new QuestionDao();
		int countOfComment = expected.getCountOfComment();
		dut.update(expected.getQuestionId());
		assertEquals(countOfComment + 1, expected.getCountOfComment());
		
	}
}
