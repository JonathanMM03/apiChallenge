package ShopAll.com.Controller;

import ShopAll.com.Entity.Persona;
import ShopAll.com.Entity.Producto;
import ShopAll.com.Service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marca la clase como un controlador REST
@RequestMapping("/personas") // Define la raíz de URL para todas las rutas manejadas por este controlador
@Tag(name = "Usuarios") // Etiqueta para documentación Swagger
@CrossOrigin(origins="*") //Permite que una API REST sea accesible desde cualquier origen
public class PersonaController {
    @Autowired // Realiza la inyección automática de la dependencia UserService
    private PersonaService personaService;

    // Crea un nuevo usuario
    @Operation(summary = "Creacion de nueva persona") // Descripción de la operación para Swagger
    @PostMapping("/crearPersona") // Mapeo para manejar solicitudes POST en la ruta /users/create
    public Persona crearPersona(@RequestBody Persona persona){
        return personaService.createPersona(persona);
    }

    // Buscar persona por id
    @Operation(summary = "Obtener persona con su ID")
    @GetMapping("/{id}") // Mapeo para manejar solicitudes GET en la ruta /users/{id}
    public Persona obtenerPersona(@PathVariable Long id){
        return personaService.obtenerPersona(id);
    }

    // Buscar persona por email
    /*@Operation(summary = "Obtener persona por email")
    @GetMapping("/buscarEmail")
    public Persona buscarPorEmail(@RequestParam String email){
        return personaService.buscarPorEmail(email);
    }*/
    @Operation(summary = "Eliminar persona por id")
    @DeleteMapping("/{id}") // Mapeo para manejar solicitudes DELETE en la ruta /users/{id}
    public void eliminarPersona(@PathVariable Long id){personaService.eliminarPersona(id);}
    @Operation(summary = "Actualizar persona por ID") // Descripción de la operación para Swagger
    @PutMapping("/{id}") // Mapeo para manejar solicitudes PUT en la ruta /users/{id}
    public Persona updateUser(@PathVariable Long id, @RequestBody Persona nuevaPersona) {
        return personaService.actualizarPersona(id, nuevaPersona);
    }
    @Operation(summary = "Obtener listado de personas")
    @GetMapping("/listarPersonas")
    public List<Persona> listarPersonas(){return personaService.listarPersonas();}
    @Operation(summary = "Obteneomos lista de compra con el id")
    @GetMapping("/{id}/shop_list")
    public ResponseEntity<?> getUserShopList(@PathVariable Long id){
        Persona user = personaService.getUser(id);
        if(user != null){
            List<Producto> productos = user.getListaCompra();
            return ResponseEntity.ok(productos);
        } else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
