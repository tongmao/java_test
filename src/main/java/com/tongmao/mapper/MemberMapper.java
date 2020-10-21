package com.tongmao.mapper;

import com.tongmao.pojo.Member;

import java.util.List;

public interface MemberMapper {
    Member queryMember(int id);
    boolean deleteMember(int id);
    boolean updateMembr(Member member);
    boolean addMember(Member member);
}
