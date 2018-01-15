/**
 * project: svnUpdate
 * package: com.senyint.leon.svn.util
 * file: FileUtil.java
 * description: TODO
 * Senyint
 * Copyright 2017 All rights Reserved
 * 
 * author: sunxu
 * version: V3.0
 * date: 2017年12月1日 上午10:17:19
 *
 * history:
 * date          author          version          description
 * -----------------------------------------------------------------------------------
 * 2017年12月1日       Administrator          3.0             1.0
 * modification
 */
package com.senyint.leon.svn.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
  * class: FileUtil<BR>
  * description: TODO<BR>
  * author: sunxu<BR>
  * date: 2017年12月1日 上午10:17:19<BR>
  *
  */
public class FileUtil {

    /**
     *<p>
      * method:  copyFiles<BR>
      * description:  TODO<BR>
      * author:  sunxu<BR>
      * date:  2017年12月1日 上午10:17:26<BR>
     * @param targetFile 
     * @param sourceFile 
      *   
      */
    public static void copyFiles(File sourceFile, File targetFile) {
        try {
            File fileParent = targetFile.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            
            
            
            BufferedInputStream inBuff = null;
            BufferedOutputStream outBuff = null;
            try {
                // 新建文件输入流并对它进行缓冲  
                inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

                // 新建文件输出流并对它进行缓冲  
                outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

                // 缓冲数组  
                byte[] b = new byte[1024 * 5];
                int len;
                while ((len = inBuff.read(b)) != -1) {
                    outBuff.write(b, 0, len);
                }
                // 刷新此缓冲的输出流  
                outBuff.flush();
            } finally {
                // 关闭流  
                if (inBuff != null)
                    inBuff.close();
                if (outBuff != null)
                    outBuff.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
