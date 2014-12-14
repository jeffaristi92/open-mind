/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transversal;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author ARISTIZABAL
 */
public class Imprimible implements Printable {

    Component componente;

    public Imprimible() {

    }

    public void setComponente(Component c) {
        componente = c;
    }

    public void imprimir() {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            job.printDialog();
            job.print();
        } catch (PrinterException ex) {
        }
    }

    @Override
    public int print(Graphics grphcs, PageFormat pf, int i) throws PrinterException {
        if (i == 0) {
            Graphics2D gp = (Graphics2D) grphcs;
            gp.translate(pf.getImageableX(), pf.getImageableY());
            gp.scale(1, 1);
            componente.printAll(gp);
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

}
