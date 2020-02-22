package study.young.app.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	// 当用户访问未授权的资源时，跳转到 unauthorized 视图，并携带出错信息
	@ExceptionHandler(AuthorizationException.class)
	public ModelAndView error(AuthorizationException e) {
		ModelAndView mv = new ModelAndView("unauthorized");
		mv.addObject("error", e.getMessage());
		return mv;
	}
}