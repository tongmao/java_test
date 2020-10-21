package com.tongmao.service;

import com.tongmao.dto.NewMember;
import com.tongmao.mapper.MemberMapper;
import com.tongmao.mapper.SimpleMemberMapper;
import com.tongmao.pojo.Member;
import com.tongmao.pojo.SimpleMember;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemberServiceImpl implements MemberService{

    private SimpleMemberMapper simpleMemberMapper;
    private MemberMapper memberMapper;

    public void setSimpleMemberMapper(SimpleMemberMapper simpleMemberMapper) {
        this.simpleMemberMapper = simpleMemberMapper;
    }

    public void setMemberMapper(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }
    //获取所有简单业务
    @Override
    public List<SimpleMember> getMemebers() {
        return simpleMemberMapper.querySimpleMembers();
    }

    //获取简单业务重载
    @Override
    public List<SimpleMember> getMemebers(String str) {
        if(str.equals("")||str==null) return getMemebers();
        else return simpleMemberMapper.qeurySimpleMembersByName(str);
    }

    //获取详细信息业务
    @Override
    public Member getAMember(int id) {
        if(id>0){
            return memberMapper.queryMember(id);
        }else return null;
    }

    //设置会员信息业务（增/改）
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String setAMember(NewMember newMember) {
        if(newMember.getIsNewMember().equals("true")){
            //添加会员
            boolean b0 = simpleMemberMapper.addSimpleMember(new SimpleMember(newMember.getUsername(),
                    newMember.getLevel()));
            boolean b1 = memberMapper.addMember(new Member(newMember.getUsername(), newMember.getLevel(), newMember.getName(),
                    newMember.getAge(), newMember.getTel(), newMember.getEndtime(), newMember.getEmail(), newMember.getAddress()));
            if(b0&&b1) return "添加成功";
            return "添加失败，请重试";
        }else {
            //修改会员
            boolean b0 =simpleMemberMapper.updateSimpleMember(new SimpleMember(newMember.getUsername(),
                    newMember.getLevel(),newMember.getMemberID()));
            boolean b1 =memberMapper.updateMembr(new Member(newMember.getUsername(),newMember.getLevel(),
                    newMember.getName(),newMember.getAge(),newMember.getTel(),newMember.getEndtime(),
                    newMember.getEmail(),newMember.getAddress(),newMember.getMemberID()));
            if(b0&&b1) return "修改成功";
            return "修改失败，请重试";
        }
    }

    //删除信息业务
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String dropAMember(int id) {
        if(simpleMemberMapper.deleteSimpleMember(id)&&memberMapper.deleteMember(id)) return "删除成功";
        else return "删除失败，请重试";
    }
}
