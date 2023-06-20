package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Talk;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.model.VO.TalkVO;

import java.util.List;

public interface TalkService extends IService<Talk> {
    /**
     * 查看说说列表
     *
     * @return 说说列表
     */
    PageResult<TalkVO> listTalkVO();

    /**
     * 查看首页说说
     *
     * @return 首页说说
     */
    List<String> listTalkHome();
}
