package server.Businesses;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import server.DAO.DepartmentRepository;
import server.DAO.TableRepository;
import server.Models.DepartmentModel;
import server.Models.TableModel;
import server.Models.Response.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Configurable
public class TableBus {

    @Autowired
    private TableRepository tableRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TableResp> getList(Map<String,String[]> params){
        int page = Integer.parseInt(params.get("pg_page")[0]),
            size = Integer.parseInt(params.get("pg_size")[0]);

        List<TableResp> list = new ArrayList<>();
        Page<TableModel> data = null;
        Pageable pageable;

        if (params.get("search_department") != null) {
            String department = params.get("search_department")[0];
            ObjectId departmentId = new ObjectId(department);
            pageable = PageRequest.of(page,size, Sort.by("name").ascending());
            data = tableRepository.findByDepartment(departmentId,pageable);
        } else if (params.get("search_name") != null) {
            String name = params.get("search_name")[0];
            pageable = PageRequest.of(page,size,Sort.by("department").ascending());
            data = tableRepository.findByName(name,pageable);
        } else if (params.get("search_status") != null){
            int status = Integer.parseInt(params.get("search_status")[0]);
            pageable = PageRequest.of(page,size, Sort.by("department").ascending().and(Sort.by("name").ascending()));
            data = tableRepository.findByStatus(status,pageable);
        } else {
        	pageable = PageRequest.of(page,size,Sort.by("department").ascending().and(Sort.by("name").ascending()));
            data = tableRepository.findAll(pageable);
        }

        for (TableModel entity : data.toList()) {
            TableResp tableObject = new TableResp(entity.getId(),
                    entity.getDepartment(),
                    entity.getStatus(),
                    entity.getName());
            list.add(tableObject);
        }

        return list;
    }

    public TableResp updateStatus(Map<String,String[]> params){
        String id = params.get("_id")[0];
        int status = Integer.parseInt(params.get("status")[0]);
        
        boolean flat = tableRepository.updateStatus(id, status);
        
        if (flat) {
        	TableModel tableModel =  tableRepository.findById(id).get();
        	if (tableModel == null) {
        		return null;
        	}

        	return new TableResp(tableModel.getId(),tableModel.getDepartment(),tableModel.getStatus(),tableModel.getName());
        }
        
        return null;
    }

	public TableResp getTableResp(String tableId) {
		TableModel tableModel = tableRepository.findById(tableId).get();
		
		if (tableModel != null) {
			TableResp result = new TableResp(tableModel.getId(),tableModel.getDepartment(),
											 tableModel.getStatus(),tableModel.getName());
			return result;
		}
		
		return null;
	}
	
	public TableWebResponse convertToTableWebResp(TableModel tableModel) {
		DepartmentModel department = departmentRepository.findById(tableModel.getDepartment()).get();
		TableWebResponse result = new TableWebResponse(tableModel.getId(),department,
				 tableModel.getStatus(),tableModel.getName());
		return result;
	}
	
	public TableWebResponse create(String name, String department) {
		TableModel data =  tableRepository.create(name,department);
		if (data == null) return null;
		
		return convertToTableWebResp(data);
	}

	public TableWebResponse update(String name, String department, String id) {
		TableModel data = tableRepository.update(name,department, id);
		if (data == null) return null;
		return convertToTableWebResp(data);
	}

	public boolean delete(String id) {
		try {
			tableRepository.deleteById(id);
			return true;
		} catch (Exception x) {
			return false;
		}
	}
}
