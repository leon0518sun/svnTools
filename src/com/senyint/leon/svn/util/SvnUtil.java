/**
 * project: svnUpdate
 * package: com.senyint.leon
 * file: SvnTools.java
 * description: TODO
 * Senyint
 * Copyright 2017 All rights Reserved
 * 
 * author: sunxu
 * version: V3.0
 * date: 2017年11月30日 下午4:50:56
 *
 * history:
 * date          author          version          description
 * -----------------------------------------------------------------------------------
 * 2017年11月30日       Administrator          3.0             1.0
 * modification
 */
package com.senyint.leon.svn.util;

import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
  * class: SvnTools<BR>
  * description: TODO<BR>
  * author: sunxu<BR>
  * date: 2017年11月30日 下午4:50:56<BR>
  *
  */
public class SvnUtil {
    
    public static SVNRepository getRepository(String url, String username, String password) {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        SVNRepository repository = null;
        SVNNodeKind nodeKind = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            @SuppressWarnings("deprecation")
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);
            nodeKind = repository.checkPath("", -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (nodeKind == SVNNodeKind.NONE) {
            throw new RuntimeException("There is no entry at '" + url + "'.");
        } else if (nodeKind == SVNNodeKind.FILE) {
            throw new RuntimeException("The entry at '" + url + "' is a file while a directory was expected.");
        }
        return repository;
    }
}
