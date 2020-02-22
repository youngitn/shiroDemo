package study.young.app.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	// 登录接口
	@PostMapping("/doLogin")
	public String doLogin(String username, String password, Model model) {
		// 通过接收到的用户名和密码构造一个 UsernamePasswordToken 实例 
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 获取一个Subject对象
		Subject subject = SecurityUtils.getSubject();
		try {
			// 执行登录操作
			subject.login(token);
		} catch (AuthenticationException e) {
			// 登录操作执行过程中，当有异常抛出时，说明登录失败，携带错误信息返回登录视图
			model.addAttribute("error", "用户名或密码输入错误!");
			return "login";
		}
		// 当登录成功时，则重定向到"/index"
		return "redirect:/index";
	}

	// 暴露"/admin"接口，并且该接口需要具有admin角色才能访问
	@RequiresRoles("admin")
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	// 暴露"/user"接口，并且该接口只要有admin或者user角色就能访问
	@RequiresRoles(value = { "admin", "user" }, logical = Logical.OR)
	@GetMapping("/user")
	public String user() {
		return "user";
	}
}
