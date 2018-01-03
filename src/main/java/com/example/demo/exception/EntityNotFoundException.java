package com.example.demo.exception;

/**
 * Created by GChidhambaranathan on 12/30/2017.
 */
public class EntityNotFoundException extends RuntimeException{
    private final long id;

    public EntityNotFoundException(long id){
        super("Entity Not found Exception");
       this.id = id;
    }

    public long getId(){
        return this.id;
    }
}
