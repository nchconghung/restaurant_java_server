package server.DAO;

import server.Model.TableModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository  extends MongoRepository<TableModel,String> {
    List<TableModel> findByName(String name);
    List<TableModel> findByDepartment(String departmentId);
}
