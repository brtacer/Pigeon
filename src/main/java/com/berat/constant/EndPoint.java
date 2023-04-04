package com.berat.constant;

public class EndPoint {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static final String USER_PROFILE = API+VERSION+"/userprofile";
    public static final String POST = API+VERSION+"/post";
    public static final String SHARE = API+VERSION+"/share";
    public static final String COMMENT = API+VERSION+"/comment";
    public static final String LIKE = API+VERSION+"/like";
    public static final String FOLLOW = API+VERSION+"/follow";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    public static final String BYPOSTID = "/{postId}";
    public static final String BYUSERPROFILEID = "/{userProfileId}";
    public static final String BYCOMMENTID = "/{commentId}";

    public static final String GETALL = "/getall";
    public static final String GETONE = "/getone";
    public static final String CREATE = "/create";
    public static final String BLOCK = "/block";
    public static final String DEACTIVATE = "/deactivate";
    public static final String DELETE = "/delete";
    public static final String UPDATE = "/update";
    public static final String PASSWORD = "/password";
    public static final String PROFILE = "/profile";

    public static final String FOLLOWS = "/follows";
    public static final String FOLLOWERS = "/followers";
    public static final String POST_LIKE = "/post_like";
    public static final String POST_SHARE = "/post_share";
    public static final String COMMENT_LIKE = "/comment_like";



}
