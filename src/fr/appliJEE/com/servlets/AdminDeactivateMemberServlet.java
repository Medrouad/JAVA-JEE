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
import fr.papyfinance.com.dao.UserDao;
import fr.papyfinance.com.resources.Util;

@WebServlet("/admin/deactivate")
public class AdminDeactivateMemberServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private UserDao ud;
  @Inject
  private Util util;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/all/company-members.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    User u = ud.getByEmail(util.getInputValue(request, "email"));
    u.setConfirmed(false);
    if (ud.update(u)) {
      request.getSession().setAttribute("deactivated", "L'utilisateur " + u.getEmail() + " a bien �t� d�sactiv� !");
    } else {
      request.getSession().setAttribute("not_deactivated", "L'utilisateur " + u.getEmail() + " n'a pas �t� d�sactiv� !");
    }
    if (util.getInputValue(request, "role").equals("company-member")) {
      response.sendRedirect("company-members");
    } else {
      response.sendRedirect("investors");
    }
  }
}
