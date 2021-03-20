package work.huangxin.share.model;

public class DecodePhoneMessage {


    String encryptedData;
    String sessionId;
    String iv;

    @Override
    public String toString() {
        return "GetPhoneMessage{" +
                "encryptedData='" + encryptedData + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
