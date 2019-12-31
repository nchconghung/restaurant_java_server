package server.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import server.Models.OrderModel;
import server.Models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String>{
//	@Query("{$text:{$search: ?0}")
	@Query("{fullname: ?0}")
	List<UserModel> findByNameFTS(String keyword);
	
}
