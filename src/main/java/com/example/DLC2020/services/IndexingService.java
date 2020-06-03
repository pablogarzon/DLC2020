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
	private List<String> ignoredWords = new ArrayList<>();

	@Inject
	public IndexingService(DocumentoDao documentoDao, VocabularioDao vocabularioDao) {
		this.documentoDao = documentoDao;
		this.vocabularioDao = vocabularioDao;
	}

	public void indexing(String folder) throws Exception {
		File directory = new File(folder);

		if (!directory.isDirectory())
			throw new Exception("not valid directory");

		Map<String, Map<Integer, Integer>> wordPosts = new HashMap<>();
		Map<Integer, Documento> docs = new HashMap<>();

		for (final File file : directory.listFiles()) {
			Documento d = new Documento();
			d.setNombre(file.getName());
			d.setUrl(folder + "/" + file.getName());
			documentoDao.create(d);
			readFile(file, d.getIddoc(), wordPosts);
			docs.put(d.getIddoc(), d);
		}
		save(wordPosts, docs);
	}

	public void readFile(File file, int iddoc, Map<String, Map<Integer, Integer>> wordPosts) {

		try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
			stream.forEach(l -> {
				List<String> line = Stream.of(l.toLowerCase().split(Constants.DELIMS)).collect(Collectors.toList());

				// remover los stopwords
				line.removeAll(Constants.STOP_WORDS);

				for (String word : line) {
					if (ignoredWords.contains(word))
						continue;
					Map<Integer, Integer> posts = wordPosts.getOrDefault(word, new HashMap());
					int tf = posts.getOrDefault(iddoc, 0) + 1;
					if (tf < 50) {
						posts.put(iddoc, tf);
						wordPosts.put(word, posts);
					} else {
						if (word.length() < 2) {
							wordPosts.remove(word);
							ignoredWords.add(word);
						}
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void save(Map<String, Map<Integer, Integer>> wordPosts, Map<Integer, Documento> docs) {
		List<Vocabulario> words = new ArrayList<>();
		
		int cnt = 0;

		for (Entry<String, Map<Integer, Integer>> entry : wordPosts.entrySet()) {
			String word = entry.getKey();
			Map<Integer, Integer> posts = entry.getValue();

			Vocabulario v = new Vocabulario(word);

			for (Entry<Integer, Integer> post : posts.entrySet()) {
				int iddoc = post.getKey();

				Posteo p = new Posteo();
				p.setVocabulario(v);
				p.setDocumento(docs.get(iddoc));
				p.setTf(post.getValue());
				v.addPosteo(p);
			}
			
			words.add(v);
			
			if (cnt++ % 500 == 0) {
				vocabularioDao.createBatch(words);
				words.clear();
			}
		}
		if(words.size() > 0) {
			vocabularioDao.createBatch(words);
		}
	}
}
