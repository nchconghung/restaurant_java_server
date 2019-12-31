package server.Controllers.Web;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import server.Businesses.AuthBus;
import server.Businesses.CategoryBus;
import server.Businesses.CheckParamsBus;
import server.Businesses.ImageBus;
import server.Models.CategoryModel;
import server.Models.UserModel;

@RestController
@RequestMapping(path="/v1/category")
public class CategoryWebController {
	@Autowired
	CheckParamsBus checkParamsBus;
	
	@Autowired 
	CategoryBus categoryBus;
	
	@Autowired
	AuthBus authBus;
	
	@Autowired
	ImageBus imageBus;
	

	
	@RequestMapping(path="/index",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		
		if(!checkParamsBus.checkParamCategoryIndex(httpRequest)) {
			Map<String,Object> resp = new HashMap<String,Object>();
			
			resp.put("message","error");
			
			return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.BAD_REQUEST);
		}
		UserModel user = authBus.autheticateUser(httpRequest.getParameter("Authentication").toString());
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
		
		resp.put("message","success");
        resp.put("categories", categoryBus.getListForWeb(httpRequest.getParameterMap()));
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
	
	@RequestMapping(path="/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> create(HttpServletRequest httpRequest,
			@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "name",defaultValue = "") String name
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		
		
		String imgUrl = "";
		
		if (file != null) {
			
			imgUrl = "/images/" + imageBus.storageImage(file,"category");
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		CategoryModel rs  = categoryBus.create(imgUrl,name);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Thêm danh mục thành công!");
		resp.put("category",rs);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/edit",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> update(HttpServletRequest httpRequest,
			@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "name",defaultValue = "") String name,
			@RequestParam(value="id") String id
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		
		
		String imgUrl = "";
		
		if (!file.isEmpty()) {
			imgUrl = "/images/" + imageBus.storageImage(file,"category");
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		CategoryModel rs  = categoryBus.update(imgUrl,name,id);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Cập nhật thông tin danh mục thành công!");
		resp.put("category",rs);
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
		boolean rs  = categoryBus.delete(id);
		if (rs == false) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Xóa  thành công!");
		resp.put("category",id);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
}
