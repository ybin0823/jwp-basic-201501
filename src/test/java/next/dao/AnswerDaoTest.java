package next.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import next.model.Answer;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import core.jdbc.ConnectionManager;

public class AnswerDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(AnswerDaoTest.class);
	
	long questionId = 1L;
	Answer expected = new Answer("javajigi", "answer contents", questionId);
	AnswerDao dut = AnswerDao.getInstance();
	
	QuestionDao questionDao = QuestionDao.getInstance();
	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("jwp.sql"));
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	public void crud() throws Exception {
		dut.insert(expected);
		List<Answer> answers = dut.findAllByQuestionId(questionId);
		assertTrue(answers.size() > 0);
	}
	
	@Test
	public void delete() throws Exception {
		long questionId = 1L;
		Answer expected = new Answer("test", "test test", questionId);
		AnswerDao dut = AnswerDao.getInstance();
		dut.insert(expected);
		
		int expectedNum = questionDao.findById(questionId).getCountOfComment();
		dut.delete(6,1);
		int actualNum = questionDao.findById(questionId).getCountOfComment();
		assertEquals(expectedNum - 1, actualNum);
	}
}
