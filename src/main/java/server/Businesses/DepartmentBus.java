package server.Businesses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.DAO.DepartmentRepository;
import server.Models.DepartmentModel;
import server.Models.Response.DepartmentResp;

@Service 
public class DepartmentBus {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<DepartmentResp> getList(Map<String,String[]> params){
//		String hospital = params.get("search_hospital")[0];
		String hospital = "5cde3a3f69c0a05894064039";
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
	
	public DepartmentResp getDepartmentResp(String departmentId) {
		DepartmentModel data = departmentRepository.findById(departmentId).get();
		if (data != null) {
			DepartmentResp departmentResp = new DepartmentResp(data.getId(),data.getTables(),
															   data.getAddress(),data.getName());
			return departmentResp;
		}
		return null;
	}
}
