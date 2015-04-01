package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	private AnswerDao answerDao = AnswerDao.getInstance();
	private Answer answer;
	private QuestionDao questionDao = QuestionDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		answer = new Answer(writer, contents, questionId);
		logger.debug(answer.toString());
		answerDao.insert(answer);
		questionDao.update(questionId);
		
		ModelAndView mav = jstlView("/show.jsp");

		return mav;
	}
}
