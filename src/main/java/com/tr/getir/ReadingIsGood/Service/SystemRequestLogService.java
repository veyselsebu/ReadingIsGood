package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Dto.GetLogRequestDTO;
import com.tr.getir.ReadingIsGood.Model.SystemRequestLog;
import com.tr.getir.ReadingIsGood.Repository.SystemRequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SystemRequestLogService {
    @Autowired
    SystemRequestLogRepository systemRequestLogRepository;

    public SystemRequestLog addLog(SystemRequestLog systemRequestLog){
        return  systemRequestLogRepository.insert(systemRequestLog);
    }

    public List<SystemRequestLog> getLog(GetLogRequestDTO requestDTO){
        Date startDate = requestDTO.getStartDate();
        Date endDate = requestDTO.getEndDate();
        if (startDate == null){
            startDate = new Date(Long.MIN_VALUE);
        }
        if (endDate == null){
        endDate= new Date();
        }

        Optional <List<SystemRequestLog>> oSystemRequestLog =systemRequestLogRepository.findByRequestDateBetween(startDate,endDate);

        return oSystemRequestLog.isPresent() ? oSystemRequestLog.get() : new ArrayList<>();
    }

}
