package com.lm.service.banner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.entity.bo.banner.BannerBo;
import com.lm.entity.pojo.Banner;
import com.lm.entity.vo.banner.BannerVo;

public interface BannerService extends IService<Banner> {
    IPage<BannerBo> findBannerPage(BannerVo bannerVo);

}
