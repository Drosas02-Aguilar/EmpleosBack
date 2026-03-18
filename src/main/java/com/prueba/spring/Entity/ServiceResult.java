
package com.prueba.spring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


public class ServiceResult<T> {
    @JsonIgnore
    public boolean correct;
    public int status;
    public String ErrorMessage;
    public T object;
    public Exception ex;
    public List objects; 
}
