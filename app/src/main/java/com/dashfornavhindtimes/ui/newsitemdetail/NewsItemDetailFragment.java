package com.dashfornavhindtimes.ui.newsitemdetail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dashfornavhindtimes.BuildConfig;
import com.dashfornavhindtimes.R;
import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.injection.newsitemdetail.NewsItemDetailModule;
import com.dashfornavhindtimes.ui.main.MainActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lucas on 02/01/2017.
 */

public class NewsItemDetailFragment extends Fragment implements NewsItemDetailContract.View, YouTubePlayer.OnInitializedListener {

    private static final String KEY_POST = "key_post";

    @BindView(com.dashfornavhindtimes.R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.textview_fragment_news_item_detail_content_time)
    TextView post_time;

    @BindView(R.id.textview_fragment_news_item_detail_content_title)
    TextView post_title;

    @BindView(R.id.textview_fragment_news_item_detail_content_body)
    TextView post_body;

    @BindView(R.id.fab_news_item_detail)
    FloatingActionButton floatingActionButton;

    @Nullable
    @BindView(R.id.image_fragment_news_item_detail_image)
    ImageView imageView;


    private Post post;

    private SimpleDateFormat sdf;
    long time, now;
    CharSequence ago;

    @Inject
    NewsItemDetailPresenter presenter;

    //Firebase Analytics
    private FirebaseAnalytics firebaseAnalytics;


    String youtubeUrl;

    public static NewsItemDetailFragment newInstance(Post post) {
        NewsItemDetailFragment fragment = new NewsItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_POST, Parcels.wrap(post));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.post = Parcels.unwrap(getArguments().getParcelable(KEY_POST));

        firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        inject();
        presenter.attachView(this);


        if (this.post.getFeaturedMedia() != 0 && !this.post.getCategories().contains(16)) {
            view = inflater.inflate(com.dashfornavhindtimes.R.layout.fragment_news_item_detail_image, container, false);
            presenter.loadImage(this.post.getFeaturedMedia());
        } else if (this.post.getCategories().contains(16)) {
            view = inflater.inflate(R.layout.fragment_news_item_detail_video, container, false);

            YouTubePlayerSupportFragment mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.youtubeFragment, mYoutubePlayerFragment);
            fragmentTransaction.commit();

            mYoutubePlayerFragment.initialize(getString(R.string.youtube_player_key), this);


        } else {
            view = inflater.inflate(R.layout.fragment_news_item_detail_noimage, container, false);
        }


        return view;

    }


    void inject() {
        ((MainActivity) getActivity())
                .getMainComponent()
                .plus(new NewsItemDetailModule())
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            time = sdf.parse(post.getDateGmt()).getTime();
            now = System.currentTimeMillis();
            ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            post_time.setText(ago);
        } catch (ParseException e) {
            post_time.setText("Error in retrieving post time");
        }

        post_title.setText(post.getTitle().getRendered());

        if (Build.VERSION.SDK_INT >= 24) {
            post_body.setText(Html.fromHtml(post.getContent().getRendered(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            post_body.setText(Html.fromHtml(post.getContent().getRendered()));
        }

        try {
            if (post.getContent().getRendered().contains("iframe") && post.getCategories().contains(16)) {

                //src="https://www.youtube.com/embed/hukHpdoTUjw?feature=oembed"
                int start = post.getContent().getRendered().indexOf("/embed/") + 7;
                int end = start + 11;

                youtubeUrl = post.getContent().getRendered().substring(start, end);
            }

        } catch (Exception e) {

        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void setupToolbar() {
        toolbar.inflateMenu(com.dashfornavhindtimes.R.menu.news_item_detail);

        if (!((MainActivity) getActivity()).getContainersLayout().hasTwoColumns()) {
            toolbar.setNavigationIcon(R.drawable.ic_action_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        }

        if (((MainActivity) getActivity()).getContainersLayout().hasTwoColumns()) {
            toolbar.setVisibility(View.GONE);
        }


    }

    @Override
    public void showImage(String sourceUrl) {
        Picasso.with(getActivity()).load(sourceUrl).into(imageView);

    }


    @Override
    public void showImageFailure() {

    }

    @OnClick(R.id.fab_news_item_detail)
    public void shareNewsItem() {

        Bundle bundle = new Bundle();
        bundle.putString("ARTICLE_TITLE", post.getTitle().getRendered());
        bundle.putInt("ARTICLE_ID", post.getId());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ARTICLE_SHARE");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);


        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, post.getTitle().getRendered() + " : " + post.getLink());
        startActivity(Intent.createChooser(i, "Share URL"));
    }
    ///


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(youtubeUrl); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(getActivity(), 1).show();
        } else {
            String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
        }
    }


}
