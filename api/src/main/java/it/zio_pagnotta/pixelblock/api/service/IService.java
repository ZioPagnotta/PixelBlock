package it.zio_pagnotta.pixelblock.api.service;

public interface IService {
    default void enable() throws RuntimeException {}
    default void disable() throws RuntimeException {}
}
