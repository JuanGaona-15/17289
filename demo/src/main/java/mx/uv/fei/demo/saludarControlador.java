package mx.uv.fei.demo; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class saludarControlador {
    String nombre;

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
}
