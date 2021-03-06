package com.arcsoft.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arcsoft.hotel.pojo.User;
import com.arcsoft.hotel.pojo.UserReserve;
import com.arcsoft.hotel.service.faceService;
import com.arcsoft.hotel.service.userReserveService;
import com.arcsoft.hotel.util.DaysUtil;
import com.arcsoft.hotel.util.FileUploadUtil;
import com.arcsoft.hotel.util.ImageUtil;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/*预定功能

 */
@RestController
public class reserveController {
    @Autowired
    userReserveService userReserveService;
    @Autowired
    faceService faceService;

    /*
    小程序预约房间
     */
    @RequestMapping("/reserve")
    public JSONObject reserve(@RequestParam("reserveType") String rsvType, @RequestParam("roomType") String roomType,
                              @RequestParam("rsvName") String name, @RequestParam("rsvPhone") String phone,
                              @RequestParam("rsvRoomNum") String numStr, @RequestParam("checkInTime") String CheckIn,
                              @RequestParam("checkOutTime") String CheckOut, @RequestParam("face") MultipartFile facefile) throws IOException {

        JSONObject result = new JSONObject();
        //获取上传的file
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String filePath = fileUploadUtil.fileUpload(facefile, OriginPath);

        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
//        if(!faceService.hasFace(filePath,enginePath)){
//            result.put("success",0);
//            result.put("error","照片识别失败！");
//            return result;
//        }
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        byte[] face = faceRecUtil.getFaceFeature(new File(filePath)).getFeatureData();
        //住房预定
        if (rsvType.equals("0")) {
            int num = -1;
            if (numStr == null || numStr.equals("")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
                return result;
            }
            num = Integer.parseInt(numStr.trim());
            JSONObject isSuccess = userReserveService.reserveRoom(name, phone, roomType, num, CheckIn, CheckOut, face);
            if (isSuccess.get("ok").equals("-1")) {
                result.put("success", 0);
                result.put("error", "参数获取失败！");
            } else if (isSuccess.get("ok").equals("0")) {
                result.put("success", 0);
                result.put("error", "预定失败！");
            } else {
                result.put("success", 1);
            }
            return result;

        }
        //会议室预订
        else if (rsvType.equals("1")) {

            return result;
        }
        return result;
    }

    /*
    前台平板 扫脸获取预约信息
     */
    @RequestMapping("/getreserve1")
    public JSONArray getReserveByFace(@RequestBody String facefile) throws IOException, ParseException {
        JSONArray result = new JSONArray();
        //保存上传的图片
        Resource resource = new ClassPathResource("");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        String OriginPath = resource.getFile().getAbsolutePath();//D:\work\extra\十一届服务外包\AS_Hotel\target\classes(编译目标地址)
        String path = OriginPath + File.separator + "upload" + File.separator + "getreserev.jpg";
        ImageUtil imageUtil = new ImageUtil();
        imageUtil.savePhoto(facefile, path);
        File file = new File(path);//获取文件
        //引擎地址
        String enginePath = OriginPath + File.separator + "lib" + File.separator + "WIN64";
        enginePath = enginePath.replaceAll("\\\\", "/");
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);

        //获取时间
        DaysUtil daysUtil = new DaysUtil();
        Date date = daysUtil.initialTime(new Date());
        //在当前时间可入住的所有预约信息
        List<UserReserve> reserves = userReserveService.getReserve(date);
        for (UserReserve userReserve : reserves) {
            double similar = faceRecUtil.getFaceSimilar(file, userReserve.getFace());
            if (similar > 0.8) {
                JSONObject json = new JSONObject();
                json.put("id", userReserve.getId());
                json.put("checkinDate", userReserve.getCheckinDate());
                json.put("checkoutDate", userReserve.getCheckoutDate());
                json.put("roomType", userReserve.getRoomType());
                json.put("num", userReserve.getNum());
                result.add(json);
            }
        }
        return result;
    }

    /*
    前台  根据手机号查询预约信息
     */
    @RequestMapping("/getreserve2")
    public JSONArray getReserveByPhone(@RequestBody String phone) throws ParseException {
        JSONArray result = new JSONArray();
        //获取时间
        DaysUtil daysUtil = new DaysUtil();
        Date date = daysUtil.initialTime(new Date());
        //在当前时间可入住的所有预约信息
        List<UserReserve> reserves = userReserveService.getReserve(date);
        for (UserReserve userReserve : reserves) {
            if (userReserve.getPhoneNumber().equals(phone)) {
                JSONObject json = new JSONObject();
                json.put("id", userReserve.getId());
                json.put("checkinDate", userReserve.getCheckinDate());
                json.put("checkoutDate", userReserve.getCheckoutDate());
                json.put("roomType", userReserve.getRoomType());
                json.put("num", userReserve.getNum());
                result.add(json);
            }
        }
        return result;
    }
}
