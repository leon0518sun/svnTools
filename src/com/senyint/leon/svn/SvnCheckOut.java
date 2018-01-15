/**
 * project: svnUpdate
 * package: com.senyint.leon.test
 * file: SvnTest.java
 * description: TODO
 * Senyint
 * Copyright 2017 All rights Reserved
 * 
 * author: sunxu
 * version: V3.0
 * date: 2017年11月30日 下午4:51:25
 *
 * history:
 * date          author          version          description
 * -----------------------------------------------------------------------------------
 * 2017年11月30日       Administrator          3.0             1.0
 * modification
 */
package com.senyint.leon.svn;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.io.SVNRepository;

import com.senyint.leon.svn.util.SvnUtil;

/**
  * class: SvnTest<BR>
  * description: TODO<BR>
  * author: sunxu<BR>
  * date: 2017年11月30日 下午4:51:25<BR>
  *
  */
public class SvnCheckOut {

    final static String USERNAME = "sunxu";

    final static String PASSWORD = "sxJAVA!@1705";
    
    static SVNRepository repository = null;
    
    public SvnCheckOut(String URL){
        repository = SvnUtil.getRepository(URL, USERNAME, PASSWORD);
    }
    
    public List<File> getSVNChangeFileList(long startRevision,String projectPath){
        List<File> list  = null;
        try {
            list =  getSVNChangeFileList(startRevision,repository.getLatestRevision(),projectPath,"");
        } catch (SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    
    @SuppressWarnings("rawtypes")
    public List<File> getSVNChangeFileList(long startRevision,long endRevision,String projectPath, String projectname){
        List<File> changeFile = new ArrayList<File>();
        try {
            
            
            Collection logEntries = null;
            logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);
            
            for ( Iterator entries = logEntries.iterator( ); entries.hasNext( ); ) {
                SVNLogEntry logEntry = ( SVNLogEntry ) entries.next( );
//                System.out.println( "---------------------------------------------" );
//                System.out.println ("revision: " + logEntry.getRevision( ) );
//                System.out.println( "author: " + logEntry.getAuthor( ) );
//                System.out.println( "date: " + logEntry.getDate( ) );
//                System.out.println( "log message: " + logEntry.getMessage( ) );

                if ( logEntry.getChangedPaths( ).size( ) > 0 ) {
//                    System.out.println( );
//                    System.out.println( "changed paths:" );
                    Set changedPathsSet = logEntry.getChangedPaths( ).keySet( );

                    for ( Iterator changedPaths = changedPathsSet.iterator( ); changedPaths.hasNext( ); ) {
                        SVNLogEntryPath entryPath = ( SVNLogEntryPath ) logEntry.getChangedPaths( ).get( changedPaths.next( ) );
                        String svnFilePath = entryPath.getPath( );
                        if(svnFilePath.contains("src/java")){
                            //截取目录
                            svnFilePath = svnFilePath.substring(svnFilePath.indexOf("src/java/"), svnFilePath.length());
                            svnFilePath = svnFilePath.replaceAll("src/java/", "");
                            //将.java后缀替换为.class
                            svnFilePath = svnFilePath.replaceAll(".java", ".class");
                            svnFilePath = projectname + File.separator + "WEB-INF/classes/" + svnFilePath;
                        }
                        if(svnFilePath.contains("src/resources")){
                            svnFilePath = svnFilePath.substring(svnFilePath.indexOf("src/resources"),svnFilePath.length());
                            svnFilePath = svnFilePath.replaceAll("src/resources/", "");
                            svnFilePath = projectname + File.separator + "WEB-INF/classes/" + svnFilePath;
                        }
                        if(svnFilePath.contains("src/sql")){
                            svnFilePath = svnFilePath.substring(svnFilePath.indexOf("src/sql"),svnFilePath.length());
                            svnFilePath = svnFilePath.replaceAll("src/", "");
                        }
                        if(svnFilePath.contains("resources/js/")){
                            svnFilePath = projectname + File.separator +svnFilePath.substring(svnFilePath.indexOf("resources/js/"),svnFilePath.length());
                        }
                        if(svnFilePath.contains("WEB-INF/pages/")){
                            svnFilePath = projectname + File.separator +svnFilePath.substring(svnFilePath.indexOf("WEB-INF/pages/"),svnFilePath.length());
                        }
                        if(svnFilePath.contains("resources/themes/")){
                            svnFilePath = projectname + File.separator +svnFilePath.substring(svnFilePath.indexOf("resources/themes/"),svnFilePath.length());
                        }
                        if(svnFilePath.contains("sql/tms/")){
                            svnFilePath = "src/"+svnFilePath.substring(svnFilePath.indexOf("sql/tms/"),svnFilePath.length());
                        }
                        if(svnFilePath.contains("src/doc/")){
                            svnFilePath = svnFilePath.substring(svnFilePath.indexOf("src/doc/"),svnFilePath.length());
                        }
                        changeFile.add(new File(projectPath + "/" + svnFilePath));
//                        System.out.println(svnFilePath );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return changeFile;
    }
    
}
