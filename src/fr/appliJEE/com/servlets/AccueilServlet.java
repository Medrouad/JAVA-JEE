package fr.papyfinance.com.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.papyfinance.com.resources.Util;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Inject
  private Util util;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (util.currentUser(request.getSession()) == null) {
      this.getServletContext().getRequestDispatcher("/WEB-INF/landing/landing.jsp").forward(request, response);
    } else if (util.currentUser(request.getSession()).getRole().getName().equals("Administrateur")) {
      this.getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
    } else if (util.currentUser(request.getSession()).getRole().getName().equals("Membre soci�t�")) {
      this.getServletContext().getRequestDispatcher("/WEB-INF/landing/landing-company.jsp").forward(request, response);
    } else {
      this.getServletContext().getRequestDispatcher("/WEB-INF/landing/landing-investor.jsp").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
