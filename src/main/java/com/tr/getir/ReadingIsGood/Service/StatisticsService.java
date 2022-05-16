package com.tr.getir.ReadingIsGood.Service;

import com.tr.getir.ReadingIsGood.Dto.StatisticResponseDTO;
import com.tr.getir.ReadingIsGood.Model.Order;
import com.tr.getir.ReadingIsGood.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {
    @Autowired
    OrderService orderService;


    public List<StatisticResponseDTO> orderStatisticsByUser(User user){
        List<Order> orders = orderService.ordersByUser(user);
        return createStatistic(orders);
    }

    public List<StatisticResponseDTO> orderStatisticsAllSystem(){
        List<Order> orders = orderService.getOrders();
        return createStatistic(orders);
    }

    private List<StatisticResponseDTO> createStatistic(List<Order> orders){
        Map <String,StatisticResponseDTO> statisticsMap = new HashMap<>();
        if (orders != null){
            for (Order order : orders){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(order.getOrderDate());
                String key = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
                key += " - " + calendar.get(Calendar.YEAR);
                StatisticResponseDTO statisticResponseDTO = statisticsMap.get(key);
                if (statisticResponseDTO != null ){
                    statisticResponseDTO.setTotalOrderCount(statisticResponseDTO.getTotalOrderCount()+1);
                    statisticResponseDTO.setTotalAmount(order.getTotalAmount() + statisticResponseDTO.getTotalAmount());
                    statisticResponseDTO.setTotalBookCount(order.getCount() + statisticResponseDTO.getTotalBookCount());
                } else {
                    statisticResponseDTO = new StatisticResponseDTO();
                    statisticResponseDTO.setMonth(key);
                    statisticResponseDTO.setTotalOrderCount(1);
                    statisticResponseDTO.setTotalAmount(order.getTotalAmount());
                    statisticResponseDTO.setTotalBookCount(order.getCount());
                }
                statisticsMap.put(key,statisticResponseDTO);

            }
        }
        return new ArrayList<StatisticResponseDTO>(statisticsMap.values());
    }

}
