package server.Businesses;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class CheckParamsBus {
	// GET:/table/index
    public  boolean checkParamTableIndex(HttpServletRequest httpRequest) {
        Map<String,String[]> listParams = httpRequest.getParameterMap();

        if (listParams.containsKey("pg_page") && listParams.containsKey("pg_size") && listParams.size() <= 3){
            if (listParams.containsKey("search_department") || listParams.containsKey("search_name") || listParams.containsKey("search_status") || listParams.size() == 2){
                return true;
            }
        }
        return false;
    }
    
    // POST:/table/status
    public boolean checkParamsUpdateTableStatus(HttpServletRequest httpRequest){
        Map<String,String[]> listParams = httpRequest.getParameterMap();
        if (listParams.containsKey("_id") && listParams.containsKey("status") && listParams.size() == 2){
            return true;
        }
        return false;
    }
    
    // GET: /department/index
    public boolean checkParamDepartmentIndex(HttpServletRequest httpRequest) {
    	Map<String,String[]> listParams = httpRequest.getParameterMap();
    	if (listParams.containsKey("search_hospital") && listParams.size() == 1) {
    		return true;
    	}
    	return false;
    }
    
    // GET /category/index
    public boolean checkParamCategoryIndex(HttpServletRequest httpRequest) {
    	Map<String,String[]> listParams = httpRequest.getParameterMap();
    	if (listParams.size() == 0) {
    		return true;
    	}
    	return false;
    }
    
    // GET: /product/index
    public boolean checkParamProductIndex(HttpServletRequest httpRequest) {
    	Map<String,String[]> listParams = httpRequest.getParameterMap();
    	if (listParams.containsKey("pg_page") && listParams.containsKey("pg_size") && listParams.size() <= 3){
    		if (listParams.containsKey("search_category") || listParams.containsKey("search_name") || listParams.size() == 2) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // GET: /table_statistics/index
    public boolean checkParamTableStatisticIndex(HttpServletRequest httpRequest) {
    	Map<String,String[]> listParams = httpRequest.getParameterMap();
    	if (listParams.containsKey("start_date_s") && listParams.containsKey("end_date_s")) {
    		return true;
    	}
    	return false;
    }
    
    // GET: /table_order/index
    public boolean checkParamOrderIndex(HttpServletRequest httpRequest) {
    	Map<String,String[]> listParams = httpRequest.getParameterMap();
    	if (listParams.containsKey("pg_page") && listParams.containsKey("pg_size") && listParams.containsKey("search_status")) {
    		if ((listParams.containsKey("start_date_s") && listParams.containsKey("end_date_s") && listParams.size() == 5) || listParams.size() == 3) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // POST: /table_order/status
	public boolean checkParamOrderUpdateStatus(HttpServletRequest httpRequest) {
		Map<String,String[]> listParams = httpRequest.getParameterMap();
		if (listParams.containsKey("_id") && listParams.containsKey("status") && listParams.size() == 2) {
			return true;
		}
		return false;
	}
}
