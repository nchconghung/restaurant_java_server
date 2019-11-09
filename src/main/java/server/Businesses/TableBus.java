package server.Businesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.DAO.TableRepository;
import server.Models.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Configurable
public class TableBus {

    @Autowired
    private TableRepository tableRepository;

    public List<TableObject> getList(Map<String,String[]> params){
        int page = Integer.parseInt(params.get("pg_page")[0]),
            size = Integer.parseInt(params.get("pg_size")[0]);

        List<TableObject> list = new ArrayList<>();
        Page<TableModel> data = null;
        Pageable pageable;

        if (params.get("search_department") != null) {
            String departmentId = params.get("search_department")[0];
            pageable = PageRequest.of(page,size, Sort.by("name").ascending());
            data = tableRepository.findByDepartment(departmentId,pageable);
        } else if (params.get("search_name") != null) {
            String name = params.get("search_name")[0];
            pageable = PageRequest.of(page,size,Sort.by("department").ascending());
            data = tableRepository.findByName(name,pageable);
        } else {
            pageable = PageRequest.of(page,size,Sort.by("department").ascending().and(Sort.by("name").ascending()));
            data = tableRepository.findAll(pageable);
        }

        for (TableModel entity : data.toList()) {
            TableObject tableObject = new TableObject(entity.getId(),
                    entity.getDepartment(),
                    entity.getStatus(),
                    entity.getName());
            list.add(tableObject);
        }

        return list;
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
