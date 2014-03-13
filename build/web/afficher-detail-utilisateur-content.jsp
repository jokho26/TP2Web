<h3>Afficher les détails d'un utilisateur</h3>  
<form action="ServletUsers" method="get">  
    login : <input type="text" name="login"/><br>  
    <input type="hidden" name="action" value="chercherParLogin"/>  
    <input type="submit" value="Chercher" name="submit"/>  
</form>  