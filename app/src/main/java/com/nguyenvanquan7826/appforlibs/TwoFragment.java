package com.nguyenvanquan7826.appforlibs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TwoFragment extends Fragment {
    private static final String TAG = TwoFragment.class.getSimpleName();
    private User u;

    public static TwoFragment newInstance(User u) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putString("u", u.getName());
        IntentHelper.addObjectForKey(u, u.getName());
        fragment.setArguments(args);
        return fragment;
    }

    public static TwoFragment create(User u) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        args.putBinder("object_value", new ObjectWrapperForBinder(u));
        Log.d(TAG, "original object=" + u);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String keyUser = getArguments().getString("u");
//        u = (User) IntentHelper.getObjectForKey(keyUser);

        u = (User) ((ObjectWrapperForBinder) getArguments().getBinder("object_value")).getData();
        Log.d(TAG, "received object=" + u);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.no();
            }
        });
    }
}
