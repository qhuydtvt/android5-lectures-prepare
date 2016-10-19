package techkids.vn.demonetworking;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by apple on 10/18/16.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private static final String TAG = PostAdapter.class.toString();
    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View vItem = layoutInflater.inflate(R.layout.recyclerview_item_post, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(vItem);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        if(posts == null) {
            return 0;
        } else {
            Log.d(TAG, "getItemCount");
            return posts.size();
        }
    }
}
