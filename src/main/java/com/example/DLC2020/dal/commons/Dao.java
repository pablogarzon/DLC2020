/**
 * 
 */
package com.example.DLC2020.dal.commons;

//import utn.dlc.commons.exceptions.TechnicalException;
import java.util.List;

import javax.persistence.criteria.Expression;

/**
 * Interfaz que define los métodos mínimos a ser implementados por un dao para una entidad mapeada a la base de datos
 *  
 * @author Felipe
 *
 * @param <E> Entidad para la cual se implementa el presente dao
 * @param <K> Clave de la entidad representada por el dao
 */
public interface Dao<E extends DalEntity, K>
{
    void update(E pData);

    void delete(K pKey);
    
    E create(E pData);
    
    boolean createBatch(E[] pData);

    E retrieve(K pKey);

    List<E> findAll();
    
    List<E> findByFilter(Expression<Boolean> expression);
    
    long count();

}
