package com.pingan.bank.libs.myapplication;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pingan.bank.libs.fundverify.Common;
import com.pingan.bank.libs.fundverify.FundVerifyBack;
import com.pingan.bank.libs.fundverify.PAFundVerify;


public class MainActivity extends Activity {
	TextView result, sign_data;
	ImageView image;
	private ProgressDialog mDialog;
	private static final String APP_KEY = "88f7190cb48541198f5b5e3d7ff97126";

	private EditText et_orderid, et_P2PCode, et_thirdCustId, et_custAccId,
			et_mobile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		result = (TextView) findViewById(R.id.result);
		sign_data = (TextView) findViewById(R.id.sign_data);
		et_orderid = (EditText) findViewById(R.id.et_orderid);
		et_P2PCode = (EditText) findViewById(R.id.et_P2PCode);
		et_thirdCustId = (EditText) findViewById(R.id.et_thirdCustId);
		et_custAccId = (EditText) findViewById(R.id.et_custAccId);
		et_mobile = (EditText) findViewById(R.id.et_mobile);

		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (input_form.isShown()) {
				// }
				if (!check()) {
					return;
				}
				mDialog = new ProgressDialog(MainActivity.this);
				mDialog.setTitle("提示");
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.setCancelable(false);
				mDialog.setMessage("请稍候");
				try {
					mDialog.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				start(PAFundVerify.TYPE_DEFAULT);

			}
		});
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (input_form.isShown()) {
				// }
				if (!check()) {
					return;
				}
				mDialog = new ProgressDialog(MainActivity.this);
				mDialog.setTitle("提示");
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.setCancelable(false);
				mDialog.setMessage("请稍候");
				try {
					mDialog.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				start(PAFundVerify.TYPE_MODIFY_PASSWORD);

			}
		});
		findViewById(R.id.btn3).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (input_form.isShown()) {
				// }
				if (!check()) {
					return;
				}
				mDialog = new ProgressDialog(MainActivity.this);
				mDialog.setTitle("提示");
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.setCancelable(false);
				mDialog.setMessage("请稍候");
				try {
					mDialog.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				start(PAFundVerify.TYPE_FORGET_PASSWORD);

			}
		});
		findViewById(R.id.btn4).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if (input_form.isShown()) {
				// }
				if (!check()) {
					return;
				}
				mDialog = new ProgressDialog(MainActivity.this);
				mDialog.setTitle("提示");
				mDialog.setCanceledOnTouchOutside(false);
				mDialog.setCancelable(false);
				mDialog.setMessage("请稍候");
				try {
					mDialog.show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				start(PAFundVerify.TYPE_SET_PASSWORD);

			}
		});

		findViewById(R.id.btn_reset).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				et_orderid.setText("APP-"
						+ Calendar.getInstance().getTimeInMillis());
				et_P2PCode.setText("3001");
				et_thirdCustId.setText("xyz001");
				et_custAccId.setText("888100000289052");
				et_mobile.setText("15710801164");
			}
		});

		sign_data.setOnLongClickListener(new OnLongClickListener() {

			@SuppressLint("NewApi")
			@Override
			public boolean onLongClick(View v) {
				ClipboardManager cmb = (ClipboardManager) getApplicationContext()
						.getSystemService(Context.CLIPBOARD_SERVICE);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					cmb.setPrimaryClip(ClipData.newPlainText(
							ClipDescription.MIMETYPE_TEXT_PLAIN, sign_data
									.getText().toString().trim()));
				} else {
					cmb.setText(sign_data.getText().toString().trim()); //
				}
				Toast.makeText(MainActivity.this, "内容已复制到剪贴板",
						Toast.LENGTH_SHORT).show();
				// cmb.setText(info.getText().toString().trim()); //
				// 将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
				// cm.getText();//获取粘贴信息
				return true;
			}
		});

	}

	private void start(final int type) {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
		PAFundVerify fundVerify = new PAFundVerify(MainActivity.this, APP_KEY,
				true);
		/*fundVerify.setMAIN_BACKGROUND_COLOR(Color.parseColor("#74DCF5"));//主背景颜色
		fundVerify.setSURE_BUTTON_FONT_COLOR(Color.parseColor("#f55227"));	//确定按钮颜色
		fundVerify.setCANCEL_BUTTON_FONT_COLOR(Color.parseColor("#5a9c2e"));//取消按钮颜色
		fundVerify.setTITLE_FONT_COLOR(Color.parseColor("#4c4c4c"));//title字体颜色
*/		fundVerify.start(new FundVerifyBack() {

			@Override
			public void startCheck() {
				Log.d("Test", "startCheck");
			}

			@Override
			public void processCheck() {
				Log.d("Test", "processCheck");
			}

			@Override
			public void finishCheck() {
				Log.d("Test", "finishCheck");
			}

			@Override
			public void failedCheck(String error) {
				Log.d("Test", "failedCheck->" + error);
			}
		}, getFormData(type), type);

	}

	/**
	 * 获取表单数据
	 * 
	 * @return
	 */
	private HashMap<String, String> getFormData(int type) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("orderid", et_orderid.getText().toString().trim());
		params.put("P2PCode", et_P2PCode.getText().toString().trim());
		params.put("thirdCustId", et_thirdCustId.getText().toString().trim());
		params.put("custAccId", et_custAccId.getText().toString().trim());
		params.put("mobile", et_mobile.getText().toString());
		if (type == PAFundVerify.TYPE_DEFAULT) {
			String orig = String.format("%s&%s&", et_P2PCode.getText()
					.toString().trim(), et_custAccId.getText().toString()
					.trim());
			params.put("orig", orig);
		}
		return params;
	}

	/**
	 * 检查订单数据
	 * 
	 * @return
	 */
	private boolean check() {
		if (TextUtils.isEmpty(et_orderid.getText().toString().trim())) {
			Toast.makeText(MainActivity.this, "本次交易流水号不能为空", Toast.LENGTH_SHORT)
					.show();
			et_orderid.requestFocus();
			return false;
		}
		if (TextUtils.isEmpty(et_P2PCode.getText().toString().trim())) {
			Toast.makeText(MainActivity.this, "第三方平台ID不能为空", Toast.LENGTH_SHORT)
					.show();
			et_P2PCode.requestFocus();
			return false;
		}
		if (TextUtils.isEmpty(et_thirdCustId.getText().toString().trim())) {
			Toast.makeText(MainActivity.this, "会员代码不能为空", Toast.LENGTH_SHORT)
					.show();
			et_thirdCustId.requestFocus();
			return false;
		}
		if (TextUtils.isEmpty(et_custAccId.getText().toString().trim())) {
			Toast.makeText(MainActivity.this, "会员子账号不能为空", Toast.LENGTH_SHORT)
					.show();
			et_custAccId.requestFocus();
			return false;
		}

		if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
			Toast.makeText(MainActivity.this, "会员手机号不能为空", Toast.LENGTH_SHORT)
					.show();
			et_mobile.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Common.CODE_Verify) {
			Log.d("Test", resultCode + "");
			if (resultCode == Common.Result_OK) {
				String str = data.getStringExtra(Common.PAY_RESULT_BACK);
				result.setText(str);
			} else if (resultCode == Common.Result_Failed) {
				String str = data.getStringExtra(Common.PAY_RESULT_BACK);
				result.setText(str);
			} else {
				result.setText("取消");
			}
		}
		super.onActivityResult(requestCode, resultCode, data);

	}
}
