package server.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import server.Models.CategoryModel;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel,String>{

}
