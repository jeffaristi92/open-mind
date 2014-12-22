/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ConnectionFactory;
import Controlador.OpmRecursosRolJpaController;
import Controlador.OpmRolJpaController;
import Controlador.OpmUsuarioJpaController;
import Modelo.OpmRecursosRol;
import Modelo.OpmRol;
import Modelo.OpmUsuario;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ARISTIZABAL
 */
public class Login extends javax.swing.JPanel {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jtpPassword = new javax.swing.JPasswordField();
        jbtIngresar = new javax.swing.JButton();

        jLabel1.setText("USUARIO");

        jLabel2.setText("CONTRAEÑA");

        jbtIngresar.setText("Ingresar");
        jbtIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtIngresar)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfUsuario)
                            .addComponent(jtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtIngresar)
                .addContainerGap(108, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIngresarActionPerformed
        // TODO add your handling code here:
        OpmUsuarioJpaController controladorUsuario = new OpmUsuarioJpaController((new ConnectionFactory()).getFactory());
        int usuario = Integer.parseInt(jtfUsuario.getText());
        String pass = jtpPassword.getText();

        OpmUsuario user = controladorUsuario.findOpmUsuario(usuario);
        
        if (user != null) {
            if (pass.equals(user.getNvPass())) {
                JOptionPane.showMessageDialog(this, "Login exitoso","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                OpmRolJpaController controladorRol = new OpmRolJpaController((new ConnectionFactory()).getFactory());
                OpmRol rol =  controladorRol.findOpmRol(user.getNmRol());
                List<OpmRecursosRol> lstRecursos = rol.getOpmRecursosRolList();
                for(OpmRecursosRol recurso : lstRecursos){
                    System.out.println(recurso.getNmRecurso());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraeña incorrectos","Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }else {
                JOptionPane.showMessageDialog(this, "Usuario o contraeña incorrectos","Mensaje", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jbtIngresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtIngresar;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JPasswordField jtpPassword;
    // End of variables declaration//GEN-END:variables
}
