package com.work.backendlibrary.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.work.backendlibrary.entity.Director;
import com.work.backendlibrary.model.DirectorModel;

public interface DirectorService {
	public abstract List<DirectorModel> listAllDirectores();
	public abstract Director addDirector(DirectorModel directorm);
	public abstract void removeDirector(int id);
	public abstract Director updateDirector(Director director);
	public abstract DirectorModel consultarDirector(int id);
	public abstract List<DirectorModel> listPage(Pageable pageable);
}
