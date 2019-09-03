package com.liuchao.down;

public class DownTest {
    public static void main(String[] args) {
        FileDown fileDown=new FileDown("E:\\spring源码讲解\\0901-某雷场景下断点续传核心实现（四）\\20190901_201512.mp4",
                                       "E:\\spring源码讲解\\0901-某雷场景下断点续传核心实现（四）\\liuchao.mp4",1);

        fileDown.down();
    }
}
