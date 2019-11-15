package server.DAO;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import server.Models.TableModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository  extends MongoRepository<TableModel,String>,TableRepositoryCustom {
    Page<TableModel> findByName(String name, Pageable pageable);
    Page<TableModel> findByDepartment(ObjectId departmentId, Pageable pageable);
    Page<TableModel> findByStatus(int status,Pageable pageable);
}
