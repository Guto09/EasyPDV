/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easypdv;

import tela.TelaPrincipal;
import apoio.ConexaoBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tela.Senha;
 import javax.swing.UIManager.*;
/**
 *
 * @author Mileto
 */
public class EasyPDV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       

try {
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {
    // If Nimbus is not available, you can set the GUI to another look and feel.
}

        if (ConexaoBD.getInstance()
                .getConnection() != null) {
            Senha senha = new Senha();
            senha.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Erro ao abrir banco de dados, entre em contato com o suporte!");
        }

    }

}
