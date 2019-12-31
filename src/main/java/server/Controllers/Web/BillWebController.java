package server.Controllers.Web;

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
import org.springframework.web.bind.annotation.RestController;

import server.Businesses.CheckParamsBus;
import server.Businesses.OrderBus;

@RestController
@RequestMapping(path="/v1/bill")
public class BillWebController {
	@Autowired
	private OrderBus orderBus;
	
	@Autowired
	private CheckParamsBus checkParamsBus;
	
	@RequestMapping(path="/filter",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Map<String,Object>> searchBill(HttpServletRequest httpRequest,
			@RequestParam(defaultValue = "-1") int startDate,
			@RequestParam(defaultValue = "-1") int endDate,
			@RequestParam(defaultValue = "") String employeeSearch, 
			@RequestParam(defaultValue = "-1") int total,
			@RequestParam(defaultValue = "") String billID,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "1") int pageSize
			){
//		if (!checkParamsBus.checkParamWebBillSearch(httpRequest)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		Map<String,Object> resp = new HashMap<String,Object>();
		resp.put("bills",orderBus.getListWebAdmin(startDate,endDate,employeeSearch,total,billID,pageNo,pageSize));
		return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
}
