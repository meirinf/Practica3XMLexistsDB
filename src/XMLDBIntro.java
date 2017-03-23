import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;

import java.io.File;
import java.util.Scanner;

/**
 * Created by dremon on 10/03/16.
 */
public class XMLDBIntro {

    static Scanner sc;
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private static Collection col;

    public static void main(String args[]) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

        sc = new Scanner(System.in);


        while(true){
            System.out.println("#### Exist DB ####");
            System.out.println("# 1. Crear una colección , Añadir un recurso, Pedir recurso#");
            System.out.println("# 2. Hacer una consulta #");
            System.out.println("# 3. Hacer Consulta sobre colección #");
            System.out.print("# Option:   ");
            int option = sc.nextInt();
            switch(option){
                case 1:
                    afegirFitxer("mondial.xml");
                    break;

                case 2:
                    hacerConsulta ();
                    break;

                case 3:
                    hacerConsultaSobreCole();
                    break;
                case 4:
                    System.exit(1);
                    break;
            }
        }

    }

    private static void hacerConsultaSobreCole() {
    }

    private static void hacerConsulta() {

    }

    private static void afegirFitxer(String fl) throws XMLDBException,
            ClassNotFoundException, IllegalAccessException, InstantiationException{

        File f = new File("mondial.xml");

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // crear el manegador
        DatabaseManager.registerDatabase(database);

        //Crear Colección
        col = DatabaseManager.getCollection(URI+"/db","admin","ADMIN");
        CollectionManagementService agt =(CollectionManagementService) col.getService("CollectionManagementService", "1.0");
        col = agt.createCollection("mondialPrueba");        //afegir el recurs que farem servir
        Resource res = col.createResource("mondialPrueba","XMLResource");

       //Añadir recurso
        res.setContent(f);
        col.storeResource(res);

        //Pedir Recurso
        Resource res2 = col.getResource("mondialPrueba");
        System.out.println(res2.getContent());
    }


}