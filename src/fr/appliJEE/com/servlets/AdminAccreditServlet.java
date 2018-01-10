package fr.papyfinance.com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.papyfinance.com.beans.Company;
import fr.papyfinance.com.dao.CompanyDao;
import fr.papyfinance.com.resources.Util;

@WebServlet("/admin/accredit")
public class AdminAccreditServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @EJB
  private CompanyDao cd;
  @Inject
  private Util util;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Company> listeCompanies = cd.getAllCompanyNotAccredit();
    request.setAttribute("listeCompanies", listeCompanies);

    this.getServletContext().getRequestDispatcher("/WEB-INF/admin/accredit.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = util.getInputValue(request, "name");
    if (name != null) {
      Company c = cd.getByName(name);
      if (c != null) {
        c.setConfirmed(true);
        cd.update(c);
      }
    }

    doGet(request, response);
  }
}
