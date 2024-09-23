package com.example.demo.repository.search;

import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.example.demo.service.BoardService;
import com.example.demo.service.BoardServiceImpl;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{
    //제목, 내용 작성자에 따라서 검색한다.
    //제목인지 내용인지는 types에 따라서 그에 맞게 검색조건을 작성한다.
    // 그러기 위해서는 QuerydslRepositorySupport를 사용한다.
    //버전업에 따라 그레이들을 수정해준다.

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;        //q도메인객체

        JPQLQuery<Board> query = from(board); //select * from board
        System.out.println(query); //select * from board

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        //and, or등을 사용하는 객체


        //select * from board //제목이 null select * from board where ssss = sss
        //%ddd% select * from board where title = '%'안녕'%
        //String[] types 만들어서 올꺼예요, t c w title content writer
        //tc title = %keyword% or content = %keyword%
        for(String str : types) {
            switch (str) {
                case "t" :
                    booleanBuilder.or(board.title.contains(keyword)); //%q%
                    break;

                case "c" :
                    booleanBuilder.or(board.content.contains(keyword)); //%q%
                    break;

                case "w" :
                    booleanBuilder.or(board.writer.contains(keyword)); //%q%
                    break;
            }
        }

        query.where(booleanBuilder);
        System.out.println("where문 추가" + query);

        query.where(board.bno.gt(0L));
        //select * from board where (title = %keyword% or content = %keyword%) and bno >= 0;

        //페이징 처리
        this.getQuerydsl().applyPagination(pageable, query); //리미트 알려주기

        List<Board> boardList = query.fetch(); //실행

        long count = query.fetchCount(); //row수

        return new PageImpl<>(boardList, pageable, count);
    }
}
