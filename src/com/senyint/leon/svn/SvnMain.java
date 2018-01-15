/**
 * project: svnUpdate
 * package: com.senyint.leon.svn
 * file: SvnMain.java
 * description: TODO
 * Senyint
 * Copyright 2017 All rights Reserved
 * 
 * author: sunxu
 * version: V3.0
 * date: 2017年12月1日 上午9:44:36
 *
 * history:
 * date          author          version          description
 * -----------------------------------------------------------------------------------
 * 2017年12月1日       Administrator          3.0             1.0
 * modification
 */
package com.senyint.leon.svn;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.senyint.leon.svn.util.FileUtil;
import com.senyint.leon.svn.util.zipUtil;

/**
  * class: SvnMain<BR>
  * description: TODO<BR>
  * author: sunxu<BR>
  * date: 2017年12月1日 上午9:44:36<BR>
  *
  */
public class SvnMain {
    
    /**svn地址*/
    /**项目classes路径地址*/
    /**项目名称**/
    /**SVN起始版本号*/
    /**SVN结束版本号*/
    /**输出保存文件路径*/
    
    //TMS3.0
    final static String SVNURL="https://36.102.232.6:8443/svn/29.YCHZ_YLPT/01.%E5%BC%80%E5%8F%91%E5%BA%93/02.%E4%BB%A3%E7%A0%81/01.SourceCode/TMS";
    final static String PROJECTPATH = "E:/Eclipse_Mars/Eclipse_Mars/workspace/TMS3";
    final static String PROJECTNAME = "/TMS";
    final static long STARTREVISION = 10358;
    final static long ENDREVISION = 10540;
    
    //TMSServer
//    final static String SVNURL="https://36.102.232.6:8443/svn/29.YCHZ_YLPT/01.%E5%BC%80%E5%8F%91%E5%BA%93/02.%E4%BB%A3%E7%A0%81/01.SourceCode/TMSServer/TMSServer";
//    final static String PROJECTPATH = "E:/Eclipse_Mars/Eclipse_Mars/workspace/TMSServer";
//    final static String PROJECTNAME = "/TMSServer";
//    final static long STARTREVISION = 10342;
//    final static long ENDREVISION = 10515;
    
    
    final static String OUTFILEPATH = "E:\\svnUpdate";
    
    public static void main(String[] args) {
        SvnCheckOut svnCheckOut = new SvnCheckOut(SVNURL);
        List<File> checkFile = svnCheckOut.getSVNChangeFileList(STARTREVISION,ENDREVISION, PROJECTPATH,PROJECTNAME);
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String data = simpleDateFormat.format(new Date());
        
        String targetPath = OUTFILEPATH + File.separator +PROJECTPATH.substring(PROJECTPATH.lastIndexOf("/"), PROJECTPATH.length()) + "_" + data + "-" + STARTREVISION + "~" + ENDREVISION;
        //输出文件目录
        for (File file : checkFile) {
            String sourcesFile = file.getPath();
            String targetFile= targetPath + sourcesFile.substring(PROJECTPATH.length(), sourcesFile.length());
            System.out.println(targetFile);
            FileUtil.copyFiles(new File(sourcesFile),new File(targetFile));
            System.out.println(targetFile + "复制完成!");
        }
        //打包
        try {
//            String zipFileName = PROJECTNAME + "_" + data + "_" + STARTREVISION + "-" + ENDREVISION + ".zip";
//            zipUtil.zip(targetPath, OUTFILEPATH, zipFileName);
//            System.out.println(OUTFILEPATH + File.separator + zipFileName + "  打包完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
