package com.example.jpm.myapplication;

import java.util.List;

/**
 * Created by mayn on 2018/12/10.
 */

public class SignInbean extends Basebean{

    private List<BodyBean> body;

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * M_DID : null
         * M_DName : null
         * M_DParentID : null
         * M_DPath : null
         * M_Email :
         * M_ID : f1c4704e-3b72-4700-ac28-2075233bceda
         * M_LoginAccount : tcfgw
         * M_MobilePhone : 18625117982
         * M_Name : 测试员
         * M_PassWord : CAF1A3DFB505FFED0D024130F58C5CFA
         * M_PositionName : null
         * M_PositionSortIndex : 0
         * M_Telephone : 0512-53577877
         */

        private Object M_DID;
        private Object M_DName;
        private Object M_DParentID;
        private Object M_DPath;
        private String M_Email;
        private String M_ID;
        private String M_LoginAccount;
        private String M_MobilePhone;
        private String M_Name;
        private String M_PassWord;
        private Object M_PositionName;
        private int M_PositionSortIndex;
        private String M_Telephone;

        public Object getM_DID() {
            return M_DID;
        }

        public void setM_DID(Object M_DID) {
            this.M_DID = M_DID;
        }

        public Object getM_DName() {
            return M_DName;
        }

        public void setM_DName(Object M_DName) {
            this.M_DName = M_DName;
        }

        public Object getM_DParentID() {
            return M_DParentID;
        }

        public void setM_DParentID(Object M_DParentID) {
            this.M_DParentID = M_DParentID;
        }

        public Object getM_DPath() {
            return M_DPath;
        }

        public void setM_DPath(Object M_DPath) {
            this.M_DPath = M_DPath;
        }

        public String getM_Email() {
            return M_Email;
        }

        public void setM_Email(String M_Email) {
            this.M_Email = M_Email;
        }

        public String getM_ID() {
            return M_ID;
        }

        public void setM_ID(String M_ID) {
            this.M_ID = M_ID;
        }

        public String getM_LoginAccount() {
            return M_LoginAccount;
        }

        public void setM_LoginAccount(String M_LoginAccount) {
            this.M_LoginAccount = M_LoginAccount;
        }

        public String getM_MobilePhone() {
            return M_MobilePhone;
        }

        public void setM_MobilePhone(String M_MobilePhone) {
            this.M_MobilePhone = M_MobilePhone;
        }

        public String getM_Name() {
            return M_Name;
        }

        public void setM_Name(String M_Name) {
            this.M_Name = M_Name;
        }

        public String getM_PassWord() {
            return M_PassWord;
        }

        public void setM_PassWord(String M_PassWord) {
            this.M_PassWord = M_PassWord;
        }

        public Object getM_PositionName() {
            return M_PositionName;
        }

        public void setM_PositionName(Object M_PositionName) {
            this.M_PositionName = M_PositionName;
        }

        public int getM_PositionSortIndex() {
            return M_PositionSortIndex;
        }

        public void setM_PositionSortIndex(int M_PositionSortIndex) {
            this.M_PositionSortIndex = M_PositionSortIndex;
        }

        public String getM_Telephone() {
            return M_Telephone;
        }

        public void setM_Telephone(String M_Telephone) {
            this.M_Telephone = M_Telephone;
        }
    }
}
