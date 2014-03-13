/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Siddi Steven
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {
    
    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods. 
     * 
     * @param request servlet request 
     * @param response servlet response 
     * @throws ServletException if a servlet-specific error occurs 
     * @throws IOException if an I/O error occurs 
     */  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        // Pratique pour décider de l'action à faire  
        String action = request.getParameter("action");  
        String forwardTo = "";  
        String message = "";  
  
        if (action != null) {  
            if (action.equals("listerLesUtilisateurs")) {  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if (action.equals("creerUtilisateursDeTest")) {  
                  gestionnaireUtilisateurs.creerUtilisateursDeTest();  
                Collection<Utilisateur> liste = gestionnaireUtilisateurs.getAllUsers();  
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";  
                message = "Liste des utilisateurs";  
            } else if (action.equals("creerUnUtilisateur")) {
                // On récupere les parametres pour créer un utilisateur
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String login = request.getParameter("login");
                
                // TODO verif les attributs
                Utilisateur u = gestionnaireUtilisateurs.creeUtilisateur(nom, prenom, login);
                
                if (u == null) {
                    forwardTo = "index.jsp?action=erreurCreationUtilisateur";  
                    message = "Erreur dans la création de l'utilisateur : paramètre incorrect";  
                }
                else {
                    ArrayList<Utilisateur> liste = new  ArrayList<Utilisateur>();
                    liste.add(u);
                    request.setAttribute("listeDesUsers", liste); 
                    forwardTo = "index.jsp?action=listerLesUtilisateurs";
                    message = "Création d'un nouvel utilisateur."; 
                }

            } else if (action.equals("chercherParLogin")) {
                String login = request.getParameter("login");
                
                Utilisateur u = gestionnaireUtilisateurs.getUserByLogin(login);
                ArrayList<Utilisateur> liste = new  ArrayList<Utilisateur>();
                liste.add(u);
                request.setAttribute("listeDesUsers", liste);  
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Chercher un utilisateur par login";
                
            }
            else if (action.equals("updateUtilisateur")) {
                // On récupere les parametres pour mettre à jour un utilisateur
                String newNom = request.getParameter("nom");
                String newPrenom = request.getParameter("prenom");
                String login = request.getParameter("login");
                
                Utilisateur u = gestionnaireUtilisateurs.updateUser(login, newNom, newPrenom);
                ArrayList<Utilisateur> liste = new  ArrayList<Utilisateur>();
                liste.add(u);
                
                request.setAttribute("listeDesUsers", liste); 
                forwardTo = "index.jsp?action=listerLesUtilisateurs";
                message = "Mise à jour d'un utilisateur."; 
                
            } else { 
                forwardTo = "index.jsp?action=todo";  
                message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";  
            }  
        }  
  
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);  
        dp.forward(request, response);  
        // Après un forward, plus rien ne peut être exécuté après !  
    }  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
