package com.library.desafio.library.service;

public interface IConvierteDatos {
    public <T> T obtenerDatos(String json, Class<T> clase);
}
