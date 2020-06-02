package com.example.DLC2020.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.inject.Inject;

import com.example.DLC2020.Commons;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class IndexingService {
	
	DocumentoDao dd;
	
	@Inject
	public IndexingService(DocumentoDao dd) {
		this.dd = dd;
	}

	public void indexing(String folder) throws Exception {
		File directory = new File(folder);

		if (!directory.isDirectory())
			throw new Exception("not valid directory");

		for (final File file : directory.listFiles()) {
			Documento d = new Documento();
			d.setNombre(file.getName());
			d.setUrl(folder + "/" + file.getName());
			dd.create(d);
			saveWords(file, d);
		}
	}
	
	public void saveWords(File file, Documento doc) {
		StringBuilder sb=new StringBuilder();
        
		try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append("\n");

            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo de entrada...");
        }
		
		String [] words = sb.toString().split(Commons.delims);
		
		for(int i =0; i< words.length;i++)
        {
            Vocabulario v =new Vocabulario(words[i].trim().toLowerCase());
            
//            ht.put(d.getIddoc(), palabras[i].trim().toLowerCase());
//                        
//            
//            Posteo p=new Posteo(new PosteoPK(palabras[i].trim().toLowerCase(), d.getIddoc()));
//            
//            Posteo p = new Posteo();
//			p.setVocabulario(v);
//			p.setDocumento(doc);
//			p.setTf(doc.getValue());
//			vocabulario.addPosteo(p);
//            
//            
//            p.setVocabulario(v);
//            p.setDocumentos(d);
//            PosteoDao pd=new PosteoDao();
//            VocabularioDao vd= new VocabularioDao();
//            try{
//
//                try{
//                    vd.create(v);
//                }
//                catch(Exception e){
//                    v=vd.retrieve(palabras[i].trim().toLowerCase());
//                    vd.update(v);
//                }
//                pd.create(p);
//            
//            }catch(Exception e){
//                //System.out.println("Sale por la excepcion: "+ e.getMessage());
//                //System.out.println("Clase de excepcion: "+e.getClass());
//                p=pd.retrieve(d.getIddoc(),v.getPalabra());
//                //System.out.println(p.toString());
//                p.aumentarTf(); //si el termino ya aparecio, incremento en 1
//                pd.update(p);
//            }
                        
        }
	}
}
