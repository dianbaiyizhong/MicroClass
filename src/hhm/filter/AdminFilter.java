package hhm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filter) throws IOException, ServletException {

		// 判断session中是否有用户名，如果有则通过，否则跳转到登录页面
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		try {
			if (session.getAttribute("UserName").equals("admin")) {
				// 跳转到下一个过滤器，如果没有则跳转到目标页面
				filter.doFilter(request, response);

			} else {

				request.setAttribute("TipsInfo", "请登录管理员");
				request.getRequestDispatcher("/tipsPage/TipsPage.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("TipsInfo", "出错请重试");

			request.getRequestDispatcher("/tipsPage/TipsPage.jsp")
			.forward(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
