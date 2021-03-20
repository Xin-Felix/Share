package work.huangxin.share.controller;

import com.baidu.aip.speech.AipSpeech;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import work.huangxin.share.model.SpeechMessage;
import work.huangxin.share.util.common.JsonUtils;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.ResponseData;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/wx")
public class SpeechController {


    @PostMapping("/speechRecognition")
    public BaseResponse speechRecognition(HttpServletRequest request) throws IOException {

        InputStream file = ((MultipartHttpServletRequest) request).getFile("file").getInputStream();


        byte[] pcmBytes = new byte[0];
        try {
            pcmBytes = mp3Convert2pcm(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject res = speechBdApi(pcmBytes);

        SpeechMessage speechMessage = JsonUtils.jsonToPojo(res.toString(), SpeechMessage.class);

        return ResponseData.success(speechMessage);
    }
    // 设置APPID/AK/SK，注册百度语音识别API即可获取,自己去注册,否则无法使用
    public static final String APP_ID = "******";
    public static final String API_KEY = "********************";
    public static final String SECRET_KEY = "****************************************";

    /**
     * MP3转换PCM
     *
     * @param inputStream MP3输入流
     * @return byte[]
     * @throws Exception 语音流转换抛出的异常
     */
    public  byte[] mp3Convert2pcm(InputStream inputStream) throws Exception {
        // 转换PCM audioInputStream 数据
        AudioInputStream audioInputStream = getPcmAudioInputStream(inputStream);
        byte[] pcmBytes = IOUtils.toByteArray(audioInputStream);
        return pcmBytes;
    }

    /**
     * 获取PCM AudioInputStream 数据
     *
     * @param inputStream MP3输入流
     * @return AudioInputStream PCM输入流
     */
    private  AudioInputStream getPcmAudioInputStream(InputStream inputStream) {
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
            AudioInputStream in = null;
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(inputStream);
            AudioFormat baseFormat = in.getFormat();
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    /**
     * @param pcmBytes
     * @return
     * @Description 调用百度语音识别API
     * @author liuyang
     * @blog http://www.pqsky.me
     * @date 2018年1月30日
     */
    public  JSONObject speechBdApi(byte[] pcmBytes) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        JSONObject res = client.asr(pcmBytes, "pcm", 16000, null);

        return res;
    }

}
