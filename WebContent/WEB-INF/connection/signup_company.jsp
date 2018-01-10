<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<layout:connection pageTitle="Inscription en tant que Membre Société">
  <div class="col s12">
    <h4 class="header center orange-text">Inscription à PapyFinance en tant que membre société</h4>
    <form class="col s4 offset-s4 hoverable" method="post">
      <div class="row">
        <c:if test="${ !empty error }">
          <div class="col s12 card-panel red lighten-1">${ error }</div>
        </c:if>
        <div class="input-field col s6">
          <i class="material-icons prefix">account_circle</i> <input id="fname" name="fname" type="text" class="validate" required aria-required="true"> <label for="fname">Prénom</label>
        </div>
        <div class="input-field col s6">
          <input id="lname" name="lname" type="text" class="validate" required aria-required="true"> <label for="lname">Nom</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix">email</i> <input id="email" name="email" type="email" class="validate" required aria-required="true"> <label for="email">Email</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s12">
          <i class="material-icons prefix prefix-select">work</i> <select id="company" name="company" class="validate">
            <option value="" disabled selected>Choisir une société</option>
            <c:forEach var="company" items="${companies}">
              <option value="${ company.id }">${ company.name }</option>
            </c:forEach>
          </select> <label for="company">Société</label>
        </div>
      </div>
      <div class="row">
        <button class="waves-effect waves-light btn" type="submit">Je m'inscris!</button>
      </div>
    </form>
  </div>
  <div class="col s12">
    <p class="light">
      Vous êtes un papy déjà connu? <a href="../login">Connectez-vous.</a>
    </p>
  </div>
</layout:connection>