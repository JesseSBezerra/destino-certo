package br.com.destino_certo.util.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GerarTabelas {
	
	public static void main (String [] args){
        try {
            Configuration cfg = new Configuration();
            cfg.configure();
            SchemaExport se = new SchemaExport(cfg);
            se.create(true, true);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

}
