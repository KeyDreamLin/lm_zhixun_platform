package com.lm.controller.banner;

import com.lm.controller.BaseController;
import com.lm.entity.bo.banner.BannerBo;
import com.lm.service.banner.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BannerController extends BaseController {
    @Autowired
    private BannerService bannerService;
    /**
     * 查询轮播图管理列表信息并分页
     * @path : /admin/banner/load
     * @version 1.0.0
     */
    @PostMapping("/banner/load")
//    @PugDoc(name="查询轮播图管理列表信息并分页")
    public List<BannerBo> findBanners() {
        List<BannerBo> bannerBoList = new ArrayList<>();
//        @RequestBody BannerVo bannerVo
        bannerBoList.add(new BannerBo());
        bannerBoList.add(new BannerBo());
        bannerBoList.add(new BannerBo());
        return bannerBoList;
//        return bannerService.findBannerPage(bannerVo);
    }

}
