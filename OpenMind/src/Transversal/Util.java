/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transversal;

import java.util.List;

/**
 *
 * @author RONALD
 */
public class Util {
    
    public static boolean validarCamposObligatorios(List<String> camposAlfanumericos, List<String> camposNumericos) {
        for (String campoAlfanumerico : camposAlfanumericos) {
            if (campoAlfanumerico.isEmpty()) {
                return false;
            }
        }
        for (String campoNumerico : camposNumericos) {
            if (validarNumero(campoNumerico)) {
                return false;
            }
        }
        return true;
    }
    
    static boolean validarNumero(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (Exception exp) {
            return false;
        }
    }
    
}
