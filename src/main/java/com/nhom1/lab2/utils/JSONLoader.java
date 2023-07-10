package com.nhom1.lab2.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JSONLoader<T> {
    default T load(Loader<T> loader, String filePath) throws Exception {
        return loader.load(filePath);
    };
}
