<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>


<layout:investor pageTitle="Enchérir">
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <div class="row center">
        <div class="col s12">
          <h4 class="header center orange-text">Donner un prix</h4>
          <table class="bordered centered highlight">
            <thead>
              <tr>
                <th>Id Offre</th>
                <th>Vendeur</th>
                <th>Société</th>
                <th>Quantité</th>
                <th>Prix</th>
                <th>Type de l'offre</th>
                <th>Mode de négociation</th>
                <th>Type du contrat</th>
                <th>Prix total</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>${offer.id}</td>
                <td>${offer.user.login}</td>
                <td>${offer.company.name}</td>
                <td>${offer.quantity}</td>
                <td>${offer.price}</td>
                <td>${offer.offerType.name}</td>
                <td>${offer.negociationMode.name}</td>
                <td>${offer.contractType.name}</td>
                <td>${(offer.quantity)*(offer.price)}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row center">
        <div class="col s6">
          <h4 class="header center orange-text">Historique</h4>
          <c:choose>
            <c:when test="${empty listAuctionOffers }">
              <p>Aucune offre n'a été faite pour l'instant.</p>
            </c:when>
            <c:otherwise>
              <table class="bordered centered highlight">
                <thead>
                  <tr>
                    <th>Id Offre</th>
                    <th>Acheteur</th>
                    <th>Prix</th>
                  </tr>
                </thead>
                <c:forEach var="auctionOffer" items="${listAuctionOffers}">
                  <tbody>
                    <tr>
                      <td>${auctionOffer.id}</td>
                      <td>${auctionOffer.user.login}</td>
                      <td>${auctionOffer.amount}</td>
                    </tr>
                  </tbody>
                </c:forEach>
              </table>
            </c:otherwise>
          </c:choose>
        </div>

        <c:choose>
          <c:when test="${user.id eq offer.user.id}">
            <div class="col s6">Ceci est votre offre.</div>
          </c:when>
          <c:when test="${not empty over}">Enchère terminée!</c:when>
          <c:otherwise>
            <div class="col s6">
              <h4 class="header center orange-text">Votre prix</h4>
              <form method="post" action="../offers/bid">
                <input id="oid" type="hidden" name="oid" value="${offer.id}" />
                <div class="input-field col s3 offset-s3">
                  <input id="price" name="price" type="number" step="any" min="${minBid}" class="validate" required aria-required="true"> <label for="price">Prix</label>
                </div>
                <div class="input-field col s3">
                  <button class="waves-effect waves-light btn" type="submit">Valider</button>
                </div>
              </form>
            </div>
          </c:otherwise>
        </c:choose>

      </div>
    </div>
  </div>

</layout:investor>
