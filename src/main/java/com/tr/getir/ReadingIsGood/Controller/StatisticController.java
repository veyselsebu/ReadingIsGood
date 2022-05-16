package com.tr.getir.ReadingIsGood.Controller;

import com.tr.getir.ReadingIsGood.Dto.StatisticResponseDTO;
import com.tr.getir.ReadingIsGood.Service.StatisticsService;
import com.tr.getir.ReadingIsGood.Service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("statistic")
public class StatisticController {
    @Autowired
    StatisticsService statisticsService;
    @Autowired
    UserService userService;

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping()
    public List<StatisticResponseDTO> getStatisticByUser(HttpServletRequest httpServletRequest){
        return statisticsService.orderStatisticsByUser(userService.getTokenToUser(httpServletRequest));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/admin/user/{userId}")
    public List<StatisticResponseDTO> getStatisticAdminByUser(@PathVariable String userId){
        return statisticsService.orderStatisticsByUser(userService.getUserById(userId));
    }

    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    @GetMapping("/admin/system")
    public List<StatisticResponseDTO> getStatisticAdminSystem(){
        return statisticsService.orderStatisticsAllSystem();
    }
}
