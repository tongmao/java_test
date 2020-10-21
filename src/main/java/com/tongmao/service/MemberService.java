package com.tongmao.service;

import com.tongmao.dto.NewMember;
import com.tongmao.pojo.Member;
import com.tongmao.pojo.SimpleMember;

import java.util.List;

public interface MemberService {
    List<SimpleMember> getMemebers();
    List<SimpleMember> getMemebers(String str);
    Member getAMember(int id);
    String setAMember(NewMember newMember);
    String dropAMember(int id);
}
