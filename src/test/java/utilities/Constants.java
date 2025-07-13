package utilities;

public class Constants {
	// Sheet Names
    public static final String SHEET_CourseDetails = "CourseDetails";
    public static final String SHEET_CourseLanguage = "CourseLanguage";
    public static final String SHEET_ErrorMessage = "ErrorMessage";
    public static final String SHEET_PartnerInfo = "PartnerInfo";
    public static final String SHEET_OnlineDegree = "OnlineDegree";
    public static final String SHEET_BusinessPlan = "BusinessPlan";
    public static final String SHEET_CourseDetailedInfo = "CourseDetailedInfo";
    public static final String SHEET_GovtFileValidation = "GovtFileValidation";
    public static final String SHEET_FooterValidation = "FooterValidation";
    public static final String SHEET_AppStorePlayStore = "AppStore&PlayStore";
    public static final String SHEET_FooterSocialLinkValidation = "FooterSocialLinkValidation";
    
    
    // Handling CourseDetails 1
    public static final int ROW_DATA= 1;   //for initial where data is starting we are using for getting first row for reading and writing
    public static final int COL_SEARCH= 0;
    public static final int COL_LANGUAGE = 1;
    public static final int COL_LEVEL = 2;
    public static final int COL_COURSE_NUMBER =3;
    public static final String COL_TITLE= "Title";
    public static final String COL_RATING= "Rating";
    public static final String COL_DURATION= "Duration";
 
    //For List of Languauge 2
    public static final String COL_LANG_LIST= "Language";
    public static final String COL_NUM_COURSE= "No of Course";

    //For Business 3
    public static final String COL_EMAIL_ERR= "Email Error Message";
    public static final String COL_PHONE_ERR="Phone Error Message";
    
    //For partners flow 4
    public static final String COL_PARTNER_LINK="Partner Link";
    public static final String COL_LOGO_DISP="Logo Displayed";
    public static final String COL_PARTNER_NAME="Partner Name";
    
    //For Card Details 5
    public static final String COL_CARD_DETAILS="Card Details";
    
    // For course detailed info 7
    public static final int COL_COURSE_POS= 0;
    public static final String COL_COURSE_TITLE= "Title";
    public static final String COL_MODULE= "Total Module";
    public static final String COL_COURSE_RATING= "Rating";
    public static final String COL_REVIEW="Total Reviews";
    public static final String COL_SKILLS="Skills";
    public static final String COL_WELEARN="We Learn";
   
    
    //For Footer Info 9
    public static final String COL_SECTIONS= "Section";
    public static final String COL_FOOTER_TITLE="Title";
    public static final String COL_FOOTER_LINK="Link";
    
   
    //FOr Govt 8
    public static final String COL_CONF_MSG="Confirmation Message";
    
    // Row Indexes for Specific Validations 
    public static final int ROW_CREATE_ACCOUNT = 1;
    public static final int ROW_CHILD_WINDOW = 2;
    public static final int ROW_CHILD_TITLE = 3;

   
    // Excel File Path
    public static final String EXCEL_FILE = "CourseraAutomationData.xlsx";
    
    //XML File Path
    public static final String XML_FILE = "CourseraAutomation.xml";
    
    public static final String SHEET_JOB_LISTINGS = "JobListings";
    
}
