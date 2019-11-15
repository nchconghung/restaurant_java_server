package server.Controllers;

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
import org.springframework.web.bind.annotation.RestController;

import server.Businesses.CheckParamsBus;
import server.Businesses.OrderBus;
import server.Models.Response.OrderResp;
import server.Models.Temporary.OrderTemporary;
import server.Models.Temporary.SessionAddTemporary;

@RestController
@RequestMapping(path="/table_order")
public class OrderController {
	
	@Autowired
	private OrderBus orderBus;
	
	@Autowired 
	private CheckParamsBus checkParamsBus;
	
	@RequestMapping(path="/index",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> getList(HttpServletRequest httpRequest){
		if (!checkParamsBus.checkParamOrderIndex(httpRequest)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();

        resp.put("docs",orderBus.getList(params));

        return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(path="/index",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrder(HttpServletRequest httpRequest,@RequestBody OrderTemporary order){
		String id = orderBus.createOrder(order);
		
		if (id == null) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(id,HttpStatus.OK);
	}
	
	@RequestMapping(path="/add_session",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addSession(HttpServletRequest httpRequest,@RequestBody SessionAddTemporary session){
		System.out.println(session.getSession().getCreated_at());
		boolean flat = orderBus.addSession(session);
		
		if (!flat) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/status",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addSession(HttpServletRequest httpRequest){
		if (!checkParamsBus.checkParamOrderUpdateStatus(httpRequest)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		Map<String,String[]> params = httpRequest.getParameterMap();
		boolean flat;
		
		flat = orderBus.updateStatus(params);
		
		if (!flat) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/billing",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Object>> orderBilling(@RequestBody OrderResp order){
		// _id + bills
		
		boolean flat = orderBus.updateBilling(order);
		
		if (!flat) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
