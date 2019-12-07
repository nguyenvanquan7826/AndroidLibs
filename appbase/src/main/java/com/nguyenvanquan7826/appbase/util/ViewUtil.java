package com.nguyenvanquan7826.appbase.util;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nguyenvanquan7826.appbase.R;


/**
 * Created by nguyenvanquan7826 on 8/18/17
 */

public class ViewUtil {

    public static void addFragment(FragmentManager fragmentManager, int layoutId, Fragment fragment, boolean isAddStack) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(layoutId, fragment);
        if (isAddStack) ft.addToBackStack(null);
        ft.commit();
    }

    public static void addFragmentClearBackStack(FragmentManager fragmentManager, int layoutId, Fragment fragment) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        addFragment(fragmentManager, layoutId, fragment, false);
    }

    public static int dpToPx(Context context, int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    public static void setUpToolbar(final AppCompatActivity activity, Toolbar toolbar) {
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(view -> activity.onBackPressed());
        }
    }

    public static LinearLayoutManager setLayoutRV(Context context, RecyclerView rv) {
        return setLayoutRV(context, rv, false);
    }

    public static LinearLayoutManager setLayoutRV(Context context, RecyclerView rv, boolean hasItemDecoration) {
        final LinearLayoutManager lm = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rv.setHasFixedSize(false);
        rv.setLayoutManager(lm);
        if (hasItemDecoration) {
            rv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        }
        return lm;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (i == 0) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        Log.e("ListView", params.height + "/" + totalHeight);
        listView.setLayoutParams(params);
    }

    public static void showFbChat(Context context, String urlFb) {
        Uri uri = Uri.parse(urlFb);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            toast(context, "Please install facebook messenger");
        }
    }

    public static void feedbackApp(Context context, String title, String email[], String subject, String sms, String errorSMS) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email[0]));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, sms);
        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public static void sendSmsByDefaultApp(Context context, String phoneTo, String content) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", phoneTo);
        smsIntent.putExtra("sms_body", content);
        context.startActivity(smsIntent);
    }

    public static void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    public static void chooseFile(Activity activity, int requestCodeChooseFile, int requestCodePermission, boolean isRequest) {
        final String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        if (!checkPermissions(activity, permissions)) {
            if (isRequest) {
                ActivityCompat.requestPermissions(activity, permissions, requestCodePermission);
            }
        } else {
//                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                activity.startActivityForResult(i, requestCodeChooseFile);

            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
            activity.startActivityForResult(chooserIntent, requestCodeChooseFile);
        }
    }

    public static void chooseFile(Fragment fragment, int requestCodeChooseFile, int requestCodePermission, boolean isRequest) {
        final String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        if (!ViewUtil.checkPermissions(fragment.getContext(), permissions)) {
            if (isRequest) {
                fragment.requestPermissions(permissions, requestCodePermission);
            }
        } else {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
            fragment.startActivityForResult(chooserIntent, requestCodeChooseFile);
        }
    }

    public static boolean checkPermissions(Context context, String... permisstions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permisstions != null) {
            for (String permission : permisstions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setColorIcon(Context context, int color, TextView... views) {
        for (TextView view : views) {
            Drawable[] drawables = view.getCompoundDrawables();
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    drawable.mutate().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN);
                }
            }

            Drawable[] drawablesRelative = view.getCompoundDrawablesRelative();
            for (Drawable drawable : drawablesRelative) {
                if (drawable != null) {
                    drawable.mutate().setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN);
                }
            }
        }
    }

    public static void resizeIcon(TextView... views) {
        for (final TextView view : views) {
            Drawable[] drawables = view.getCompoundDrawables();
            ajustCompoundDrawableSizeWithText(view, drawables[0], drawables[1], drawables[2], drawables[3]);
        }
    }

    public static void resizeIconRelative(TextView... views) {
        for (final TextView view : views) {
            Drawable[] drawables = view.getCompoundDrawablesRelative();

            float textSize = view.getTextSize();
            final int iconSize = (int) textSize;
            for (Drawable drawable : drawables) {
                if (drawable == null) continue;
                drawable.setBounds(0, 0, iconSize, iconSize);
            }

            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    view.setCompoundDrawablesRelative(drawables[0], drawables[1], drawables[2], drawables[3]);
                    view.removeOnLayoutChangeListener(this);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static void ajustCompoundDrawableSizeWithText(final TextView textView, final Drawable leftDrawable, final Drawable topDrawable, final Drawable rightDrawable, final Drawable bottomDrawable) {
        float textSize = textView.getTextSize();
        final int iconSize = (int) textSize + 1;
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (leftDrawable != null) {
                    leftDrawable.setBounds(0, 0, iconSize, iconSize);
                }
                if (topDrawable != null) {
                    topDrawable.setBounds(0, 0, iconSize, iconSize);
                }
                if (rightDrawable != null) {
                    rightDrawable.setBounds(0, 0, iconSize, iconSize);
                }
                if (bottomDrawable != null) {
                    bottomDrawable.setBounds(0, 0, iconSize, iconSize);
                }
                textView.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
                textView.removeOnLayoutChangeListener(this);
            }
        });
    }

    public static void setGifWithWebView(WebView webView, String gifName) {
        // or "[path]/name.gif" (e.g: file:///android_asset/name.gif for resources in asset folder), and in loadDataWithBaseURL(), you don't need to set base URL, on the other hand, it's similar to loadData() method.
        gifName = "file:///android_asset/" + gifName;
        String yourData = "<html style=\"margin: 0;\">\n" +
                "    <body style=\"margin: 0;\">\n" +
                "    <img src=" + gifName + " style=\"width: 100%; height: 100%\" />\n" +
                "    </body>\n" +
                "    </html>";
        // Important to add this attribute to webView to get resource from outside.
        webView.getSettings().setAllowFileAccess(true);
        // Notice: should use loadDataWithBaseURL. BaseUrl could be the base url such as the path to asset folder, or SDCard or any other path, where your images or the other media resides related to your html
        webView.loadDataWithBaseURL("file:///android_asset/", yourData, "text/html", "utf-8", null);
    }

    public static void setColorImageView(Context context, int color, ImageView iv) {
        iv.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN);
    }

    public static void setColorImageView(Context context, int[] colors, ImageView[] ivs) {
        for (int i = 0; i < colors.length; i++) {
            setColorImageView(context, colors[i], ivs[i]);
        }
    }

    public static void setColorImageView(Context context, int color, ImageView... ivs) {
        for (int i = 0; i < ivs.length; i++) {
            setColorImageView(context, color, ivs[i]);
        }
    }

    private static int getResourceId(Context context, String name, String resourceName) {
        try {
            return context.getResources().getIdentifier(name, resourceName, context.getPackageName());
        } catch (Exception e) {
            Log.e("Error getResourceId", e.getMessage());
            return -1;
        }
    }

    public static int getDrawableByNameInValueXml(Context context, String name, int defaultId) {
        int id = getResourceId(context, name, "drawable");
        Log.e("Recouce id:", id + "");
        if (id == -1) id = defaultId;
        return id;
    }

    public static void setColorText(Context context, int color, TextView... tvs) {
        for (TextView tv : tvs) {
            tv.setTextColor(ContextCompat.getColor(context, color));
        }
    }

    public static void showWebView(WebView webView, String file) {
        webView.loadUrl("file:///android_asset/" + file);
    }

    public static void setSwipeRefresColor(SwipeRefreshLayout swipeRefreshLayout, int color1, int color2, int color3) {
        swipeRefreshLayout.setColorSchemeResources(color1, color2, color3);
    }

    public static void setSwipeRefresColor(SwipeRefreshLayout swipeRefreshLayout) {
        setSwipeRefresColor(swipeRefreshLayout, R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
    }

    public static void showSwipeRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public static void dismisSwipeRefreshLayout(final SwipeRefreshLayout swipeRefreshLayout) {
        if (swipeRefreshLayout != null) {
            System.out.println("finish load");
            swipeRefreshLayout.postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 200);
        }
    }

    public static void swipeRefreshWithRecycleView(SwipeRefreshLayout swipeRefresh, RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefresh.setEnabled(topRowVerticalPosition >= 0);
            }
        });
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int message) {
        toast(context, context.getString(message));
    }

    @Deprecated
    public static void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void dismissKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        // for old way
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void showSoftKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    public static void showSoftKeyboard(Activity activity) {
        InputMethodManager imgr = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void shareText(Context context, String shareBody, String subject, String textSelectAppToShare) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, textSelectAppToShare));
    }

    public static void copyTOClipboard(Context context, String label, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        return false;
    }

    public static String formatPhoneNumber(String phone) {
        phone = phone.trim();
        phone = phone.replace("+84", "0");
        phone = phone.replaceAll("[^a-zA-Z0-9]", "");
        return phone;
    }
}
