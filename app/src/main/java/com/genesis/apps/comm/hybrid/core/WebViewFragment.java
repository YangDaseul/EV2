package com.genesis.apps.comm.hybrid.core;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.genesis.apps.BuildConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class WebViewFragment extends Fragment {
	private static final String TAG = "WebViewFragment";

	public static final String EXTRA_MAIN_URL = "mainUrl";
	//	public static final String EXTRA_ENABLE_SELECT_IMAGE = "selectImageDownloadEnabled";
	public static final String EXTRA_POST_DATA = "postData";
	public static final String EXTRA_USE_ZOOM = "useZoom";
	public static final String EXTRA_NEED_CLEAR_CHCHE = "needClearCache";
	public static final String EXTRA_HEADER_NAMES = "headerNames";
	public static final String EXTRA_HEADER_VALUES = "headerValues";

	public static final String EXTRA_HTML_BODY = "htmlBody";
	private static final String ENC_TYPE_UTF8="UTF-8";

	private Context context = null;
	private String mainUrl = null;
	private MyWebView webView = null;
	private MyWebViewClient webViewClient = null;
	private MyWebChromeClient webChromeClient = null;
	private boolean mainCall = false;
	private byte[] postData = null;
	private boolean useZoom = false;
	private boolean needClearCache = false;

	private String htmlBody = null;

	private Map<String, String> header = new HashMap<>();

	public List<WebView> openWindows = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.context = this.getActivity();
		mainUrl = getArguments().getString(EXTRA_MAIN_URL);
		postData = getArguments().getByteArray(EXTRA_POST_DATA);
		useZoom = getArguments().getBoolean(EXTRA_USE_ZOOM);
		needClearCache = getArguments().getBoolean(EXTRA_NEED_CLEAR_CHCHE);

		htmlBody = getArguments().getString(EXTRA_HTML_BODY);

		String[] headerNames = getArguments().getStringArray(EXTRA_HEADER_NAMES);
		String[] headerValues = getArguments().getStringArray(EXTRA_HEADER_VALUES);
		if (headerNames != null && headerNames.length > 0) {
			for (int i = 0; i < headerNames.length; i++) {
				header.put(headerNames[i], headerValues[i]);
			}
		}

		createWebView();
		webViewSetting();
		enableCookies(webView);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();

		if (!mainCall) {
			ViewGroup vg = getWebViewContainer();
			vg.addView(webView, new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			if(!TextUtils.isEmpty(htmlBody)) {
				loadData(htmlBody);
			} else if (postData != null) {
				postUrl(mainUrl, postData);
			} else {
				if (header.size() > 0) {
					loadUrl(mainUrl, header);
				} else {
					loadUrl(mainUrl);
				}
			}

			mainCall = true;
		} else {
		}
	}

	public Context getContext() {
		return this.context;
	}

	@Override
	public void onDestroyView() {
		Log.d(TAG, "onDestroyView()");
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy()");

		webViewClient = null;
		webChromeClient = null;

		if (this.webView != null) {
			this.webView.stopLoading();
			this.webView.setWebChromeClient(null);
			this.webView.setWebViewClient(null);
			this.webView.removeAllViews();
			this.webView.destroy();
			this.webView = null;
		}

		context = null;

		super.onDestroy();
	}

	public void loadUrl(final String url) {
		Log.v(TAG, "loadUrl() url[" + url + "]");
		this.getActivity().runOnUiThread(() -> {
			if (webView != null) {
				webView.loadUrl(url);
			}
		});
	}

	public void clearHistory() {
		this.getActivity().runOnUiThread(() -> {
			if (webView != null) {
				webView.clearHistory();
			}
		});
	}

	public void loadUrl(final String url, Map<String, String> additionalHttpHeaders) {
		Log.v(TAG, "loadUrl() url[" + url + "]");
		if(getActivity() == null) return;
		getActivity().runOnUiThread(() -> {
			if (webView != null) {
				webView.loadUrl(url, additionalHttpHeaders);
			}
		});
	}

	public void loadData(final String data) {
		Log.v(TAG, "loadData() data[" + data + "]");
		if(getActivity() == null) return;
		getActivity().runOnUiThread(() -> {
			if(webView != null) {
				webView.loadData(data, "text/html", ENC_TYPE_UTF8);
			}
		});
	}

	public void reload() {
		Log.v(TAG, "reload()");
		if(getActivity() == null) return;
		getActivity().runOnUiThread(() -> {
			if (webView != null) {
				webView.reload();
			}
		});
	}

	public void postUrl(final String url, final byte[] postData) {
		if(getActivity() == null) return;
		Log.v(TAG, "postUrl() " + url);
		getActivity().runOnUiThread(() -> {
			if (webView != null) {
				webView.postUrl(url, postData);
			}
		});
	}

	public boolean canGoBack() {
		boolean ret = false;
		if (webView != null) {
			ret = webView.canGoBack();
		}

		return ret;
	}

	public void goBack() {
		if (webView != null) {
			if (webView.canGoBack()) {
				webView.goBack();
			}
		}
	}

	public String getCurrentUrl() {
		if (webView != null) {
			return webView.getUrl();
		}
		return null;
	}

	/**
	 * 로딩 창
	 * @param show
	 */
	public abstract void showProgress(boolean show);

	/**
	 * 웹뷰 생성
	 */
	private void createWebView() {
		webViewClient = new MyWebViewClient(context);
		webChromeClient = new MyWebChromeClient();

		this.webView = new MyWebView(this.getActivity());
		this.webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		this.webView.setWebChromeClient(webChromeClient);
		this.webView.setWebViewClient(webViewClient);
		this.webView.setFocusable(true);
		this.webView.setHorizontalScrollBarEnabled(false);
		if (needClearCache) {
			Log.d(TAG, "clearCache!!!");
			webView.clearCache(true);
			needClearCache = false;
		}
	}

	public MyWebView createNewWebView() {
		MyWebView newWebView = new MyWebView(context);
		newWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
		newWebView.setWebChromeClient(webChromeClient);
		newWebView.setWebViewClient(webViewClient);
		newWebView.setFocusable(true);
		newWebView.setHorizontalScrollBarEnabled(false);

		if (needClearCache) {
			Log.d(TAG, "clearCache!!!");
			webView.clearCache(true);
			needClearCache = false;
		}

		openWindows.add(newWebView);

		return newWebView;
	}

	/**
	 * 웹뷰 설정
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void webViewSetting() {
		WebSettings settings = webView.getSettings();
		settings.setDefaultTextEncodingName(ENC_TYPE_UTF8);
		settings.setJavaScriptEnabled(true);
		settings.setSaveFormData(false);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
			settings.setSavePassword(false);
			settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
			settings.setAppCacheMaxSize(5 * 1048576);
		}
		settings.setAppCacheEnabled(true);
		settings.setGeolocationEnabled(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setAllowFileAccess(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setSupportMultipleWindows(true);
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);

		if (useZoom) {
			settings.setSupportZoom(true);
			settings.setBuiltInZoomControls(true);
			settings.setUseWideViewPort(true);
			settings.setLoadWithOverviewMode(true);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				settings.setDisplayZoomControls(false);
			}
		}

		String userAgentString = settings.getUserAgentString();
		Log.v(TAG, "userAgentString[" + userAgentString + "]");

		// Jellybean rightfully tried to lock this down. Too bad they didn't
		// give us a whitelist
		// while we do this
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
			Level16Apis.enableUniversalAccess(settings);
		}

		String pathToCache = this.getActivity().getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		settings.setAppCachePath(pathToCache);
		settings.setAppCacheEnabled(true);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}

		//settings.setUserAgentString(getString(R.string.app_full_name));
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void newWebViewSetting(WebView webView) {
		WebSettings settings = webView.getSettings();
		settings.setDefaultTextEncodingName(ENC_TYPE_UTF8);
		settings.setJavaScriptEnabled(true);

		settings.setSaveFormData(false);
		settings.setAppCacheEnabled(true);
		settings.setGeolocationEnabled(true);
		settings.setLoadsImagesAutomatically(true);
		settings.setAllowFileAccess(true);
		settings.setDomStorageEnabled(true);
		settings.setDatabaseEnabled(true);
		settings.setSupportMultipleWindows(true);
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setDisplayZoomControls(false);

		String userAgentString = settings.getUserAgentString();
		Log.v(TAG, "userAgentString[" + userAgentString + "]");
		Level16Apis.enableUniversalAccess(settings);


		String pathToCache = requireActivity().getApplicationContext()
				.getDir("database", Context.MODE_PRIVATE).getPath();
		settings.setAppCachePath(pathToCache);
		settings.setAppCacheEnabled(true);

		settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        settings.setUserAgentString(userAgentString + "android");
	}

	/**
	 * 세션 유지를 위해 추가
	 */
	public void enableCookies(WebView webView) {
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
		cookieManager.setAcceptThirdPartyCookies(webView, true);
	}


	public abstract boolean onJsConfirm(WebView view, String url, String message,
                                        JsResult result);
	public abstract boolean onJsAlert(WebView view, String url, String message,
                                      JsResult result);
	public abstract void onReceivedError(WebView view, int errorCode,
                                         String description, String failingUrl);
	public abstract void onPageFinished(WebView view, String url);
	public abstract void onPageStarted(WebView view, String url, Bitmap favicon);
	public abstract boolean shouldOverrideUrlLoading(WebView view, String url);
	public abstract ViewGroup getWebViewContainer();

	public abstract void onCloseWindow(WebView window);

	/**
	 * WebChromeClient 구현
	 *
	 */
	private class MyWebChromeClient extends WebChromeClient {
		private static final String LOG_TAG = "MyWebChromeClient";

		@Override
		public void onRequestFocus(WebView view) {
			Log.v(LOG_TAG, "WebView onRequestFocus]");
			super.onRequestFocus(view);
		}

		@Override
		public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
			if ("dev".equalsIgnoreCase(BuildConfig.FLAVOR)) {
				return super.onConsoleMessage(consoleMessage);
			} else {
				return true;
			}
		}

		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			Log.v(LOG_TAG, "onCreateWindow isDialog[" + isDialog + "] isUserGesture[" + isUserGesture + "] resultMsg[" + resultMsg + "] originalUrl [" + view.getOriginalUrl() + "]");
			MyWebView newWebView = createNewWebView();
			newWebViewSetting(newWebView);
			enableCookies(newWebView);
			newWebView.setWebViewClient(webViewClient);
			newWebView.setWebChromeClient(new MyWebChromeClient() {
				@Override
				public void onCloseWindow(WebView window) {
					Log.v(LOG_TAG, "onCloseWindow window[" + window + "]");
					WebViewFragment.this.onCloseWindow(window);
				}
			});
			getWebViewContainer().addView(newWebView, new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));

			((WebView.WebViewTransport) resultMsg.obj).setWebView(newWebView);
			resultMsg.sendToTarget();
			return true;
