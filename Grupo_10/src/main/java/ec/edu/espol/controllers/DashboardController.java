/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.ArrayList;
import ec.edu.espol.model.Favorito;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.util.UtileriaFunciones;
import static ec.edu.espol.util.UtileriaFunciones.ajustarTamañoImagen;
import ec.edu.espol.util.UtileriaMensajes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author omits
 */
public class DashboardController implements Initializable {

    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Button btnSeccionPrincipal;
    @FXML
    private Button btnSeccionVer;
    @FXML
    private Button btnSeccionAgregar;
    @FXML
    private AnchorPane dashboardPrincipal;
    @FXML
    private Label lblCantAutos;
    @FXML
    private Label lblCantMotos;
    @FXML
    private Label lblCantCamionetas;
    @FXML
    private AnchorPane dashboardVV;
    @FXML
    private TableView<Vehiculo> tvVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tvColTipo;
    @FXML
    private TableColumn<Vehiculo, String> tvColPlaca;
    @FXML
    private TableColumn<Vehiculo, String> tvColMarca;
    @FXML
    private TableColumn<Vehiculo, String> tvColModelo;
    @FXML
    private TableColumn<Vehiculo, Double> tvColPrecio;
    @FXML
    private TableColumn<Vehiculo, Integer> tvColKm;
    @FXML
    private ComboBox<String> cbxFTipo;
    @FXML
    private ComboBox<String> cbxFMarca;
    @FXML
    private ComboBox<String> cbxFModelo;
    @FXML
    private ComboBox<String> cbxFPrecio;
    @FXML
    private AnchorPane dashboardAV;
    @FXML
    private ComboBox<String> cbxTipo;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtKm;
    @FXML
    private TextField txtPrecio;
    
    // ATRIBUTOS NO FXML
    private ObservableList obListVehiculos;
    
    private Usuario usuarioActual;
    private Vehiculo vehiculoSeleccionadoActual;
    private Vehiculo vehiculoActual;
//    private Favorito favoritoActual;
    FileChooser fc = new FileChooser();
    private File imgFile;
    private File imgCargarFile;
    private File imgFileSeleccionado;
    private ArrayList<Vehiculo> vehiculos;
    private int indiceActual = 0;
    private int cantidadAutos;
    private int cantidadCamionetas;
    private int cantidadMotos;
//    private ArrayList<Favorito> favoritos;
    // ATRIBUTOS NO FXML
    
    @FXML
    private ImageView imMostrarVehiculo;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnAtrás;
    @FXML
    private Text txtAreaInfo;
    @FXML
    private ImageView imMostrarMiVehiculo;
    @FXML
    private ImageView imCargarVehiculo;
    @FXML
    private Text txtAreaInfoMiVeh;
    @FXML
    private Button btnEliminarVehiculo;
    @FXML
    private Button btnComprar;
    @FXML
    private Button btnFavoritos;
    @FXML
    private AnchorPane dashboardFavoritos;
    @FXML
    private Label lblMDefault;
    @FXML
    private HBox hbPrincipal;
    @FXML
    private Button btnQuitarFavorito;
    @FXML
    private Text txtInfoFavorito;
    @FXML
    private Button btnAñadirFavorito;
    @FXML
    private ComboBox<String> cbxFKilometraje;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.readFileSer();
//        favoritos = Favorito.readFileSer();
//        habilitarBtnsCA(vehiculoActual);
      
        if(!vehiculos.isEmpty()){
            vehiculoActual = vehiculos.get(indiceActual);
            habilitarBtnsCA(vehiculoActual);
            navegarEnLista(vehiculos);   
        }
        
//        favoritosDinamico(); // PRUEBA Y ERROR
        
        cargarFiltros();
        
        cbxTipo.getItems().addAll("Carro", "Moto", "Camioneta");
        cbxTipo.getSelectionModel().selectFirst();

