package ec.edu.uce.controlador;

import android.os.Environment;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ec.edu.uce.modelo.Vehiculo;

public class ArchivosConfig {
    public static List<Vehiculo> vehiculos;

    public List<Object> leerUsuario (String carpeta, String nombreArchivo) {

        List<Object> objects = new ArrayList();
        Object object;
        File localFile = new File((Environment.getExternalStorageDirectory() + carpeta));

        if (!localFile.exists()) {
            localFile.mkdir();
        }

        File file = new File(localFile, nombreArchivo + ".txt");

        try {
            FileInputStream fiInSt = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fiInSt);
            while (fiInSt.available() > 0) {
                object = ois.readObject();
                objects.add(object);
            }
            ois.close();
            fiInSt.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return objects;
    }

    public List<Object> leerVehiculo (String carpeta, String nombreArchivo){

        XStream xs = new XStream(new DomDriver());
        final String[] FECHAS = new String[]
                {
                        "dd-MMM-yyyy", "dd-MMM-yy", "dd-MM-yyyy",
                        "MM-dd-yy",
                        "yyyy-MMM-dd", "yyyy-MM-dd", "yyyy-dd-MM",
                        "yyyy/MM/dd", "yyyy.MM.dd"
                };
        DateConverter dateConv = new DateConverter("dd/MM/yyyy", FECHAS);
        xs.registerConverter(dateConv);
        xs.alias("vehiculos", List.class);
        xs.alias("vehiculo", Vehiculo.class);

        List<Object> objects = new ArrayList();
        //Object object;

        File localFile = new File((Environment.getExternalStorageDirectory() + carpeta));
        if (!localFile.exists()) {
            localFile.mkdir();
        }

        File file = new File(localFile, nombreArchivo + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            String linea ="";
            //FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((linea = br.readLine())!= null ){
                sb.append(linea);
            }
            objects.addAll((List<Vehiculo>) xs.fromXML(sb.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<Object> leerArchivoVehiculo(String carpeta, String nombre) {

        XStream xs = new XStream(new DomDriver());
        final String[] PATRONES = new String[]{"dd-MMM-yyyy",
                "dd-MMM-yy",
                "yyyy-MMM-dd",
                "yyyy-MM-dd",
                "yyyy-dd-MM",
                "yyyy/MM/dd",
                "yyyy.MM.dd",
                "MM-dd-yy",
                "dd-MM-yyyy"};
        DateConverter dateConverter = new DateConverter("dd/MM/yyyy", PATRONES);//pone todas las fecha que encuentre en un solo formato

        xs.registerConverter(dateConverter);
        xs.alias("vehiculos", List.class);//pone autos enbes de list en el xml
        xs.alias("vehiculo", Vehiculo.class);//pone sola auto enbes de pones todo el paquete

        List<Object> objects = new ArrayList();
        Object object;

        File localFile = new File((Environment.getExternalStorageDirectory() + carpeta));
        if (!localFile.exists()) {
            localFile.mkdir();
        }

        File file = new File(localFile, nombre + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            String texto = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((texto = br.readLine()) != null) {
                sb.append(texto);
            }
            objects.addAll((List<Vehiculo>) xs.fromXML(sb.toString()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return objects;
    }

    public static void persistir() throws IOException {
        File file;
        File localFile = new File(Environment.getExternalStorageDirectory() + "/archivos/");
        file = new File(localFile, "vehiculos.txt");
        file.delete();
        XStream xs = new XStream(new DomDriver());
        final String[] FECHAS = new String[]{"dd-MMM-yyyy",
                "dd-MMM-yy",
                "yyyy-MMM-dd",
                "yyyy-MM-dd",
                "yyyy-dd-MM",
                "yyyy/MM/dd",
                "yyyy.MM.dd",
                "MM-dd-yy",
                "dd-MM-yyyy"};
        DateConverter dateConverter = new DateConverter("dd/MM/yyyy", FECHAS);//pone todas las fecha que encuentre en un solo formato

        xs.registerConverter(dateConverter);
        xs.alias("vehiculos", List.class);//pone autos enbes de list en el xml
        xs.alias("vehiculo",  Vehiculo.class);//pone sola auto enbes de pones todo el paquete
        String xml = xs.toXML(vehiculos);

        FileWriter escribir = new FileWriter(file, true);
        escribir.write("");
        escribir.write(xml);
        escribir.close();
    }


}
