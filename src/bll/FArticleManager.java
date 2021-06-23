package bll;

import bll.impl.ArticleManager;
import bll.impl.UserManager;

public class FArticleManager {

    public static IArticleManager getArticleManager(){

        return (IArticleManager) new ArticleManager();
    }
}
