import org.example.database.dao.PetDAO;
import org.example.database.modelos.Pet;
import org.example.database.modelos.enums.Especie;
import org.example.database.modelos.enums.Sexo;
import org.example.database.utils.JPAUtil;

import javax.persistence.EntityManager;

public class PetTest {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        PetDAO petDAO = new PetDAO(em);
        Pet pet = new Pet("Juninho", 12, Especie.CACHORRO, Sexo.MACHO, "RND", true, true);
        em.getTransaction().begin();
        petDAO.cadastrar(pet);
        em.getTransaction().commit();
        em.close();
    }
}
