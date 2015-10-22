package Serializar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class Serializar2 {

    String ruta = "/home/oracle/Desktop/Pruebas/texto12.txt";
    String[] cod = {"p1", "p2", "p3"};
    String[] desc = {"parafusos", "cravos", "tachas"};
    int[] price = {3, 4, 5};

    public static void main(String[] args) {
        Serializar2 ser = new Serializar2();
        ser.Escribir();
        ser.Leer();
    }

    public void Escribir() {

        try {

            FileOutputStream fos = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < price.length; i++) {
                Products p1 = new Products(cod[i], desc[i], price[i]);
                oos.writeObject(p1);
            }
            //oos.writeObject(null);
            oos.close();
            fos.close();
            System.out.println("Grabado");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serializar2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serializar2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Leer() {
        try {
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Products obx;
            while (fis.available() != 0) {

                obx = (Products) ois.readObject();
                System.out.println("Valores del objeto Products: "
                        + "\n Código: " + obx.getCodigo()
                        + "\n Descripción: " + obx.getDescripcion()
                        + "\n Precio: " + obx.getPrezo());
            }
            /*Otra forma de hacerlo
             while ((obx=(Products) ois1.readObject()) != null) {
             // override method --> System.out.println(obx.toString());
             System.out.println("Valores del objeto Products: " 
             + "\n Código: " + obx.getCodigo() 
             + "\n Descripción: " + obx.getDescripcion() 
             + "\n Precio: " + obx.getPrezo());            }*/
            ois.close();
            fis.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");

        } catch (IOException ex) {
            System.out.println("Eror: " + ex);
        }

    }
}