        actualizarContabilidad(vehiculos);
        tvVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            seleccionTV();
        });
        
    }

    public void setUsuario(Usuario u){
        this.usuarioActual = u;
        lblNombreUsuario.setText(usuarioActual.getNombre());
//        this.favoritoActual = new Favorito(usuarioActual);
    }
    
    public void navegarEnLista(ArrayList<Vehiculo> vehiculos){
        indiceActual = 0;
        
        vehiculoActual = vehiculos.get(indiceActual);
//        actualizarVehiculo(vehiculos.get(indiceActual));
        
        if (!vehiculos.isEmpty()) {
                habilitarBtnsCA(vehiculoActual);
                actualizarVehiculo(vehiculoActual);
//                favoritosDinamico(); // PRUEBA Y ERROR
            }

        btnSiguiente.setDisable(vehiculos.isEmpty());
        btnAtrás.setDisable(true);

        btnSiguiente.setOnAction(event -> {
            indiceActual++;
            if (indiceActual >= vehiculos.size()) {
                indiceActual = 0;
            }
            
            vehiculoActual = vehiculos.get(indiceActual);
            actualizarVehiculo(vehiculoActual);
            habilitarBtnsCA(vehiculoActual);
            
//            actualizarVehiculo(vehiculos.get(indiceActual));           
//            habilitarBtnsCA(vehiculos.get(indiceActual));
        });

        btnAtrás.setOnAction(event -> {
            indiceActual--;
            if (indiceActual < 0) {
                indiceActual = vehiculos.size() - 1; 
            }
            
            vehiculoActual = vehiculos.get(indiceActual);
            actualizarVehiculo(vehiculoActual);
            habilitarBtnsCA(vehiculoActual);
            
//            actualizarVehiculo(vehiculos.get(indiceActual));
//            habilitarBtnsCA(vehiculos.get(indiceActual));
        });         
    }
    
    private void actualizarContabilidad(ArrayList<Vehiculo> vehiculoContados){
        cantidadAutos = UtileriaFunciones.contarTipos(vehiculoContados, "Carro");
        cantidadCamionetas = UtileriaFunciones.contarTipos(vehiculoContados, "Camioneta");
        cantidadMotos = UtileriaFunciones.contarTipos(vehiculoContados, "Moto");
        lblCantAutos.setText(String.valueOf(cantidadAutos));
        lblCantCamionetas.setText(String.valueOf(cantidadCamionetas));
        lblCantMotos.setText(String.valueOf(cantidadMotos)); 
    }

    private void actualizarVehiculo(Vehiculo vehiculo) {
        UtileriaFunciones.mostrarImagen(vehiculo.getPlaca(), imgFile, imMostrarVehiculo);
        String mensaje = UtileriaFunciones.crearMensajeAuto(vehiculo.getTipo(), vehiculo.getPlaca(), vehiculo.getMarca(), vehiculo.getModelo(), vehiculo.getPrecio(), vehiculo.getKilometraje());
        txtAreaInfo.setText(mensaje);
        btnAtrás.setDisable(vehiculos.size() <= 1); 
    }
    
    public void habilitarBtnsCA(Vehiculo vehiculo){
        if(usuarioActual != null && vehiculo != null){
            if(UtileriaFunciones.otorgarPermisos(usuarioActual, vehiculo)){
                btnComprar.setDisable(true);
                btnAñadirFavorito.setDisable(true);
            } else{
                btnComprar.setDisable(false);
                btnAñadirFavorito.setDisable(false);
            }}
//        } else{
//            btnComprar.setDisable(true);
//            btnAñadirCarrito.setDisable(true);
//        }       
    }
    
    
    @FXML
    public void cambiarPestañas(ActionEvent event){
        vehiculos = Vehiculo.readFileSer();
        if(event.getSource() == btnSeccionPrincipal){
            dashboardPrincipal.setVisible(true);
            dashboardVV.setVisible(false);
            dashboardAV.setVisible(false);
            dashboardFavoritos.setVisible(false);
            if(!vehiculos.isEmpty()){
                navegarEnLista(vehiculos);                
            }
            cargarFiltros();
        } else if(event.getSource() == btnSeccionVer){
            dashboardPrincipal.setVisible(false);
            dashboardVV.setVisible(true);
            dashboardAV.setVisible(false);
            dashboardFavoritos.setVisible(false);
            cargarVehiculos();
//            actualizarContabilidad(vehiculos);
        } else if(event.getSource() == btnSeccionAgregar){
            dashboardPrincipal.setVisible(false);
            dashboardVV.setVisible(false);
            dashboardFavoritos.setVisible(false);
            dashboardAV.setVisible(true);
        } else if(event.getSource() == btnFavoritos){
            dashboardFavoritos.setVisible(true);
            dashboardPrincipal.setVisible(false);
            dashboardVV.setVisible(false);
            dashboardAV.setVisible(false);
//            favoritosDinamico(); // PRUEBA Y ERROR
        } else{
            dashboardPrincipal.setVisible(true);
            dashboardVV.setVisible(false);
            dashboardAV.setVisible(false);
            dashboardFavoritos.setVisible(false);
//            habilitarBtnsCA(vehiculoActual);
        }
    }
    
    @FXML
    private void fnSalir(MouseEvent event) {
        UtileriaFunciones.salirPantallaT(event);
    }

    @FXML
    public void fnCerrarSesion(MouseEvent event) {
        if(UtileriaMensajes.generarAlertaConfirmacion("Cerrar sesión", "¿Está seguro de cerrar su sesión?")){
            UtileriaFunciones.cambiarEscena(event, "login");
        }
    }

    @FXML
    private void fnFiltrar(MouseEvent event) {
        String tipo = this.cbxFTipo.getSelectionModel().getSelectedItem();
        String marca = this.cbxFMarca.getSelectionModel().getSelectedItem();
        String modelo = this.cbxFModelo.getSelectionModel().getSelectedItem();
        String precio = this.cbxFPrecio.getSelectionModel().getSelectedItem();
        String kilometraje = this.cbxFKilometraje.getSelectionModel().getSelectedItem();
        int precioMin = 1, precioMax = Integer.MAX_VALUE;
        double kilometrajeMin = 0, kilometrajeMax = Double.MAX_VALUE;
        try{
            if (precio.contains("+")){
                precioMin = Integer.parseInt(precio.split("\\+")[0]);
                precioMax = Integer.MAX_VALUE;
            } else if (precio.contains(" - ")){
                String[] precios = precio.split(" - ");
                precioMin = Integer.parseInt(precios[0]);
                precioMax = Integer.parseInt(precios[1]);
            }
            
            if (kilometraje.contains("+")){
                kilometrajeMin = Double.parseDouble(kilometraje.split("\\+")[0]);
                kilometrajeMax = Double.MAX_VALUE;
            } else if (kilometraje.contains(" - ")){
                String[] kilometrajes = kilometraje.split(" - ");
                kilometrajeMin = Double.parseDouble(kilometrajes[0]);
                kilometrajeMax = Double.parseDouble(kilometrajes[1]);
            }

            ArrayList<Vehiculo> vehFiltrados = Vehiculo.filtrarVehiculos(vehiculos, tipo, marca, modelo, precioMin, precioMax, kilometrajeMin, kilometrajeMax);
            navegarEnLista(vehFiltrados);
            actualizarContabilidad(vehFiltrados);
        } catch(Exception ex){
            UtileriaMensajes.generarAlertaError("Error de conexión", "Ocurrió un error en el proceso de filtrado");
        }
    }

    @FXML
    private void fnLimpiarFiltro(MouseEvent event) {
        cbxFTipo.getSelectionModel().selectFirst();
        cbxFMarca.getSelectionModel().selectFirst();
        cbxFModelo.getSelectionModel().selectFirst();
        cbxFPrecio.getSelectionModel().selectFirst();
        cbxFKilometraje.getSelectionModel().selectFirst();
    }

    @FXML
    private void fnImportarImagen(MouseEvent event) {
        fc.setTitle("Buscar Imagen");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*"));
        
        imgCargarFile = fc.showOpenDialog(null);
        
        if(imgCargarFile != null){
            Image imagen = new Image(imgCargarFile.toURI().toString()); 
            // Ajustando imagen
            imagen = UtileriaFunciones.ajustarTamañoImagen(imagen, imCargarVehiculo.getFitWidth(), imCargarVehiculo.getFitHeight());
            imCargarVehiculo.setImage(imagen);
        }
    }

    @FXML
    private void fnAgregarVehiculo(MouseEvent event) throws IOException {
        String placa = (String)this.txtPlaca.getText();
        String marca = (String)this.txtMarca.getText();
        String modelo = (String)this.txtModelo.getText();
        String tipo = (String)this.cbxTipo.getSelectionModel().getSelectedItem();
        String stKilometraje = (String)this.txtKm.getText();
        String stPrecio = (String)this.txtPrecio.getText();
        if(placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || stKilometraje.isEmpty() || stPrecio.isEmpty()){
            UtileriaMensajes.generarAlertaError("Información incompleta", "Debe rellenar todos los campos obligatoriamente");
        }
        else if(Vehiculo.checkPlaca(vehiculos, placa)){
            UtileriaMensajes.generarAlertaError("Vehiculo existente", "La placa del vehículo que intenta agregar ya se encuentra registrada");
        }
        else{
            try{
                double kilometraje; int precio;
                if(UtileriaFunciones.verificacionesNumericas(stKilometraje) && UtileriaFunciones.verificacionesNumericas(stPrecio)){
                    kilometraje = Double.parseDouble(stKilometraje);
                    precio = Integer.parseInt(stPrecio);
                    UtileriaFunciones.guardarImagen(imgCargarFile, placa);
                    if(UtileriaMensajes.generarAlertaConfirmacion("Confirmar registro", "¿Está seguro de registrar este vehiculo?")){
                        Vehiculo newVehiculo = new Vehiculo(placa, marca, modelo, tipo, precio, kilometraje, usuarioActual);
                        vehiculos.addLast(newVehiculo);
                        Vehiculo.saveListVehiculosSer(vehiculos);
                        usuarioActual.getVehiculos().addLast(newVehiculo);
                        UtileriaMensajes.generarAlertaInfo("Registro exitoso", "¡El vehiculo con placa "+ placa +" se ha registrado!");
                        actualizarContabilidad(vehiculos);
                    }
                } else{
                    UtileriaMensajes.generarAlertaError("Valores no permitidos", "Ingrese valores válidos.");
                }  
            } catch(IOException ioe){
                    UtileriaMensajes.generarAlertaError("Falta imagen", "Suba una imagen de su vehículo.");       
            }
        }
    }

    @FXML
    private void fnEliminarVehiculo(MouseEvent event) {
        if(UtileriaMensajes.generarAlertaConfirmacion("Eliminar vehículo", "¿Está seguro de eliminar el vehículo de placa "+vehiculoSeleccionadoActual.getPlaca()+"?")){
            UtileriaFunciones.eliminarMiVehiculo(usuarioActual, vehiculoSeleccionadoActual);
        } else{}
        limpiarSeleccion();
        btnEliminarVehiculo.setVisible(false);
        cargarVehiculos();
        actualizarContabilidad(vehiculos);
    }

    @FXML
    private void fnActualizarInfoVehiculo(MouseEvent event) {
        String placa = (String)this.txtPlaca.getText();
        if (!Vehiculo.checkPlaca(usuarioActual.getVehiculos(), placa)){
            UtileriaMensajes.generarAlertaError("Valores no permitidos", "No tiene un vehículo con la placa: " + placa);
        } else{
            Vehiculo vehiculoAct = Vehiculo.filtrarPlaca(vehiculos, placa);
            String marca = (String)this.txtMarca.getText();
            String modelo = (String)this.txtModelo.getText();
            String tipo = (String)this.cbxTipo.getSelectionModel().getSelectedItem();
            String stKilometraje = (String)this.txtKm.getText();
            String stPrecio = (String)this.txtPrecio.getText();
            
            if (!marca.isEmpty()){
                vehiculoAct.setMarca(marca);
            }
            if (!modelo.isEmpty()){
                vehiculoAct.setModelo(modelo);
            }
            if (!tipo.isEmpty()){
                vehiculoAct.setTipo(tipo);
            }
            if (!stKilometraje.isEmpty()){
                double kilometraje = Double.parseDouble(stKilometraje);
                vehiculoAct.setKilometraje(kilometraje);
            }
            if (!stPrecio.isEmpty()){
                int precion = Integer.parseInt(stPrecio);
                vehiculoAct.setPrecio(precion);
            }
//            try{
//                UtileriaFunciones.guardarImagen(imgCargarFile, placa);
                try{
                    Vehiculo.saveListVehiculosSer(vehiculos);
                    cargarVehiculos();
                    UtileriaMensajes.generarAlertaInfo("Actualizacion exitosa", "¡El vehiculo con placa "+ placa +" ha sido modificado!");
                } catch(Exception e){
                    UtileriaMensajes.generarAlertaError("Error ", "No se pudo actualizar el vehiculo de placa" + placa);
                }
//            } catch(Exception e){
//            UtileriaMensajes.generarAlertaError("Error ", "No se pudo actualizar el vehiculo de placa" + placa);
//            }
        }
    }

    @FXML
    private void fnLimpiar(MouseEvent event) {
        cbxTipo.getSelectionModel().selectFirst();
        txtPrecio.clear();
        txtModelo.clear();
        txtKm.clear();
        txtPlaca.clear();
        txtMarca.clear();
        imCargarVehiculo.setImage(null); 
        imgCargarFile = null;
    }
    
    private void cargarVehiculos(){
        obListVehiculos=FXCollections.observableArrayList();
        tvColTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tvColPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tvColMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tvColModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tvColPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tvColKm.setCellValueFactory(new PropertyValueFactory<>("kilometraje"));
        
        vehiculos = Vehiculo.readFileSer();
        for(int i = 0; i < vehiculos.size(); i++){
            obListVehiculos.add(vehiculos.get(i));
        }
        this.tvVehiculo.setItems(obListVehiculos);
    }
    
    private void comprarVehiculo(Vehiculo v){
        if(UtileriaMensajes.generarAlertaConfirmacion("Confirmar compra", "¿Está seguro de comprar este vehículo?")){
            ArrayList<Usuario> usuarios = Usuario.readFileSer();
            Usuario vendedor = Usuario.filtrarUsuario(usuarios, v.getPropietario().getCorreo());
            vendedor.getVehiculos().remove(v);
            UtileriaFunciones.eliminarImagenVehiculoEliminado(v.getPlaca()); // Para eliminar la imagen de ese vehículo
            vehiculos.remove(v);
            Usuario.saveListUsuariosSer(usuarios);
            Vehiculo.saveListVehiculosSer(vehiculos);
            String cuerpo = "El propietario del vehículo:\n" + v.getMarca() + " " + v.getModelo() + " - Placa: " + v.getPlaca() + " - Recorrido: " + v.getKilometraje() + "\nHa aceptado tu oferta de: " + v.getPrecio();
            UtileriaMensajes.sendMensaje(vendedor.getCorreo(), "¡Una de sus ofertas ha sido aceptada!", cuerpo + "\nCorreo del propietario: " + usuarioActual.getCorreo());
            UtileriaMensajes.generarAlertaInfo("", "¡Se ha informado con éxito al vendedor de su compra!");
        } else {
            UtileriaMensajes.generarAlertaInfo("", "Parece que todavía no es el momento para que lo compres, ¡una lástima! :c");
        }
        actualizarContabilidad(vehiculos);
    }
    
    private void cargarFiltros(){
        limpiarCombobox();
        cbxFPrecio.getItems().addAll("0 - 4000", "4001 - 8000" , "8001 - 12000", "12000+");
        cbxFKilometraje.getItems().addAll("0 - 2000", "2001 - 5000" , "5001 - 8000", "8000+");

        ArrayList<String> tipos = UtileriaFunciones.getTipos(vehiculos);
        for(int i = 0; i < tipos.size(); i++){
            cbxFTipo.getItems().add(tipos.get(i));
        }
        ArrayList<String> marcas = UtileriaFunciones.getMarcas(vehiculos);
        for(int i = 0; i < marcas.size(); i++){
            cbxFMarca.getItems().add(marcas.get(i));
        }
        ArrayList<String> modelos = UtileriaFunciones.getModelos(vehiculos);
        for(int i = 0; i < modelos.size(); i++){
            cbxFModelo.getItems().add(modelos.get(i));
        }
    }
    
    private void seleccionTV(){
        ObservableList<Vehiculo>  filaSeleccionada = tvVehiculo.getSelectionModel().getSelectedItems();
        if(filaSeleccionada.size() == 1){
            Vehiculo vehiculoSeleccionado = filaSeleccionada.get(0);
            UtileriaFunciones.mostrarImagen(vehiculoSeleccionado.getPlaca(), imgFileSeleccionado, imMostrarMiVehiculo);
            String mensaje = UtileriaFunciones.crearMensajeAuto(vehiculoSeleccionado.getTipo(), vehiculoSeleccionado.getPlaca(), vehiculoSeleccionado.getMarca(), vehiculoSeleccionado.getModelo(), vehiculoSeleccionado.getPrecio(), vehiculoSeleccionado.getKilometraje());
            txtAreaInfoMiVeh.setText(mensaje);
            UtileriaFunciones.verificarPertenencia(usuarioActual, vehiculoSeleccionado, btnEliminarVehiculo);
            vehiculoSeleccionadoActual = vehiculoSeleccionado;
        }
    }
    
    private void limpiarSeleccion(){
        txtAreaInfoMiVeh.setText("");
        imgFileSeleccionado = null;
        imMostrarMiVehiculo.setImage(null);
    }
    
    public void limpiarCombobox(){
        cbxFTipo.getItems().setAll("Todos");
        cbxFMarca.getItems().setAll("Todos");
        cbxFModelo.getItems().setAll("Todos");
        cbxFPrecio.getItems().setAll("Todos");
        cbxFKilometraje.getItems().setAll("Todos");
        
        cbxFTipo.getSelectionModel().selectFirst();
        cbxFMarca.getSelectionModel().selectFirst();
        cbxFModelo.getSelectionModel().selectFirst();
        cbxFPrecio.getSelectionModel().selectFirst();
        cbxFKilometraje.getSelectionModel().selectFirst();
    }

    @FXML
    private void fnComprarVehiculo(MouseEvent event) {
        comprarVehiculo(vehiculoActual);
    }


    @FXML
    private void fnEliminarFavorito(MouseEvent event) {
    }

    @FXML
    private void fnComprarFavorito(MouseEvent event) {
    }

    @FXML
    private void fnAñadirFavorito(MouseEvent event) {
        
    }
