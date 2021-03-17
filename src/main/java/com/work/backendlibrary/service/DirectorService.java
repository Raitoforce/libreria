package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.model.DirectorModel;

public interface DirectorService {
	List<DirectorModel> listAllDirectores();
	Director addDirector(DirectorModel directorm);
	void removeDirector(int id);
	Director updateDirector(Director director);
	DirectorModel consultarDirector(int id);
	List<DirectorModel> listPage(Pageable pageable);
}
