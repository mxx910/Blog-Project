package com.mxx910.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mxx910.blog.entity.Message;
import com.mxx910.blog.entity.SiteConfig;
import com.mxx910.blog.mapper.MessageMapper;
import com.mxx910.blog.model.DTO.CheckDTO;
import com.mxx910.blog.model.DTO.ConditionDTO;
import com.mxx910.blog.model.DTO.MessageDTO;
import com.mxx910.blog.model.VO.MessageBackVO;
import com.mxx910.blog.model.VO.MessageVO;
import com.mxx910.blog.model.VO.PageResult;
import com.mxx910.blog.service.MessageService;
import com.mxx910.blog.service.SiteConfigService;
import com.mxx910.blog.utils.BeanCopyUtils;
import com.mxx910.blog.utils.HTMLUtils;
import com.mxx910.blog.utils.IpUtils;
import com.mxx910.blog.utils.PageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mxx910.blog.constant.CommonConstant.FALSE;
import static com.mxx910.blog.constant.CommonConstant.TRUE;

/**
 * @author: mxx910
 * @date: 2023/6/19
 * @description:
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SiteConfigService siteConfigService;

    @Override
    public List<MessageVO> listMessageVO() {
        // 查询留言列表
        return messageMapper.selectMessageVOList();
    }

    @Override
    public PageResult<MessageBackVO> listMessageBackVO(ConditionDTO condition) {
        // 查询留言数量
        Long count = messageMapper.selectCount(new LambdaQueryWrapper<Message>()
                .like(StringUtils.hasText(condition.getKeyword()), Message::getNickname, condition.getKeyword())
                .eq(Objects.nonNull(condition.getIsCheck()), Message::getIsCheck, condition.getIsCheck()));
        if (count == 0) {
            return new PageResult<>();
        }
        // 查询后台友链列表
        List<MessageBackVO> messageBackVOList = messageMapper.selectMessageBackVOList(PageUtils.getLimit(), PageUtils.getSize(), condition);
        return new PageResult<>(messageBackVOList, count);
    }

    @Override
    public void addMessage(MessageDTO message) {
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        Integer messageCheck = siteConfig.getMessageCheck();
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        Message newMessage = BeanCopyUtils.copyBean(message, Message.class);
        newMessage.setMessageContent(HTMLUtils.filter(message.getMessageContent()));
        newMessage.setIpAddress(ipAddress);
        newMessage.setIsCheck(messageCheck.equals(FALSE) ? TRUE : FALSE);
        newMessage.setIpSource(ipSource);
        messageMapper.insert(newMessage);
    }

    @Override
    public void updateMessageCheck(CheckDTO check) {
        // 修改留言审核状态
        List<Message> messageList = check.getIdList()
                .stream()
                .map(id -> Message.builder()
                        .id(id)
                        .isCheck(check.getIsCheck())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(messageList);
    }
}
