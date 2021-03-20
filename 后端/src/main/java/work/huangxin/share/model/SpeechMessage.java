package work.huangxin.share.model;

import lombok.Data;

@Data
public class SpeechMessage {

    private String[] result;
    private String err_msg;
    private String sn;
    private String corpus_no;
    private String err_no;


}
