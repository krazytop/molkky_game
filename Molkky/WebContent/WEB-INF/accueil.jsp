<!DOCTYPE html>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    
    <title>Page d'accueil</title>
</head>


<body style="background-color:black">
    <div align="center" class="w3-panel w3-round-xxlarge w3-grey">
        <h1>Une partie de Molkky ?</h1>

        <form method="post">
            <table>
                <tr>
                    <td>
                        <label for="name1">Nom de la première équipe</label>
                        <input type="text" name="name1" required style="background-color:grey"/>
                    </td>
                </tr>
                <tr>
                    <td>
                    	<label for="name2">Nom de la seconde équipe&nbsp;</label>
                        <input type="text" name="name2" required style="background-color:grey"/>
                    </td>

                    <td colspan="3">
                </tr>
            </table>
            <br>
            <input type="submit" name="equipes" value="Commencer le match !" style="background-color:lightgrey"/>
        </form>
        <br>
    </div>

    <div align="center" class="w3-panel w3-round-xxlarge w3-grey">
        <p>Equipes déjà existantes pouvant être réutilisées : </p>
        <ul class="w3-ul w3-hoverable">
            <c:forEach var="equipe" items="${existingTeamsName}">
                <li>
                    <c:out value="${equipe}" />
                </li>
            </c:forEach>
        </ul>
    </div>