package ShopAll.com.Service;

import ShopAll.com.Entity.Persona;
import ShopAll.com.Entity.Producto;
import ShopAll.com.Repository.PersonaRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indica que esta clase es un componente de servicio gestionado por Spring, lógica de negocio
public class PersonaService {

    @Autowired // Inyecta automáticamente una instancia de UserRepository en esta clase
    private PersonaRepository personaRepository;

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona obtenerPersona(Long id){
        return personaRepository.findById(id).orElse(null);
    }
    //public Persona buscarPorEmail(String email){return personaRepository.buscarPorEmail(email);}
    public void eliminarPersona(Long id){personaRepository.deleteById(id);}
    public List<Persona> listarPersonas(){return personaRepository.findAll();}
    public Persona actualizarPersona(Long id, Persona nuevaPersona){
        // Busca un usuario existente por su ID
        Persona personaPrevia =personaRepository.findById(id).orElse(null);
        if (personaPrevia != null) {
            // Actualiza los campos del usuario existente con los valores del usuario actualizado
            personaPrevia = nuevaPersona;
            // Guarda y devuelve el usuario actualizado
            return personaRepository.save(personaPrevia);
        }
        // Devuelve null si no se encuentra el usuario con el ID proporcionado
        return null;
    }

    public Persona getUser(Long id) {
        return personaRepository.findById(id).orElse(null);
    }
}
