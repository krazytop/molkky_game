<!DOCTYPE html>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    
    <title>Page de match</title>
</head>


<body style="background-color:black">
    <div align="center" class="w3-panel w3-round-xxlarge w3-grey">
        <h1>Jouez !</h1>
        <c:choose>
            <c:when test="${ equipe1.isTour()}">
                <form action="${pageContext.request.contextPath}/match" method="post">
                    <table>
                        <tr>
                            <td>
                                <label for="score1">Points marqués par l'équipe ${ equipe1.nom }</label>
                                <input type="number" name="score1" id="score1" max="50" min="0" required style="background-color:grey" autofocus="autofocus"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Valider" style="background-color:lightgrey"/>
                </form>
            </c:when>

            <c:otherwise>
                <form action="${pageContext.request.contextPath}/match" method="post">
                    <table>
                        <tr>
                            <td><label for="score2">Points marqués par l'équipe ${ equipe2.nom }</label>
                                <input type="number" name="score2" id="score2" max="12" min="0" required style="background-color:grey" autofocus="autofocus"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="Valider" style="background-color:lightgrey"/>
                </form>
            </c:otherwise>
        </c:choose>
        <br>
        <p>Score de l'équipe ${ equipe1.nom } : ${match.scoreEquipe1 }</p>
        <p>Score de l'équipe ${ equipe2.nom } : ${match.scoreEquipe2 }</p>
        <br>
        <a href="Molkky/Accueil">Arrêter le match et revenir à l'accueil</a>
    </div>
    <div align="center" class="w3-panel w3-round-xxlarge w3-grey">
        <h3>Historique des tirs</h3>
        <ul class="w3-ul w3-hoverable">
            <c:forEach var="tir" items="${listeTirs}">
                <li>
                    <p>Tir de l'équipe
                        <c:out value="${tir.equipe.nom}" />, valeur :
                        <c:out value="${tir.valeur}" />
                    </p>
                </li>
            </c:forEach>
        </ul>
    </div>