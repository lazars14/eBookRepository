package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ChangeLanguageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageServlet.class);
	
    public ChangeLanguageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{	
			String lang = request.getParameter("lang");
			
			String language = (lang.equals("en")) ? "en" : "sr";
			
			String location = (request.getParameter("role") == null) ? "/Login.jsp" : "/MenuVisitorServlet";

			HttpSession session = request.getSession(true);
			session.setAttribute("language", language);
			
			getServletContext().getRequestDispatcher(location).forward(request, response);
		}
		catch (ServletException e)
		{
			LOGGER.error(e);
			throw e;
		}
		catch (IOException e)
		{
			LOGGER.error(e);
			throw e;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
