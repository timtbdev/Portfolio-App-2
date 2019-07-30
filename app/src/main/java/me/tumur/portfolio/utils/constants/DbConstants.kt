package me.tumur.portfolio.utils.constants

object DbConstants {

    /** DATABASE CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    const val DATABASE_NAME = "database.db"
    const val PERSON_ID = "t8AFhsy1JQjxdjjmnYLK"

    /** DATABASE POPULATION CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    const val WELCOME_JSON = "welcome.json"
    const val PROFILE_JSON = "profile.json"
    const val ABOUT_JSON = "about.json"
    const val SOCIAL_JSON = "social.json"
    const val BUTTON_JSON = "button.json"
    const val TASK_JSON = "task.json"
    const val PORTFOLIO_JSON = "portfolio.json"
    const val EXPERIENCE_JSON = "experience.json"
    const val CATEGORY_JSON = "category.json"
    const val SCREENSHOT_JSON = "screenshot.json"
    const val APP_JSON = "app.json"

    /** TABLE CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    const val WELCOME="welcome"
    const val PROFILE = "profile"
    const val SOCIAL = "social"
    const val ABOUT = "about"
    const val APP = "app"
    const val PORTFOLIO = "portfolio"
    const val EXPERIENCE = "experience"
    const val TASKS = "tasks"
    const val BUTTON = "button"
    const val CATEGORY = "category"
    const val SCREENSHOT = "screenshot"

    /** COLUMNS CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    const val ID = "id"
    const val ROW_ID = "rowid"
    const val OWNER_ID = "owner_id"
    const val NAME = "name"
    const val TITLE = "title"
    const val SUB_TITLE = "sub_title"
    const val TEXT = "text"
    const val DATE = "date"
    const val URL = "url"
    const val LOGO = "logo"
    const val LOGO_DESCRIPTION = "logo_description"
    const val IMAGE = "image"
    const val COVER_IMAGE = "cover_image"
    const val IMAGE_DESCRIPTION = "image_description"
    const val ICON = "icon"
    const val ICON_DESCRIPTION = "icon_description"
    const val LOCATION = "location"
    const val INFO = "info"
    const val DATE_FROM = "date_from"
    const val DATE_TO = "date_to"
    const val GREETING = "greeting"
    const val EMAIL = "email"
    const val COMPANY = "company"
    const val HEADER = "header"
    const val TASK = "task"
    const val ORDER = "order"
    const val ORDERS = "orders"
    const val TAB = "tab"
    const val TYPE = "type"
    const val YOUTUBE_ID = "youtube_id"
    const val DESCRIPTION = "description"
    const val VIDEO_URL = "video_url"
    const val QUERY = "query"

    /** QUERIES'S CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Welcome table's queries ----------------------------------------------------------------- */
    const val CHECK_WELCOME = "SELECT COUNT(*) $WELCOME"
    const val GET_WELCOME_BY_ID = "SELECT * FROM $WELCOME WHERE $ID=:$ID"
    const val GET_ALL_WELCOME = "SELECT * FROM $WELCOME ORDER BY $ORDERS"
    const val DELETE_WELCOME = "DELETE FROM $WELCOME"

    /** Profile table's queries ----------------------------------------------------------------- */
    const val GET_PROFILE_BY_ID = "SELECT * FROM $PROFILE WHERE $ID=:$ID"
    const val DELETE_PROFILE = "DELETE FROM $PROFILE"

    /** About table's queries ------------------------------------------------------------------- */
    const val DELETE_ABOUT = "DELETE FROM $ABOUT"
    const val GET_ABOUT_BY_OWNER_ID = "SELECT * FROM $ABOUT WHERE $OWNER_ID=:$ID ORDER BY $ORDERS"

    /** Social table's queries ------------------------------------------------------------------ */
    const val DELETE_SOCIAL = "DELETE FROM $SOCIAL"
    const val GET_SOCIAL_BY_OWNER_ID = "SELECT * FROM $SOCIAL WHERE $OWNER_ID=:$ID ORDER BY $ORDERS"

    /** App table's queries --------------------------------------------------------------------- */
    const val GET_APP = "SELECT * FROM $APP"
    const val DELETE_APP = "DELETE FROM $APP"

    /** Portfolio table's queries --------------------------------------------------------------- */
    const val DELETE_PORTFOLIO = "DELETE FROM $PORTFOLIO"
    const val GET_PORTFOLIO_LIST_BY_OWNER_ID = "SELECT * FROM $PORTFOLIO WHERE $OWNER_ID=:$ID ORDER BY $ORDERS"
    const val GET_PORTFOLIO_ITEM_BY_ID = "SELECT * FROM $PORTFOLIO WHERE $ID=:$ID"
    const val GET_PORTFOLIO_BY_QUERY = "SELECT * FROM $PORTFOLIO WHERE $TITLE MATCH $QUERY"

    /** Buttons table's queries -------------------------------------------------------- */
    const val DELETE_BUTTON = "DELETE FROM $BUTTON"
    const val GET_BUTTON_BY_OWNER_ID = "SELECT * FROM $BUTTON WHERE $OWNER_ID=:$ID ORDER BY $ORDERS"

    /** Experience table's queries -------------------------------------------------------------- */
    const val DELETE_EXPERIENCE = "DELETE FROM $EXPERIENCE"
    const val GET_EXPERIENCE = "SELECT * FROM $EXPERIENCE"

    /** Tasks table's queries -------------------------------------------------------- */
    const val DELETE_TASK= "DELETE FROM $TASKS"

    /** Category table's queries --------------------------------------------------------------- */
    const val DELETE_CATEGORY = "DELETE FROM $CATEGORY"
    const val GET_CATEGORY_BY_TYPE = "SELECT * FROM $CATEGORY WHERE $TYPE=:$TYPE ORDER BY $ORDERS"

    /** Screenshot table's queries --------------------------------------------------------------- */
    const val DELETE_SCREENSHOT = "DELETE FROM $SCREENSHOT"
    const val GET_SCREENSHOT_BY_OWNER_ID = "SELECT * FROM $SCREENSHOT WHERE $OWNER_ID=:$ID ORDER BY $ORDERS"
}