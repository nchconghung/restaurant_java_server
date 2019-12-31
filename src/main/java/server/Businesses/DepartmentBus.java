package server.Businesses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import server.DAO.DepartmentRepository;
import server.Models.DepartmentModel;
import server.Models.ProductModel;
import server.Models.Response.DepartmentResp;

@Service 
public class DepartmentBus {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<DepartmentResp> getList(Map<String,String[]> params){
		String hospital = params.get("search_hospital")[0];
//		String hospital = "5cde3a3f69c0a05894064039";
		List<DepartmentModel> data;
		List<DepartmentResp> result = new ArrayList<>();
		
		ObjectId hospitalId = new ObjectId(hospital);
		
		data = departmentRepository.findByHospital(hospitalId);
		for (DepartmentModel entity : data) {
			DepartmentResp departmentObject = new DepartmentResp(entity.getId(),
																 entity.getTables(),
																 entity.getAddress(),
																 entity.getName());
			result.add(departmentObject);
		}
		
		return result;
	}
	
	public List<DepartmentModel> getListForWeb(Map<String,String[]> params){
		
		int page = Integer.parseInt(params.get("pg_page")[0]);
		int size = Integer.parseInt(params.get("pg_size")[0]);
		Pageable pageable = PageRequest.of(page,size);
		
		List<DepartmentModel> result;
		
		if (params.get("search_name") != null) {
			String name = params.get("search_name")[0];
			result = departmentRepository.findByName(name,pageable);
		} else {
			result = departmentRepository.findAll(pageable).toList();
		}
		return result;
	}
	
	public DepartmentResp getDepartmentResp(String departmentId) {
		DepartmentModel data = departmentRepository.findById(departmentId).get();
		if (data != null) {
			DepartmentResp departmentResp = new DepartmentResp(data.getId(),data.getTables(),
															   data.getAddress(),data.getName());
			return departmentResp;
		}
		return null;
	}
	
	public DepartmentModel create(String name) {
		return departmentRepository.create(name);
	}

	public DepartmentModel update(String name,String id) {
		DepartmentModel data = departmentRepository.update(name,id);
		return data;
	}

	public boolean delete(String id) {
		try {
			departmentRepository.deleteById(id);
			return true;
		} catch (Exception x) {
			return false;
		}
	}
}
