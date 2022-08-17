package com.lm.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lm.entity.pojo.Banner;
import com.lm.entity.vo.banner.BannerUserVo;
import com.lm.entity.vo.banner.BannerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BannerMapper extends BaseMapper<Banner> {

    /**
     * 查询Banners，可添加条件构造器
     * @param queryWrapper
     * @return
     */
    List<Banner> queryBanners(@Param("ew") Wrapper<Banner> queryWrapper);

    /**
     * 分页查询Banner
     * @param page
     * @return
     */
    IPage<Banner> queryBannersPage(Page page);



    /**
     * 查询banner,可以模糊查询title
     * @param bannerVo
     * @return
     */
    List<Banner> findBanners(@Param("bannerVo") BannerVo bannerVo);

    /**
     * 查询轮播图和对应用户信息
     * @param bannerVo
     * @return
     */
    List<Map<String,Object>> findBannerUsers(@Param("bannerVo") BannerVo bannerVo);
    List<BannerUserVo> findBannerUsersVo(@Param("bannerVo") BannerVo bannerVo);


    /**
     * 分页条件查询
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<BannerUserVo> findBannerUsersVoPage(Page page, @Param(Constants.WRAPPER) Wrapper<BannerVo> queryWrapper);
}
