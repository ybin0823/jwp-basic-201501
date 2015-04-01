package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ListController extends AbstractController {
	private QuestionDao questionDao = QuestionDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Question> questions = questionDao.findAll();
		
		ModelAndView mav = jstlView("/list.jsp");
		mav.addObject("questions", questions);
		return mav;
	}
}
