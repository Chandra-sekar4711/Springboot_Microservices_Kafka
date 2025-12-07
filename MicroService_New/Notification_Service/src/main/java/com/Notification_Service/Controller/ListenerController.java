package com.Notification_Service.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListenerController {

    @PostMapping("/TestApi")
    public String TestApi()
    {
        return "APi hit successfully";
    }
}
