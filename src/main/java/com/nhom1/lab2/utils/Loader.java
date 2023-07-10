package com.nhom1.lab2.utils;
@FunctionalInterface
public interface Loader<T> {
    T load(String filePath) throws Exception;
}
