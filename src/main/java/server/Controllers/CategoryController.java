package server.Controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import server.Businesses.CategoryBus;
import server.Businesses.CheckParamsBus;

@RestController
@RequestMapping(path="/category")
public class CategoryController {
	
	@Autowired
	CheckParamsBus checkParamsBus;
	
	@Autowired 
	CategoryBus categoryBus;
	
	@RequestMapping(path="/index",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		
		if(!checkParamsBus.checkParamCategoryIndex(httpRequest)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
//        Map<String,String[]> params = httpRequest.getParameterMap();
		
        resp.put("docs", categoryBus.getList());
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
	
}
