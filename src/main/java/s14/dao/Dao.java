package s14.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> { 		  //interfaccia generica

    Optional<T> get(long id);	  

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(long id);
}