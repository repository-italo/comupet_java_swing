import org.example.database.dao.UsuarioDAO;
import org.example.database.modelos.Usuario;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioTest {
    public static void main(String[] args){
        Usuario usuario = new Usuario("Italo", "italo@mail.com", "1234", true);
        EntityManager em = JPAUtil.getEntityManager();
        UsuarioDAO usuarioDAO = new UsuarioDAO(em);
        em.getTransaction().begin();
        usuarioDAO.cadastrar(usuario);
        em.getTransaction().commit();
        em.close();
    }
}
