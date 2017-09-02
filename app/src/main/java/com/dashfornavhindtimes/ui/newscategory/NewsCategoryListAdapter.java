package com.dashfornavhindtimes.ui.newscategory;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dashfornavhindtimes.R;
import com.dashfornavhindtimes.data.model.Post.Post;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Aldrich on 12-Jun-17.
 */

public class NewsCategoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Ref : https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
    Context context;
    private final List<Post> mPostList;
    private InteractionListener mListInteractionListener;

    long time, now;
    SimpleDateFormat sdf;
    CharSequence ago;

    private final int NOIMAGE = 0, IMAGE = 1;
    public  final int VIEW_TYPE_LOADING = 2;

    int titleCategoryNumber;
    String categoryName;


    public NewsCategoryListAdapter(Context context, int titleCategoryNumber, String categoryName) {
        mPostList = new ArrayList<>();
        mListInteractionListener = null;

        this.context = context;
        this.titleCategoryNumber = titleCategoryNumber;
        this.categoryName = categoryName;
    }


    @Override
    public long getItemId(int position) {
        return mPostList.size() >= position ? mPostList.get(position).getId() : -1;

    }


    @Override
    public int getItemViewType(int position) {
        if(mPostList.get(position) == null){
            return  VIEW_TYPE_LOADING;
        }
       else if (mPostList.get(position).getFeaturedMedia() == 0) {
            return NOIMAGE;
        } else {
            return IMAGE;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case NOIMAGE:
                View v1 = inflater.inflate(R.layout.item_postlist, viewGroup, false);
                viewHolder = new NoImagePostViewHolder(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.item_postlist_image, viewGroup, false);
                viewHolder = new HasImagePostViewHolder(v2);
                break;
            case VIEW_TYPE_LOADING:
                return onIndicationViewHolder(viewGroup);
            default:
                View v = inflater.inflate(R.layout.item_postlist, viewGroup, false);
                viewHolder = new NoImagePostViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NOIMAGE:
                NoImagePostViewHolder vh1 = (NoImagePostViewHolder) holder;
                configureNoImagePostViewHolder(vh1, position);
                break;
            case IMAGE:
                HasImagePostViewHolder vh2 = (HasImagePostViewHolder) holder;
                configureHasImagePostViewHolder(vh2, position);
                break;
            case VIEW_TYPE_LOADING:
                return;
            default:
                NoImagePostViewHolder vh = (NoImagePostViewHolder) holder;
                configureNoImagePostViewHolder(vh, position);
                break;
        }
    }





    private RecyclerView.ViewHolder onIndicationViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress_bar, parent, false);
        return new ProgressBarViewHolder(view);
    }


    void configureNoImagePostViewHolder(NoImagePostViewHolder noImagePostViewHolder, int position) {

        noImagePostViewHolder.name.setText(mPostList.get(position).getTitle().getRendered());
        noImagePostViewHolder.cateogryname.setText(categoryName);

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            time = sdf.parse(mPostList.get(position).getDateGmt()).getTime();
            now = System.currentTimeMillis();
            ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            noImagePostViewHolder.time.setText(ago);
        } catch (ParseException e) {
            noImagePostViewHolder.time.setText("FAIL");
        }


        Picasso.with(noImagePostViewHolder.listItem.getContext()).load(R.drawable.no_image_default).into(noImagePostViewHolder.imageft);
        noImagePostViewHolder.imageft.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    void configureHasImagePostViewHolder(HasImagePostViewHolder hasImagePostViewHolder, int position) {

        hasImagePostViewHolder.name.setText(mPostList.get(position).getTitle().getRendered());
        hasImagePostViewHolder.cateogryname.setText(categoryName);

        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            time = sdf.parse(mPostList.get(position).getDateGmt()).getTime();
            now = System.currentTimeMillis();
            ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            hasImagePostViewHolder.time.setText(ago);
        } catch (ParseException e) {
            hasImagePostViewHolder.time.setText("FAIL");
        }


        Picasso.with(hasImagePostViewHolder.listItem.getContext()).load(R.drawable.list_hasimage).into(hasImagePostViewHolder.imageft);
        hasImagePostViewHolder.imageft.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }



    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public void addItems(List<Post> itemsList) {
        mPostList.addAll(itemsList);
        notifyItemRangeInserted(getItemCount(), mPostList.size() - 1);
    }

    public void removeAll() {
        mPostList.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public class HasImagePostViewHolder extends RecyclerView.ViewHolder {

        public final View listItem;
        public final TextView name;
        public final ImageView imageft;
        public final TextView time;
        public final TextView cateogryname;


        public HasImagePostViewHolder(View view) {
            super(view);
            listItem = view;

            name = (TextView) view.findViewById(R.id.textview_item_postlist_image_name);
            imageft = (ImageView) view.findViewById(R.id.imageview_item_postlist_image_post);
            time = (TextView) view.findViewById(R.id.textview_item_postlist_image_time);
            cateogryname = (TextView) view.findViewById(R.id.textview_item_postlist_image_categoryname);
            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListInteractionListener != null) {
                        mListInteractionListener.onListClick(mPostList.get(getAdapterPosition()));

                    }
                }
            });

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }

    public class NoImagePostViewHolder extends RecyclerView.ViewHolder {

        public final View listItem;
        public final TextView name;
        public final ImageView imageft;
        public final TextView time;
        public final TextView cateogryname;


        public NoImagePostViewHolder(View view) {
            super(view);
            listItem = view;

            name = (TextView) view.findViewById(R.id.textview_item_postlist_name);
            imageft = (ImageView) view.findViewById(R.id.imageview_item_postlist_post);
            time = (TextView) view.findViewById(R.id.textview_item_postlist_time);
            cateogryname = (TextView) view.findViewById(R.id.textview_item_postlist_categoryname);
            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListInteractionListener != null) {
                        mListInteractionListener.onListClick(mPostList.get(getAdapterPosition()));
                    }
                }
            });

        }

        @Override
        public String toString() {
            return super.toString() + " '" + name.getText() + "'";
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

        public final ProgressBar progressBar;

        public ProgressBarViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }

    public void add(Post post) {
        mPostList.add(null);
        notifyItemInserted(mPostList.size() - 1);
    }


    public boolean addLoadingView() {
        if (getItemViewType(mPostList.size() - 1) != VIEW_TYPE_LOADING) {
            add(null);
            return true;
        }
        return false;
    }

    public boolean removeLoadingView() {
        if (mPostList.size() > 1) {
            int loadingViewPosition = mPostList.size() - 1;
            if (getItemViewType(loadingViewPosition) == VIEW_TYPE_LOADING) {
                remove(loadingViewPosition);
                return true;
            }
        }
        return false;
    }


    public void remove(int position) {
        if (mPostList.size() < position) {
            return;
        }
        mPostList.remove(position);
        notifyItemRemoved(position);
    }




    /**
     * Interface for handling list interactions
     */
    public interface InteractionListener {
        void onListClick(Post post);
    }

    public void setListInteractionListener(InteractionListener listInteractionListener) {
        mListInteractionListener = listInteractionListener;
    }


}
