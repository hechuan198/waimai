package com.hechuan.waimai.service.impl;

import com.hechuan.waimai.dao.FilmDAO;
import com.hechuan.waimai.entity.Film;
import com.hechuan.waimai.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("filmService")
public class FilmServiceImpl implements FilmService {
	@Autowired
	@Resource
	private FilmDAO filmDAO;

	@Override // 继承接口的更新 返回值0(失败),1(成功)
	public int updateFilm(Film film) {
		return this.filmDAO.updateFilm(film);
	}


	@Override // 继承接口的查询全部
	public List<Film> getAllFilm() {
		return this.filmDAO.getAllFilm();
	}

	@Override // 继承接口的查询全部
	public List<Film> getFilmByHot() {
		return this.filmDAO.getFilmByHot();
	}

	@Override // 继承接口的查询全部
	public List<Film> getFilmByCate(String cateid) {
		return this.filmDAO.getFilmByCate(cateid);
	}

	@Override // 继承接口的按条件精确查询
	public List<Film> getFilmByCond(Film film) {
		return this.filmDAO.getFilmByCond(film);
	}

	@Override // 继承接口的按条件模糊查询
	public List<Film> getFilmByLike(Film film) {
		return this.filmDAO.getFilmByLike(film);
	}

	@Override // 继承接口的按主键查询 返回Entity实例
	public Film getFilmById(String filmid) {
		return this.filmDAO.getFilmById(filmid);
	}

}
