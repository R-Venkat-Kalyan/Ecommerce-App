package com.ecom.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BuildInfoController {

//    @Value("${build.id:DEfault}")
//    private String buildID;
//
//    @Value("${build.version:Default}")
//    private String buildVersion;
//
//    @Value("${build.name:default}")
//    private String buildName;

    private BuildInfo buildInfo;

    @GetMapping("/build-info")
    public String getBuildInfo(){
        return "Build Id:"+buildInfo.getId()+", Build Version:"+buildInfo.getVersion()+", Build Name:"+buildInfo.getName();
    }
}
