/**
* <h1>Servicio de usuarios</h1> 
* Esta clase  se encarga de comunicarse con el repositorio.
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 25/2/2016
*/
package com.techflow.propiedadesCR.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techflow.propiedadesCR.contracts.PasswordRequest;
import com.techflow.propiedadesCR.contracts.UsersRequest;
import com.techflow.propiedadesCR.contracts.UsersResponse;
import com.techflow.propiedadesCR.ejb.Tproperty;
import com.techflow.propiedadesCR.ejb.Trole;
import com.techflow.propiedadesCR.ejb.Tuser;
import com.techflow.propiedadesCR.pojo.DistrictPOJO;
import com.techflow.propiedadesCR.pojo.PropertyPOJO;
import com.techflow.propiedadesCR.ejb.TuserReview;
import com.techflow.propiedadesCR.pojo.RolePOJO;
import com.techflow.propiedadesCR.pojo.UserPOJO;
import com.techflow.propiedadesCR.pojo.UserReviewPOJO;
import com.techflow.propiedadesCR.repositories.UsersRepository;


@Service
public class UsersService implements UsersServiceInterface{
	
	/**	 
     * El objeto usersRepository es el que se encarga de la comunicación con la BD. 
     */
	@Autowired private UsersRepository usersRepository;
	

	/**
	  * Este método retorna todos los usuarios registrados en el sistema
	  *
	  * @param pusersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return uiUsers Retorna la respuesta del repositorio hacia el controlador.
	  */
	@Override
	@Transactional
	public List<UserPOJO> getAll(UsersRequest pusersRequest) {
		List<Tuser> users = usersRepository.findAll();
		return generateUserDtos(users);
	}
	

	/**
	  * Este método retorna todos los usuarios vendedores registrados en el sistema
	  *
	  * @param pusersRequest Este parámetro encapsula la información solicitada por el usuario.
	  *
	  * @return response Retorna la respuesta del repositorio hacia el controlador.
	  */
	public UsersResponse getAllVendors(UsersRequest pusersRequest) {
		UsersResponse response = new UsersResponse();
		Trole role = new Trole();
		role.setIdRole(3);	
		Page<Tuser> users = usersRepository.findAllByTrole(role,new PageRequest(pusersRequest.getPageNumber(),pusersRequest.getPageSize()));
		response.setUsers(generateUserDtos(users.getContent()));
		response.setTotalPages(users.getTotalPages());
		response.setCode(200);
		response.setCodeMessage("Users fetch successful");
		return response;
	}
	
	/**
	  * Este método genera los objetos POJOS que se retornaran a la UI.
	  *
	  * @param pusers Lista de usuarios.
	  *
	  * @return uiUsers Se retorna una lista de usuarios POJOS.
	  */
	private List<UserPOJO> generateUserDtos(List<Tuser> pusers) {
		List<UserPOJO> uiUsers = new ArrayList<UserPOJO>();
		pusers.stream().forEach(u -> {
			Trole role =u.getTrole();			
			RolePOJO rolePOJO = new RolePOJO();
			UserPOJO dto = new UserPOJO();
			BeanUtils.copyProperties(role, rolePOJO);
			BeanUtils.copyProperties(u, dto);
			dto.setRole(rolePOJO);
			uiUsers.add(dto);
		});
		return uiUsers;
	}
	
