<!DOCTYPE html>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <style type="text/css">
        span.winner {
            color: #07ED5B;
            font-weight: bold;
        }

        span.loser {
            color: #CC1616;
            font-weight: bold;
        }
    </style>

    <title>Page de résultat</title>
</head>

<body style="background-color:black">
<div align="center" class="w3-panel w3-round-xxlarge w3-grey">
    <div align="center">
        <h1>Bravo !</h1>
        <c:choose>
            <c:when test="${ match.scoreEquipe1 == 50}">
                <p> Félicitations équipe ${ equipe1.nom} , vous avez gagné !</p>
            </c:when>
            <c:otherwise>
                <p> Félicitations équipe ${ equipe2.nom} , vous avez gagné !</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div align="center">
        <h3>Rappel des scores</h3>
        <p>Score de l'équipe ${ equipe1.nom } : ${match.scoreEquipe1 }</p>
        <p>Score de l'équipe ${ equipe2.nom } : ${match.scoreEquipe2 }</p>
    </div>
    
	<div align="center">
    	<a href="Molkky/Accueil">Retour à la page d'accueil</a>
    	<br>
    	<p></p>
	</div>
</div>	
    <div align="center" class="w3-panel w3-round-xxlarge w3-grey" style="min-width:200px">
        <h1>Historique des matchs</h1>
        <ul class="w3-ul w3-hoverable">
            <c:forEach var="match" items="${listeMatchs}">
                <li>
                    <c:set var="scoreEquipe1" scope="session" value="${match.scoreEquipe1}" />
                    <c:set var="scoreEquipe2" scope="session" value="${match.scoreEquipe2}" />
                    <p>Équipe
                        <c:choose>
                            <c:when test="${scoreEquipe1 > scoreEquipe2}">
                                <span class="winner">
                                    <c:out value="${match.equipe1.nom}" />
                                </span>
                                VS Équipe
                                <span class="loser">
                                    <c:out value="${match.equipe2.nom}" />
                                </span>
                            </c:when>
                            <c:when test="${scoreEquipe1 < scoreEquipe2}">
                                <span class="loser">
                                    <c:out value="${match.equipe1.nom}" />
                                </span> VS Équipe
                                <span class="winner">
                                    <c:out value="${match.equipe2.nom}" />
                                </span> :
                                VS Équipe
                                <c:out value="${match.equipe2.nom}" /> :
                            </c:when>
                            <c:otherwise>
                                <c:out value="${match.equipe1.nom}" /> VS Équipe
                                <c:out value="${match.equipe2.nom}" />
                            </c:otherwise>
                        </c:choose>
                        <c:out value="${match.scoreEquipe1}" /> /
                        <c:out value="${match.scoreEquipe2}" />
                    </p>
                </li>
            </c:forEach>
        </ul>
    </div>