package server.Businesses;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class CheckParamsBus {
    public  boolean checkParamTableIndex(HttpServletRequest httpRequest) {
        Map<String,String[]> listParams = httpRequest.getParameterMap();

        if (listParams.containsKey("pg_page") && listParams.containsKey("pg_size") && listParams.size() <= 3){
            if (listParams.containsKey("search_department") || listParams.containsKey("search_name") || listParams.size() == 2){
                return true;
            }
        }
        return false;
    }
}
