package com.bootcampProject.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;

public interface ModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
