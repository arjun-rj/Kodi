package com.ourteam.kodi.service;

import java.util.List;
import java.util.Optional;

import com.ourteam.kodi.document.Hen;

public interface RootService {

	public List<Hen> getAllHens();

	public Hen addHen(Hen hen);

	public Optional<Hen> getHen(String id);
}