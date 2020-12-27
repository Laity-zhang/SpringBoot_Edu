package com.zzn.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Program : edu_parent
 * @ClassName : EduLoginController
 * @Description : TOOD
 * @Author : zhangzhaonian
 * @Date: 2020-12-17 21:10
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决浏览器跨域问题
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar",
                        "https://www.easyicon.net/api/resizeApi.php?id=1108446&size=128");
    }

}
