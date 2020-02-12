package com.arcsoft.hotel.controller;

import com.arcsoft.hotel.pojo.RoomConsume;
import com.arcsoft.hotel.service.roomConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class consumeController {
    @Autowired
    roomConsumeService roomConsumeService;

    @RequestMapping("/pay")
    public void consume(@RequestParam("deviceId") String deviceId, @RequestParam("roomId") int roomId,
                        @RequestParam("shoppingName") String shoppingName, @RequestParam("pay") double pay) {
        RoomConsume roomConsume = new RoomConsume();
        roomConsume.setItem(deviceId + "#" + shoppingName);
        roomConsume.setRoomId(roomId);
        roomConsume.setPrice(pay);
        roomConsume.setTime(new Date());
        roomConsumeService.addConsume(roomConsume);
    }
}
