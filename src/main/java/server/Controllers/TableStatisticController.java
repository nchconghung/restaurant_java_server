package server.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import server.Businesses.TableStatisticBus;
import server.Models.Response.TableStatisticResp;

@RestController
@RequestMapping(path="/table_statistics")
public class TableStatisticController {
	
	@Autowired
	CheckParamsBus checkParamsBus;
	
	@Autowired
	TableStatisticBus tableStatisticBus;
	
	@RequestMapping(path="/index",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TableStatisticResp> getStatistic(HttpServletRequest httpRequest){
		if(!checkParamsBus.checkParamTableStatisticIndex(httpRequest)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Map<String,String[]> params = httpRequest.getParameterMap();
		
		TableStatisticResp resp = tableStatisticBus.getStatistics(params);
        
        return new ResponseEntity<TableStatisticResp>(resp,HttpStatus.OK);
	}
}
