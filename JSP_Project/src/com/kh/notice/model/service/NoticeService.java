package com.kh.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static com.kh.common.JDBCTemplate.*;//JDBCTemplate 클래스의 모든 메소드들을 그냥 가져다 쓸수 있음

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	
	public ArrayList<Notice> selectNoticeList(){
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		close(conn);
		
		return list;
	}
	
}
