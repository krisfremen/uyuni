/**
 * Copyright (c) 2020 SUSE LLC
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package com.suse.manager.webui.utils;

import com.redhat.rhn.common.conf.ConfigDefaults;
import com.redhat.rhn.common.security.acl.Access;
import com.redhat.rhn.common.security.acl.Acl;
import com.redhat.rhn.common.security.acl.AclFactory;
import com.redhat.rhn.domain.user.User;
import com.redhat.rhn.frontend.struts.RequestContext;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * Utilities for user preferences.
 */
public class UserPreferenceUtils {

    private UserPreferenceUtils() { }

    /**
     * Get the users current locale. If no user is available return the config default
     *
     * @param pageContext the current PageContext
     * @return the users locale
     */
    public static String getCurrentLocale(PageContext pageContext) {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        User user = new RequestContext(request).getCurrentUser();

        Map<String, Object> aclContext = new HashMap<>();
        aclContext.put("user", user);
        Acl acl = AclFactory.getInstance().getAcl(Access.class.getName());

        if (acl.evalAcl(aclContext, "user_authenticated()")) {
            String locale = user.getPreferredLocale();
            if (locale != null) {
                return locale;
            }
        }
        return ConfigDefaults.get().getDefaultLocale();
    }
}