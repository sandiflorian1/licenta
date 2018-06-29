package com.back4app.quickstartexampleapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NetworkFragment extends Fragment {

    private List<PostClass> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PostAdapter mAdapter;

    private static final String TAG = "Network";
    private Button netbtn;
    private ImageView imagev;
    private Bitmap bitmapG;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_tab, container,false);
        View rootView = inflater.inflate(R.layout.network_tab, container, false);
        netbtn = (Button) rootView.findViewById(R.id.netbtn);
        imagev = (ImageView) rootView.findViewById(R.id.myImage);
        final ImageView imagev = (ImageView) rootView.findViewById(R.id.imageView);
        final LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);

        netbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Toast.makeText(getActivity(),"ai apasat netbtn",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddPost.class);
                startActivity(intent);
            }
        });

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        rv.setHasFixedSize(true);

        mAdapter = new PostAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rv.setAdapter(mAdapter);
        preparePostData();

        rv.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PostClass post = postList.get(position);
                //Toast.makeText(getContext(), trip.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AboutPost.class);
                intent.putExtra("postId", post.getIdPost());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    private void preparePostData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.addDescendingOrder("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> posts, ParseException e) {

                if (e == null) {

                    if (posts.size() > 0) {

                        for (ParseObject post : posts) {
                            String postId = post.getObjectId();
                            String nameTrip = String.valueOf(post.get("nameTrip"));
                            String username = String.valueOf(post.get("username"));
                            ParseFile file = (ParseFile) post.get("image");


                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {

                                    if (e == null && data != null) {

                                       Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                        bitmapG = bitmap;

                                    }


                                }
                            });

                            PostClass postC = new PostClass(postId, nameTrip, username, bitmapG);
                            postList.add(postC);

                        }

                        mAdapter.notifyDataSetChanged();
                    }


                } else {

                    e.printStackTrace();

                }

            }
        });

    }

}
