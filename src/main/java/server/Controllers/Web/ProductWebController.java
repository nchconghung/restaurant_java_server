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

import server.Businesses.AuthBus;
import server.Businesses.CheckParamsBus;
import server.Businesses.ImageBus;
import server.Businesses.ProductBus;
import server.Models.CategoryModel;
import server.Models.ProductModel;
import server.Models.UserModel;
import server.Models.Response.ProductResp;

@RestController
@RequestMapping(path="/v1/dish")
public class ProductWebController {
	@Autowired
	CheckParamsBus checkParamsBus;
	
	@Autowired
	AuthBus authBus;
	
	@Autowired
	ImageBus imageBus;
	
	@Autowired
	ProductBus productBus;

	@RequestMapping(path="/index",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		if(!checkParamsBus.checkParamProductIndex(httpRequest)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();
        
        resp.put("message", "success");
        resp.put("dishes",productBus.getList(params));
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
	
	@RequestMapping(path="/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> create(HttpServletRequest httpRequest,
			@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "name",defaultValue = "") String name,
			@RequestParam(value = "price",defaultValue = "0") int price,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "describe") String describe
			){
//		if (!checkParamsBus.checkWebCategoryCreate(httpRequest)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
//		}
//		
		
		String imgUrl = "";
		
		if (file != null) {
			
			imgUrl = "/images/" + imageBus.storageImage(file,"product");
		}
		Map<String,Object> resp = new HashMap<String,Object>();
		ProductResp rs  = productBus.create(imgUrl,name,price,category,describe);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Thêm món ăn thành công!");
		resp.put("dish",rs);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path="/edit",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> update(HttpServletRequest httpRequest,
			@RequestParam(value = "img", required = false) MultipartFile file,
			@RequestParam(value = "id",defaultValue = "") String id,
			@RequestParam(value = "name",defaultValue = "") String name,
			@RequestParam(value = "price",defaultValue = "-1") int price,
			@RequestParam(value = "category",defaultValue = "") String category,
			@RequestParam(value = "describe",defaultValue = "") String describe
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
		ProductResp rs  = productBus.update(imgUrl,name,price,category,describe,id);
		if (rs == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Cập nhật thông tin món ăn thành công!");
		resp.put("dish",rs);
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
		boolean rs  = productBus.delete(id);
		if (rs == false) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resp.put("message", "Xóa thành công!");
		resp.put("dish",id);
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
}
