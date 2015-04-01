package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteAnswerController extends AbstractController implements Controller {
	private AnswerDao answerDao = AnswerDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long answerId = ServletRequestUtils.getLongParameter(request, "answerId");
		long questionId = ServletRequestUtils.getLongParameter(request, "questionId");
		
		answerDao.delete(answerId, questionId);
		
		ModelAndView mav = jstlView("redirect:/list.next");

		return mav;
	}
}
