package com.liuchao.down;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileDown {
    private String inputPath;
    private String outPutPath;
    private int treadCount;
    private Long fileLength;

    public FileDown(String inputPath,String outPutPath,int treadCount){
            this.inputPath=inputPath;
            this.outPutPath=outPutPath;
            this.treadCount=treadCount;

    }

    public void down(){
        File file=new File(inputPath);
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }
         fileLength = file.length();
        System.out.println("文件的大小为："+fileLength);

        Long threadDownLength=fileLength/treadCount;
        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile(outPutPath,"rw");
            randomAccessFile.setLength(fileLength);
            randomAccessFile.close();

            for (int i=0;i<treadCount;i++){
                Long start =i*threadDownLength;
                RandomAccessFile writeFile=new RandomAccessFile(outPutPath,"rw");
                writeFile.seek(start);
                DownThread downThread=new DownThread(start,file,writeFile,threadDownLength);
                downThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
