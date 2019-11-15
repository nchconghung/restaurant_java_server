package server.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import server.Models.OrderModel;

@Repository
public interface OrderRepository extends MongoRepository<OrderModel,String>,OrderRepositoryCustom{
	
	@Query("{created_at: {$gte: ?0,$lte: ?1}}")
	List<OrderModel> findOrderInTime(int startDate,int endDate);
	
	@Query("{created_at: {$gte: ?0,$lte: ?1},status: ?2}")
	Page<OrderModel> findOrderInTimeWithPageable(int startDate,int endDate,int status,Pageable pageable);
	
	Page<OrderModel> findByStatus(int status,Pageable pageable);
}