//            return super.onCreateWindow(view , isDialog , isUserGesture , resultMsg);
		}

		@Override
		public void onCloseWindow(WebView window) {
			Log.v(LOG_TAG, "onCloseWindow window[" + window + "]");
			WebViewFragment.this.onCloseWindow(window);
			openWindows.remove(window);
		}

		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
                                   JsResult result) {
			Log.v(LOG_TAG, "onJsConfirm url[" + url + "] message[" + message
					+ "]");
			if (getActivity().isFinishing()) {
				Log.w(LOG_TAG,
						"Trying to confirm while activity is finishing!!");
				result.cancel();
				return true;
			}

			return WebViewFragment.this.onJsConfirm(view, url, message, result);
		}

		@Override
		public boolean onJsAlert(WebView view, String url, String message,
                                 JsResult result) {
			Log.v(LOG_TAG, "onJsAlert url[" + url + "] message[" + message
					+ "]");
			if (getActivity().isFinishing()) {
				Log.w(LOG_TAG, "Trying to alert while activity is finishing!!");
				result.cancel();
				return true;
			}

			return WebViewFragment.this.onJsAlert(view, url, message, result);
		}
	}

	/**
	 * WebViewClient 구현
	 *
	 */
	public class MyWebViewClient extends WebViewClient {
		private static final String LOG_TAG = "MyWebViewClient";
		private Context context = null;

		public MyWebViewClient(Context context) {
			super();
			this.context = context;
		}

		@Override
		public void onLoadResource(WebView view, String url) {
			Log.d(LOG_TAG, "onLoadResource url[" + url + "]");
			super.onLoadResource(view, url);
		}

		@SuppressLint("NewApi")
		@Override
		public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {
			WebResourceResponse ret = super.shouldInterceptRequest(view, url);
			Log.d(LOG_TAG, "shouldInterceptRequest url[" + url + "]");
			return ret;
		}

		/**
		 * 페이지 로딩시 에러처리
		 */
		@Override
		public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {

			switch (errorCode) {
				case ERROR_UNSUPPORTED_SCHEME:
					Log.v(LOG_TAG, "errorCode[" + errorCode + "] description["
							+ description + "] failingUrl[" + failingUrl + "]");
					super.onReceivedError(view, errorCode, description, failingUrl);
					break;

				default:
					Log.d(LOG_TAG, "errorCode[" + errorCode + "] description["
							+ description + "] failingUrl[" + failingUrl + "]");


					WebViewFragment.this.onReceivedError(view, errorCode, description, failingUrl);
					break;
			}
		}

		/**
		 * 페이지 로딩 완료.<br/>
		 * 페이지를 다운로드 완료 하였지만 아직 화면에 보이기 전이다.
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			Log.v(LOG_TAG, "pageFinished[" + url + "]");

			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				CookieSyncManager.getInstance().sync();
			}
			WebViewFragment.this.onPageFinished(view, url);
			super.onPageFinished(view, url);
		}

		/**
		 * 페이지 로딩 시작
		 */
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			Log.v(LOG_TAG, "pageStarted[" + url + "]");

			WebViewFragment.this.onPageStarted(view, url, favicon);
			super.onPageStarted(view, url, favicon);
		}

		/** 링크 클릭시 혹은 로딩할 URL 검사 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.v(LOG_TAG, "shouldOverrideUrlLoading() url[" + url + "]");
			if (url.startsWith(WebView.SCHEME_TEL)) {
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
					startActivity(intent);
				} catch (Exception e) {
					Log.e(LOG_TAG, "", e);
				}
				return true;
			} else if (url.startsWith(WebView.SCHEME_MAILTO)) {
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
					startActivity(intent);
				} catch (Exception e) {
					Log.e(LOG_TAG, "", e);
				}
				return true;
			} else if (url.startsWith(WebView.SCHEME_GEO)) {
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
					startActivity(intent);
				} catch (Exception e) {
					Log.e(LOG_TAG, "", e);
				}
				return true;
			} else if (url.startsWith("market://")) {
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
					startActivity(intent);
				} catch (Exception e) {
					Log.e(LOG_TAG, "", e);
				}
				return true;
			} else {
				return WebViewFragment.this.shouldOverrideUrlLoading(view, url);
			}
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			if(handler != null) handler.proceed();
			//super.onReceivedSslError(view, handler, error);
		}
	}

	// Wrapping these functions in their own class prevents warnings in adb
	// like:
	// VFY: unable to resolve virtual method 285:
	// Landroid/webkit/WebSettings;.setAllowUniversalAccessFromFileURLs
	@TargetApi(16)
	private static class Level16Apis {
		static void enableUniversalAccess(WebSettings settings) {
			settings.setAllowUniversalAccessFromFileURLs(true);
		}
	}

}