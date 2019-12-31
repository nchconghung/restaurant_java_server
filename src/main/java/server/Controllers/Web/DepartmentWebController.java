package server.Controllers.Web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import server.Businesses.CheckParamsBus;
import server.Businesses.DepartmentBus;
import server.Models.DepartmentModel;
import server.Models.ProductModel;

@RestController
@RequestMapping(path="/v1/department")
public class DepartmentWebController {
	@Autowired 
	private CheckParamsBus checkParamsBus;
	
	@Autowired
	private DepartmentBus departmentBus;
	
	@RequestMapping(path="/index",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		
//        if (!checkParamsBus.checkParamDepartmentIndex(httpRequest)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();
        
        resp.put("message","success");
        resp.put("departments", departmentBus.getListForWeb(params));
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
	
	@RequestMapping(path="/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> create(HttpServletRequest httpRequest,
			@RequestParam(value = "name",defaultValue = "") String name
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		
		
		Map<String,Object> resp = new HashMap<String,Object>();
		DepartmentModel rs  = departmentBus.create(name);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Thêm thành công!");
		resp.put("department",rs);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path="/edit",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> update(HttpServletRequest httpRequest,
			@RequestParam(value = "id",defaultValue = "") String id,
			@RequestParam(value = "name",defaultValue = "") String name
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		

		Map<String,Object> resp = new HashMap<String,Object>();
		DepartmentModel rs  = departmentBus.update(name,id);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Cập nhật thông tin thành công!");
		resp.put("department",rs);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path="/delete",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> delete(HttpServletRequest httpRequest,
			@RequestParam(value="id") String id
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		
		
		Map<String,Object> resp = new HashMap<String,Object>();
		boolean rs  = departmentBus.delete(id);
		if (rs == false) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Xóa thành công!");
		resp.put("department",id);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
}
