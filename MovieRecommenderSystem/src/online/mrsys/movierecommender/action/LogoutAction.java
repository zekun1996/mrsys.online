package online.mrsys.movierecommender.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import online.mrsys.movierecommender.action.base.BaseAction;
import online.mrsys.movierecommender.action.base.WebConstant;

public class LogoutAction extends BaseAction {

    private static final long serialVersionUID = -684687597322368752L;

    @Override
    public String execute() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        if (actionContext.getSession() != null) {
            actionContext.getSession().put(WebConstant.USER, null);
            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                ServletActionContext.getResponse().addCookie(cookie);
            }
            return SUCCESS;
        } else {
            actionContext.getSession().put(WebConstant.INTERCEPT, "No current account has logged in");
            return ERROR;
        }

    }

}
