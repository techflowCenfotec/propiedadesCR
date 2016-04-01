package com.techflow.propiedadesCR.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.techflow.propiedadesCR.ejb.Tguide;
import com.techflow.propiedadesCR.ejb.Tbank;
/**
* <h1>Repositorio de guias</h1>
* Esta clase se encarga de la comunicación con la base de datos
*
* @author  Jimmi Vila
* @version 1.0
* @since 15/03/2016
*/
public interface GuidesRepository extends CrudRepository<Tguide, Integer>{
	
   List<Tguide> findAllByTbank(Tbank pbank);
}
