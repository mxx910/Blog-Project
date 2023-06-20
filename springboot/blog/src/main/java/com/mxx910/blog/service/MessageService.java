package com.mxx910.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mxx910.blog.entity.Message;
import com.mxx910.blog.model.DTO.CheckDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.MessageDTO;
import com.mxx910.blog.model.VO.MessageBackVO;
import com.mxx910.blog.model.VO.MessageVO;
import com.mxx910.blog.model.VO.PageResult;

import java.util.List;


public interface MessageService extends IService<Message> {
    /**
     * 查看留言列表
     *
     * @return 留言列表
     */
    List<MessageVO> listMessageVO();

    /**
     * 查看后台留言列表
     *
     * @param condition 条件
     * @return 后台留言列表
     */
    PageResult<MessageBackVO> listMessageBackVO(ConditionDTO condition);

    /**
     * 添加留言
     *
     * @param message 留言
     */
    void addMessage(MessageDTO message);

    /**
     * 审核留言
     *
     * @param check 审核信息
     */
    void updateMessageCheck(CheckDTO check);
}
