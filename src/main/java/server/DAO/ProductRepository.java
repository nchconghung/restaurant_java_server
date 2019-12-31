package server.DAO;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import server.Models.ProductModel;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel,String>,ProductRepositoryCustom{
	@Query("{name: ?0}")
	Page<ProductModel> findByName(String name,Pageable pageable);
	
	Page<ProductModel> findByCategory(ObjectId category,Pageable pageable);
}
