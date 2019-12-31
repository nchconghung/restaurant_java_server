package server.DAO;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import server.Models.DepartmentModel;

@Repository
public interface DepartmentRepository extends MongoRepository<DepartmentModel,String>,DepartmentRepositoryCustom {
	List<DepartmentModel> findByHospital(ObjectId hospital);

	List<DepartmentModel> findByName(String name, Pageable pageable);
}
