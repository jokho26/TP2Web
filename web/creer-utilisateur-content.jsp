<h3>Cr�er un utilisateur</h3>  
<form action="ServletUsers" method="get">  
    Nom : <input type="text" name="nom"/><br>  
    Pr�nom : <input type="text" name="prenom"/><br>  
    Login : <input type="text" name="login"/><br>  
    <!-- Astuce pour passer des param�tres � une servlet depuis un formulaire JSP !-->  
    <input type="hidden" name="action" value="creerUnUtilisateur"/>  
    <input type="submit" value="Cr�er l'utilisateur" name="submit"/>  
</form>  