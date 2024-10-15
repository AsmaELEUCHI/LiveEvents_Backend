package com.liveEvents.liveEvents_backend.config;

public class ApiConstants {

    //Frontend Url
    public static final String BASE_Front_URL= "http://localhost:4200";
    //Endpoint partenaires
    public static final String PARTNERS_BASE_URL = "/api/partenaires";
    public static final String PARTNER_CREATE_URL = "";
    public static final String PARTNER_GET_ALL_URL = "";
    public static final String PARTNER_GET_URL = "/{id}";
    public static final String PARTNER_UPDATE_URL = "/{id}";
    public static final String PARTNER_DELETE_URL = "/{id}";

    //Endpoint Artistes
    public static final String ARTIST_BASE_URL = "/api/artistes";
    public static final String ARTIST_CREATE_URL = "";
    public static final String ARTIST_GET_ALL_URL = "";
    public static final String ARTIST_GET_URL = "/{id}";
    public static final String ARTIST_UPDATE_URL = "/{id}";
    public static final String ARTIST_DELETE_URL = "/{id}";


    //Endpoint Scenes
    public static final String SCENES_BASE_URL = "/api/scenes";
    public static final String SCENES_CREATE_URL = "";
    public static final String SCENES_GET_ALL_URL = "";
    public static final String SCENES_GET_URL = "/{id}";
    public static final String SCENES_UPDATE_URL = "/{id}";
    public static final String SCENES_DELETE_URL = "/{id}";



    //Endpoint Programs
    public static final String PROGRAMS_BASE_URL = "/api/programmes";
    public static final String PROGRAMS_CREATE_URL = "";
    public static final String PROGRAMS_GET_ALL_URL = "";
    public static final String PROGRAMS_GET_URL = "/{id}";
    public static final String PROGRAMS_UPDATE_URL = "/{id}";
    public static final String PROGRAMS_DELETE_URL = "/{id}";


    //Endpoint Map
    public static final String MAP_BASE_URL = "/api/carte";
    public static final String MAP_CREATE_URL = "";
    public static final String MAP_GET_URL = "";
    public static final String MAP_UPDATE_URL = "";
    public static final String MAP_DELETE_URL = "";

    //Endpoint Markers
    public static final String MARKER_BASE_URL = "/api/marqueurs";
    public static final String MARKER_CREATE_URL = "";
    public static final String MARKER_GET_ALL_URL = "";
    public static final String MARKER_GET_URL = "/{id}";
    public static final String MARKER_UPDATE_URL = "/{id}";
    public static final String MARKER_DELETE_URL = "/{id}";

    //Endpoint User
    public static final String USER_BASE_URL="/api/utilisateurs";
    public static final String REGISTER_URL = "/inscription";
    public static final String LOGOUT_URL="/deconnexion";
    public static final String LOGIN_URL = "/connexion";
    public static final String CONTACT_FORM_URL = "/contact-question";
    public static final String CONTACT_RESPONSE_URL = "/contact-reponse/{id}";
    public static final String PERSONAL_SPACE_URL = "/compte";

    //Endpoint Favorite
    public static final String FAVORITE_BASE_URL="/api/favoris";
    public static final String FAVORITE_CREATE_URL = "/{artistId}";
    public static final String FAVORITE_GET_ALL_URL = "";
    public static final String FAVORITE_DELETE_URL = "/{artistId}";

    //Endpoint Mention légale
    public static final String LEGAL_NOTICE_BASE_URL = "/api/mentions-legales";
    public static final String LEGAL_NOTICE_CREATE_URL = "";
    public static final String LEGAL_NOTICE_GET_ALL_URL = "";
    public static final String LEGAL_NOTICE_GET_URL = "/{id}";
    public static final String LEGAL_NOTICE_UPDATE_URL = "/{id}";
    public static final String LEGAL_NOTICE_DELETE_URL = "/{id}";

}