	/**
	  * Este método registra un usuario en el sistema.
	  *
	  * @param puserRequest Encapsula los datos requeridos por el usuario.
	  * @param pidRole Identificador del rol asignado al usuario
      * 
	  * @return nuser Retorna el usuario creado.
	  */
	@Override
	public UserPOJO saveUser(UsersRequest puserRequest, int pidRole) {
		
		Tuser user = new Tuser();
		Trole role = new Trole();
		role.setIdRole(pidRole);
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		user.setTrole(role);
		StringBuffer md5password = new StringBuffer();
    	
        MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(puserRequest.getUser().getPassword().getBytes());
		        
		        byte byteData[] = messageDigest.digest();
		        //convert the byte to hex format method 1
		       
		        for (int i = 0; i < byteData.length; i++) {
		         md5password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		         user.setPassword(md5password.toString());
		        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserPOJO userPOJO = new UserPOJO();
		Tuser newUser = usersRepository.save(user);
		BeanUtils.copyProperties(newUser, userPOJO);
		
		return userPOJO;
	
	}
	
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pidUser Identificador del usuario.
      * 
	  * @return userPOJO Retorna el usuario consultado.
	  */
	@Override
	public UserPOJO consultUser(int pidUser){
		
		Tuser nuser = usersRepository.findByIdUser(pidUser);
		UserPOJO userPOJO =null;
		
		if (null != nuser){
			Trole role =nuser.getTrole();			
			RolePOJO rolePOJO = new RolePOJO();
			BeanUtils.copyProperties(role, rolePOJO);
			userPOJO = new UserPOJO();
			userPOJO.setRole(rolePOJO);
			BeanUtils.copyProperties(nuser, userPOJO);
		}	
		return userPOJO;
		
	}
	
	/**
	  * Actualiza el usuario con la lista de propiedades. Retorna la entidad almacenada por si hay que realizar operaciones adicionales
	  * ya que la entidad puede cambiar al ser almacenda.
	  * 
	  * @param pUser Contiene la infomarción a almacenar a la base de 
	  * datos por medio del repositorio. No debe ser nulo.
	  * 
	  * @return user Una entidad del tipo.
	  */
	@Override
	@Transactional
	public Tuser updateFavorites(Tuser pUser) {
		Tuser user =  usersRepository.save(pUser);
		return user;
	}
	
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pIdUser Identificador del usuario.
      * 
	  * @return nuser Retorna el usuario modificado.
	  */

	
	@Override
	public Tuser modifyUser(UsersRequest puserRequest, int pidRole) {
		
		Tuser user = new Tuser();
		Trole role = new Trole();
		role.setIdRole(pidRole);
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		user.setPassword(usersRepository.findOne(puserRequest.getUser().getIdUser()).getPassword());
		user.setTrole(role);
	
		Tuser nuser = usersRepository.save(user);
		
		return nuser;
	
	}

	@Override
	public Tuser getUserByEmail(String pemail){
		
		Tuser nuser = usersRepository.findUserByEmail(pemail);
		return nuser;
		
	}
	
	/**
	  * Este método modifica la contraseña de un usuario
	  *
	  * @param ppasswordRequest Encapsula los datos requeridos 
	  *	pora el cambio  de contraseña.
      * 
	  * @return nuser Retorna el usuario modificado.
	  */
	@Override
	public Tuser changePass(PasswordRequest ppasswordRequest){
		StringBuffer md5password = new StringBuffer();
		MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update( ppasswordRequest.getNewPass().getBytes());
			        
			        byte byteData[] = messageDigest.digest();
			        //convert the byte to hex format method 1
			       
			        for (int i = 0; i < byteData.length; i++) {
			         md5password.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	
			        }
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		Tuser user = usersRepository.findByIdUser(ppasswordRequest.getId());
		
		user.setPassword(md5password.toString());
		Tuser modifiedUser = usersRepository.save(user);
	
		return modifiedUser;
		
	}
	/**
	  * Este retorna el usaurio que se consulto.
	  *
	  * @param pIdUser Identificador del usuario.
     * 
	  * @return Tuser Retorna el usuario consultado.
	  */
	@Override
	@Transactional
	public Tuser getUserByID(int pIdUser) {
		return usersRepository.findOne(pIdUser);

	}
	
	/**
	  * Método encargado de retornar un usario administrador.
	  *
	  *	@author Valeria Ramírez Cordero
	  * 
	  * @return Tuser Retorna el usuario Administrador del sistema.
	  */
	
	@Override
	public Tuser getUserAdmin() {
		 return usersRepository.findOne(1);
	}

	/**
	 * Este método realiza un borrado logico al usuario
	 * 
	 * @param puserRequest Encapsula la información del correo.
	 *
	 *@return response Retorna el objeto al que se le aplicó el borrado.
	 */
	@Override
	public Tuser deleteUser(UsersRequest puserRequest) {
		
		Tuser user = new Tuser();
		user.setTrole(new Trole());
		BeanUtils.copyProperties(puserRequest.getUser(), user);
		BeanUtils.copyProperties(puserRequest.getUser().getRole(),user.getTrole());
		return usersRepository.save(user);
		

	}
	/**
	  * Este retorna el vendedor que se consulto.
	  *
	  * @param pidUser Identificador del usuario.
      * 
	  * @return userPOJO Retorna el usuario consultado.
	  */
	@Override
	public UserPOJO consultVendor(int pidUser){
		
		Tuser nuser = usersRepository.findByIdUser(pidUser);
		UserPOJO userPOJO =null;
		
		if (null != nuser){
			Trole role =nuser.getTrole();			
			RolePOJO rolePOJO = new RolePOJO();
			BeanUtils.copyProperties(role, rolePOJO);
			userPOJO = new UserPOJO();
			userPOJO.setRole(rolePOJO);
			BeanUtils.copyProperties(nuser, userPOJO);
		}
		generateReviewsDtos(nuser.getTuserReviews2(), userPOJO);
		return userPOJO;
		
	}
	/**
	  * Este método genera los objetos POJOS que se retornaran a la UI.
	  *
	  * @param pusers Lista de usuarios.
	  *
	  */
	private void generateReviewsDtos(List<TuserReview> pReviews,UserPOJO user) {
		List<UserReviewPOJO> uiRatings = new ArrayList<UserReviewPOJO>();
		pReviews.stream().forEach(u -> {
			UserReviewPOJO userRatingPOJO = new UserReviewPOJO();
			BeanUtils.copyProperties(u, userRatingPOJO);
			userRatingPOJO.setTuser1(new UserPOJO());
			BeanUtils.copyProperties(u.getTuser1(), userRatingPOJO.getTuser1());
			uiRatings.add(userRatingPOJO);
		});
		user.setVendorRatings(uiRatings);
	}
	
	/**
	  * Este método se encarga de retornar las propiedades favoritas de un usuario.
	  * 
	  * @author Valeria Ramírez Cordero
	  * 
	  *@param puserRequest Objeto que contiene el id del.
	  *
	  */
	public List<PropertyPOJO> getAllFavorites(UsersRequest puserRequest){
		
		Tuser userLogged = usersRepository.findOne(puserRequest.getUser().getIdUser());
		List<PropertyPOJO> favoritesList = new ArrayList<PropertyPOJO>();
		List<Tproperty> propertiesList = userLogged.getTproperties2();
		
		propertiesList.stream().forEach(property ->{
			PropertyPOJO propertyPOJO = new PropertyPOJO();
			BeanUtils.copyProperties(property, propertyPOJO);
		
			propertyPOJO.setTuser(null);
			propertyPOJO.setTusers(null);
			propertyPOJO.setCoordinates(null);
			propertyPOJO.setSaleType(null);
			propertyPOJO.setSoldDate(null);
			propertyPOJO.setTbenefits(null);;
			propertyPOJO.setTdistrict(new DistrictPOJO());
			propertyPOJO.getTdistrict().setName(property.getTdistrict().getName());
			favoritesList.add(propertyPOJO);
			
		});
		return favoritesList;
	}
	
	/**
	  * Este método cambiia el estado de un usuario
	  * para que no sea su primera vez en la aplicación.
	  *
	  * @param puserRequest Contiene información del objeto a modificar.
      * 
	  * @return newUser Devuelve el usuario con su nuevo estado.
	  */
	
	public Tuser notFirstTime(UsersRequest puserRequest) {
		Tuser user = usersRepository.findOne(puserRequest.getUser().getIdUser());
		user.setFirstTime((byte) 1);;
		Tuser newUser = usersRepository.save(user);
		return newUser;
	}
}

