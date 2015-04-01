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
		
		answerDao.delete(answerId);
		
		ModelAndView mav = jstlView("redirect:/api/list.next");
		
		return mav;
	}

}
