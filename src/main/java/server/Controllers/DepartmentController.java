package server.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.Businesses.CheckParamsBus;
import server.Businesses.DepartmentBus;
import server.Models.Response.DepartmentResp;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
	
	@Autowired 
	private CheckParamsBus checkParamsBus;
	
	@Autowired
	private DepartmentBus departmentBus;
	
	@RequestMapping(path="/index")
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		
//        if (!checkParamsBus.checkParamDepartmentIndex(httpRequest)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();
        
        resp.put("docs", departmentBus.getList(params));
        
        return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
}
