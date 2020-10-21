package com.tongmao.mapper;

import com.tongmao.pojo.SimpleMember;

import java.util.List;

public interface SimpleMemberMapper {
    List<SimpleMember> querySimpleMembers();
    List<SimpleMember> qeurySimpleMembersByName(String str);
    boolean deleteSimpleMember(int id);
    boolean addSimpleMember(SimpleMember simpleMember);
    boolean updateSimpleMember(SimpleMember simpleMember);
}
