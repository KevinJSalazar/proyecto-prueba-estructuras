package ec.edu.espol.grupo_10;

import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.ArrayList;
import ec.edu.espol.model.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        
        Usuario u1 = new Usuario("Kevin", "Salazar", "yope@gmail.com", "123");
        Usuario u2 = new Usuario("Steven", "Lino", "peyo@gmail.com", "456");
        
        Usuario[] usuarios = {u1, u2};
        String[] placas = {"AAA", "BBB", "CCC", "DDD", "EEE"};
        String[] marcas = {"TOYOTA", "NISAN", "CHEVROLET", "HONDA"};
        String[] modelos = {"GRANDE", "PEQUEÑO", "MEDIANO"};
        String[] tipos = {"RAPIDO", "PESADO", "ROBUSTO", "INTELIGENTE"};
        
        String placa, marca, modelo, tipo;
        int precio; int kilometraje;
        Usuario usuario; Vehiculo vehiculo;
        for (int i = 1; i <= 20; i++){
            placa = placas[new Random().nextInt(placas.length)];
            marca = marcas[new Random().nextInt(marcas.length)];
            modelo = modelos[new Random().nextInt(modelos.length)];
            tipo = tipos[new Random().nextInt(tipos.length)];
            usuario = usuarios[new Random().nextInt(usuarios.length)];
            precio = new Random().nextInt(20);
            kilometraje = new Random().nextInt(20);
            vehiculo = new Vehiculo(placa, marca, modelo, tipo, precio, kilometraje, usuario);
            vehiculos.addLast(vehiculo);
            usuario.getVehiculos().addLast(vehiculo);
        }
        for(Vehiculo v : vehiculos){
            System.out.println(v);
        }
        
        System.out.println("//////////////////");
        
        ArrayList<Vehiculo> vehfiltrados = Vehiculo.filtrarVehiculos(vehiculos, "", "TOYOTA", "", 1, 20, 0, 20);
        for(Vehiculo v : vehfiltrados){
            System.out.println(v);
        }
        
        System.out.println("//////////////////");
        
        for(Vehiculo v : u1.getVehiculos()){
            System.out.println(v);
        }
        
        ArrayList<Usuario> usuariosAL = new ArrayList<>();
        usuariosAL.addLast(u1);
        usuariosAL.addLast(u2);
        Vehiculo.saveListVehiculosSer(vehiculos);
        Usuario.saveListUsuariosSer(usuariosAL);
        
        launch();
    }
}