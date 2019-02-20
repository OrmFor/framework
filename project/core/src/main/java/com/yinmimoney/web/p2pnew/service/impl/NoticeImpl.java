package com.yinmimoney.web.p2pnew.service.impl;

import com.yinmimoney.web.p2pnew.dao.NoticeMapper;
import com.yinmimoney.web.p2pnew.pojo.Notice;
import com.yinmimoney.web.p2pnew.service.INotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cc.s2m.web.utils.webUtils.service.BaseServiceImpl;

@Service
public class NoticeImpl extends BaseServiceImpl<Notice, NoticeMapper, java.lang.Integer> implements INotice {
    @Autowired
    private NoticeMapper noticeMapper;

    protected NoticeMapper getDao() {
        return noticeMapper;
    }
}