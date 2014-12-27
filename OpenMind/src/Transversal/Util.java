/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transversal;

import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author RONALD
 */
public class Util {

    public static boolean validarCamposObligatorios(List<String> campos) {
        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static String getFormatoMoneda(double valor) {
        DecimalFormat formato = new DecimalFormat("$###,###.###");
        return formato.format(valor);
    }

    public static String getValorMoneda(String moneda) {
        return moneda.replace("$", "").replace(".", "");
    }

    public static boolean validarNumero(String valor, boolean entero) {
        try {
            if (entero) {
                Integer.parseInt(valor);
                return true;
            } else {
                Double.parseDouble(valor);
                return true;
            }
        } catch (Exception exp) {
            return false;
        }
    }

    public static boolean validarCampoNumerico(KeyEvent evt, boolean decimal, String cadena) {
        char key = evt.getKeyChar();
        if (Character.isDigit(key) || (key == '.')) {
            if (decimal) {
                if (key == '.') {
                    if (cadena.contains(".")) {
                        return false;
                    }
                }
            } else {
                if (key == '.') {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
