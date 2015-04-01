package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteController.class);
	
	private QuestionDao questionDao = QuestionDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		Question question = questionDao.findById(questionId);
		logger.debug("questionId : {}, writer : {}", questionId, writer);
		if(question.getCountOfComment() > 0 && !writer.equals(question.getWriter())) {
			//@TODO 삭제 불가
			return null;
		}
		
		questionDao.delete(questionId);
		ModelAndView mav = jstlView("redirect:/");
		
		return mav;
	}

}
