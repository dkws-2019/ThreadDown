package com.liuchao.down;

import java.io.*;

public class DownThread extends Thread{
    private File inputFile;

    private Long start;

    private RandomAccessFile writeFile;

    private Long threadDownLength;

    public DownThread(Long start,File inputFile,RandomAccessFile writeFile,Long threadDownLength){
        this.inputFile=inputFile;
        this.start=start;
        this.writeFile=writeFile;
        this.threadDownLength=threadDownLength;
    }

    @Override
    public void run() {
        threadDown();
    }

    private void threadDown() {
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=new FileInputStream(inputFile);
            fileInputStream.skip(start);
            int count=0;
            byte[] bytes=new byte[1024];
            Long downSize=0L;
            while(downSize<threadDownLength &&(count=fileInputStream.read(bytes))!=-1){
                writeFile.write(bytes);

                downSize+=count;
            }
            System.out.println(Thread.currentThread().getName()+"下载的文件大小："+downSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                writeFile.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
