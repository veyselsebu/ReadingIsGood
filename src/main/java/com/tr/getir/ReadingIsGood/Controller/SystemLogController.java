package com.tr.getir.ReadingIsGood.Controller;


import com.tr.getir.ReadingIsGood.Dto.GetLogRequestDTO;
import com.tr.getir.ReadingIsGood.Model.SystemRequestLog;
import com.tr.getir.ReadingIsGood.Service.SystemRequestLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("system-log")
public class SystemLogController {
    @Autowired
    SystemRequestLogService systemRequestLogService;


    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @PostMapping("/get")
    public List<SystemRequestLog> getRequestLog(@RequestBody GetLogRequestDTO requestDTO){
        return systemRequestLogService.getLog(requestDTO);
    }
}
