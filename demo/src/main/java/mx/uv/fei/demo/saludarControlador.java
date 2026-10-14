package mx.uv.fei.demo; 

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class saludarControlador {
    String nombre;
    Saludador saludador;

    @GetMapping("/saludos")
    public String saludar(){
        return "hola mundo!" + nombre;
    }

    @GetMapping("/despedidas")
    public String despedirse(){
        System.out.print("Si funciona salu2");
        return "adios mundo!";
    }

    @PostMapping("/nombres")
    public void nombre(){
        nombre="Juan Luis";
    }

    @PutMapping("/nombramientos")
    public void met1(){
        nombre="actualizar";
    }

    @DeleteMapping("/nombramientos")
    public void met2(){
        nombre=null;
    }

    @GetMapping("/saludador/{nombre}")
    public Saludador met3(@PathVariable String nombre) {
        return new Saludador(nombre);
    }

    @GetMapping("/saludador-alternativo/{nombreX}")
    public Saludador met4(@PathVariable("nombreX") String nombre) {
        System.out.println("get:"+nombre);
        saludador = new Saludador(nombre); 
        return saludador;
    }

    @RequestMapping("/")
    public String indice(){
        return  """
                <h1>Menu principal</h1>
                <ul>
                    <li><a href ='saludos'>/saludos</a></li>
                    <li><a href ='despedidas'>/despedidas</a></li>
                    <li>/nombramientos(Post - Put - Delete)</li>
                    <li><a href = '/saludador/default'>/saludador/{nombre}</a></li>
                    <li><a href = '/saludador-alternativo/default2'>/saludador-alternativo/{nombreX}</a></li>
                    <li><a href = '/crearSaludador'>/crearSaludador</a></li>
                    <li><a href = '/obtenerSaludador'>/obtenerSaludador</a></li>
                </ul>  
                """;
    }

    //Esta pagina no abre porque es un POST no un GET, 
    //hay que pasarle el body con curl -i localhost:8081/crearSaludador -d "Juan" 
    //o con curl -i localhost:8081/crearSaludador -d '{"contenido":"Jane"}' -H "Content-Type:aplication/json"
    @PostMapping("/crearSaludador")
    public void crearSaludador(@RequestBody String nombre){
        System.out.println("post:"+nombre);
        saludador = new Saludador(nombre);
    }

    @GetMapping("/obtenerSaludador")
    public Saludador obtenerSaludador(){
        return saludador;
    }

    //Mandar parametros con curl -i "localhost:8081/getQuery?query=valor"
    //ejemplo: curl -i "localhost:8081/getQuery?nombre=Juan&valor=Gaona"
    //o en el navegador: http://localhost:8081/getQuery?nombre=Juan&paterno=Gaona
    @GetMapping("/getQuery")
    public String GetQueryString(@RequestParam String nombre, @RequestParam String paterno){
        return nombre + " " + paterno;
    }

    @GetMapping("/getQuery2")
    public String GetQueryString2(@RequestParam String nombre, @RequestParam (defaultValue ="")String paterno){
        return nombre + " " + paterno;
    }
}
