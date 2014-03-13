package utilisateurs.gestionnaires;

import java.util.Collection;  
import java.util.List;
import javax.ejb.Stateless;  
import javax.persistence.EntityManager;  
import javax.persistence.PersistenceContext;  
import javax.persistence.Query;  
import utilisateurs.modeles.Utilisateur;  

/**
 *
 * @author Siddi Steven
 */
@Stateless  
public class GestionnaireUtilisateurs {  
   
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext(unitName = "TP2WebPU")
    private EntityManager em;
    
    public void creerUtilisateursDeTest() {  
        creeUtilisateur("John", "Lennon", "jlennon");  
        creeUtilisateur("Paul", "Mac Cartney", "pmc");  
        creeUtilisateur("Ringo", "Starr", "rstarr");  
        creeUtilisateur("Georges", "Harisson", "georgesH");  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login) {  
        Utilisateur u = new Utilisateur(nom, prenom, login);  
        em.persist(u);  
        return u;  
    }  
  
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }
    
    public Utilisateur getUserByLogin(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:param");
        q.setParameter("param", login);
        return (Utilisateur)(q.getResultList().get(0));  
    }
    
    public Utilisateur updateUser(String login, String  newNom,String  newPrenom) {

        Utilisateur u = getUserByLogin(login);
        u.setFirstname(newPrenom);
        u.setLastname(newNom);
        em.persist(u);

        return u;
        
    }
    
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  

    public void persist(Object object) {
        em.persist(object);
    }

    public void persist1(Object object) {
        em.persist(object);
    }
}  