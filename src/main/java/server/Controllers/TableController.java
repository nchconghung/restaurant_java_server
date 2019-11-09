package server.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import server.Businesses.CheckParamsBus;
import server.Businesses.TableBus;
import server.DAO.TableRepository;
import server.Models.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/table")
public class TableController {

    @Autowired
    private TableBus tableBus;

    @Autowired
    private CheckParamsBus checkParamsBus;
    @RequestMapping(path="/index",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> index(HttpServletRequest httpRequest
//                                                           @RequestParam(value = "search_department",defaultValue = "") String departmentId,
//                                                           @RequestParam(value = "search_name",defaultValue = "") String name,
//                                                           @RequestParam(value = "pg_page",required = true) Integer page,
//                                                           @RequestParam(value = "pg_size",required = true) Integer size
    ){
        if (!checkParamsBus.checkParamTableIndex(httpRequest)){
            return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
        }

        Map<String,Object> resp = new HashMap<String,Object>();
        Map<String,String[]> params = httpRequest.getParameterMap();

        resp.put("docs",tableBus.getList(params));

        return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
    }


}