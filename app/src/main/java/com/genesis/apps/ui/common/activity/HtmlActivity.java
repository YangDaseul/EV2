package com.genesis.apps.ui.common.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;

import com.genesis.apps.R;
import com.genesis.apps.comm.hybrid.MyWebViewFrament;
import com.genesis.apps.comm.hybrid.core.WebViewFragment;
import com.genesis.apps.comm.model.vo.TermVO;
import com.genesis.apps.comm.util.FileUtil;
import com.genesis.apps.comm.util.excutor.ExecutorService;
import com.genesis.apps.databinding.ActivityHtmlBinding;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import androidx.fragment.app.FragmentTransaction;

public class HtmlActivity extends SubActivity<ActivityHtmlBinding>  {

    public MyWebViewFrament.WebViewListener webViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        ui.setActivity(this);
        webViewListener = webViewListenerNormal;
    }

    @Override
    public void onClickCommon(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();

                break;
        }
    }

    @Override
    public void setViewModel() {

    }

    @Override
    public void setObserver() {

    }

    @Override
    public void getDataFromIntent() {

    }

    public void setWebViewListener(MyWebViewFrament.WebViewListener webViewListener){
        this.webViewListener = webViewListener;
    }

    public void loadTerms(String html){
        if(!TextUtils.isEmpty(html)){
            Bundle bundle = new Bundle();
            bundle.putString(WebViewFragment.EXTRA_HTML_BODY, html);

            MyWebViewFrament fragment = new MyWebViewFrament();
            fragment.setArguments(bundle);
            if(webViewListener==null) webViewListener=webViewListenerNormal;
            fragment.setWebViewListener(webViewListener);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fm_holder, fragment);
            ft.commitAllowingStateLoss();
        }

    }

    public void loadTermsUrl(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WebViewFragment.EXTRA_MAIN_URL, url);

        MyWebViewFrament fragment = new MyWebViewFrament();
        if(webViewListener==null) webViewListener=webViewListenerNormal;
        fragment.setWebViewListener(webViewListener);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fm_holder, fragment);
        ft.commitAllowingStateLoss();
    }

    public MyWebViewFrament.WebViewListener webViewListenerNormal = new MyWebViewFrament.WebViewListener() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
        @Override
        public void onPageFinished(String url) { }
        @Override
        public boolean onBackPressed() {
            return false;
        }
        @Override
        public void onCloseWindow(WebView window) { }
    };


    /**
     * @brief ???????????? ??????????????? ?????? html ????????? asset ???????????? (local) ?????? ??? ??????
     * @return
     */
    public String getStringFromAssetsFile(String termsCd){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(termsCd+".html"), StandardCharsets.UTF_8 ))){
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            sb=null;
        }

        return sb!=null?sb.toString():"";
    }

    public void setTopView(int layout){
        ViewStub stub = findViewById(R.id.vs_contents);
        stub.setLayoutResource(layout);
        stub.inflate();
    }


    public void loadTermVo(TermVO termVO) {
        showProgressDialog(true);
        ExecutorService es = new ExecutorService("");
        Futures.addCallback(es.getListeningExecutorService().submit(() -> {

            boolean isSuccess = false;
            String termCont="";
            try {
                if (!TextUtils.isEmpty(termVO.getTermCont())&&!TextUtils.isEmpty(termVO.getTermCd())) {
                    termCont = unescapeHtml(termVO.getTermCont());
                    isSuccess = saveTermInfo(this, termVO.getTermCd(), termCont);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                isSuccess = false;
            }
            return isSuccess;
        }), new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@NullableDecl Boolean isSuccess) {
                if(isSuccess) loadTermsUrl("file://"+loadTermPath(HtmlActivity.this, termVO.getTermCd()));
                showProgressDialog(false);
                es.shutDownExcutor();
            }

            @Override
            public void onFailure(Throwable t) {
                showProgressDialog(false);
                es.shutDownExcutor();
            }
        }, es.getUiThreadExecutor());
    }


    /**
     * @brief ????????????/???????????? ???????????? ?????? ???????????? ??????
     * @param context
     * @param code ????????? ????????? (??????)
     * @return ????????????/???????????? ????????????
     */
    public String loadTermInfo(Context context, String code) {
        return FileUtil.ReadFileString(context.getFilesDir() + "/terms/term_"+code+".html");
    }

    public String loadTermPath(Context context, String code) {
        return context.getFilesDir() + "/terms/term_"+code+".html";
    }


    /**
     * @brief ????????????/???????????? ???????????? ?????? ????????? ??????
     * @param context
     * @param code ????????? ????????? (??????)
     * @param msg ????????? ??????
     */
    public boolean saveTermInfo(Context context, String code, String msg) {
        return FileUtil.WriteFile(context.getFilesDir() + "/terms/term_"+code+".html", msg.getBytes());
    }

    /**
     * ?????????????????? ?????? ?????? ??????
     * @param str ?????? ??????????????? ?????????
     * @return
     */
    public String unescapeHtml(String str) {
        if(TextUtils.isEmpty(str)) return "";
        return str.replaceAll("&quot;", "\"");
        /*.addEscape('\'', "&#39;")
        .addEscape('&', "&amp;")
        .addEscape('<', "&lt;")
        .addEscape('>', "&gt;")*/
    }


}
