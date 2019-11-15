package server.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import server.Models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String>{
}
