package fr.papyfinance.com.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.papyfinance.com.beans.User;
import fr.papyfinance.com.dao.CompanyDao;
import fr.papyfinance.com.dao.UserDao;
import fr.papyfinance.com.forms.SubscribeCompanyForm;
import fr.papyfinance.com.resources.Util;

@WebServlet("/signup/company")
public class SubscribeCompanyServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private SubscribeCompanyForm subscribeCompanyForm;
  @EJB
  private UserDao userDao;
  @EJB
  private CompanyDao companyDao;
  @Inject
  private Util util;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (util.currentUser(request.getSession()) == null) {
      request.setAttribute("companies", companyDao.getAllWithoutNone());
      this.getServletContext().getRequestDispatcher("/WEB-INF/connection/signup_company.jsp").forward(request, response);
    } else {
      request.getSession().setAttribute("already_connected", "Vous �tes d�j� connect�.");
      response.sendRedirect("/PapyFinance");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User u = subscribeCompanyForm.getUser(request);

    if (userDao.create(u)) {
      request.getSession().setAttribute("subscribe", "Inscription r�ussie, veuillez attendre la validation de votre compte.");
      response.sendRedirect("/PapyFinance");
    } else {
      request.setAttribute("error", "Votre email n'est pas disponible.");
      this.getServletContext().getRequestDispatcher("/WEB-INF/connection/signup_company.jsp").forward(request, response);
    }
  }
}
