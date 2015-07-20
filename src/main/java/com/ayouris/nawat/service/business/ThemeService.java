package com.ayouris.nawat.service.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ayouris.nawat.service.business.impl.ThemeServiceImpl.Theme;

public interface ThemeService {

	List<Theme> getThemes();

}
