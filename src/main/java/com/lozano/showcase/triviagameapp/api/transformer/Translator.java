package com.lozano.showcase.triviagameapp.api.transformer;

public abstract class Translator<T , K> {

    public T toApiModel(K domain){
        return null;
    }

    public K toDomainModel(T api){
        return null;
    }

}
