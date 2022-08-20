package com.lm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lm.entity.pojo.AdminMenu;
import com.lm.entity.vo.banner.BannerUserVo;
import com.lm.entity.vo.banner.BannerVo;
import com.lm.mapper.BannerMapper;
import com.lm.service.adminmenu.AdminMenuService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = LoginDemoApplication.class)
@RunWith(SpringRunner.class)
class LoginDemoApplicationTests {

    @Autowired
    private AdminMenuService adminMenuService;

    @Test
    public void contextLoads() {
        List<AdminMenu> adminMenuTree = adminMenuService.findAdminMenuTree();
        System.out.println(adminMenuTree);
//        BannerVo bannerVo = new BannerVo();
//        bannerVo.setKeyword("米");
////        List<Banner> banners = bannerMapper.findBanners(bannerVo);
//        List<BannerUserVo> bannerUsersVo = bannerMapper.findBannerUsersVo(bannerVo);
//        for (BannerUserVo bannerUserVo : bannerUsersVo) {
//            System.out.println(bannerUserVo.toString());
//        }
//
//        // 设置分页
//        Page<BannerUserVo> page = new Page<>(1,2);
//        // 设置条件
//        QueryWrapper<BannerVo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotEmpty(bannerVo.getKeyword()),"t1.title",bannerVo.getKeyword());
//        IPage<BannerUserVo> bannerUsersVoPage = bannerMapper.findBannerUsersVoPage(page, queryWrapper);
//        System.out.println(bannerUsersVoPage.toString());


//        LambdaQueryWrapper<Banner> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(Banner::getTitle,"1111");
//        List<Banner> banners = bannerMapper.queryBanners(lambdaQueryWrapper);
//        System.out.println(banners.toString());

//        // 设置分页构造器
//        Page<BannerUserVo> page = new Page<>(1,2);
//        IPage<Banner> bannerIPage = bannerMapper.queryBannersPage(page);
//        int i = 12;
    }
    @Test
    public void testC(){
        String str = "123";
        System.out.println(str.hashCode());
        str = "23";
        System.out.println(str.hashCode());
        int a = 1;
    }

}
