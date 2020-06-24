package com.example.DLC2020.services;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.example.DLC2020.commons.Constants;
import com.example.DLC2020.dal.commons.DocumentoDao;
import com.example.DLC2020.dal.commons.VocabularioDao;
import com.example.DLC2020.entities.Documento;
import com.example.DLC2020.entities.Posteo;
import com.example.DLC2020.entities.Vocabulario;

public class IndexingService {

	private final DocumentoDao documentoDao;
	private final VocabularioDao vocabularioDao;

	@Inject
	public IndexingService(DocumentoDao documentoDao, VocabularioDao vocabularioDao) {
		this.documentoDao = documentoDao;
		this.vocabularioDao = vocabularioDao;
	}

	public void indexing(String folder) throws Exception {
		File directory = new File(folder);

		if (!directory.isDirectory())
			throw new Exception("not valid directory");

		// map de primitivos donde la clave es la palabra, el valor seria otro map con 
		// el iddoc como clave y la cantidad de ocurrencias como valor
		Map<String, Integer> posts = new HashMap<>();

		int index = 0;
		for (final File file : directory.listFiles()) {
			if(documentoDao.findByName(file.getName()) == null) {
				try {
					posts = readFile(file);
					Documento d = new Documento();
					d.setNombre(file.getName());
					d.setUrl(folder + "/" + file.getName());
					save(d, posts);
				} catch (IOException e) {
					continue;
				}
			}
			
			index += 1;
			System.out.println("leyendo " + file.getName()+ " - " + index);
			
		}
	}

	public Map<String, Integer> readFile(File file) throws IOException {
		Map<String, Integer> posts = new HashMap<>();
		
		try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
			stream.forEach(l -> {
				List<String> line = Stream.of(l.toLowerCase().split(Constants.DELIMS)).collect(Collectors.toList());

				// remover los stopwords
				line.removeAll(Constants.STOP_WORDS);

				for (String word : line) {
					// posteos para este documento
					int tf = posts.getOrDefault(word, 0) + 1;
					posts.put(word, tf);
				}
			});
			
			return posts;
		} catch (IOException e) {
			throw e;
		}
	}

	private void save(Documento documento, Map<String, Integer> posts) {
		final int chunkSize = 500;
		
		ArrayList<Vocabulario> wordsToSave = new ArrayList<>();
		ArrayList<Vocabulario> wordsToUpdate = new ArrayList<>();
		
		documentoDao.create(documento);

		for (Entry<String, Integer> post : posts.entrySet()) {
			String word = post.getKey();
			
			boolean insert = false;
			
			Vocabulario v = vocabularioDao.findByWord(word);
			
			if (v == null) {
				v = new Vocabulario(word);
				insert = true;
			}

			Posteo p = new Posteo();
			p.setVocabulario(v);
			p.setDocumento(documento);
			p.setTf(post.getValue());
			v.addPosteo(p);
			
			if (insert) {
				wordsToSave.add(v);
			} else {
				wordsToUpdate.add(v);
			}
			
			if (wordsToSave.size() == chunkSize) {
				createWords(wordsToSave);
			}
			if (wordsToUpdate.size() == chunkSize) {
				updateWords(wordsToUpdate);
			}
		}
		createWords(wordsToSave);
		updateWords(wordsToUpdate);
	}
	
	private void updateWords(ArrayList<Vocabulario> wordsToUpdate) {
		if (wordsToUpdate.size() > 0) {
			vocabularioDao.updateBatch(wordsToUpdate);
			wordsToUpdate.clear();
		}
	}

	private void createWords(ArrayList<Vocabulario> wordsToSave) {
		if (wordsToSave.size() > 0) {
			vocabularioDao.createBatch(wordsToSave);
			wordsToSave.clear();
		}
	}
}
