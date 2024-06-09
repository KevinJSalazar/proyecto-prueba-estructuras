/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author omits
 */
public class Favorito implements Serializable{
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculosFavoritos;

    public Favorito(Usuario usuario) {
        this.usuario = usuario;
        this.vehiculosFavoritos = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Vehiculo> getVehiculosFavoritos() {
        return vehiculosFavoritos;
    }

    public void setVehiculosFavoritos(ArrayList<Vehiculo> vehiculosFavoritos) {
        this.vehiculosFavoritos = vehiculosFavoritos;
    }
    
    public static void saveListFavoritosSer(ArrayList<Favorito> favoritos){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("favoritos.ser"))){
            out.writeObject(favoritos);
        } catch(IOException e){}
    }
    
    public static ArrayList<Favorito> readFileSer(){
        ArrayList<Favorito> favoritos = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("favoritos.ser"))){
            favoritos = (ArrayList<Favorito>)in.readObject();
        } catch(ClassNotFoundException | IOException c){}
        
        return favoritos;
    }
    
    
}
