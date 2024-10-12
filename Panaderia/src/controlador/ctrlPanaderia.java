package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Panaderia;
import vista.frmPanaderia;

/**
 *
 * @author pc
 */
public class ctrlPanaderia implements MouseListener {
    
     //1- Mandar a llamar a las otras dos capas (modelo, vista)
    private Panaderia modelo;
    private frmPanaderia vista;
    
    //2- Crear el constructor de la clase
    public ctrlPanaderia (Panaderia modelo, frmPanaderia vista){
    
        this.modelo = modelo;
        this.vista = vista;
    
        
        vista.btnGuardar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.jtbPanadero.addMouseListener(this);
        
        modelo.Mostrar(vista.jtbPanadero);
    }
    
    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        vista.txtNombre.setText("");  
        vista.txtEdad.setText("");    
        vista.txtPeso.setText("");    
        vista.txtCorreo.setText("");   
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.jtbPanadero) {
        // Detecta si el clic fue un doble clic
        if (e.getClickCount() == 2) {
            // Obtener la fila seleccionada
            int filaSeleccionada = vista.jtbPanadero.getSelectedRow();
            
            if (filaSeleccionada != -1) {
                // Extraer los datos de la fila seleccionada
                String nombre = vista.jtbPanadero.getValueAt(filaSeleccionada, 1).toString();
                String edad = vista.jtbPanadero.getValueAt(filaSeleccionada, 2).toString();
                String peso = vista.jtbPanadero.getValueAt(filaSeleccionada, 3).toString();
                String correo = vista.jtbPanadero.getValueAt(filaSeleccionada, 4).toString();
                
                // Asignar los datos a los campos de texto
                vista.txtNombre.setText(nombre);
                vista.txtEdad.setText(edad);
                vista.txtPeso.setText(peso);
                vista.txtCorreo.setText(correo);
            }
        }
    }
        
        if(e.getSource() == vista.btnGuardar){
         
            modelo.setNombre_Panadero(vista.txtNombre.getText());
            modelo.setEdad_Panadero( Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPeso_Panadero(Double.parseDouble(vista.txtPeso.getText()));
            modelo.setCorreo_Panadero (vista.txtCorreo.getText());

            
            modelo.Guardar();  
            modelo.Mostrar(vista.jtbPanadero);
            limpiarCampos();
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbPanadero);
            modelo.Mostrar(vista.jtbPanadero);
            limpiarCampos(); 
        }
        
        if (e.getSource() == vista.btnActualizar) { 
        modelo.setNombre_Panadero(vista.txtNombre.getText());
        modelo.setEdad_Panadero(Integer.parseInt(vista.txtEdad.getText()));
        modelo.setPeso_Panadero(Double.parseDouble(vista.txtPeso.getText()));
        modelo.setCorreo_Panadero(vista.txtCorreo.getText());
        
        modelo.Actualizar(vista.jtbPanadero); 
        modelo.Mostrar(vista.jtbPanadero);
        limpiarCampos();
        }
        
        if (e.getSource() == vista.btnLimpiar) { 
        limpiarCampos(); 
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
