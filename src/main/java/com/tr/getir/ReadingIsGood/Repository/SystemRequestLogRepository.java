package com.tr.getir.ReadingIsGood.Repository;

import com.tr.getir.ReadingIsGood.Model.SystemRequestLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SystemRequestLogRepository extends MongoRepository<SystemRequestLog , String> {

    Optional<List<SystemRequestLog>> findByRequestDateBetween (Date startDate,Date EndDate);


}
