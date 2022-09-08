package com.lm.service.banner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.entity.bo.banner.BannerBo;
import com.lm.entity.pojo.banner.Banner;
import com.lm.entity.vo.banner.BannerVo;
import com.lm.mapper.BannerMapper;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public IPage<BannerBo> findBannerPage(BannerVo bannerVo) {

        return null;
    }
}
