package server.Controller;

import org.springframework.data.mongodb.repository.MongoRepository;
import server.DAO.TableRepository;
import server.Model.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/table")
public class TableController {

    @Autowired
    private TableRepository tableRepository;

    @RequestMapping(path = "/test",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(){
        return "test";
    }

    @RequestMapping(path="/index",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> getBooksList(@RequestParam(value = "search_department",defaultValue = "") String departmentId,
                                                           @RequestParam(value = "search_name",defaultValue = "") String name,
                                                           @RequestParam(value = "pg_page",defaultValue = "0") Integer page,
                                                           @RequestParam(value = "pg_size",defaultValue = "0") Integer size){
        List<TableObject> list = new ArrayList<>();
        Map<String,Object> resp = new HashMap<String,Object>();
        List<TableModel> tables = null;

        if (!departmentId.isEmpty()) {
            tables = this.tableRepository.findByDepartment(departmentId);
        } else if (!name.isEmpty()) {
            tables = this.tableRepository.findByName(name);
        } else {
            tables = this.tableRepository.findAll();
        }

        for (TableModel entity : tables) {
            TableObject tableObject = new TableObject(entity.getId(),
                    entity.getDepartment(),
                    entity.getStatus(),
                    entity.getName());
            list.add(tableObject);
        }

        resp.put("docs",list);
        return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.OK);
    }

    private class TableObject{
        public String _id;
        public String department;
        public int status;
        public String name;

        public TableObject(String _id, String department, int status, String name) {
            this._id = _id;
            this.department = department;
            this.status = status;
            this.name = name;
        }
    }
}