//        favoritos = Favorito.readFileSer();
//        if(favoritos.isEmpty()){
//            favoritos.addLast(favoritoActual);
////            Favorito.saveListFavoritosSer(favoritos);
//        } else{
//            for(int i = 0; i < favoritos.size(); i++){
//                if(favoritos.get(i).getUsuario().getCorreo().equals(favoritoActual.getUsuario().getCorreo())){
//                    ArrayList<Vehiculo> vehiculosFavoritos1 = favoritos.get(i).getVehiculosFavoritos();
//                    if(vehiculosFavoritos1.isEmpty()){
//                       vehiculosFavoritos1.addLast(vehiculoActual);
//                       favoritos.get(i).setVehiculosFavoritos(vehiculosFavoritos1);
//                       UtileriaMensajes.generarAlertaInfo("", "¡Has añadido este vehículo a tus favoritos!");
////                       Favorito.saveListFavoritosSer(favoritos);
//                    } else{
//                        ArrayList<Vehiculo> vehiculosFavoritos2 = favoritos.get(i).getVehiculosFavoritos();
//                        for(int j = 0; j < vehiculosFavoritos2.size(); j++){
//                            if(vehiculosFavoritos2.get(j).getPlaca().equals(vehiculoActual.getPlaca())){
//                                UtileriaMensajes.generarAlertaError("", "Ya ha agregado este vehículo a sus favoritos.");
//                            } else{
//                                vehiculosFavoritos2.addLast(vehiculoActual);
//                                favoritos.get(i).setVehiculosFavoritos(vehiculosFavoritos2);
//                                UtileriaMensajes.generarAlertaInfo("", "¡Has añadido este vehículo a tus favoritos!");
////                                Favorito.saveListFavoritosSer(favoritos);
//                            }
//                        }
//                    }                        
//                }
//            }
//        }  
//        Favorito.saveListFavoritosSer(favoritos);  
//        favoritosDinamico();
//    }
//    
//    private void favoritosDinamico(){
//        ArrayList<Vehiculo> vehiculosFav = favoritoActual.getVehiculosFavoritos();
//        hbPrincipal.setSpacing(10);
//        if(vehiculosFav.size() > 0 ){
//            lblMDefault.setText("");
//            
//            for(int i = 0; i < vehiculosFav.size(); i++){
//                Vehiculo veh = vehiculosFav.get(i);
//                VBox vbox = new VBox(10);
//                String nombreImagen = veh.getPlaca()+".png";
//                String rutaProyecto = System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+File.separator+"resources";
//                String rutaCarpetaDestino = rutaProyecto + File.separator + "imagenesVehiculos";
//                imgFile = new File(rutaCarpetaDestino, nombreImagen);
//                Image imagen = new Image(imgFile.toURI().toString());
//                ImageView imv = null;
//                imagen = ajustarTamañoImagen(imagen, imv.getFitWidth() ,imv.getFitHeight());
//                imv.setImage(imagen);
//                String previoI = veh.getMarca().toUpperCase()+" "+veh.getModelo().toUpperCase()+"\n 💲"+veh.getPrecio();
//                Text text = new Text(previoI);
//                vbox.getChildren().addAll(imv, text);
//                VBox.setMargin(imv, new Insets(5));
//                VBox.setMargin(text, new Insets(5));
//                HBox.setMargin(vbox, new Insets(10));
//                hbPrincipal.getChildren().add(vbox); 
//                
//                vbox.setOnMouseClicked(event ->{
//                    String info = UtileriaFunciones.crearMensajeAuto(veh.getTipo(), veh.getPlaca(),veh.getMarca(), veh.getModelo(), veh.getPrecio(), veh.getKilometraje());
//                    txtInfoFavorito.setText(info);
//                });
//                
//            } 
//        } else{
//            lblMDefault.setText("No tiene favoritos por el momento");
//        } 
//    }
}
