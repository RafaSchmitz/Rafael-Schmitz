package br.edu.utfpr.model;

import java.io.Serializable;

public interface AbstractModel <ID extends  Serializable> 
            extends Serializable{
    ID getId();
}
