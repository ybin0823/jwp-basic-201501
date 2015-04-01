package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import next.controller.ShowController;
import next.model.Question;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

public class QuestionDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(QuestionDaoTest.class);
	Question expected = new Question("자바지기", "title", "contents");
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		QuestionDao dut = QuestionDao.getInstance();
		dut.insert(expected);
		
		List<Question> questions = dut.findAll();
		for(Iterator<Question> i = questions.iterator(); i.hasNext(); ) {
			logger.debug(i.next().toString());
		}
		assertTrue(questions.size() > 0);
	}
	
	@Test
	public void update() throws Exception {
		QuestionDao dut = QuestionDao.getInstance();
		dut.update(2);
		
		assertEquals(4, dut.findById(2).getCountOfComment());
	}
}
