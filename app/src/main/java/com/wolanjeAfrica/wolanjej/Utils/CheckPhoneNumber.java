package com.wolanjeAfrica.wolanjej.Utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wolanjeAfrica.wolanjej.models.BalanceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPhoneNumber {
    private static final String TAG = "CheckPhoneNumber";
    private static volatile CheckPhoneNumber INSTANCE = null;
    private List<BalanceModel> balanceModel = new ArrayList<>();
    private String phoneCompany;
    private String[] balance = new String[1];
    private CheckPhoneNumber() {
    }

    public static CheckPhoneNumber getInstance() {
        if (INSTANCE == null) {
            synchronized (CheckPhoneNumber.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CheckPhoneNumber();
                }
            }
        }
        return INSTANCE;
    }

    public Map<String, String> checkPhoneNo(Context context, String inputPhone) {
        String validPhoneNo = "Fasle";
        Map<String, String> map = new HashMap<>();
        String safaricom = "^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-9])|(?:6[8-9])|(?:5[7-9])|(?:4[5-6])|(?:4[8])|(4[0-3]))[0-9]{6})$";
        String telkom = "^(?:254|\\+254|0)?(7(?:(?:[7][0-9]))[0-9]{6})$";
        String airtel = "^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(?:6[2])|(8[0-9]))[0-9]{6})$";
        Pattern patt;
        Matcher match;
        if (!inputPhone.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match = patt.matcher(replPhone2);
            if (match.find()) {
                Log.e(TAG, "checkPhoneNo: safaricom number" );
                String replPhone3 = "null";
                phoneCompany = "safaricom";
                if (replPhone2.startsWith("0")) {
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("7")) {
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("+")) {
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Log.e(TAG, "checkPhoneNo: Airtel number" );
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
                    if (replPhone2.startsWith("0")) {
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("7")) {
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("+")) {
                        validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        Log.e("TAG phone number +", validPhoneNo);
                    }
                } else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Log.e(TAG, "checkPhoneNo: Telkom number" );
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
                        if (replPhone2.startsWith("0")) {
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("7")) {
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("+")) {
                            validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            Log.e("TAG phone number +", validPhoneNo);
                        }
                    } else {
                        Toast.makeText(context.getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                    }
                }

            }
        } else {
            Toast.makeText(context.getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
        }
        map.put(phoneCompany, validPhoneNo);
        return map;
    }

    public String changePhoneNo(Context context, String inputPhone, View view) {
        String validPhoneNo = "Fasle";
        String safaricom = "^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-9])|(?:6[8-9])|(?:5[7-9])|(?:4[5-6])|(?:4[8])|(4[0-3]))[0-9]{6})$";
        String telkom = "^(?:254|\\+254|0)?(7(?:(?:[7][0-9]))[0-9]{6})$";
        String airtel = "^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(?:6[2])|(8[0-9]))[0-9]{6})$";
        Pattern patt;
        Matcher match;
        if (inputPhone != null && !inputPhone.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match = patt.matcher(replPhone2);
            if (match.find()) {
                Toast.makeText(context, "Safaricom Number", Toast.LENGTH_LONG).show();
                String replPhone3 = "null";
                phoneCompany = "safaricom";
                if (replPhone2.startsWith("0")) {
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("7")) {
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("+")) {
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Toast.makeText(context, "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
                    if (replPhone2.startsWith("0")) {
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("7")) {
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("+")) {
                        validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        Log.e("TAG phone number +", validPhoneNo);
                    }
                } else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Toast.makeText(context, "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
                        if (replPhone2.startsWith("0")) {
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("7")) {
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("+")) {
                            validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            Log.e("TAG phone number +", validPhoneNo);
                        }
                    } else {
                        Toast.makeText(context, "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                    }
                }

            }
        } else {
            Toast.makeText(context, "Please enter a mobile number ", Toast.LENGTH_LONG).show();
        }

        return validPhoneNo;
    }
}
