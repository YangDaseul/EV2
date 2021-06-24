package com.genesis.apps.ui.main.store;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.genesis.apps.R;
import com.genesis.apps.comm.hybrid.MyWebViewFrament;
import com.genesis.apps.comm.hybrid.core.WebViewFragment;
import com.genesis.apps.comm.model.api.APPIAInfo;
import com.genesis.apps.comm.model.api.gra.CMS_1001;
import com.genesis.apps.comm.model.constants.KeyNames;
import com.genesis.apps.comm.model.constants.VariableType;
import com.genesis.apps.comm.util.PackageUtil;
import com.genesis.apps.comm.viewmodel.CMSViewModel;
import com.genesis.apps.comm.viewmodel.LGNViewModel;
import com.genesis.apps.databinding.ActivityStoreWebBinding;
import com.genesis.apps.ui.common.activity.SubActivity;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class StoreWebActivity extends SubActivity<ActivityStoreWebBinding> {
    private final String TAG = getClass().getSimpleName();

    public MyWebViewFrament fragment;

    private LGNViewModel lgnViewModel;
    private CMSViewModel cmsViewModel;

    private Handler mHandler = null;
    private String url = "";
    private boolean isClearHistory=false;
    public String fn=""; //javascript 함수
    private String mCustInfo = "";

    private String isDlp = "NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_web);

        setViewModel();
        getDataFromIntent();
        setObserver();
        initView();
    }

    @Override
    public void onClickCommon(View v) {

    }

    @Override
    public void setViewModel() {
        ui.setLifecycleOwner(this);

        lgnViewModel = new ViewModelProvider(this).get(LGNViewModel.class);
        cmsViewModel = new ViewModelProvider(this).get(CMSViewModel.class);
    }

    @Override
    public void setObserver() {
        cmsViewModel.getRES_CMS_1001().observe(this, result -> {

            switch (result.status) {
                case LOADING:
                    showProgressDialog(true);
                    break;
                case SUCCESS:
                    showProgressDialog(false);
                    if(result.data!=null&&result.data.getRtCd().equalsIgnoreCase("0000")){
                        Log.d("JJJJ", "getCustInfo : " + result.data.getCustInfo());
                        try {
                            mCustInfo = result.data.getCustInfo();
                            String postData = "data=" + URLEncoder.encode(mCustInfo, "UTF-8");
                            fragment.postUrl(url, postData.getBytes());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case ERROR:
                    showProgressDialog(false);
                    fragment.loadUrl(url);
                    break;
            }
        });
    }

    @Override
    public void getDataFromIntent() {
        Intent intent = getIntent();
        if(intent == null) {
            finish();
            return;
        }

        url = intent.getStringExtra(KeyNames.KEY_NAME_URL);
        try{
            mCustInfo = intent.getStringExtra(KeyNames.KEY_NAME_CUST_INFO);
        }catch (Exception e){

        }
        Log.d("JJJJ", "store url : " + url);
    }

    // 하드웨어/소프트웨어 이전 키 설정
    boolean clickCheck = false;
    @Override
    public void onBackPressed() {
        if(isDlp.equals("YES")) {
            fragment.loadUrl("javascript:bwcAppClose();");
        } else {
            if(!TextUtils.isEmpty(fn)){
                if(fragment.openWindows.size()>0){
                    fragment.openWindows.get(0).loadUrl("javascript:"+fn);
                }else{
                    fragment.loadUrl("javascript:"+fn);
                }
            } else {
                if(fragment.canGoBack()) {
                    fragment.goBack();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    private void initView() {
        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(getCustGbCd()) && !getCustGbCd().equalsIgnoreCase(VariableType.MAIN_VEHICLE_TYPE_0000)&&TextUtils.isEmpty(mCustInfo)) {
            cmsViewModel.reqCMS1001(new CMS_1001.Request(APPIAInfo.SM02.getId()));
        } else {
            bundle.putString(WebViewFragment.EXTRA_MAIN_URL, url);
            try {
                if (!TextUtils.isEmpty(mCustInfo)) {
                    String postData = "data=" + URLEncoder.encode(mCustInfo, "UTF-8");
                    bundle.putByteArray(WebViewFragment.EXTRA_POST_DATA, postData.getBytes());
                }
            }catch (Exception e){

            }
        }

        fragment = new MyWebViewFrament();
        fragment.setWebViewListener(webViewListener);
        fragment.setJavaInterface(new ScriptInterface(), "isdlpshown");
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fm_holder, fragment);
        ft.commitAllowingStateLoss();
    }

    public String getCustGbCd() {
        String custGbCd = "";
        try {
            custGbCd = lgnViewModel.getUserInfoFromDB().getCustGbCd();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return custGbCd;
        }
    }

    private MyWebViewFrament.WebViewListener webViewListener = new MyWebViewFrament.WebViewListener() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return parseURL(url);
        }
        @Override
        public void onPageFinished(String url) {
            Log.d(TAG, "onPageFinished:" + url);
            if(url.startsWith("about:blank")) finish();
            if(isClearHistory){
                clearHistory();
                setClearHistory(false);
            }
        }
        @Override
        public boolean onBackPressed() {
            Log.d("JJJJ", "onBackPressed");
            if(clearWindowOpens2()) {
                return true;
            }else {
                return back(fragment.getUrl());
            }

        }

        @Override
        public void onCloseWindow(WebView window) {
            Log.d(TAG, "onCloseWindow:" + url);
            //
        }
    };

    public void setClearHistory(boolean clearHistory) {
        isClearHistory = clearHistory;
    }

    public void clearHistory(){
        if(fragment!=null) fragment.clearHistory();
    }

    public boolean parseURL(String url) {
        Uri uri = Uri.parse(url);
        Log.d("JJJJ", "parseURL : " + url);
        try {

            if (url.equalsIgnoreCase("https://www.genesis.com/kr/ko")
                    || url.equalsIgnoreCase("https://www.genesis.com/kr/ko/genesis-membership.html")) {
                finish();
                return true;
            } else if (url.startsWith("genesisapp://exeApp") || url.startsWith("genesisapps://exeApp")) {
                String packgeName;
                try {
                    packgeName = uri.getQueryParameter("schm");
                    if (!TextUtils.isEmpty(packgeName)) {
                        PackageUtil.runApp(this, packgeName);
                    }
                } catch (Exception e) {

                }
                return true;
            } else if (url.startsWith("genesisapp://close") || url.startsWith("genesisapps://close")) {
                if (url.contains("all=y")) {
                    finish();
                } else {
                    if (clearWindowOpens3()) {
                        return true;
                    } else {
                        fragment.getWebView().clearHistory();
                        return back(fragment.getUrl());
                    }
                }
                return true;
            } else if (url.startsWith("genesisapp://openView") || url.startsWith("genesisapps://openView")) {
//                fragment.loadUrl(uri.getQueryParameter("url"));
                 startActivitySingleTop(new Intent(this, StoreWebActivity.class).putExtra(KeyNames.KEY_NAME_URL, uri.getQueryParameter("url")), 0, VariableType.ACTIVITY_TRANSITION_ANIMATION_HORIZONTAL_SLIDE);
                return true;
            } else if (url.startsWith("genesisapp://open") || url.startsWith("genesisapps://open")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(QueryString.encode(uri.getQueryParameter("url")));
                intent.setData(Uri.parse(uri.getQueryParameter("url")));
                startActivity(intent); //TODO 테스트 필요 0002
                return true;
            } else if (url.startsWith("genesisapp://newView") || url.startsWith("genesisapps://newView")) {
                fragment.createChildWebView(uri.getQueryParameter("url"));
                return true;
            } else if (url.startsWith("genesisapp://sendAction") || url.startsWith("genesisapps://sendAction")) {
//            fragment.loadUrl(QueryString.encodeString(uri.getQueryParameter("fn")));
//            fragment.loadUrl("javascript:"+QueryString.encodeString(uri.getQueryParameter("fn")));
                fragment.loadUrl("javascript:" + uri.getQueryParameter("fn"));
                return true;
            } else if (url.startsWith("genesisapp://backAction") || url.startsWith("genesisapps://backAction")) {
                this.fn = uri.getQueryParameter("fn");
                return true;
            } else if (url.startsWith("genesisapp://menu?id=") || url.startsWith("genesisapps://menu?id=")) {
                moveToNativePage(url, false);
                return true;
            } else if (url.startsWith("genesisapp://getSsoInfo") || url.startsWith("genesisapps://getSsoInfo")) {
                fragment.loadUrl("javascript:setSsoInfo('" + mCustInfo + "');");
                return true;
            } else if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("javascript")) {
                Intent intent;
                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                } catch (URISyntaxException use) {
                    return false;
                }

                uri = Uri.parse(intent.getDataString());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException anfe) {
                    if (url.startsWith("ispmobile://")) {
                        try {
                            getPackageManager().getPackageInfo("kvp.jjy.MispAndroid320", 0);
                        } catch (PackageManager.NameNotFoundException nnfe) {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mobile.vpay.co.kr/jsp/MISP/andown.jsp"));
                            startActivity(intent);
                            return true;
                        }
                    } else if (url.contains("kb-acp")) {
                        try {
                            Intent exceptIntent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                            exceptIntent = new Intent(Intent.ACTION_VIEW);
                            exceptIntent.setData(Uri.parse("market://details?id=com.kbcard.kbkookmincard"));

                            startActivity(exceptIntent);
                        } catch (URISyntaxException use1) {
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mobile.vpay.co.kr/jsp/MISP/andown.jsp"));
                            startActivity(intent);
                            return true;
                        }
                    } else {
                        try {
                            Intent exceptIntent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                            String packageNm = exceptIntent.getPackage();
                            exceptIntent = new Intent(Intent.ACTION_VIEW);
                            exceptIntent.setData(Uri.parse("market://search?q=" + packageNm));

                            startActivity(exceptIntent);
                        } catch (URISyntaxException use1) {
                        }
                    }
                }
            } else if (url != null && (url.startsWith("intent:")
                    || url.contains("market://")
                    || url.contains("vguard")
                    || url.contains("droidxantivirus")
                    || url.contains("v3mobile")
                    || url.contains(".apk")
                    || url.contains("mvaccine")
                    || url.contains("smartwall://")
                    || url.contains("http://m.ahnlab.com/kr/site/download"))) {
                Intent intent = null;

                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                } catch (URISyntaxException ex) {
                    Log.e(TAG, "[error] Bad request uri format : [" + url + "] =" + ex.getMessage());
                    return false;
                }

                if (getPackageManager().resolveActivity(intent, 0) == null) {
                    String pkgName = intent.getPackage();
                    if (pkgName != null) {
                        Uri uriMarket = Uri.parse("market://search?q=pname:" + pkgName);
                        intent = new Intent(Intent.ACTION_VIEW, uriMarket);
                        startActivity(intent);
                    }
                } else {
                    Uri uriDeep = Uri.parse(intent.getDataString());
                    intent = new Intent(Intent.ACTION_VIEW, uriDeep);
                    startActivity(intent);
                }
            } else {
                fragment.loadUrl(url);
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean back(String currentUrl) {
        if (fragment.canGoBack()) {
            fragment.goBack();
        } else {
            finish();
        }
        return true;
    }

    public boolean clearWindowOpens2() {

        if(!TextUtils.isEmpty(fn)){
            if(fragment.openWindows.size()>0){
                fragment.openWindows.get(0).loadUrl("javascript:"+fn);
            }else{
                fragment.loadUrl("javascript:"+fn);
            }
//            fn="";
            return true;
        }else if(!fragment.openWindows.isEmpty()) {
            try {
                for (WebView webView : fragment.openWindows) {
                    webView.clearHistory();
                    fragment.getWebViewContainer().removeView(webView);
                    fragment.onCloseWindow(webView);
//                    if(webView.canGoBack()){
//                        webView.goBack();
//                    }else {
//                        webView.clearHistory();
//                        fragment.getWebViewContainer().removeView(webView);
//                        fragment.onCloseWindow(webView);
//                    }
                }
                fragment.openWindows.clear();
                return true;
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    public boolean clearWindowOpens3() {

        if(!fragment.openWindows.isEmpty()) {
            try {
                for (WebView webView : fragment.openWindows) {
                    if(webView.canGoBack()){
                        webView.goBack();
                    }else {
                        webView.clearHistory();
                        fragment.getWebViewContainer().removeView(webView);
                        fragment.onCloseWindow(webView);
                        fragment.openWindows.clear();
                    }
                }
                return true;
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    // Javascript Brige class
    class ScriptInterface {
        @JavascriptInterface
        public void setMessage(final String value) {
            Log.d("JJJJ", "setMessage : " + value);
            mHandler = new Handler();
            mHandler.post(new Runnable() {
                public void run() {
                    isDlp = value;
                }
            });
        }
    }
}