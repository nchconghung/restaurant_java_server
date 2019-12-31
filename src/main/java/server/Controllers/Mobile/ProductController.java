package server.Controllers.Mobile;

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

import server.Businesses.CheckParamsBus;
import server.Businesses.ProductBus;

@RestController
@RequestMapping(path="/mobi-v1/product")
public class ProductController {
	
	@Autowired
	CheckParamsBus checkParamsBus;
	
	@Autowired
	ProductBus productBus;
	
	@RequestMapping(path="/index",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		if(!checkParamsBus.checkParamProductIndex(httpRequest)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();
        
        resp.put("docs",productBus.getList(params));
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
}
