/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ralph
 */
public class Client {

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public static int login(String username, String password) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_UNAME='" + username + "' AND CLIENT_PW='" + PasswordHasher.passwordHasher(password) + "'";
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return rs.getInt("CLIENT_ID");
        } else {
            return 0;
        }
    }

    public static int loginWithEmail(String email, String password) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_EMAIL='" + email + "' AND CLIENT_PW='" + PasswordHasher.passwordHasher(password) + "'";
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return rs.getInt("CLIENT_ID");
        } else {
            return 0;
        }
    }

    public static boolean setUsername(String username, int clientId) {
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "UPDATE CA_ABS.CLIENT SET CLIENT_UNAME = '" + username + "' WHERE CLIENT_ID=" + clientId;
            int result = st.executeUpdate(DBQ);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean setPassword(String password, int clientId) {
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "UPDATE CA_ABS.CLIENT SET CLIENT_PW = '" + password + "' WHERE CLIENT_ID=" + clientId;
            int result = st.executeUpdate(DBQ);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean changePassword(String password, int clientId) {
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "UPDATE CA_ABS.CLIENT SET CLIENT_PW = '" + password + "', NEW_ACCOUNT=" + 0 + " WHERE CLIENT_ID=" + clientId;
            int result = st.executeUpdate(DBQ);
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean resetCodeToNull(int clientId) throws SQLException {

        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "UPDATE CA_ABS.CLIENT SET RESET_PW_CODE = '' WHERE CLIENT_ID=" + clientId;
        int result = st.executeUpdate(DBQ);
        if (result > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkIfAccountExists(int accountNumber) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ACCTNUM=" + accountNumber;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean checkIfPinIsCorrect(int pin) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_PIN=" + pin;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIfNew(int clientId) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            if (rs.getInt("NEW_ACCOUNT") == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkIfRegistered(int accountNumber) {
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ACCTNUM=" + accountNumber;
            ResultSet rs = st.executeQuery(DBQ);
            if (rs.next()) {
                if (rs.getString("CLIENT_UNAME").trim().isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean checkIfEmailExists(String email) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPasswordResetCode(String resetCode, String email) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE RESET_PW_CODE='" + resetCode + "' AND CLIENT_EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }

    public static int getId(int accountNumber) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ACCTNUM=" + accountNumber;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return rs.getInt("CLIENT_ID");
        } else {
            return 0;
        }
    }

    public static int getIdwithResetCode(String resetCode) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE RESET_PW_CODE='" + resetCode + "'";
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return rs.getInt("CLIENT_ID");
        } else {
            return 0;
        }
    }

    public static String getEmail(int accountId) throws SQLException {
        String email = "";
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + accountId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            email = rs.getString("CLIENT_EMAIL");
        } else {
            return null;
        }
        return email;
    }

    public static String getFormattedBalance(int clientId){
        String formattedBalance;
        double balance = 0;
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
            ResultSet rs = st.executeQuery(DBQ);
            if (rs.next()) {
                balance = rs.getDouble("CURRENT_BALANCE");
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                formattedBalance = format.format(balance);
                return formattedBalance;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
         return null;
    }
     public static String getFormattedExpectedBalance(int clientId){
        String formattedBalance;
        double balance = 0;
        try {
            Statement st = DatabaseConnection.connect().createStatement();
            String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
            ResultSet rs = st.executeQuery(DBQ);
            if (rs.next()) {
                balance = rs.getDouble("EXPECTED_BALANCE");
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                formattedBalance = format.format(balance);
                return formattedBalance;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
         return null;
    }
    public static String getPassword(int accountId) throws SQLException {
        String password = "";
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + accountId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            password = rs.getString("CLIENT_PW");
        } else {
            return null;
        }
        return password;
    }

    public static String getFirstName(int clientId) throws SQLException {
        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {
            return rs.getString("CLIENT_FN");
        } else {
            return null;
        }
    }

    public static String createUserName(int clientId) throws SQLException {
        String username = "", firstName = "", lastName = "", middleName = "";

        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {

            firstName = rs.getString("CLIENT_FN");
            lastName = rs.getString("CLIENT_LN");
            middleName = rs.getString("CLIENT_MN");

            username = firstName.substring(0, 1).toLowerCase() + middleName.substring(0, 1).toLowerCase() + lastName.trim().toLowerCase();
        }

        return username;
    }

    public static String createFullName(int clientId) throws SQLException {
        String fullName = "", firstName = "", lastName = "", middleName = "";

        Statement st = DatabaseConnection.connect().createStatement();
        String DBQ = "SELECT * FROM CA_ABS.CLIENT WHERE CLIENT_ID=" + clientId;
        ResultSet rs = st.executeQuery(DBQ);
        if (rs.next()) {

            firstName = rs.getString("CLIENT_FN");
            lastName = rs.getString("CLIENT_LN");
            middleName = rs.getString("CLIENT_MN");

            fullName = firstName + " " + middleName.substring(0, 1) + ". " + lastName;
        }

        return fullName;
    }

    public static boolean sendCredentials(String receipient, String username, String password) {
        String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n"
                + "    <!--[if gte mso 9]><xml>\n"
                + "     <o:OfficeDocumentSettings>\n"
                + "      <o:AllowPNG/>\n"
                + "      <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "     </o:OfficeDocumentSettings>\n"
                + "    </xml><![endif]-->\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width\">\n"
                + "    <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
                + "    <title></title>\n"
                + "    <!--[if !mso]><!-- -->\n"
                + "	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "	<!--<![endif]-->\n"
                + "\n"
                + "    <style type=\"text/css\" id=\"media-query\">\n"
                + "      body {\n"
                + "  margin: 0;\n"
                + "  padding: 0; }\n"
                + "\n"
                + "table, tr, td {\n"
                + "  vertical-align: top;\n"
                + "  border-collapse: collapse; }\n"
                + "\n"
                + ".ie-browser table, .mso-container table {\n"
                + "  table-layout: fixed; }\n"
                + "\n"
                + "* {\n"
                + "  line-height: inherit; }\n"
                + "\n"
                + "a[x-apple-data-detectors=true] {\n"
                + "  color: inherit !important;\n"
                + "  text-decoration: none !important; }\n"
                + "\n"
                + "[owa] .img-container div, [owa] .img-container button {\n"
                + "  display: block !important; }\n"
                + "\n"
                + "[owa] .fullwidth button {\n"
                + "  width: 100% !important; }\n"
                + "\n"
                + "[owa] .block-grid .col {\n"
                + "  display: table-cell;\n"
                + "  float: none !important;\n"
                + "  vertical-align: top; }\n"
                + "\n"
                + ".ie-browser .num12, .ie-browser .block-grid, [owa] .num12, [owa] .block-grid {\n"
                + "  width: 480px !important; }\n"
                + "\n"
                + ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {\n"
                + "  line-height: 100%; }\n"
                + "\n"
                + ".ie-browser .mixed-two-up .num4, [owa] .mixed-two-up .num4 {\n"
                + "  width: 160px !important; }\n"
                + "\n"
                + ".ie-browser .mixed-two-up .num8, [owa] .mixed-two-up .num8 {\n"
                + "  width: 320px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.two-up .col, [owa] .block-grid.two-up .col {\n"
                + "  width: 240px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.three-up .col, [owa] .block-grid.three-up .col {\n"
                + "  width: 160px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.four-up .col, [owa] .block-grid.four-up .col {\n"
                + "  width: 120px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.five-up .col, [owa] .block-grid.five-up .col {\n"
                + "  width: 96px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.six-up .col, [owa] .block-grid.six-up .col {\n"
                + "  width: 80px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.seven-up .col, [owa] .block-grid.seven-up .col {\n"
                + "  width: 68px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.eight-up .col, [owa] .block-grid.eight-up .col {\n"
                + "  width: 60px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.nine-up .col, [owa] .block-grid.nine-up .col {\n"
                + "  width: 53px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.ten-up .col, [owa] .block-grid.ten-up .col {\n"
                + "  width: 48px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.eleven-up .col, [owa] .block-grid.eleven-up .col {\n"
                + "  width: 43px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.twelve-up .col, [owa] .block-grid.twelve-up .col {\n"
                + "  width: 40px !important; }\n"
                + "\n"
                + "@media only screen and (min-width: 500px) {\n"
                + "  .block-grid {\n"
                + "    width: 480px !important; }\n"
                + "  .block-grid .col {\n"
                + "    vertical-align: top; }\n"
                + "    .block-grid .col.num12 {\n"
                + "      width: 480px !important; }\n"
                + "  .block-grid.mixed-two-up .col.num4 {\n"
                + "    width: 160px !important; }\n"
                + "  .block-grid.mixed-two-up .col.num8 {\n"
                + "    width: 320px !important; }\n"
                + "  .block-grid.two-up .col {\n"
                + "    width: 240px !important; }\n"
                + "  .block-grid.three-up .col {\n"
                + "    width: 160px !important; }\n"
                + "  .block-grid.four-up .col {\n"
                + "    width: 120px !important; }\n"
                + "  .block-grid.five-up .col {\n"
                + "    width: 96px !important; }\n"
                + "  .block-grid.six-up .col {\n"
                + "    width: 80px !important; }\n"
                + "  .block-grid.seven-up .col {\n"
                + "    width: 68px !important; }\n"
                + "  .block-grid.eight-up .col {\n"
                + "    width: 60px !important; }\n"
                + "  .block-grid.nine-up .col {\n"
                + "    width: 53px !important; }\n"
                + "  .block-grid.ten-up .col {\n"
                + "    width: 48px !important; }\n"
                + "  .block-grid.eleven-up .col {\n"
                + "    width: 43px !important; }\n"
                + "  .block-grid.twelve-up .col {\n"
                + "    width: 40px !important; } }\n"
                + "\n"
                + "@media (max-width: 500px) {\n"
                + "  .block-grid, .col {\n"
                + "    min-width: 320px !important;\n"
                + "    max-width: 100% !important;\n"
                + "    display: block !important; }\n"
                + "  .block-grid {\n"
                + "    width: calc(100% - 40px) !important; }\n"
                + "  .col {\n"
                + "    width: 100% !important; }\n"
                + "    .col > div {\n"
                + "      margin: 0 auto; }\n"
                + "  img.fullwidth, img.fullwidthOnMobile {\n"
                + "    max-width: 100% !important; }\n"
                + "  .no-stack .col {\n"
                + "    min-width: 0 !important;\n"
                + "    display: table-cell !important; }\n"
                + "  .no-stack.two-up .col {\n"
                + "    width: 50% !important; }\n"
                + "  .no-stack.mixed-two-up .col.num4 {\n"
                + "    width: 33% !important; }\n"
                + "  .no-stack.mixed-two-up .col.num8 {\n"
                + "    width: 66% !important; }\n"
                + "  .no-stack.three-up .col.num4 {\n"
                + "    width: 33% !important; }\n"
                + "  .no-stack.four-up .col.num3 {\n"
                + "    width: 25% !important; }\n"
                + "  .mobile_hide {\n"
                + "    min-height: 0px;\n"
                + "    max-height: 0px;\n"
                + "    max-width: 0px;\n"
                + "    display: none;\n"
                + "    overflow: hidden;\n"
                + "    font-size: 0px; } }\n"
                + "\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body class=\"clean-body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #FFFFFF\">\n"
                + "  <style type=\"text/css\" id=\"media-query-bodytag\">\n"
                + "    @media (max-width: 520px) {\n"
                + "      .block-grid {\n"
                + "        min-width: 320px!important;\n"
                + "        max-width: 100%!important;\n"
                + "        width: 100%!important;\n"
                + "        display: block!important;\n"
                + "      }\n"
                + "\n"
                + "      .col {\n"
                + "        min-width: 320px!important;\n"
                + "        max-width: 100%!important;\n"
                + "        width: 100%!important;\n"
                + "        display: block!important;\n"
                + "      }\n"
                + "\n"
                + "        .col > div {\n"
                + "          margin: 0 auto;\n"
                + "        }\n"
                + "\n"
                + "      img.fullwidth {\n"
                + "        max-width: 100%!important;\n"
                + "      }\n"
                + "			img.fullwidthOnMobile {\n"
                + "        max-width: 100%!important;\n"
                + "      }\n"
                + "      .no-stack .col {\n"
                + "				min-width: 0!important;\n"
                + "				display: table-cell!important;\n"
                + "			}\n"
                + "			.no-stack.two-up .col {\n"
                + "				width: 50%!important;\n"
                + "			}\n"
                + "			.no-stack.mixed-two-up .col.num4 {\n"
                + "				width: 33%!important;\n"
                + "			}\n"
                + "			.no-stack.mixed-two-up .col.num8 {\n"
                + "				width: 66%!important;\n"
                + "			}\n"
                + "			.no-stack.three-up .col.num4 {\n"
                + "				width: 33%!important;\n"
                + "			}\n"
                + "			.no-stack.four-up .col.num3 {\n"
                + "				width: 25%!important;\n"
                + "			}\n"
                + "      .mobile_hide {\n"
                + "        min-height: 0px!important;\n"
                + "        max-height: 0px!important;\n"
                + "        max-width: 0px!important;\n"
                + "        display: none!important;\n"
                + "        overflow: hidden!important;\n"
                + "        font-size: 0px!important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "  <!--[if IE]><div class=\"ie-browser\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table class=\"nl-container\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #FFFFFF;width: 100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "	<tbody>\n"
                + "	<tr style=\"vertical-align: top\">\n"
                + "		<td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #FFFFFF;\"><![endif]-->\n"
                + "\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div align=\"center\" class=\"img-container center fixedwidth \" style=\"padding-right: 0px;  padding-left: 0px;\">\n"
                + "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px;line-height:0px;\"><td style=\"padding-right: 0px; padding-left: 0px;\" align=\"center\"><![endif]-->\n"
                + "  <img class=\"center fixedwidth\" align=\"center\" border=\"0\" src=\"https://c2.staticflickr.com/2/1965/44402302585_2c010a20c3_m.jpg\" alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: 0;height: auto;float: none;width: 100%;max-width: 168px\" width=\"168\">\n"
                + "<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:#DEDFE3;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#DEDFE3;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#000000;line-height:200%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"line-height:24px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;font-size:12px;color:#000000;text-align:left;\"><p style=\"margin: 0;line-height: 24px;text-align: center;font-size: 12px\"><span style=\"font-size: 38px; line-height: 76px; color: rgb(0, 0, 0);\"><strong>University Bank</strong></span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "\n"
                + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"divider \" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 100%;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "    <tbody>\n"
                + "        <tr style=\"vertical-align: top\">\n"
                + "            <td class=\"divider_inner\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-right: 10px;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;min-width: 100%;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                <table class=\"divider_content\" height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"10%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #333333;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                    <tbody>\n"
                + "                        <tr style=\"vertical-align: top\">\n"
                + "                            <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                                <span>&#160;</span>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </tbody>\n"
                + "                </table>\n"
                + "            </td>\n"
                + "        </tr>\n"
                + "    </tbody>\n"
                + "</table>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\">You are receiving this email because you registered through University Bank's mobile application. The information below are your account credentials that you will need to sign in the application.</p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:200%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"line-height:24px;font-size:12px;color:#555555;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 28px;text-align: center\"><strong><span style=\"font-size: 20px; line-height: 40px;\">Username</span></strong></p><p style=\"margin: 0;font-size: 14px;line-height: 28px;text-align: center\"><span style=\"font-size: 24px; line-height: 48px; color: rgb(51, 102, 255);\"><span style=\"line-height: 48px; font-size: 24px;\">" + username + "</span></span></p><p style=\"margin: 0;font-size: 14px;line-height: 28px;text-align: center\"><strong><span style=\"font-size: 20px; line-height: 40px;\"><span style=\"line-height: 40px; font-size: 20px;\">Password</span></span></strong></p><p style=\"margin: 0;line-height: 24px;text-align: center;font-size: 12px\"><span style=\"font-size: 24px; line-height: 48px; color: rgb(51, 102, 255);\">" + password + "</span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;color:#555555;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px;text-align: center\"><em><span style=\"font-size: 14px; line-height: 16px; color: rgb(255, 102, 0);\">*Please change your password immediately</span></em></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;color:#555555;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px;text-align: center\"><span style=\"font-size: 18px; line-height: 21px;\"><strong>Thank you for using University Bank!</strong></span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "   <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "		</td>\n"
                + "  </tr>\n"
                + "  </tbody>\n"
                + "  </table>\n"
                + "  <!--[if (mso)|(IE)]></div><![endif]-->\n"
                + "\n"
                + "\n"
                + "</body></html>";
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("csspec6.n491@gmail.com", "haji12345");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = receipient;
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("University Bank Account Registration");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html; charset=utf-8");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
            return true;
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
            return false;
        }
    }

    public static boolean sendResetPassword(String receipient, String resetCode) {
        String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n"
                + "    <!--[if gte mso 9]><xml>\n"
                + "     <o:OfficeDocumentSettings>\n"
                + "      <o:AllowPNG/>\n"
                + "      <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "     </o:OfficeDocumentSettings>\n"
                + "    </xml><![endif]-->\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width\">\n"
                + "    <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
                + "    <title></title>\n"
                + "    <!--[if !mso]><!-- -->\n"
                + "	<link href=\"https://fonts.googleapis.com/css?family=Lato\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "	<!--<![endif]-->\n"
                + "\n"
                + "    <style type=\"text/css\" id=\"media-query\">\n"
                + "      body {\n"
                + "  margin: 0;\n"
                + "  padding: 0; }\n"
                + "\n"
                + "table, tr, td {\n"
                + "  vertical-align: top;\n"
                + "  border-collapse: collapse; }\n"
                + "\n"
                + ".ie-browser table, .mso-container table {\n"
                + "  table-layout: fixed; }\n"
                + "\n"
                + "* {\n"
                + "  line-height: inherit; }\n"
                + "\n"
                + "a[x-apple-data-detectors=true] {\n"
                + "  color: inherit !important;\n"
                + "  text-decoration: none !important; }\n"
                + "\n"
                + "[owa] .img-container div, [owa] .img-container button {\n"
                + "  display: block !important; }\n"
                + "\n"
                + "[owa] .fullwidth button {\n"
                + "  width: 100% !important; }\n"
                + "\n"
                + "[owa] .block-grid .col {\n"
                + "  display: table-cell;\n"
                + "  float: none !important;\n"
                + "  vertical-align: top; }\n"
                + "\n"
                + ".ie-browser .num12, .ie-browser .block-grid, [owa] .num12, [owa] .block-grid {\n"
                + "  width: 480px !important; }\n"
                + "\n"
                + ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {\n"
                + "  line-height: 100%; }\n"
                + "\n"
                + ".ie-browser .mixed-two-up .num4, [owa] .mixed-two-up .num4 {\n"
                + "  width: 160px !important; }\n"
                + "\n"
                + ".ie-browser .mixed-two-up .num8, [owa] .mixed-two-up .num8 {\n"
                + "  width: 320px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.two-up .col, [owa] .block-grid.two-up .col {\n"
                + "  width: 240px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.three-up .col, [owa] .block-grid.three-up .col {\n"
                + "  width: 160px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.four-up .col, [owa] .block-grid.four-up .col {\n"
                + "  width: 120px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.five-up .col, [owa] .block-grid.five-up .col {\n"
                + "  width: 96px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.six-up .col, [owa] .block-grid.six-up .col {\n"
                + "  width: 80px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.seven-up .col, [owa] .block-grid.seven-up .col {\n"
                + "  width: 68px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.eight-up .col, [owa] .block-grid.eight-up .col {\n"
                + "  width: 60px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.nine-up .col, [owa] .block-grid.nine-up .col {\n"
                + "  width: 53px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.ten-up .col, [owa] .block-grid.ten-up .col {\n"
                + "  width: 48px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.eleven-up .col, [owa] .block-grid.eleven-up .col {\n"
                + "  width: 43px !important; }\n"
                + "\n"
                + ".ie-browser .block-grid.twelve-up .col, [owa] .block-grid.twelve-up .col {\n"
                + "  width: 40px !important; }\n"
                + "\n"
                + "@media only screen and (min-width: 500px) {\n"
                + "  .block-grid {\n"
                + "    width: 480px !important; }\n"
                + "  .block-grid .col {\n"
                + "    vertical-align: top; }\n"
                + "    .block-grid .col.num12 {\n"
                + "      width: 480px !important; }\n"
                + "  .block-grid.mixed-two-up .col.num4 {\n"
                + "    width: 160px !important; }\n"
                + "  .block-grid.mixed-two-up .col.num8 {\n"
                + "    width: 320px !important; }\n"
                + "  .block-grid.two-up .col {\n"
                + "    width: 240px !important; }\n"
                + "  .block-grid.three-up .col {\n"
                + "    width: 160px !important; }\n"
                + "  .block-grid.four-up .col {\n"
                + "    width: 120px !important; }\n"
                + "  .block-grid.five-up .col {\n"
                + "    width: 96px !important; }\n"
                + "  .block-grid.six-up .col {\n"
                + "    width: 80px !important; }\n"
                + "  .block-grid.seven-up .col {\n"
                + "    width: 68px !important; }\n"
                + "  .block-grid.eight-up .col {\n"
                + "    width: 60px !important; }\n"
                + "  .block-grid.nine-up .col {\n"
                + "    width: 53px !important; }\n"
                + "  .block-grid.ten-up .col {\n"
                + "    width: 48px !important; }\n"
                + "  .block-grid.eleven-up .col {\n"
                + "    width: 43px !important; }\n"
                + "  .block-grid.twelve-up .col {\n"
                + "    width: 40px !important; } }\n"
                + "\n"
                + "@media (max-width: 500px) {\n"
                + "  .block-grid, .col {\n"
                + "    min-width: 320px !important;\n"
                + "    max-width: 100% !important;\n"
                + "    display: block !important; }\n"
                + "  .block-grid {\n"
                + "    width: calc(100% - 40px) !important; }\n"
                + "  .col {\n"
                + "    width: 100% !important; }\n"
                + "    .col > div {\n"
                + "      margin: 0 auto; }\n"
                + "  img.fullwidth, img.fullwidthOnMobile {\n"
                + "    max-width: 100% !important; }\n"
                + "  .no-stack .col {\n"
                + "    min-width: 0 !important;\n"
                + "    display: table-cell !important; }\n"
                + "  .no-stack.two-up .col {\n"
                + "    width: 50% !important; }\n"
                + "  .no-stack.mixed-two-up .col.num4 {\n"
                + "    width: 33% !important; }\n"
                + "  .no-stack.mixed-two-up .col.num8 {\n"
                + "    width: 66% !important; }\n"
                + "  .no-stack.three-up .col.num4 {\n"
                + "    width: 33% !important; }\n"
                + "  .no-stack.four-up .col.num3 {\n"
                + "    width: 25% !important; }\n"
                + "  .mobile_hide {\n"
                + "    min-height: 0px;\n"
                + "    max-height: 0px;\n"
                + "    max-width: 0px;\n"
                + "    display: none;\n"
                + "    overflow: hidden;\n"
                + "    font-size: 0px; } }\n"
                + "\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body class=\"clean-body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #FFFFFF\">\n"
                + "  <style type=\"text/css\" id=\"media-query-bodytag\">\n"
                + "    @media (max-width: 520px) {\n"
                + "      .block-grid {\n"
                + "        min-width: 320px!important;\n"
                + "        max-width: 100%!important;\n"
                + "        width: 100%!important;\n"
                + "        display: block!important;\n"
                + "      }\n"
                + "\n"
                + "      .col {\n"
                + "        min-width: 320px!important;\n"
                + "        max-width: 100%!important;\n"
                + "        width: 100%!important;\n"
                + "        display: block!important;\n"
                + "      }\n"
                + "\n"
                + "        .col > div {\n"
                + "          margin: 0 auto;\n"
                + "        }\n"
                + "\n"
                + "      img.fullwidth {\n"
                + "        max-width: 100%!important;\n"
                + "      }\n"
                + "			img.fullwidthOnMobile {\n"
                + "        max-width: 100%!important;\n"
                + "      }\n"
                + "      .no-stack .col {\n"
                + "				min-width: 0!important;\n"
                + "				display: table-cell!important;\n"
                + "			}\n"
                + "			.no-stack.two-up .col {\n"
                + "				width: 50%!important;\n"
                + "			}\n"
                + "			.no-stack.mixed-two-up .col.num4 {\n"
                + "				width: 33%!important;\n"
                + "			}\n"
                + "			.no-stack.mixed-two-up .col.num8 {\n"
                + "				width: 66%!important;\n"
                + "			}\n"
                + "			.no-stack.three-up .col.num4 {\n"
                + "				width: 33%!important;\n"
                + "			}\n"
                + "			.no-stack.four-up .col.num3 {\n"
                + "				width: 25%!important;\n"
                + "			}\n"
                + "      .mobile_hide {\n"
                + "        min-height: 0px!important;\n"
                + "        max-height: 0px!important;\n"
                + "        max-width: 0px!important;\n"
                + "        display: none!important;\n"
                + "        overflow: hidden!important;\n"
                + "        font-size: 0px!important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "  <!--[if IE]><div class=\"ie-browser\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table class=\"nl-container\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #FFFFFF;width: 100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "	<tbody>\n"
                + "	<tr style=\"vertical-align: top\">\n"
                + "		<td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #FFFFFF;\"><![endif]-->\n"
                + "\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div align=\"center\" class=\"img-container center fixedwidth \" style=\"padding-right: 0px;  padding-left: 0px;\">\n"
                + "<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr style=\"line-height:0px;line-height:0px;\"><td style=\"padding-right: 0px; padding-left: 0px;\" align=\"center\"><![endif]-->\n"
                + "  <img class=\"center fixedwidth\" align=\"center\" border=\"0\" src=\"https://c2.staticflickr.com/2/1965/44402302585_2c010a20c3_m.jpg\" alt=\"Image\" title=\"Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: 0;height: auto;float: none;width: 100%;max-width: 168px\" width=\"168\">\n"
                + "<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:#DEDFE3;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:#DEDFE3;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#000000;line-height:200%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"line-height:24px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;font-size:12px;color:#000000;text-align:left;\"><p style=\"margin: 0;line-height: 24px;text-align: center;font-size: 12px\"><span style=\"font-size: 38px; line-height: 76px; color: rgb(0, 0, 0);\"><strong>University Bank</strong></span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "\n"
                + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"divider \" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 100%;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "    <tbody>\n"
                + "        <tr style=\"vertical-align: top\">\n"
                + "            <td class=\"divider_inner\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;padding-right: 10px;padding-left: 10px;padding-top: 10px;padding-bottom: 10px;min-width: 100%;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                <table class=\"divider_content\" height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"10%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #333333;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                    <tbody>\n"
                + "                        <tr style=\"vertical-align: top\">\n"
                + "                            <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
                + "                                <span>&#160;</span>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </tbody>\n"
                + "                </table>\n"
                + "            </td>\n"
                + "        </tr>\n"
                + "    </tbody>\n"
                + "</table>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;color:#555555;font-family:Arial, 'Helvetica Neue', Helvetica, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px\">You are receiving this email because you requested for a password reset code. If you did not do this, please ignore this email</p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:200%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"line-height:24px;font-size:12px;color:#555555;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 28px;text-align: center\"><strong><span style=\"font-size: 20px; line-height: 40px;\">Reset Code</span></strong></p><p style=\"margin: 0;font-size: 14px;line-height: 28px;text-align: center\"><span style=\"font-size: 24px; line-height: 48px; color: rgb(51, 102, 255);\"><span style=\"line-height: 48px; font-size: 24px;\">" + resetCode + "</span></span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "    <div style=\"background-color:transparent;\">\n"
                + "      <div style=\"Margin: 0 auto;min-width: 320px;max-width: 480px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\" class=\"block-grid \">\n"
                + "        <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"background-color:transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 480px;\"><tr class=\"layout-full-width\" style=\"background-color:transparent;\"><![endif]-->\n"
                + "\n"
                + "              <!--[if (mso)|(IE)]><td align=\"center\" width=\"480\" style=\" width:480px; padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "            <div class=\"col num12\" style=\"min-width: 320px;max-width: 480px;display: table-cell;vertical-align: top;\">\n"
                + "              <div style=\"background-color: transparent; width: 100% !important;\">\n"
                + "              <!--[if (!mso)&(!IE)]><!--><div style=\"border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\"><!--<![endif]-->\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;color:#555555;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px;text-align: center\"><em><span style=\"font-size: 14px; line-height: 16px; color: rgb(255, 102, 0);\">*Please change your password immediately</span></em></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "                    <div class=\"\">\n"
                + "	<!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\"><![endif]-->\n"
                + "	<div style=\"color:#555555;line-height:120%;font-family:'Lato', Tahoma, Verdana, Segoe, sans-serif; padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px;\">\n"
                + "		<div style=\"font-size:12px;line-height:14px;font-family:Lato, Tahoma, Verdana, Segoe, sans-serif;color:#555555;text-align:left;\"><p style=\"margin: 0;font-size: 14px;line-height: 17px;text-align: center\"><span style=\"font-size: 18px; line-height: 21px;\"><strong>Thank you for using University Bank!</strong></span></p></div>\n"
                + "	</div>\n"
                + "	<!--[if mso]></td></tr></table><![endif]-->\n"
                + "</div>\n"
                + "\n"
                + "              <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n"
                + "        </div>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "   <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "		</td>\n"
                + "  </tr>\n"
                + "  </tbody>\n"
                + "  </table>\n"
                + "  <!--[if (mso)|(IE)]></div><![endif]-->\n"
                + "\n"
                + "\n"
                + "</body></html>";
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("csspec6.n491@gmail.com", "haji12345");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = receipient;
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("University Bank - Account Reset Password");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html; charset=utf-8");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
            return true;
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
            return false;
        }
    }

}
