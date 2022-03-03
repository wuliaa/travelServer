package com.example.travelserver.service.Impl;

import com.example.travelserver.mapper.CommentMapper;
import com.example.travelserver.mapper.UserMapper;
import com.example.travelserver.model.Comment;
import com.example.travelserver.model.User;
import com.example.travelserver.service.CommentService;
import com.example.travelserver.vo.CommentVo;
import com.example.travelserver.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<CommentVo> findByGuidelineId(Long guidelineId) {
        List<Comment> allComments = commentMapper.findByGuidelineId(guidelineId);
        if (allComments == null || allComments.size() == 0) {
            return new ArrayList<>();
        }
        List<CommentVo> comments = new ArrayList<>();
        List<CommentVo> parents = new ArrayList<>();
        for (Comment comment : allComments) {
            if (comment.getParentId()==null) {
                CommentVo commentVo = new CommentVo();
                BeanUtils.copyProperties(comment,commentVo);
                User user = userMapper.queryUserByUserId(comment.getUserId());
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(user,userVo);
                commentVo.setUser(userVo);
                comments.add(commentVo);
                parents.add(commentVo);
            } else {
                boolean foundParent=false;
                for (CommentVo parent : parents) {
                    if (comment.getParentId().equals(parent.getId())) {
                        CommentVo commentVo = new CommentVo();
                        BeanUtils.copyProperties(comment,commentVo);
                        if (parent.getChild() == null) {
                            parent.setChild(new ArrayList<>());
                        }
                        User user = userMapper.queryUserByUserId(comment.getUserId());
                        UserVo userVo = new UserVo();
                        BeanUtils.copyProperties(user,userVo);
                        commentVo.setUser(userVo);
                        parent.getChild().add(commentVo);
                        parents.add(commentVo);
                        foundParent=true;
                        //如果对list迭代过程中同时修改list，会报java.util.ConcurrentModificationException
                        // 的异常，所以我们需要break,当然break也可以提高算法效率
                        break;
                    }
                }
                if (!foundParent) {
                    throw new RuntimeException("can not find the parent comment");
                }
            }
        }
        return comments;
    }

    @Override
    public CommentVo insertComment(Comment comment) {
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        User user = userMapper.queryUserByUserId(comment.getUserId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        commentVo.setUser(userVo);
        return commentVo;
    }
}
